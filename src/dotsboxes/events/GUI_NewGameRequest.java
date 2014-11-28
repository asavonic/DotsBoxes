/**
 * 
 */
package dotsboxes.events;

import dotsboxes.players.PlayerDesc;

/**
 *
 *
 */
public class GUI_NewGameRequest extends Event {
	private static final long serialVersionUID = -4645586334808772848L;
	
	public GUI_NewGameRequest(PlayerDesc sender) {
		super(EventType.gui_New_Game_Request);
	}
}