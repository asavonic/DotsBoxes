package dotsboxes.events;

import dotsboxes.game.NewGameDesc;
import dotsboxes.players.PlayerDesc;

public class RemoteNewGameRequest extends EventWithGameDesc {

	public RemoteNewGameRequest( PlayerDesc sender, NewGameDesc NewGameDesc) 
	{
		super(EventType.remote_New_Game_Request, NewGameDesc, sender);
	}
}
