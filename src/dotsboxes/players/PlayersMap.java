/**
 * 
 */
package dotsboxes.players;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dotsboxes.players.PlayerDesc;

/**
 * 
 *
 */
public class PlayersMap implements Iterable<PlayerDesc>
{
	public PlayersMap()
	{
		m_KnownPlayers = new LinkedList<PlayerDesc>();
	}
	
	public void fromFile(Path filename) throws IOException
	{
		m_KnownPlayers.clear();
		List<String> file = Files.readAllLines(filename, Charset.forName("UTF-8") );
		
		for( String line : file ) {
			PlayerDesc player = new PlayerDesc(line);
			m_KnownPlayers.add(player);
		}
	}
	
	public LinkedList<PlayerDesc> getPlayersList()
	{
		return m_KnownPlayers;
	}
	
	private LinkedList<PlayerDesc> m_KnownPlayers;

	@Override
	public Iterator<PlayerDesc> iterator() {
		return m_KnownPlayers.iterator();
	}
}
