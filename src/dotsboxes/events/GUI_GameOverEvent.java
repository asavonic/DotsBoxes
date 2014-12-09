package dotsboxes.events;

import java.util.Vector;

public class GUI_GameOverEvent extends Event
{

	Vector<Integer> m_player_tag;
	
	public GUI_GameOverEvent(Vector<Integer> player_tag) 
	{
		super(EventType.GUI_game_over);
		m_player_tag  = player_tag;
	}
	
	public Vector<Integer> getPlrTag()
	{
		return m_player_tag;
	}
}
