package dotsboxes;
/**
 * @file   GameSession.java
 * @brief  This file implement game logic ( add edge, add player's mark, win, lose, ...)
 */
public class GameSession 
{
	int m_fieldSize;
	int m_numberOfPlayers;
	/**
	 * @name    GameSession
	 * @brief   Constructor GameSession.
	 * Init game logic objects.
	 * @param field_size - size of game field.
	 * @param players_number - number of players.
	 */
	GameSession( int field_size, int players_number /* , call_back() */ )
	{
		Debug.log("GameSassion initializated.");
	}
	
	/**
	 * @name    HandleEvent
	 * @brief   Handle event.
	 * Receive and handle event.
	 * @param event - event that needs to handled into GameSession.
	 * @retval void
	 */
	public void HandleEvent( event ev)
	{
		Debug.log("Event recieved.");
		Debug.log("Event handled.");
	}
	
	/**
	 * @name    Delete
	 * @brief   Destroy game logic objects.
	 * @param void.
	 * @retval void
	 */
	public void Delete()
	{
		Debug.log("GameSession :  deleted.");
	}
}
