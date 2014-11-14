package dotsboxes;
/**
 * @file   Event.java
 * @brief  This file implements class that represent events.
 */

public class Event 
{
	private EventType m_eventType;
	public PlayerDesc m_SenderDesc;
	/**
	 * @name    Event
	 * @brief   Constructor event.
	 * Init event object with assigning argument's type.
	 * @param EventType - type of event.
	 */
	Event( EventType someThing)
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
	
	public PlayerDesc getSenderDesc() {
		return m_SenderDesc;
	}

	public void setSenderDesc(PlayerDesc senderDesc) {
		m_SenderDesc = senderDesc;
	}
	
	public String TypeToString()
	{
		switch(m_eventType)
		{
		case game_EdgeChanged:
			return "game_EdgeChanged";
		case game_VertexChanged:
			return "game_VertexChanged";
		default:
			return "Undefined type.";
		}
	}
}
