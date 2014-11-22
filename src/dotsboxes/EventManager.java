package dotsboxes;

import java.util.TreeMap;
import java.util.Vector;

import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.utils.Debug;

public final class EventManager 
{
	static TreeMap<EventType, Vector<EventCallback>> m_subcribes;
	static Vector<EventCallback> m_customers;
	private EventManager()
	{
		m_subcribes = new TreeMap<EventType, Vector<EventCallback>>();
		m_customers = new Vector<EventCallback>();
	}
	
	static void Init()
	{
		m_subcribes = new TreeMap<EventType, Vector<EventCallback>>();
		m_customers = new Vector<EventCallback>();
	}
	
	public static void Subscribe( EventType ev_type, EventCallback callback)
	{
		if (null == m_customers || null == m_subcribes)
			Init();
		if ( EventType.Generic == ev_type && !m_customers.contains(callback))
		{
			m_customers.add(callback);
		}
		else
		{
			Vector<EventCallback> vector = m_subcribes.get(ev_type);
			if ( null != vector )
			{
				vector.add(callback);
			}
			else
			{
				vector = new Vector<EventCallback>();
				vector.add(callback);
				m_subcribes.put( ev_type, vector);
			}
		}
	}
	
	public static void NewEvent(Event event)
	{
		if (null == m_customers || null == m_subcribes)
			Init();
		EventType ev_type = event.GetType();
		Vector<EventCallback> vector = m_subcribes.get(ev_type);
		
		for(EventCallback callback: m_customers)
            callback.HandleEvent(event);
		
		if ( null != vector )
		{
			for(EventCallback callback: vector)
	            callback.HandleEvent(event);
		}
		else
		{
			Debug.log("No customers for event " + ev_type + ".");
		}
	}
}
