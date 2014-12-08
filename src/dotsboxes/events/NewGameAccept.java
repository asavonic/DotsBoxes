package dotsboxes.events;

import dotsboxes.players.PlayerDesc;

public class NewGameAccept extends EventWithSender {

	private static final long serialVersionUID = 3646293622169039596L;

	int m_number_local_players;
	RemoteNewGameRequest m_request;
	
	public NewGameAccept(RemoteNewGameRequest request, PlayerDesc sender, int number_local_players) 
	{
		super(EventType.remote_New_Game_Accept, sender);
		m_number_local_players = number_local_players;
		m_request = request;
	}
	
	public RemoteNewGameRequest getRequest() {
		return m_request;
	}

	public void setRequest(RemoteNewGameRequest request) {
		m_request = request;
	}

	public int getNumberLocalPlayers()
	{
		return m_number_local_players;
	}
}
