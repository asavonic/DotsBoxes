package dotsboxes;
/**
 * @file   SessionManager.java
 * @brief  This file implements class that handle game logic object and connection object.
 */

import dotsboxes.callbacks.GameEvent;

public class SessionManager 
{
	GameSession m_game;
	GameEvent m_game_event;
	/**
	 * @name    SessionManager
	 * @brief   Constructor SessionManager. 
	 * Init SessionManager with create GameSession and ConnectionManager.
	 * @param void.
	 */
	SessionManager()
	{
		Debug.log("Session manager: initialization:");
		int some_number_of_players = 0;
		int some_size_of_field = 0;	
		m_game_event = new GameEvent();
		m_game = new GameSession( some_number_of_players, some_size_of_field, m_game_event);
		
		Debug.log("Session manager: initializated.");
	}
	/**
	 * @name  Delete
	 * @brief Destroy GameSession and ConnectionManager.
	 * @param void.
	 * @retval void
	 */
	public void Delete()
	{
		m_game.Delete();
		Debug.log("Session manager destroyed.");
	}
}
