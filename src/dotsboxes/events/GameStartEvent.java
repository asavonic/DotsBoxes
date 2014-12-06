package dotsboxes.events;

import dotsboxes.game.NewGameDesc;
import dotsboxes.utils.CircleBuffer;

public class GameStartEvent extends EventWithGameDesc
{
	private static final long serialVersionUID = 4854650289528011194L;
	
	int m_BeginPlayerTag;
	CircleBuffer m_playersList;

	public GameStartEvent( NewGameDesc desc, int beginPlayerTag, CircleBuffer playersList) 
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
	
	public CircleBuffer getPlayersList()
	{
		return m_playersList;
	}
}
