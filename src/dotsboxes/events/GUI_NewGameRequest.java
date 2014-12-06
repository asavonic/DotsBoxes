/**
 * 
 */
package dotsboxes.events;

import dotsboxes.game.NewGameDesc;
import dotsboxes.players.PlayerDesc;

public class GUI_NewGameRequest extends EventWithGameDesc 
{
	private static final long serialVersionUID = -4645586334808772848L;
	
	public GUI_NewGameRequest(NewGameDesc NewGameDesc) 
	{
		super(EventType.GUI_New_Game_Request, NewGameDesc, null/* GUI don't know about player desc*/);	
	}
}