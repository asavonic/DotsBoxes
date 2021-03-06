/**
 * 
 */
package dotsboxes;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;

import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.CurrentPlayerChange;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.GUI_NewGameAccept;
import dotsboxes.events.GameStartEvent;
import dotsboxes.events.GameTurnEvent;
import dotsboxes.events.NewGameAccept;
import dotsboxes.events.RemoteGameTurnEvent;
import dotsboxes.events.RemoteNewGameRequest;
import dotsboxes.game.NewGameDesc;
import dotsboxes.players.PlayerDesc;
import dotsboxes.players.PlayersMap;
import dotsboxes.rmi.Connection;
import dotsboxes.rmi.ConnectionManager;
import dotsboxes.rmi.exceptions.ConnectionAlreadyEstablished;
import dotsboxes.utils.CircleBuffer;
import dotsboxes.utils.Debug;
import dotsboxes.utils.Configuration;
import dotsboxes.utils.PlayersList;
import dotsboxes.utils.TaggedValue;

/**
 *
 *
 */
public class GameConnections implements EventCallback {

	public GameConnections() {
		Debug.log("GameConnections: initialization");
		m_PlayersMap = new PlayersMap();

		try {
			m_PlayersMap.fromFile( Configuration.getKnownPlayersFilepath() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EventManager.Subscribe(EventType.remote_New_Game_Accept, this);
		EventManager.Subscribe(EventType.local_Current_Player_Change, this);
		EventManager.Subscribe(EventType.game_Start, this);
		EventManager.Subscribe(EventType.game_Turn, this);
		EventManager.Subscribe(EventType.remote_Game_Turn, this);
	}
	
	public void broadcast_event(Event event, Iterable<PlayerDesc> container)
	{		
		int events_sent = 0;
		for( PlayerDesc player : container ) {
			try {
				Connection player_connection = ConnectionManager.getConnection(player);
				if ( player_connection == null ) {
					player_connection = ConnectionManager.connect(m_CurrentPlayer, player);
				}
				player_connection.send_event(event);
				events_sent++;
			} catch (RemoteException | NotBoundException
					| ConnectionAlreadyEstablished e) {
				Debug.log("broadcast_event(): unable to connect to player" + player.getName() );
				Debug.log("broadcast_event(): " + e.getMessage() );
			}
		}
		Debug.log("broadcast_event(): sent event to " + events_sent + " players" );
	}
	
	public void broadcast_event(Event event, PlayersList container)
	{
		Debug.log("broadcast_event() up to " + m_PlayersMap.getPlayersList().size() + " players" );
		
		// used to avoid multiple event send to a single client (if it holds more than one local player)
		Vector<InetAddress> addresses_processed = new Vector<InetAddress>();
		
		for( TaggedValue<PlayerDesc, String> player : container ) {
			boolean address_processed = false;
			for (InetAddress addr : addresses_processed ) {
				if ( addr.equals( player.value.getInetAddress() ) ) {
					address_processed = true;
					break;
				}
			}
			
			if ( address_processed ) {
				continue;
			} else {
				addresses_processed.add( player.value.getInetAddress() );
			}
			
			try {
				Connection player_connection = ConnectionManager.getConnection(player.value);
				if ( player_connection == null ) {
					player_connection = ConnectionManager.connect(m_CurrentPlayer, player.value);
				}
				player_connection.send_event(event);
			} catch (RemoteException | NotBoundException
					| ConnectionAlreadyEstablished e) {
				Debug.log("broadcast_event(): unable to connect to player" + player.value.getName() );
				Debug.log("broadcast_event(): " + e.getMessage() );
			}
		}
	}
	
	public void find_n_players(int num_players, NewGameDesc new_game_desc )
	{
		Debug.log("GameConnections: find_n_players()");
		broadcast_event(new RemoteNewGameRequest(m_CurrentPlayer, new_game_desc), m_PlayersMap );
		
	}
	
	public void HandleNewGameAccept( Event event )
	{
		NewGameAccept new_game_accept = (NewGameAccept) event;
		
		if ( new_game_accept.getSender().equals( m_CurrentPlayer ) ) {
			try {
				Connection player_connection = ConnectionManager.getConnection( new_game_accept.getRequest().getSender() );
				if ( player_connection == null ) {
					player_connection = ConnectionManager.connect(m_CurrentPlayer, new_game_accept.getRequest().getSender() );
				}
				player_connection.send_event(event);
			} catch (RemoteException | NotBoundException
					| ConnectionAlreadyEstablished e) {
				Debug.log("HandleNewGameAccept(): unable to connect to player" + new_game_accept.getRequest().getSender().getName() );
				Debug.log("HandleNewGameAccept(): " + e.getMessage() );
			}
		}
		
		Debug.log("GameConnections: " + new_game_accept.getSender().getName() + " accepted our New_Game_Request");
	}
	
	public void HandleCurrentPlayerChange( Event event )
	{
		CurrentPlayerChange player_change = (CurrentPlayerChange) event;
		m_CurrentPlayer = player_change.getNewPlayer();
		Debug.log("GameConnections: " + m_CurrentPlayer.getName() + " is now the active user");
	}
	
	public void HandleGameStartEvent( Event event )
	{
		if ( m_GamePlayers == null ) {
			Debug.log("GameConnections: no remote players, skip GameStartEvent");
			return;
		}
		Debug.log("GameConnections: broadcasting game start to " + m_GamePlayers.size() + " players");
		broadcast_event(event, m_GamePlayers);
		Debug.log("GameConnections: handled new game event");
	}
	
	private void HandleGameTurnEvent(Event event) 
	{
		if ( m_GamePlayers == null ) {
			Debug.log("GameConnections: no remote players, skip GameStartEvent");
			return;
		}
		Debug.log("GameConnections: broadcasting game turn to " + m_GamePlayers.size() + " players");
		
		
		broadcast_event( new RemoteGameTurnEvent((GameTurnEvent) event, m_CurrentPlayer) , m_GamePlayers);
	}
	
	public void set_remote_players(PlayersList game_players)
	{
		try {
			m_GamePlayers = game_players.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private PlayersMap m_PlayersMap;
	private PlayerDesc m_CurrentPlayer;
	private PlayersList m_GamePlayers;

	@Override
	public void HandleEvent(Event event) {
		switch ( event.GetType() ) {
		case GUI_game_Turn:
			break;
		case Generic:
			break;
		case game_Start:
			HandleGameStartEvent(event);
			break;
		case game_Turn:
			HandleGameTurnEvent(event);
			break;
		case remote_New_Game_Accept:
			HandleNewGameAccept(event);
			break;
		case remote_New_Game_Request:
			break;
		case remote_Game_Turn:
			HandleRemoteGameTurn(event);
			break;
		case local_Current_Player_Change:
			HandleCurrentPlayerChange(event);
			break;
		default:
			break;
		}
	}

	public void HandleRemoteGameTurn(Event event) {
		RemoteGameTurnEvent remote_game_turn = (RemoteGameTurnEvent) event; 
		EventManager.NewEvent( remote_game_turn.getGameTurnEvent(), this );
	}
	
}
