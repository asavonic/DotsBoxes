/**
 * 
 */
package dotsboxes.game;

/**
 *
 *
 */
public class NewGameDesc {
	public int m_num_players;
	public int m_sizeFieldWidth;
	public int m_sizeFieldHeight;
	
	public NewGameDesc(int sizeFieldWidth, int sizeFieldHeight, int num_players)
	{
		m_sizeFieldWidth  = sizeFieldWidth;
		m_sizeFieldHeight = sizeFieldHeight;
		m_num_players     = num_players;
	}
	// TODO add more fields
}
