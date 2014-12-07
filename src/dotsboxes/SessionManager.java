package dotsboxes;
/**
 * @file   SessionManager.java
 * @brief  This file implements class that handle game logic object and connection object.
 */

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

import dotsboxes.EventManager.EventSenderPair;
import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.CurrentPlayerChange;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.GUI_NewGameAccept;
import dotsboxes.events.GUI_NewGameRequest;
import dotsboxes.events.GameStartEvent;
import dotsboxes.events.GameTurnEvent;
import dotsboxes.events.NewGameAccept;
import dotsboxes.events.RemoteNewGameRequest;
import dotsboxes.events.SleepEvent;
import dotsboxes.game.NewGameDesc;
import dotsboxes.game.TurnDesc;
import dotsboxes.players.PlayerDesc;
import dotsboxes.gui.GUI;
import dotsboxes.rmi.ConnectionManager;
import dotsboxes.utils.Debug;
import dotsboxes.utils.Configuration;
import dotsboxes.utils.CircleBuffer;
import dotsboxes.utils.Hash;
import dotsboxes.utils.PlayersList;
import dotsboxes.utils.TaggedValue;

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
		
		// TODO REMOVE HARDCODE 
		// 		LOGIN REQUIRED
		// FROM HERE
		try {
			String player_name = "Tester" + Configuration.getPort();
			String player_pass = "qwerty";
			m_CurrentPlayer = new PlayerDesc(player_name,
											 InetAddress.getByName("127.0.0.1"),
											 Configuration.getPort(),
											 new Hash(player_name, player_pass) );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//EventManager.NewAnonimEvent(new GUI_NewGameRequest(2, 1, 5, 9));
		//EventManager.NewAnonimEvent(new NewGameAccept( new PlayerDesc()));
		
		EventManager.NewEvent( new CurrentPlayerChange(m_CurrentPlayer), this);
		m_localPlayersDescs.addElement(m_CurrentPlayer);
		
		// TODO TO HERE
	}
	
	private void CheckForOurTurn()
	{
		if( null == m_localPlayersDescs || null == m_playersList)
		{
			Debug.log("Player list not initializated!");
			return;
		}
		PlayerDesc player = m_playersList.getNext().value;
		if(m_localPlayersDescs.contains(player))
		{
			m_CurrentPlayer = player;
			EventManager.NewEvent(new CurrentPlayerChange(m_CurrentPlayer), this);
			EventManager.NewEvent(new Event(EventType.turn_unlock), this);
		}
		Event event = new Event(EventType.GUI_current_player_changed);
		EventManager.NewEvent(event, this);
	}
	
	private void StartGame()
	{	
		m_playersList.append( new PlayersList( (Iterable<PlayerDesc>) m_localPlayersDescs.clone(), "local" ) );
		m_playersList.append( new PlayersList( (Iterable<PlayerDesc>) m_remotePlayersDescs.clone(), "remote" ) );
		
		m_gameConnections.set_remote_players( m_playersList.getRemotePlayers() );
		
		NewGameDesc game_desc = new NewGameDesc( m_fieldWidth, m_fieldHeight, m_local_players_num, m_remote_players_num);
		EventManager.NewEvent( new GameStartEvent( game_desc, 0, m_playersList), this);
		
		CheckForOurTurn();
	}
	
	private void InitLocalPlayers()
	{
		// TODO HARDCODED LOOP FROM 1 instead of 0ss
		for ( int i = 1; i < m_local_players_num; ++i)
		{
			PlayerDesc player = null;
			try {
				String player_name = String.valueOf(i);
				String player_pass = "qwerty";
				player = new PlayerDesc(player_name,
												 InetAddress.getByName("127.0.0.1"),
												 Configuration.getPort(),
												 new Hash(player_name, player_pass) );
			} catch (UnknownHostException | UnsupportedEncodingException | NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}																//TODO: Andrew! Write right initialization of PlayerDesc! 
			
																			// done, but this even uglier than it was before this *fix*
																			// remove later
			m_localPlayersDescs.addElement(player);
		}
	}
	
	private void GameCreate(GUI_NewGameRequest event)
	{
		Debug.log("SessionManager: GameCreate()");
		m_remote_players_num = event.getNumRemotePlayers();
		m_local_players_num = event.getNumLocalPlayers();
		m_fieldHeight = event.getFieldHeight();
		m_fieldWidth = event.getFieldWidth();
		
		InitLocalPlayers();
		
		if(0 != m_remote_players_num) //We need remote players.
		{
			m_gameConnections.find_n_players(m_remote_players_num); // Request for some number of remote players.
		}
		else StartGame(); // Only local players We can start!
	}

	private void GameCreate(GameStartEvent event)
	{
		m_remote_players_num = event.getNumPlayers() - m_local_players_num;
		
		m_fieldHeight = event.getFieldHeight();
		m_fieldWidth = event.getFieldWidth();
		
		m_playersList = event.getPlayersList();
		
		InitLocalPlayers();
		m_gameConnections.set_remote_players( m_playersList.getRemotePlayers() );
		CheckForOurTurn();
	}
	
	private void NewRemotePlayer( NewGameAccept event)
	{
		if( m_remotePlayersDescs.size() == m_remote_players_num )
		{
			Debug.log("Error! No need more players!");
			return;
		}
		
		m_remotePlayersDescs.addElement(event.getSender());
		
		if( m_remotePlayersDescs.size() == m_remote_players_num )
		{	
			StartGame();
			
		}
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
		
		switch ( event.GetType() ) 
		{
		case game_Start:
			GameCreate((GameStartEvent)event);
			break;
		case GUI_New_Game_Request:
			GameCreate((GUI_NewGameRequest)event);
			break;
		case remote_New_Game_Accept:
			NewRemotePlayer((NewGameAccept)event);
			break;
		case GUI_New_Game_Accept:
			GUI_NewGameAccept game_accept_event = (GUI_NewGameAccept) event;
			m_local_players_num = game_accept_event.getNumLocalPlayers();
			
			//get concrete remote game desc and set local players.
			//emit event remote_New_Game_Accept.
			break;
		case remote_New_Game_Request:
			RemoteNewGameRequest request_event = (RemoteNewGameRequest) event;
			m_gameDescs.add(request_event);
			break;
		case game_Turn:
			GameTurnEvent turnEvent = (GameTurnEvent) event;
			if(turnEvent.isSwitchTurn())
				CheckForOurTurn();
			break;
		case sleep_event:
			SleepEvent sleep = (SleepEvent) event;
			try {
				Thread.sleep(sleep.getTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
	
	Vector<RemoteNewGameRequest> m_gameDescs = new Vector<RemoteNewGameRequest>();
	Vector<PlayerDesc> m_localPlayersDescs = new Vector<PlayerDesc>();
	Vector<PlayerDesc> m_remotePlayersDescs = new Vector<PlayerDesc>();
	
	PlayersList m_playersList = new PlayersList();
	private PlayerDesc m_CurrentPlayer;
	

	
}
