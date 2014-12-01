package dotsboxes.utils;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Vector;
import java.util.function.Consumer;

import dotsboxes.players.PlayerDesc;

public class CircleBuffer implements Iterable<PlayerDesc>, Cloneable, java.io.Serializable
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
	
	public int size()
	{
		return m_buffer.size();
	}

	@Override
	public Iterator<PlayerDesc> iterator() {
		return m_buffer.iterator();
	}
	
	public CircleBuffer clone() throws CloneNotSupportedException
	{
		CircleBuffer obj =( CircleBuffer) super.clone();
        obj.m_buffer = (Vector<PlayerDesc>) m_buffer.clone();
        obj.m_index = m_index;
		return obj;
	}
	
}