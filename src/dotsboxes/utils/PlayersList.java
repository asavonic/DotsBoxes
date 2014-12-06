package dotsboxes.utils;

import dotsboxes.players.PlayerDesc;

public class PlayersList extends TaggedCircleBuffer<PlayerDesc, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3755895266829903162L;

	public PlayersList(Iterable<PlayerDesc> buffer, int indx_begin, String tag) {
		super(buffer, indx_begin, tag);
	}

	public PlayersList(Iterable<PlayerDesc> buffer, String tag) {
		super(buffer, tag);
	}

	public PlayersList() {
		super();
	}

}
