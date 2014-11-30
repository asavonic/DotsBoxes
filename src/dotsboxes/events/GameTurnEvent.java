package dotsboxes.events;

import dotsboxes.game.TurnDesc;

public class GameTurnEvent extends Event 
{
	boolean m_edge; // True if player mark edge.
	TurnDesc m_turnDesc;
	boolean m_switchTurn;
	
	public GameTurnEvent(EventType someThing, boolean edge, TurnDesc turnDesc, boolean switchTurn) 
	{
		super(someThing);
		m_edge = edge;
		m_turnDesc = turnDesc;
		m_switchTurn = switchTurn;
	}
	
	public GameTurnEvent(EventType someThing, boolean edge, TurnDesc turnDesc) 
	{
		super(someThing);
		m_edge = edge;
		m_turnDesc = turnDesc;
		m_switchTurn = true;
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
	
	public TurnDesc getTurnDesc()
	{
		return m_turnDesc;
	}
	
	public boolean isSwitchTurn()
	{
		return m_switchTurn;
	}
}
