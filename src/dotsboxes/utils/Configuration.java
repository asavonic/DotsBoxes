/**
 * 
 */
package dotsboxes.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
	static private InetAddress m_Address;
	
	public static void setGlobalInetAddress()
	{
		AmazonIpChecker ip_check = new AmazonIpChecker();
		try {
			Configuration.m_Address = InetAddress.getByName( ip_check.getIp() );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static InetAddress getAddress() {
		return m_Address;
	}
	public static void setAddress(InetAddress m_Address) {
		Configuration.m_Address = m_Address;
	}
	
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
