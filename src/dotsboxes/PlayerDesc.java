/**
 * 
 */
package dotsboxes;

import java.net.InetAddress;

/**
 * Represents the player.
 * Contains his address, name and unique hash
 */
public class PlayerDesc {
	InetAddress m_InetAdress;
	String m_Name;
	byte[] m_Hash;
	
	public InetAddress getInetAdress() {
		return m_InetAdress;
	}
	public void setInetAdress(InetAddress inetAdress) {
		m_InetAdress = inetAdress;
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
