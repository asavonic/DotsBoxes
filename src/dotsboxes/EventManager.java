package dotsboxes;

import java.util.TreeMap;
import java.util.Vector;

import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.utils.Debug;

public final class EventManager 
{
	public static class EventSenderPair     // Event and event sender.
	{
		Event m_event;
		EventCallback m_sender;
		public EventSenderPair(Event event, EventCallback sender){ m_event  = event; m_sender = sender;}
		public EventSenderPair(Event event){ m_event  = event; m_sender = null;}
		public Event GetEvent() { return m_event;}  
		public EventCallback GetSender() { return m_sender;}  
	};
	
	
	static TreeMap<EventType, Vector<EventCallback>> m_subcribes;
	static Vector<EventSenderPair> m_eventsQueue;
	static Vector<EventSenderPair> m_DebugHistory;
	static int m_completedCommandCount;
	
	private EventManager()
	{
		Init();
	}
	
	static void Init()
	{
		m_subcribes = new TreeMap<EventType, Vector<EventCallback>>();
		m_eventsQueue = new Vector<EventSenderPair>();
		if(Debug.isEnabled())
		{
			m_DebugHistory = new Vector<EventSenderPair>();
			m_completedCommandCount = 0;
		}
	}
	
	public static void Subscribe( EventType ev_type, EventCallback callback)
	{
		if (null == m_subcribes && null ==  m_eventsQueue)			// Check fot init.
			Init();
		
		
		Vector<EventCallback> vector    = m_subcribes.get(ev_type);           // Get target customers.
		Vector<EventCallback> vectorGen = m_subcribes.get(EventType.Generic); // Get generic customers.
		
		if (null != vector)   
		{
			if (!vector.contains(callback))
				if (!(null != vectorGen && !vectorGen.contains(callback)))
				{
					vector.add(callback);
				}
		}
		else if (null != vectorGen)
		{
			if (!vectorGen.contains(callback))
			{
				vector = new Vector<EventCallback>();
				vector.add(callback);
				m_subcribes.put( ev_type, vector);
			}
			else 
			{
				Debug.log(" You have subscribed already. Class:" + String.valueOf(callback.getClass()) + ". Event type: " + String.valueOf(ev_type));
			}
		}
		else
		{
			vector = new Vector<EventCallback>();
			vector.add(callback);
			m_subcribes.put( ev_type, vector);
		}		
	}
	
	public static void NewEvent(Event event, EventCallback callback)
	{
		if (null == m_subcribes && null ==  m_eventsQueue) 			// Check for init.
			Init();  
		
		EventSenderPair ray = new EventSenderPair(event, callback);
		m_eventsQueue.addElement(ray);         // Add event in queue.
		if (Debug.isEnabled())
			m_DebugHistory.addElement(ray);
	}
	public static void NewAnonimEvent(Event event)
	{
		if (null == m_subcribes && null ==  m_eventsQueue) 			// Check for init.
			Init();  
		
		EventSenderPair ray = new EventSenderPair(event);
		m_eventsQueue.addElement(ray);         // Add event in queue.
		if (Debug.isEnabled())
			m_DebugHistory.addElement(ray);
	}
	
	public static void ProcessEvents()
	{
		while (true)
		{
			if (!m_eventsQueue.isEmpty())
			{
				EventSenderPair ray = m_eventsQueue.get(0);
				
				Event         event  = ray.GetEvent();
				EventCallback sender = ray.GetSender();
				EventType    ev_type = event.GetType();
				
				Vector<EventCallback> vector    = m_subcribes.get(ev_type);   // Get target customers.
				Vector<EventCallback> vectorGen = m_subcribes.get(ev_type.Generic);   // Get generic customers.
				
				
				if (( null != vector) || (null != vectorGen))   
				{
					if(( null != vector))
						for(EventCallback i_callback: vector)              // Go over all target customers ...
							if (i_callback != sender)							// expect sender ...
								i_callback.HandleEvent(event);                    // and send event to them.
					if(null != vectorGen)
						for(EventCallback i_callback: vectorGen)              // Go over all generic customers ...
							if (i_callback != sender)							// expect sender ...
								i_callback.HandleEvent(event);                    // and send event to them.
				}
				else
				{
					Debug.log("No customers for event " + ev_type + ".");
				}
				Debug.log("Event  " + ev_type + " has been processed.");
				m_eventsQueue.remove(0);
				if (Debug.isEnabled())
				{
					m_completedCommandCount++;
					PrintDebugHistory();
				}
			}
		}
	}
	
	public static void PrintDebugHistory()
	{
		int numbersOfCommand = m_DebugHistory.size();
		Debug.log("All events : " + numbersOfCommand);
		Debug.log("Completed  : " + m_completedCommandCount);
		Debug.log("");
		int iterator = 0;
		for(EventSenderPair pair: m_DebugHistory)
		{
			Event event = pair.GetEvent();
			EventCallback sender = pair.GetSender();
			
			if( null != sender )
				Debug.log( iterator + " :" + String.valueOf(sender.getClass()) + " push event " + event.TypeToString());
			else
				Debug.log( iterator + " : Anonim class push event " + event.TypeToString());
			iterator++;
		}
	}
}
