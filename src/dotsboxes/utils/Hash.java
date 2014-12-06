package dotsboxes.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7478597670714224037L;
	byte[] m_digest;
	
	public byte[] getDigest() {
		return m_digest;
	}

	public void setDigest(byte[] digest) {
		m_digest = digest;
	}

	public Hash( String login, String pass ) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		String login_pass = login + pass;
		byte[] login_pass_bytes = login_pass.getBytes("UTF-8");

		MessageDigest md = MessageDigest.getInstance("MD5");
		
		m_digest = md.digest(login_pass_bytes);
	}
	
	public Hash(Hash other)
	{
		m_digest = other.m_digest;
	}
	
	public Hash(byte[] digest)
	{
		m_digest = digest;
	}
	
	public boolean equals(Hash other)
	{
		if ( m_digest.length != other.m_digest.length || m_digest.length == 0 ) {
			return false;
		}
		
		for ( int i = 0; i < m_digest.length; i++ ) {
			if ( m_digest[i] != other.m_digest[i] ) {
				return false;
			}
		}
		
		return true;
	}
}
