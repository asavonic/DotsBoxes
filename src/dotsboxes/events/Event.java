package dotsboxes.events;

import dotsboxes.players.PlayerDesc;
import dotsboxes.utils.Debug;

/**
 * @file   Event.java
 * @brief  This file implements class that represent events.
 */

public class Event implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1029903096709060936L;
	private EventType m_eventType;
	/**
	 * @name    Event
	 * @brief   Constructor event.
	 * Init event object with assigning argument's type.
	 * @param EventType - type of event.
	 */
	public Event( EventType someThing)
	{
		m_eventType = someThing;
		Debug.log("Event created.");
	}
	
	/**
	 * @name    GetType
	 * @brief   Return type of event.
	 * @param void.
	 * @retval EventType
	 */

	public EventType GetType()
	{
		Debug.log("Event type returned.");
		return m_eventType;
	}
	/**
	 * @name    Delete
	 * @brief   Destroy event object.
	 * @param void.
	 * @retval void
	 */
	public void Delete()
	{
		Debug.log("Event deleted.");
	}
	
	public String TypeToString()
	{
		switch(m_eventType)
		{
		case game_EdgeChanged:
			return "game_EdgeChanged";
		case game_VertexChanged:
			return "game_VertexChanged";
		case Generic:
			return "Generic";
		case game_Turn:
			return "game_Turn";
		default:
			return "Please define this type in ./src/dotsboxes/events/Event.java!";
		}
	}
}
