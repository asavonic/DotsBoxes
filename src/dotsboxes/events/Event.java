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
	/**
	 * @name    Event
	 * @brief   Constructor event.
	 * Init event object with assigning argument's type.
	 * @param EventType - type of event.
	 */
	public Event( EventType someThing)
	{
		m_eventType = someThing;
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
	
	public void Cast(EventType type)
	{
		m_eventType = type;
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
		case GUI_game_Turn:
			return "GUI_game_Turn";
		case Generic:
			return "Generic";
		case game_Turn:
			return "game_Turn";
		case ConnectionClose:
			return "ConnectionClose";
		case ConnectionHandshake:
			return "ConnectionHandshake";
		case ConnectionPing:
			return "ConnectionPing";
		case GUI_back_to_Menu:
			return "GUI_back_to_Menu";
		case GUI_game_exit:
			return "GUI_game_exit";
		case GUI_to_the_Game:
			return "GUI_to_the_Game";
		case NewGameRequest:
			return "NewGameRequest";
		case game_Start:
			return "game_Start";
		case game_Start_GUI_Request:
			return "game_Start_GUI_Request";
		case gui_New_Game_Request:
			return "gui_New_Game_Request";
		case remote_New_Game_Accept:
			return "remote_New_Game_Accept";
		case remote_New_Game_Request:
			return "remote_New_Game_Request";
		case internal_Current_Player_Change:
			return "internal_Current_Player_Change";
		default:
			return "Please define this type in ./src/dotsboxes/events/Event.java!";
		}
	}
}
