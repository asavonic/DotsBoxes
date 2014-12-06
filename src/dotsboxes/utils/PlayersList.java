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
	
	public PlayersList getLocalPlayers()
	{
		Vector<TaggedValue<PlayerDesc, String>> buffer = getBuffer();
		PlayersList local = new PlayersList();
		for (TaggedValue<PlayerDesc, String> player : buffer ) {
			if ( player.value.isLocal() ) {
				local.add( player.value, player.tag );
			}
		}
		
		return local;
	}

	public PlayersList getRemotePlayers()
	{
		Vector<TaggedValue<PlayerDesc, String>> buffer = getBuffer();
		PlayersList remote = new PlayersList();
		for (TaggedValue<PlayerDesc, String> player : buffer ) {
			if ( ! player.value.isLocal() ) {
				remote.add( player.value, player.tag );
			}
		}
		
		return remote;
	}
	
	private void add(PlayerDesc value, String tag) {
		super.add( new TaggedValue<PlayerDesc, String>( value, tag) );
	}

	public PlayersList clone() throws CloneNotSupportedException
	{
		return (PlayersList) super.clone();
	}
}
