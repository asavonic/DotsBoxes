package dotsboxes.events;

import dotsboxes.players.PlayerDesc;

public class CurrentPlayerChange extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2758876956151407239L;

	public CurrentPlayerChange(PlayerDesc new_player) {
		super(EventType.internal_Current_Player_Change);
		m_NewPlayer = new_player;
	}
	
	public PlayerDesc getNewPlayer() {
		return m_NewPlayer;
	}

	public void setNewPlayer(PlayerDesc newPlayer) {
		m_NewPlayer = newPlayer;
	}

	private PlayerDesc m_NewPlayer;

}
