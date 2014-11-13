package dotsboxes;
/**
 * @file   Event.java
 * @brief  This file implements class that represent events.
 */

public class Event 
{
	public Event_type happen;
	/**
	 * @name    Event
	 * @brief   Constructor event.
	 * Init event object with assigning argument's type.
	 * @param Event_type - type of event.
	 */
	Event( Event_type someThing)
	{
		happen = someThing;
		Debug.log("Event created.");
	}
	
	/**
	 * @name    GetType
	 * @brief   Return type of event.
	 * @param void.
	 * @retval Happen
	 */
	public Event_type GetType()
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
}
