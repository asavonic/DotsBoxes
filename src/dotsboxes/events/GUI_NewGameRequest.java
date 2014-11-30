/**
 * 
 */
package dotsboxes.events;

import dotsboxes.players.PlayerDesc;

/**
 *
 *
 */
public class GUI_NewGameRequest extends Event 
{
	private static final long serialVersionUID = -4645586334808772848L;
	int m_numLocalPlayers;
	int m_numRemotePlayers;
	int m_fieldHeight;	
	int m_fieldWidth;	
	
	public GUI_NewGameRequest(int numLocalPlayers, int numRemotePlayers, int fieldHeight, int fieldWidth) 
	{
		super(EventType.gui_New_Game_Request);
		m_numLocalPlayers = numLocalPlayers;
		m_numRemotePlayers = numRemotePlayers;	
		
		m_fieldHeight = fieldHeight;
		m_fieldWidth  = fieldWidth;
	}
	
	public int getNumLocalPlayers()
	{
		return m_numLocalPlayers;
	}
	
	public int getNumRemotePlayers()
	{
		return m_numRemotePlayers;
	}
	
	public int getFieldHeight()
	{
		return m_fieldHeight;
	}
	
	public int getFieldWidth()
	{
		return m_fieldWidth;
	}
}