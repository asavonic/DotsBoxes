package dotsboxes.events;

import dotsboxes.players.PlayerDesc;
import dotsboxes.utils.CircleBuffer;

public class ListPlayersEvent extends Event
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4538593659239692351L;
	
	
	CircleBuffer m_playersList;
	
	public ListPlayersEvent(CircleBuffer playersList)
	{
		super(EventType.playersList);
		m_playersList = playersList;
		
	}
	
	public CircleBuffer getPlayersList()
	{
		return m_playersList;
	}

}
