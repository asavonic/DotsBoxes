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
