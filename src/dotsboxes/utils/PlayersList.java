package dotsboxes.utils;

import java.util.Vector;

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
	
	public PlayersList getPlayersByTag(String tag)
	{
		Vector<TaggedValue<PlayerDesc, String>> buffer = getBuffer();
		Vector<PlayerDesc> players_by_tag = new Vector<PlayerDesc>();
		for (TaggedValue<PlayerDesc, String> player : buffer ) {
			if ( player.tag.equals(tag) ) {
				players_by_tag.add( player.value );
			}
		}
		
		return new PlayersList( players_by_tag, tag );
	}

	public PlayersList clone() throws CloneNotSupportedException
	{
		return (PlayersList) super.clone();
	}
}
