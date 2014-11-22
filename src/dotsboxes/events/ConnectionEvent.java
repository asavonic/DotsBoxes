package dotsboxes.events;

import dotsboxes.players.PlayerDesc;

public class ConnectionEvent extends Event 
{
	public PlayerDesc m_SenderDesc;
	
	
	public ConnectionEvent(EventType someThing) 
	{
		super(someThing);
	}

	public PlayerDesc getSenderDesc() {
		return m_SenderDesc;
	}

	public void setSenderDesc(PlayerDesc senderDesc) {
		m_SenderDesc = senderDesc;
	}
	
}
