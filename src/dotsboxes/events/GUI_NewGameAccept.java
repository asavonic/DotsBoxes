package dotsboxes.events;

import dotsboxes.game.NewGameDesc;
import dotsboxes.players.PlayerDesc;

public class GUI_NewGameAccept extends Event
{
	RemoteNewGameRequest m_game_request;
	public GUI_NewGameAccept( RemoteNewGameRequest game_request) 
	{
		super(EventType.gui_New_Game_Accept);
		m_game_request = game_request;
	}
	
	public PlayerDesc getSender()
	{
		return m_game_request.getSender();
	}
	
	public NewGameDesc getGameDesc()
	{
		return m_game_request.getNewGameDesc();
	}
	
	public int getNumLocalPlayers()
	{
		return m_game_request.getNumLocalPlayers();
	}
	
	public int getNumRemotePlayers()
	{
		return m_game_request.getNumRemotePlayers();
	}
	
	public int getFieldHeight()
	{
		return m_game_request.getFieldHeight();
	}
	
	public int getFieldWidth()
	{
		return m_game_request.getFieldWidth();
	}
}
