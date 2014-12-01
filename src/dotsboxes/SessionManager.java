package dotsboxes;
/**
 * @file   SessionManager.java
 * @brief  This file implements class that handle game logic object and connection object.
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.Vector;

import dotsboxes.EventManager.EventSenderPair;
import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.CurrentPlayerChange;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.GUI_CurrentPlayerChanged;
import dotsboxes.events.GUI_NewGameRequest;
import dotsboxes.events.GameStartEvent;
import dotsboxes.events.GameTurnEvent;
import dotsboxes.events.NewGameAccept;
import dotsboxes.game.NewGameDesc;
import dotsboxes.game.TurnDesc;
import dotsboxes.players.PlayerDesc;
import dotsboxes.gui.GUI;
import dotsboxes.rmi.ConnectionManager;
import dotsboxes.utils.Debug;
import dotsboxes.utils.Configuration;
import dotsboxes.utils.CircleBuffer;

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

		m_game = new GameSession( 0, 0, 0, 0);
		m_GUI = new GUI();
		m_GUI.m_frame.setVisible(true);
		m_GUI.ShowMenu();
		
		EventManager.Subscribe(EventType.Generic, this);
		//EventManager.Subscribe(EventType.GUI_game_Start, this);
		//EventManager.Subscribe(EventType.GUI_game_Start, this);
		
		//GameTurnEvent g_event = new GameTurnEvent(EventType.game_Turn, true, new TurnDesc( 0, 1, 1));
		
		//m_CurrentPlayer = new PlayerDesc();
		//m_CurrentPlayer.setName("Tester" + Configuration.getPort() );
		//try {
		//	m_CurrentPlayer.setInetAdress( InetAddress.getByName("127.0.0.1") );
		//} catch (UnknownHostException e) {
		//	e.printStackTrace();
		//}
		
		//EventManager.NewAnonimEvent(new GUI_NewGameRequest(2, 1, 5, 9));
		//EventManager.NewAnonimEvent(new NewGameAccept( new PlayerDesc()));
		
		//EventManager.NewEvent( new CurrentPlayerChange(m_CurrentPlayer), this);
	}
	
	private void CheckForOurTurn()
	{
		if( null == m_localPlayersDescs || null == m_playersList)
		{
			Debug.log("Player list not initializated!");
			return;
		}
		PlayerDesc player = m_playersList.getNext();
		if(m_localPlayersDescs.contains(player))
		{
			m_CurrentPlayer = player;
			EventManager.NewEvent(new CurrentPlayerChange(m_CurrentPlayer), this);
			EventManager.NewEvent(new Event(EventType.turn_unlock), this);
		}
		GUI_CurrentPlayerChanged event = new GUI_CurrentPlayerChanged(player.getName());
		EventManager.NewEvent(event, this);
	}
	
	private void GameCreate(GUI_NewGameRequest event)
	{
		Debug.log("SessionManager: GameCreate()");
		m_remote_players_num = event.getNumRemotePlayers();
		m_local_players_num = event.getNumLocalPlayers();
		m_fieldHeight = event.getFieldHeight();
		m_fieldWidth = event.getFieldWidth();
		
		if(0 == m_local_players_num)
			Debug.log("Error! Number of local players 0!");
		
		for ( int i = 0; i < m_local_players_num; ++i)
		{
			PlayerDesc player = new PlayerDesc();//TODO: Andrew! Write right initialization of PlayerDesc! 
			player.setName(String.valueOf(i));
			m_playerDescs.addElement(player);
			m_localPlayersDescs.addElement(player);
		}
		
		if(0 != m_remote_players_num) //We need remote players.
		{
			m_gameConnections.find_n_players(m_remote_players_num); // Request for some number of remote players.
		}
		else // Only local players We can start!
		{
			m_playersList = new CircleBuffer(m_playerDescs);
			NewGameDesc game_desc = new NewGameDesc( m_fieldWidth, m_fieldHeight, m_local_players_num);
			EventManager.NewEvent( new GameStartEvent( game_desc, 0, m_playersList), this);
			
			CheckForOurTurn();
			
			m_GUI.ShowField();
		}
	}
	
	private void NewRemotePlayer( NewGameAccept event)
	{
		if( m_playerDescs.size() == m_remote_players_num + m_local_players_num)
		{
			Debug.log("Error! No more need players!");
			return;
		}
		m_playerDescs.addElement(event.getDesc());
		if( m_playerDescs.size() == m_remote_players_num + m_local_players_num)
		{
			m_playersList = new CircleBuffer(m_playerDescs);
			NewGameDesc game_desc = new NewGameDesc( m_fieldWidth, m_fieldHeight, m_local_players_num);
			EventManager.NewEvent( new GameStartEvent( game_desc, 1, m_playersList), this);
			
			CheckForOurTurn();
			m_GUI.ShowField();
		}
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
	
	@Override
	public void HandleEvent(Event event) {
		Debug.log("SessionManager: recieved event: " + event.TypeToString());
		
		switch ( event.GetType() ) {
		case GUI_back_to_Menu: 
			//TODO: delete this:
			//CheckForOurTurn();
			//end of delete
			m_GUI.ShowMenu();
			break;
		case GUI_game_Start:
			//int some_number_of_players = 3;
			//int some_height_of_field = 2;	
			//int some_width_of_field = 9;	
			//NewGameDesc game_desc = new NewGameDesc( some_width_of_field, some_height_of_field, some_number_of_players);
			//EventManager.NewEvent( new GameStartEvent( game_desc, 1), this);
			
			//m_GUI.ShowField();
			m_GUI.ShowNewGameGUI();
			break;
		case game_Start:
			GameStartEvent ev = (GameStartEvent) event;
			m_playersList = ev.getPlayersList();
			CheckForOurTurn();
			break;
		case gui_New_Game_Request:
			GameCreate((GUI_NewGameRequest)event);
			break;
		case remote_New_Game_Accept:
			NewRemotePlayer((NewGameAccept)event);
			break;
		case game_Turn:
			GameTurnEvent turnEvent = (GameTurnEvent) event;
			if(turnEvent.isSwitchTurn())
				CheckForOurTurn();
			break;
		case GUI_game_exit:
			System.exit(0);
		default:
			break;
		}
	}
	
	
	int m_remote_players_num;
	int m_local_players_num;
	int m_fieldHeight;
	int m_fieldWidth;
	
	GameSession       m_game;
	ConnectionManager m_connect;
	GameConnections   m_gameConnections;
	EventManager      m_eventMngr;
	GUI               m_GUI;
	int m_current_player_tag;
	Vector<PlayerDesc> m_playerDescs = new Vector<PlayerDesc>();
	Vector<PlayerDesc> m_localPlayersDescs = new Vector<PlayerDesc>();
	CircleBuffer m_playersList;
	private PlayerDesc m_CurrentPlayer;
	

	
}
