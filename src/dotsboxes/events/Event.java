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
	protected EventType m_eventType;
	private int m_priority;
	/**
	 * @name    Event
	 * @brief   Constructor event.
	 * Init event object with assigning argument's type.
	 * @param EventType - type of event.
	 */
	public Event( EventType someThing)
	{
		m_eventType = someThing;
		m_priority = 0;
		//Debug.log("Event created.");
	}
	
	public Event( EventType someThing, int priority)
	{
		m_eventType = someThing;
		m_priority = priority;
		//Debug.log("Event created.");
	}
	
	/**
	 * @name    GetType
	 * @brief   Return type of event.
	 * @param void.
	 * @retval EventType
	 */

	public EventType GetType()
	{
		//Debug.log("Event type returned.");
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
	
	public int getPriority()
	{
		return m_priority;
	}
	
	public String TypeToString()
	{		
		switch(m_eventType)
		{
		default:
			return "Please define this type " + String.valueOf(m_eventType.getClass()) + " in ./src/dotsboxes/events/Event.java +70!";
		case GUI_game_Start:
			return "GUI_game_Start";
		case GUI_game_over:
			return "GUI_game_over";
		case GUI_game_Turn:
			return "GUI_game_Turn";
		case Generic:
			return "Generic";
		case game_Turn:
			return "game_Turn";
		case GUI_back_to_Menu:
			return "GUI_back_to_Menu";
		case GUI_game_exit:
			return "GUI_game_exit";
		//case GUI_to_the_Game:
		//	return "GUI_to_the_Game";
		case game_Start:
			return "game_Start";
		case GUI_New_Game_Request:
			return "gui_New_Game_Request";
		case remote_New_Game_Accept:
			return "remote_New_Game_Accept";
		case remote_New_Game_Request:
			return "remote_New_Game_Request";
		case remote_Game_Turn:
			return "remote_Game_Turn";
		case local_Current_Player_Change:
			return "internal_Current_Player_Change";
		case GUI_New_Game_Accept:
			return "gui_New_Game_Accept";
		case GUI_current_player_changed:
			return "GUI_current_player_changed";
		case show_history:
			return "show_history";
		case sleep_event:
			return "sleep_event";
		case turn_unlock:
			return "turn_unlock";
		
		}
	}
}
