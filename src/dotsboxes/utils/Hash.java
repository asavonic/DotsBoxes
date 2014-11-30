package dotsboxes.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	byte[] m_digest;
	
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
	
	public boolean equals(Hash other)
	{
		return m_digest.equals(other.m_digest);
	}
}
