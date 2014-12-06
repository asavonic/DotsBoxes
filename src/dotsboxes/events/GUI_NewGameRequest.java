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
public class GUI_NewGameRequest extends Event 
{
	private static final long serialVersionUID = -4645586334808772848L;
	
	private NewGameDesc m_NewGameDesc;
	
	public GUI_NewGameRequest(int numLocalPlayers, int numRemotePlayers, int fieldHeight, int fieldWidth) 
	{
		super(EventType.gui_New_Game_Request);
		m_NewGameDesc = new NewGameDesc(fieldWidth, fieldHeight, numLocalPlayers, numRemotePlayers);
	}
	
	public int getNumLocalPlayers()
	{
		return m_NewGameDesc.getNumLocalPlayers();
	}
	
	public int getNumRemotePlayers()
	{
		return m_NewGameDesc.getNumRemotePlayers();
	}
	
	public int getFieldHeight()
	{
		return m_NewGameDesc.getSizeFieldHeight();
	}
	
	public int getFieldWidth()
	{
		return m_NewGameDesc.getSizeFieldWidth();
	}
}