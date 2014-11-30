/**
 * 
 */
package dotsboxes.game;

/**
 *
 *
 */
public class NewGameDesc {
	String m_gameName;
	public int m_num_players;
	public int m_sizeFieldWidth;
	int m_sizeFieldHeight;
	
	public int getNumPlayers() {
		return m_num_players;
	}
	public void setNumPlayers(int num_players) {
		this.m_num_players = num_players;
	}
	public int getSizeFieldWidth() {
		return m_sizeFieldWidth;
	}
	public void setSizeFieldWidth(int sizeFieldWidth) {
		m_sizeFieldWidth = sizeFieldWidth;
	}
	public int getSizeFieldHeight() {
		return m_sizeFieldHeight;
	}
	public void setSizeFieldHeight(int sizeFieldHeight) {
		m_sizeFieldHeight = sizeFieldHeight;
	}
	public String getGameName() {
		return m_gameName;
	}
	public void setGameName(String gameName) {
		m_gameName = gameName;
	}

	public NewGameDesc(int sizeFieldWidth, int sizeFieldHeight, int num_players)
	{
		m_sizeFieldWidth  = sizeFieldWidth;
		m_sizeFieldHeight = sizeFieldHeight;
		m_num_players     = num_players;
	}
}
