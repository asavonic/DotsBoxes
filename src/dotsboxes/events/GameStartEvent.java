package dotsboxes.events;

import dotsboxes.game.NewGameDesc;

public class GameStartEvent extends Event
{
	
	int m_BeginPlayerTag;
	NewGameDesc m_desc;

	public GameStartEvent( NewGameDesc desc, int beginPlayerTag) 
	{
		super(EventType.game_Start);
		m_BeginPlayerTag = beginPlayerTag;
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
		return m_desc.m_num_players;
	}
	
	public int getBeginPlayer()
	{
		return m_BeginPlayerTag;
	}
}
