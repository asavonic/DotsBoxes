package dotsboxes.events;

import dotsboxes.players.PlayerDesc;

public class RemoteGameTurnEvent extends EventWithSender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 217974282532023540L;

	public RemoteGameTurnEvent(GameTurnEvent event, PlayerDesc sender) {
		super(EventType.remote_Game_Turn, sender);
		m_gameTurnEvent = event;
	}
	
	GameTurnEvent m_gameTurnEvent;

	public GameTurnEvent getGameTurnEvent() {
		return m_gameTurnEvent;
	}

	public void setGameTurnEvent(GameTurnEvent gameTurnEvent) {
		m_gameTurnEvent = gameTurnEvent;
	}

}
