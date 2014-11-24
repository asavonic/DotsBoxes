/**
 * 
 */
package dotsboxes;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.players.PlayerDesc;
import dotsboxes.players.PlayersMap;
import dotsboxes.rmi.Connection;
import dotsboxes.rmi.ConnectionManager;
import dotsboxes.rmi.exceptions.ConnectionAlreadyEstablished;
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
	}
	
	public void broadcast_event(Event event)
	{
		Debug.log("broadcast_event() up to " + m_PlayersMap.getPlayersList().size() + " players" );
		
		for( PlayerDesc player : m_PlayersMap.getPlayersList() ) {
			try {
				Connection player_connection = ConnectionManager.getConnection(player);
				if ( player_connection == null ) {
					player_connection = ConnectionManager.connect(player);
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
		broadcast_event(new Event(EventType.remote_New_Game_Request));
		
	}
	
	public void HandleNewGameAccept( Event event )
	{
		Debug.log("GameConnections: someone accepted our New_Game_Request");
	}
	
	public void HandleNewGameRequest( Event event )
	{
		Debug.log("GameConnections: someone accepted out New_Game_Request");
		EventManager.NewEvent( new Event( EventType.remote_New_Game_Accept ), this);
	}
	
	private PlayersMap m_PlayersMap;

	@Override
	public void HandleEvent(Event event) {
		switch ( event.GetType() ) {
		case ConnectionClose:
			break;
		case ConnectionHandshake:
			break;
		case ConnectionPing:
			break;
		case GUI_game_Turn:
			break;
		case Generic:
			break;
		case NewGameRequest:
			break;
		case game_Start:
			break;
		case game_Start_GUI_Request:
			break;
		case game_Turn:
			break;
		case remote_New_Game_Accept:
			HandleNewGameAccept(event);
		case remote_New_Game_Request:
			HandleNewGameRequest(event);
		default:
			break;
		}
	}
	
}
