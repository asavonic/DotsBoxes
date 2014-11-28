package dotsboxes.events;

import dotsboxes.players.PlayerDesc;

public class EventWithSender extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8859320870204839506L;

	public EventWithSender(EventType someThing, PlayerDesc sender) {
		super(someThing);
		m_Sender = sender;
	}

	public PlayerDesc getSender() {
		return m_Sender;
	}

	protected void setSender(PlayerDesc sender) {
		m_Sender = sender;
	}

	PlayerDesc m_Sender;
}
