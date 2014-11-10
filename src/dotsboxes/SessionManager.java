package dotsboxes;
/**
 * @file   SessionManager.java
 * @brief  This file implements class that handle game logic object and connection object.
 */

public class SessionManager 
{
	GameSession game;
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
		game = new GameSession( some_number_of_players, some_size_of_field);
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
		game.Delete();
		Debug.log("Session manager destroyed.");
	}
}
