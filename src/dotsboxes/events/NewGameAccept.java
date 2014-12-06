package dotsboxes.events;

import dotsboxes.players.PlayerDesc;

public class NewGameAccept extends EventWithSender {

	private static final long serialVersionUID = 3646293622169039596L;

	int m_number_local_players;
	
	public NewGameAccept(PlayerDesc sender, int number_local_players) 
	{
		super(EventType.remote_New_Game_Accept, sender);
		m_number_local_players = number_local_players;
	}
	
	public int getNumberLocalPlayers()
	{
		return m_number_local_players;
	}
}
