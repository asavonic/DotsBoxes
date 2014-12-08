package dotsboxes.events;

import dotsboxes.game.NewGameDesc;
import dotsboxes.players.PlayerDesc;

public class GUI_NewGameAccept extends Event
{
	RemoteNewGameRequest m_game_request;

	int m_num_local_players;
	public GUI_NewGameAccept( RemoteNewGameRequest game_request, int num_local_players) 
	{
		super(EventType.GUI_New_Game_Accept);
		m_game_request = game_request;
		m_num_local_players = num_local_players;
	}
	
	public PlayerDesc getSender()
	{
		return m_game_request.getSender();
	}
	
	public NewGameDesc getGameDesc()
	{
		return m_game_request.getNewGameDesc();
	}
	
	public RemoteNewGameRequest getGameRequest() {
		return m_game_request;
	}
	
	public int getNumLocalPlayers()
	{
		return m_num_local_players;
	}
}
