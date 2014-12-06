package dotsboxes.events;

public class SleepEvent extends Event
{
	public SleepEvent( int time) {
		super(EventType.sleep_event);
		m_time = time;
	}

	public SleepEvent() {
		super(EventType.sleep_event);
		m_time = 1000;
	}
	
	int m_time;
	
	public int getTime()
	{
		return m_time;
	}

}
