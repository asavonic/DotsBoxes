/**
 * 
 */
package dotsboxes.events;

import dotsboxes.game.NewGameDesc;

/**
 *
 *
 */
public class NewGameRequest extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3122388165834884899L;

	public NewGameRequest() {
		super(EventType.NewGameRequest);
	}

	private NewGameDesc m_NewGameDesc;

	public NewGameDesc getNewGameDesc() {
		return m_NewGameDesc;
	}

	public void setNewGameDesc(NewGameDesc newGameDesc) {
		m_NewGameDesc = newGameDesc;
	}
}
