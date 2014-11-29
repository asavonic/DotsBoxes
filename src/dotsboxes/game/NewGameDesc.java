/**
 * 
 */
package dotsboxes.game;

/**
 *
 *
 */
public class NewGameDesc {
	public int numPlayers;
	int m_sizeFieldWidth;
	int m_sizeFieldHeight;
	String m_gameName;
	
	public int getNumPlayers() {
		return numPlayers;
	}
	public void setNum_players(int num_players) {
		this.numPlayers = num_players;
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
}
