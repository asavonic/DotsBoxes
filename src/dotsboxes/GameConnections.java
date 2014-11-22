/**
 * 
 */
package dotsboxes;

import java.io.IOException;

import dotsboxes.players.PlayersMap;
import dotsboxes.utils.Debug;

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
	
	private PlayersMap m_PlayersMap;
	
}
