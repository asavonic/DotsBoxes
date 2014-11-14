package dotsboxes;
/**
 * @file   GameSession.java
 * @brief  This file implement game logic ( add edge, add player's mark, win, lose, ...)
 */
import dotsboxes.callbacks.EventCallback;

public class GameSession 
{

	/**
	 * @name    GameSession
	 * @brief   Constructor GameSession.
	 * Init game logic objects.
	 * @param field_size - size of game field.
	 * @param players_number - number of players.
	 */
	GameSession( int field_height, int field_width, int players_number, int _current_player_tag, EventCallback callback )
	{
		m_fieldHeight = field_height;
		m_fieldWidth  = field_width;
		m_numberOfPlayers = players_number;
		m_send_event = callback;
		current_player_tag = _current_player_tag;
		edges  = new int[m_fieldHeight + 1][m_fieldWidth + 1];
		vertex = new int[m_fieldHeight][m_fieldWidth];
		
		for( int i = 0; i < (m_fieldHeight + 1); ++i)
		{
			edges[i][0] = border;
			edges[i][m_fieldWidth] = border;
		}
		for( int j = 0; j < (m_fieldWidth + 1); ++j)
		{
			edges[0][j] = border;
			edges[m_fieldHeight][j] = border;
		}
		Debug.log("GameSassion initializated.");
		AddEdge( 1, 1, 3);
		AddMark( 0, 0, 2);
	}
	
	/**
	 * @name    HandleEvent
	 * @brief   Handle event.
	 * Receive and handle event.
	 * @param Event - event that needs to handled into GameSession.
	 * @retval void
	 */
	public void HandleEvent( Event ev)
	{
		Debug.log("Event recieved.");
		Debug.log("Event handled.");
	}
	
	private void AddEdge( int i, int j, int player_tag)
	{
		if( player_tag <= 0 || player_tag > m_numberOfPlayers)
		{
			Debug.log("Invalid value(player)! Player tag = " + player_tag + ".");
			return;
		}
		if( i <= 0 || j <= 0 || i > m_fieldHeight || j > m_fieldWidth)
		{
			Debug.log("Invalid value(edge)! Player tag = " + player_tag + ". i = " + i + "(max = " + m_fieldHeight + "); j = " + j + "(max = " + m_fieldWidth + ").");
			return;
		}
		if (0 == edges[i][j])
		{
			edges[i][j] = player_tag;
			Debug.log("Player " + player_tag + " mark edge [" + i + "][" + j + "].");
			SendEvent( new Event( EventType.game_EdgeChanged ) );
		}
		else 
		{
			Debug.log("Player " + player_tag + " try to mark edge [" + i + "][" + j + "] but edge busy already.");
		}
	}
	
	private void AddMark( int i, int j, int player_tag)
	{
		if( player_tag <= 0 || player_tag > m_numberOfPlayers)
		{
			Debug.log("Invalid value(player)! Player tag = " + player_tag + ".");
			return;
		}
		if( i < 0 || j < 0 || i > m_fieldHeight || j > m_fieldWidth)
		{
			Debug.log("Invalid value (mark)! Player tag = " + player_tag + ". i = " + i + "(max = " + m_fieldHeight + "); j = " + j + "(max = " + m_fieldWidth + ").");
			return;
		}
		if (0 == vertex[i][j])
		{
			vertex[i][j] = player_tag;
			Debug.log("Player " + player_tag + " mark vertex [" + i + "][" + j + "].");
			SendEvent( new Event( EventType.game_VertexChanged ) );
		}
		else 
		{
			Debug.log("Player " + player_tag + " try to mark edge [" + i + "][" + j + "] but edge busy already.");
		}
	}
	
	private void SendEvent( Event ev)
	{
		m_send_event.new_game_event(ev);
	}
	/**
	 * @name    Delete
	 * @brief   Destroy game logic objects.
	 * @param void.
	 * @retval void
	 */
	public void Delete()
	{
		Debug.log("GameSession :  deleted.");
	}
	
	int m_fieldHeight;
	int m_fieldWidth;
	int m_numberOfPlayers;
	int current_player_tag;
	EventCallback m_send_event;
	int edges[][];
	int vertex[][];
	static public int border = -1;
}
