package dotsboxes;
/**
 * @file   event.java
 * @brief  This file implements class that represent events.
 */

public class event 
{
	Happen happen;
	/**
	 * @name    event
	 * @brief   Constructor event.
	 * Init event object with assigning argument's type.
	 * @param Happen - type of event.
	 */
	event( Happen someThing)
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
	public Happen GetType()
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
