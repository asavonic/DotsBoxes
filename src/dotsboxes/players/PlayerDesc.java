/**
 * 
 */
package dotsboxes.players;

import java.net.InetAddress;
import java.net.UnknownHostException;

import dotsboxes.utils.Configuration;
import dotsboxes.utils.Hash;

/**
 * Represents the player.
 * Contains his address, name and unique hash
 */
public class PlayerDesc implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7892422205180102465L;
	
	InetAddress m_InetAddress;
	int m_Port;
	String m_Name;
	Hash m_Hash;
	
	public PlayerDesc(String name, InetAddress address, int port, Hash hash)
	{
		m_Name = name;
		m_InetAddress = address;
		m_Port = port;
		m_Hash = hash;
	}
	
	public PlayerDesc(String str)
	{
		try {
			fromString(str);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fromString(String str) throws UnknownHostException {
		String[] elements = str.split(" ");
		m_Name = elements[0];
		m_InetAddress = InetAddress.getByName(elements[1]);
		m_Port = Integer.parseInt(elements[2]);
		
		String hash_str = elements[3];
		byte[] hash = new byte[hash_str.length()];
		
		for( int i = 0; i < hash_str.length(); i += 2) {
			String digest = hash_str.substring(i, i+2);
			hash[i/2]= (byte) Integer.parseInt(digest, 16);
		}
		
		m_Hash = new Hash(hash);
	}
	
	public boolean equals(PlayerDesc player)
	{
		return m_Port == player.getPort() &&
			   //m_Hash.equals( player.getHash() ) &&
			   m_Name.equals( player.getName() ) &&
			   m_InetAddress.equals( player.getInetAddress() );
	}
	
	public boolean isLocal()
	{
		return m_Port == Configuration.getPort() && m_InetAddress.equals( Configuration.getAddress() );
	}
	
	public InetAddress getInetAddress() {
		return m_InetAddress;
	}
	public void setInetAddress(InetAddress inetAddress) {
		m_InetAddress = inetAddress;
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
	public Hash getHash() {
		return m_Hash;
	}
	public void setHash(Hash hash) {
		m_Hash = hash;
	}
}
