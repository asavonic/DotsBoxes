package dotsboxes.events;

public class GUI_CurrentPlayerChanged extends Event
{

	String m_playerName;
	public GUI_CurrentPlayerChanged(String playerName) {
		super(EventType.GUI_current_player_changed);
		m_playerName = playerName;
	}

	public String getPlayerName()
	{
		return m_playerName;
	}
}
