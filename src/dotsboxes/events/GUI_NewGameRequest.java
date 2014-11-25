/**
 * 
 */
package dotsboxes.events;

/**
 *
 *
 */
public class GUI_NewGameRequest extends NewGameRequest {
	private static final long serialVersionUID = -4645586334808772848L;
	
	public GUI_NewGameRequest() {
		super();
		m_eventType = EventType.gui_New_Game_Request;
	}
}