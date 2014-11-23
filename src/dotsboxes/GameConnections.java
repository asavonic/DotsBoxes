/**
 * 
 */
package dotsboxes;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dotsboxes.events.Event;
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
public class GameConnections {

	public GameConnections() {
		Debug.log("GameConnections: initialization");
		m_PlayersMap = new PlayersMap();
		try {
			m_PlayersMap.fromFile( Configuration.getKnownPlayersFilepath() );
		} catch (IOException e) {
			// if no config available - skip exception
		}
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
	
	
	
	private PlayersMap m_PlayersMap;
	
}
