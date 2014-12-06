/**
 * 
 */
package dotsboxes.game;

/**
 *
 *
 */
public class NewGameDesc implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1999455417610589169L;
	
	String m_gameName;
	public int m_num_local_players;
	public int m_num_remote_players;
	public int m_sizeFieldWidth;
	int m_sizeFieldHeight;
	
	public int getNumLocalPlayers() {
		return m_num_local_players;
	}
	public void setNumLocalPlayers(int num_remote_players) {
		this.m_num_remote_players = num_remote_players;
	}
	
	public int getNumRemotePlayers() {
		return m_num_remote_players;
	}
	public void setNumRemotePlayers(int num_local_players) {
		this.m_num_local_players = num_local_players;
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

	public NewGameDesc(int sizeFieldWidth, int sizeFieldHeight, int num_local_players, int num_remote_players)
	{
		m_sizeFieldWidth  = sizeFieldWidth;
		m_sizeFieldHeight = sizeFieldHeight;
		m_num_local_players = num_local_players;
		m_num_remote_players = num_remote_players;
	}
}
