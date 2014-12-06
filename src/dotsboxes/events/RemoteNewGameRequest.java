package dotsboxes.events;

import dotsboxes.game.NewGameDesc;
import dotsboxes.players.PlayerDesc;

public class RemoteNewGameRequest extends EventWithSender {

	NewGameDesc m_NewGameDesc;
	
	public RemoteNewGameRequest( PlayerDesc sender, NewGameDesc NewGameDesc) 
	{
		super(EventType.remote_New_Game_Request, sender);
		m_NewGameDesc = NewGameDesc;
	}

	public NewGameDesc getNewGameDesc() {
		return m_NewGameDesc;
	}

	public void setNewGameDesc(NewGameDesc newGameDesc) {
		m_NewGameDesc = newGameDesc;
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
