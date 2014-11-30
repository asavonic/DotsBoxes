package dotsboxes.utils;

import java.util.Vector;

import dotsboxes.players.PlayerDesc;

public class CircleBuffer
{
	Vector<PlayerDesc> m_buffer;
	int m_index;
	
	public CircleBuffer(Vector<PlayerDesc> buffer, int indx_begin )
	{
		m_buffer = buffer;
		m_index = indx_begin;
	}
	
	public CircleBuffer(Vector<PlayerDesc> buffer)
	{
		m_buffer = buffer;
		m_index = 0;
	}
	
	public PlayerDesc getNext()
	{
		PlayerDesc player = m_buffer.elementAt(m_index);
		m_index++;
		m_index %= m_buffer.size();
		return player;
	}
	
}