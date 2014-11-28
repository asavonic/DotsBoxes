/**
 * 
 */
package dotsboxes.players;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Represents the player.
 * Contains his address, name and unique hash
 */
public class PlayerDesc implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7892422205180102465L;
	
	InetAddress m_InetAdress;
	int m_Port;
	String m_Name;
	byte[] m_Hash;
	
	public void fromString(String str) throws UnknownHostException {
		String[] elements = str.split(" ");
		m_Name = elements[0];
		m_InetAdress = InetAddress.getByName(elements[1]);
		m_Port = Integer.parseInt(elements[2]);
		
		String hash_str = elements[3];
		m_Hash = new byte[hash_str.length()];
		
		for( int i = 0; i < hash_str.length(); i++) {
			m_Hash[i] = (byte) hash_str.charAt(i);
		}
	}
	
	public boolean equals(PlayerDesc player)
	{
		return m_Port == player.getPort() &&
			   m_Hash == player.getHash() &&
			   m_Name.equals( player.getName() ) &&
			   m_InetAdress.equals( player.getInetAdress() );
	}
	
	public InetAddress getInetAdress() {
		return m_InetAdress;
	}
	public void setInetAdress(InetAddress inetAdress) {
		m_InetAdress = inetAdress;
	}
	public int getPort() {
		return m_Port;
	}
	public void setPort(int port) {
		m_Port = port;
	}
	public String getName() {
		return m_Name;
	}
	public void setName(String name) {
		m_Name = name;
	}
	public byte[] getHash() {
		return m_Hash;
	}
	public void setHash(byte[] hash) {
		m_Hash = hash;
	}
}
