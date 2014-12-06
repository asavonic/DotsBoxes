package dotsboxes.utils;

import java.util.Iterator;
import java.util.Vector;

import dotsboxes.players.PlayerDesc;

public class CircleBuffer<T> implements Iterable<T>, Cloneable, java.io.Serializable
{
	Vector<T> m_buffer;
	int m_index;
	
	public CircleBuffer(Vector<T> buffer, int indx_begin )
	{
		setBuffer(buffer, indx_begin);
	}
	
	public void setBuffer(Vector<T> buffer, int indx_begin)
	{
		m_buffer = buffer;
		m_index = indx_begin;
	}
	
	public void setBuffer(Vector<T> buffer)
	{
		m_buffer = buffer;
		m_index = 0;
	}
	
	public Vector<T> getBuffer()
	{
		return m_buffer;
	}
	
	public CircleBuffer(Vector<T> buffer)
	{
		setBuffer(buffer);
	}
	
	public CircleBuffer()
	{
		m_buffer = new Vector<T>();
		m_index = 0;
	}
	
	public T getNext()
	{
		T value = m_buffer.elementAt(m_index);
		m_index++;
		m_index %= m_buffer.size();
		return value;
	}
	
	public int size()
	{
		return m_buffer.size();
	}

	@Override
	public Iterator<T> iterator() {
		return m_buffer.iterator();
	}
	
	public CircleBuffer clone() throws CloneNotSupportedException
	{
		CircleBuffer<T> obj = (CircleBuffer<T>) super.clone();
        obj.m_buffer = (Vector<T>) m_buffer.clone();
        obj.m_index = m_index;
		return obj;
	}
	
	public T getAt( int i)
	{
		return m_buffer.elementAt(i);
	}
	
	public void append(Vector<T> buffer)
	{
		m_buffer.addAll(buffer);
	}
	
	public void add(T value)
	{
		m_buffer.add(value);
	}
	
}