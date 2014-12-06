package dotsboxes.events;

import dotsboxes.game.NewGameDesc;
import dotsboxes.utils.CircleBuffer;

public class GameStartEvent extends Event
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4854650289528011194L;
	
	int m_BeginPlayerTag;
	NewGameDesc m_desc;
	CircleBuffer m_playersList;

	public GameStartEvent( NewGameDesc desc, int beginPlayerTag, CircleBuffer playersList) 
	{
		super(EventType.game_Start);
		m_BeginPlayerTag = beginPlayerTag;
		m_playersList = playersList;
		m_desc = desc;
	}
	
	public int getFieldWidth()
	{
		return m_desc.getSizeFieldWidth();
	}
	
	public int getFieldHeight()
	{
		return m_desc.getSizeFieldHeight();
	}
	
	public int getNumPlayers()
	{
		return m_desc.getNumLocalPlayers() + m_desc.getNumRemotePlayers();
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
