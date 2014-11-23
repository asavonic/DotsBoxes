package dotsboxes;
/**
 * @file   SessionManager.java
 * @brief  This file implements class that handle game logic object and connection object.
 */

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.GameStartEvent;
import dotsboxes.events.GameTurnEvent;
import dotsboxes.game.TurnDesc;
import dotsboxes.rmi.ConnectionManager;
import dotsboxes.utils.Debug;

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
		m_gameConnections = new GameConnections();
		int some_number_of_players = 3;
		int some_height_of_field = 2;	
		int some_width_of_field = 2;	
		m_game = new GameSession( some_height_of_field, some_width_of_field, some_number_of_players, 0);
		
		EventManager.Subscribe(EventType.Generic, this);
		
		//GameTurnEvent g_event = new GameTurnEvent(EventType.game_Turn, true, new TurnDesc( 0, 1, 1));
		//EventManager.NewEvent(g_event, this);
		//m_game.Draw();
		//Debug.log("Session manager: initializated.");
		
		
		EventManager.NewEvent( new GameStartEvent(some_number_of_players), this);
		EventManager.ProcessEvents();
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
	public void HandleEvent(Event event) {
		Debug.log("Recieved game event New!!!! : " + event.TypeToString());
		
	}
	
	
	GameSession       m_game;
	ConnectionManager m_connect;
	GameConnections   m_gameConnections;
	EventManager      m_eventMngr;
	int m_current_player_tag;
	
}
