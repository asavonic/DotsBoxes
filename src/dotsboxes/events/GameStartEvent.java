package dotsboxes.events;

public class GameStartEvent extends Event
{
	
	int m_BeginPlayerTag;

	public GameStartEvent(int beginPlayerTag) 
	{
		super(EventType.game_Start);
		m_BeginPlayerTag = beginPlayerTag;
	}

}
