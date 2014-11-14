package dotsboxes;
/**
 * @file   GameSession.java
 * @brief  This file implement game logic ( add edge, add player's mark, win, lose, ...)
 */
import dotsboxes.callbacks.EventCallback;

public class GameSession 
{

	/**
	 * @name    GameSession
	 * @brief   Constructor GameSession.
	 * Init game logic objects.
	 * @param field_size - size of game field.
	 * @param players_number - number of players.
	 */
	GameSession( int field_size, int players_number, EventCallback callback )
	{
		m_send_event = callback;
		Debug.log("GameSassion initializated.");
		SendEvent( new Event( EventType.AnythingHapping));
	}
	
	/**
	 * @name    HandleEvent
	 * @brief   Handle event.
	 * Receive and handle event.
	 * @param Event - event that needs to handled into GameSession.
	 * @retval void
	 */
	public void HandleEvent( Event ev)
	{
		Debug.log("Event recieved.");
		Debug.log("Event handled.");
	}
	
	private void SendEvent( Event ev)
	{
		m_send_event.new_game_event(ev);
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
	
	int m_fieldSize;
	int m_numberOfPlayers;
	EventCallback m_send_event;
}
