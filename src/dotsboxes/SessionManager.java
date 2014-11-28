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
		m_GUI = new GUI();
		m_GUI.frame.setVisible(true);
		m_GUI.ShowMenu();
		
		EventManager.Subscribe(EventType.Generic, this);
		EventManager.Subscribe(EventType.GUI_game_Turn, this);
		
		//GameTurnEvent g_event = new GameTurnEvent(EventType.game_Turn, true, new TurnDesc( 0, 1, 1));
		//EventManager.NewEvent(g_event, this);
		//m_game.Draw();
		//Debug.log("Session manager: initializated.");
		
		
		EventManager.NewEvent( new GameStartEvent(some_number_of_players), this);
	}
	
	public void Run()
	{
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
		Debug.log("SessionManager: recieved event: " + event.TypeToString());
		
		switch ( event.GetType() ) {
		case GUI_back_to_Menu: 
			m_GUI.ShowMenu();
			break;
		case GUI_to_the_Game:
			m_GUI.ShowField();
			break;
		case ConnectionClose:
			break;
		case ConnectionHandshake:
			break;
		case ConnectionPing:
			break;
		case GUI_game_Turn:
			break;
		case Generic:
			break;
		case NewGameRequest:
			break;
		case game_Start:
			break;
		case game_Start_GUI_Request:
			HandleGameStartGUIRequest(event);
			break;
		case game_Turn:
			break;
		case GUI_game_exit:
			System.exit(0);
		default:
			break;
		}
	}
	
	public void HandleGameStartGUIRequest(Event event)
	{
		Debug.log("SessionManager: HandleGameStartGUIRequest()");
		int remote_players_num = 2; // TODO remove hardcode, get it from event
		m_gameConnections.find_n_players(remote_players_num); // add NewGameDesc
	}
	
	
	GameSession       m_game;
	ConnectionManager m_connect;
	GameConnections   m_gameConnections;
	EventManager      m_eventMngr;
	GUI               m_GUI;
	int m_current_player_tag;
	
}
