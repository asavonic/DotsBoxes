package dotsboxes.events;

import dotsboxes.players.PlayerDesc;

public class NewGameAccept extends EventWithSender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3646293622169039596L;
	
	PlayerDesc m_playerDesc;
	
	public NewGameAccept(PlayerDesc sender) {
		super(EventType.remote_New_Game_Accept, sender);
	}
	
	public PlayerDesc getDesc()
	{
		return m_playerDesc;
	}
}