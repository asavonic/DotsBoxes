package dotsboxes.events;

public class GUI_GameOverEvent extends Event
{

	int m_player_tag;
	
	public GUI_GameOverEvent(int player_tag) 
	{
		super(EventType.GUI_game_over);
		m_player_tag  = player_tag;
	}

	
	public int getPlrTag()
	{
		return m_player_tag;
	}
}
