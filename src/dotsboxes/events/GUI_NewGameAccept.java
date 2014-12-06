package dotsboxes.events;

import dotsboxes.game.NewGameDesc;
import dotsboxes.players.PlayerDesc;

public class GUI_NewGameAccept extends Event
{
	RemoteNewGameRequest m_game_request;
	public GUI_NewGameAccept( RemoteNewGameRequest game_request) 
	{
		super(EventType.GUI_New_Game_Accept);
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
}
