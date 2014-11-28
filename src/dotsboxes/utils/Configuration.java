/**
 * 
 */
package dotsboxes.utils;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import dotsboxes.players.PlayerDesc;

/**
 *
 *
 */
public final class Configuration {
	static private Path m_KnownPlayersFilepath;
	static private int m_Port;
	static private PlayerDesc m_DefaultPlayer;
	
	public static Path getKnownPlayersFilepath() {
		return m_KnownPlayersFilepath;
	}
	public static void setKnownPlayersFilepath(Path m_KnownPlayersFilepath) {
		Configuration.m_KnownPlayersFilepath = m_KnownPlayersFilepath;
	}
	public static void setKnownPlayersFilepath(String string) {
		Configuration.m_KnownPlayersFilepath = FileSystems.getDefault().getPath(string);	
	}
	public static int getPort() {
		return m_Port;
	}
	public static void setPort(int m_Port) {
		Configuration.m_Port = m_Port;
	}
}
