package dotsboxes.events;

import dotsboxes.game.NewGameDesc;
import dotsboxes.utils.CircleBuffer;
import dotsboxes.utils.PlayersList;

public class GameStartEvent extends EventWithGameDesc
{
	private static final long serialVersionUID = 4854650289528011194L;
	
	int m_BeginPlayerTag;
	NewGameDesc m_desc;
	PlayersList m_playersList;

	public GameStartEvent( NewGameDesc desc, int beginPlayerTag, PlayersList playersList) 
	{
		super(EventType.game_Start, desc, null);
		m_BeginPlayerTag = beginPlayerTag;
		m_playersList = playersList;
	}
	
	public int getNumPlayers()
	{
		return getNumLocalPlayers() + getNumRemotePlayers();
	}
	
	public int getBeginPlayer()
	{
		return m_BeginPlayerTag;
	}
	
	public PlayersList getPlayersList()
	{
		return m_playersList;
	}
}
