package dotsboxes;
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
	public EventType happen;
	/**
	 * @name    Event
	 * @brief   Constructor event.
	 * Init event object with assigning argument's type.
	 * @param EventType - type of event.
	 */
	public Event( EventType someThing)
	{
		happen = someThing;
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
		return happen;
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
	
	public PlayerDesc m_SenderDesc;
}