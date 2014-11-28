/**
 * 
 */
package dotsboxes.events;

import dotsboxes.game.NewGameDesc;
import dotsboxes.players.PlayerDesc;

/**
 *
 *
 */
public class NewGameRequest extends EventWithSender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3122388165834884899L;

	public NewGameRequest(PlayerDesc sender) {
		super(EventType.remote_New_Game_Request, sender);
	}

	private NewGameDesc m_NewGameDesc;

	public NewGameDesc getNewGameDesc() {
		return m_NewGameDesc;
	}

	public void setNewGameDesc(NewGameDesc newGameDesc) {
		m_NewGameDesc = newGameDesc;
	}
}
