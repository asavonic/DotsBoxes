/**
 * 
 */
package dotsboxes;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.CurrentPlayerChange;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.GUI_NewGameAccept;
import dotsboxes.events.GameStartEvent;
import dotsboxes.events.NewGameAccept;
import dotsboxes.events.RemoteNewGameRequest;
import dotsboxes.players.PlayerDesc;
import dotsboxes.players.PlayersMap;
import dotsboxes.rmi.Connection;
import dotsboxes.rmi.ConnectionManager;
import dotsboxes.rmi.exceptions.ConnectionAlreadyEstablished;
import dotsboxes.utils.CircleBuffer;
import dotsboxes.utils.Debug;
import dotsboxes.utils.Configuration;

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
			// if no config available - skip exception
		}
		
		EventManager.Subscribe(EventType.remote_New_Game_Accept, this);
		EventManager.Subscribe(EventType.remote_New_Game_Request, this);
		EventManager.Subscribe(EventType.local_Current_Player_Change, this);
		EventManager.Subscribe(EventType.game_Start, this);
	}
	
	public void broadcast_event(Event event, Iterable<PlayerDesc> container)
	{
		Debug.log("broadcast_event() up to " + m_PlayersMap.getPlayersList().size() + " players" );
		
		for( PlayerDesc player : container ) {
			try {
				Connection player_connection = ConnectionManager.getConnection(player);
				if ( player_connection == null ) {
					player_connection = ConnectionManager.connect(m_CurrentPlayer, player);
				}
				player_connection.send_event(event);
			} catch (RemoteException | NotBoundException
					| ConnectionAlreadyEstablished e) {
				Debug.log("broadcast_event(): unable to connect to player" + player.getName() );
				Debug.log("broadcast_event(): " + e.getMessage() );
			}
		}
	}
	
	public void find_n_players(int num_players)
	{
		Debug.log("GameConnections: find_n_players()");
		broadcast_event(new RemoteNewGameRequest(m_CurrentPlayer, null), m_PlayersMap ); // Fix this null.
		
	}
	
	public void HandleNewGameAccept( Event event )
	{
		NewGameAccept new_game_accept = (NewGameAccept) event;
		Debug.log("GameConnections: " + new_game_accept.getSender().getName() + " accepted our New_Game_Request");
	}
	
	public void HandleNewGameRequest( Event event )
	{
		RemoteNewGameRequest new_game_request = (RemoteNewGameRequest) event;
		Debug.log("GameConnections: " + new_game_request.getSender().getName() + " requested us to join the game, accepting");

		Connection remote = ConnectionManager.getConnection(new_game_request.getSender());
		
		if ( remote == null ) {
			// this should not normally happen since we already received event from sender
			try {
				remote = ConnectionManager.connect(m_CurrentPlayer, new_game_request.getSender());
			} catch (RemoteException | NotBoundException
					| ConnectionAlreadyEstablished e) {
				e.printStackTrace();
			}
		}
		
		try {
			GUI_NewGameAccept accept_for_SessionManager = new GUI_NewGameAccept(new_game_request, 1);
			EventManager.NewEvent(accept_for_SessionManager, this);
			
			NewGameAccept accept = new NewGameAccept(m_CurrentPlayer, 1);
			remote.send_event(accept); //TODO: HARDCODED! We shouldn't answer here.		
		} catch (RemoteException e) {
			// add reset
			e.printStackTrace();
		}
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
		Debug.log("GameConnections: broadcasting game turn to " + m_GamePlayers.size() + " players");
		broadcast_event(event, m_GamePlayers);
	}
	
	public void set_remote_players(CircleBuffer game_players)
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
	private CircleBuffer m_GamePlayers;

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
			HandleNewGameRequest(event);
			break;
		case local_Current_Player_Change:
			HandleCurrentPlayerChange(event);
			break;
		default:
			break;
		}
	}
	
}
