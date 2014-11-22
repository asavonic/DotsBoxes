package dotsboxes.events;

import dotsboxes.game.TurnDesc;

public class GameEvent extends Event 
{
	boolean m_edge; // True if player mark edge.
	TurnDesc m_turnDesc;
	public GameEvent(EventType someThing, boolean edge, TurnDesc turnDesc) 
	{
		super(someThing);
		m_edge = edge;
		m_turnDesc = turnDesc;
	}
	
	public boolean isEdgeChanged()
	{
		return m_edge;
	}
	
	public int getI()
	{
		return m_turnDesc.m_i;
	}
	
	public int getJ()
	{
		return m_turnDesc.m_j;
	}
	
	public boolean getVert()
	{
		return m_turnDesc.m_vert;
	}
	
	public int getPlrTag()
	{
		return m_turnDesc.m_player_tag;
	}
}
