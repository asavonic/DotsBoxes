package dotsboxes;
/**
 * @file   SessionManager.java
 * @brief  This file implements class that handle game logic object and connection object.
 */

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import dotsboxes.callbacks.EventCallback;
import dotsboxes.rmi.ConnectionManager;

public class SessionManager implements EventCallback
{
	/**
	 * @name    SessionManager
	 * @brief   Constructor SessionManager. 
	 * Init SessionManager with create GameSession and ConnectionManager.
	 * @param void.
	 * @throws AlreadyBoundException 
	 * @throws RemoteException 
	 * @throws AccessException 
	 */
	SessionManager()
	{
		Debug.log("Session manager: initialization:");
		int some_number_of_players = 0;
		int some_size_of_field = 0;	
		m_game = new GameSession( some_number_of_players, some_size_of_field, this);
		
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
	@Override
	public void new_game_event(Event event) 
	{
		Debug.log("Recieved game event : " + event.TypeToString());
	}
	
	public void new_connect_event(Event event) 
	{
		Debug.log("Recieved connect event." + event.TypeToString());
	}
	
		GameSession       m_game;
		ConnectionManager m_connect;
}
