package dotsboxes.game;

public class TurnDesc 
{
	public int     m_player_tag;
	public boolean m_vert;       // Is vertical edges?
	public int     m_i;
	public int     m_j;
	
	public TurnDesc( int i, int j, int player_tag)
	{
		m_i = i;
		m_j = j;
		m_player_tag = player_tag;
		m_vert = false;
	}
	
	public TurnDesc( int i, int j, int player_tag, boolean vert)
	{
		m_i = i;
		m_j = j;
		m_player_tag = player_tag;
		m_vert = vert;
	}
}
