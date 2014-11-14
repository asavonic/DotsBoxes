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
	GameSession( int field_height, int field_width, int players_number, int current_player_tag, EventCallback callback )
	{
		m_fieldHeight      = field_height;
		m_fieldWidth       = field_width;
		m_numberOfPlayers  = players_number;
		m_send_event       = callback;
		current_player_tag = current_player_tag;
		m_edgesV  = new int[m_fieldHeight + 1][m_fieldWidth];
		m_edgesH  = new int[m_fieldHeight][m_fieldWidth + 1];
		m_vertex  = new int[m_fieldHeight][m_fieldWidth];
		m_counters = new int[m_numberOfPlayers + 1];
		
		for( int i = 0; i < m_fieldHeight; ++i)
		{
			m_edgesH[i][0] = border;
			m_edgesH[i][m_fieldWidth] = border;
		}
		for( int j = 0; j < m_fieldWidth; ++j)
		{
			m_edgesV[0][j] = border;
			m_edgesV[m_fieldHeight][j] = border;
		}
		
		Debug.log("GameSassion initializated.");
		AddEdge( 0, 1, /* vertical? =  */ 0,  3);
		AddEdge( 1, 0, /* vertical? =  */ 1,  3);
		AddEdge( 1, 1, /* vertical? =  */ 0,  3);
		AddEdge( 1, 1, /* vertical? =  */ 1,  3);
		AddMark( 0, 0, 3);
		AddMark( 1, 0, 3);
		AddMark( 0, 1, 3);
		AddMark( 1, 1, 3);
		Debug.log("Win player number " + CheckWin());
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
	
	private void AddEdge( int i, int j, int vert, int player_tag)
	{
		int max_number_height = ( 1 == vert ) ? (m_fieldHeight + 1) : m_fieldHeight;
		int max_number_width = ( 0 == vert ) ? (m_fieldWidth + 1) : m_fieldWidth;
		
		if( player_tag <= 0 || player_tag > m_numberOfPlayers)
		{
			Debug.log("Invalid value(player)! Player tag = " + player_tag + ".");
			return;
		}
		if( i < 0 || j < 0 || i > max_number_height || j > max_number_width)
		{
			Debug.log("Invalid value(edge)! Player tag = " + player_tag + ". i = " + i + "(max = " + max_number_height + "); j = " + j + "(max = " + max_number_width + ").");
			return;
		}
		
		if ( 1 == vert )
		{
			if (0 == m_edgesV[i][j])
			{
				m_edgesV[i][j] = player_tag;
				Debug.log("Player " + player_tag + " mark edge [" + i + "][" + j + "](vertical).");
				SendEvent( new Event( EventType.game_EdgeChanged ) );
			}
			else 
			{
				Debug.log("Player " + player_tag + " try to mark edge [" + i + "][" + j + "] (vertical) but edge busy already.");
			}
		} else 
		{
			if (0 == m_edgesH[i][j])
			{
				m_edgesH[i][j] = player_tag;
				Debug.log("Player " + player_tag + " mark edge [" + i + "][" + j + "](horizontal).");
				SendEvent( new Event( EventType.game_EdgeChanged ) );
			}
			else 
			{
				Debug.log("Player " + player_tag + " try to mark edge [" + i + "][" + j + "] (horizontal) but edge busy already.");
			}
		}
	}
	
	private void AddMark( int i, int j, int player_tag)
	{
		if (player_tag <= 0 || player_tag > m_numberOfPlayers)
		{
			Debug.log("Invalid value(player)! Player tag = " + player_tag + ".");
			return;
		}
		if (i < 0 || j < 0 || i > m_fieldHeight || j > m_fieldWidth)
		{
			Debug.log("Invalid value (mark)! Player tag = " + player_tag + ". i = " + i + "(max = " + m_fieldHeight + "); j = " + j + "(max = " + m_fieldWidth + ").");
			return;
		}
		if (((player_tag != m_edgesV[i][j])     & (border != m_edgesV[i][j]))     || 
			((player_tag != m_edgesH[i][j])     & (border != m_edgesH[i][j]))     ||  //Check for edges around.
			((player_tag != m_edgesV[i + 1][j]) & (border != m_edgesV[i + 1][j])) ||
			((player_tag != m_edgesH[i][j + 1]) & (border != m_edgesH[i][j + 1])) )
		{
			Debug.log("Player " + player_tag + " try to mark vertex [" + i + "][" + j + "] but edges around this vertex doesn't mark as him.");
			return;
		}
		if (0 == m_vertex[i][j])
		{
			m_vertex[i][j] = player_tag;
			m_counters[player_tag] += 1;
			Debug.log("Player " + player_tag + " mark vertex [" + i + "][" + j + "].");
			SendEvent( new Event( EventType.game_VertexChanged ) );
		}
		else 
		{
			Debug.log("Player " + player_tag + " try to mark edge [" + i + "][" + j + "] but edge busy already.");
		}
	}
	
	private int CheckWin()
	{
		//Go across horizontal edges.
		for( int i = 0; i < m_fieldHeight; ++i)
			for( int j = 1; j < m_fieldWidth; ++j)
			{
				if ((empty == m_edgesH[i][j]))
				{
					return 0;
				}
			}
		
		//Go across vertical edges.
		for( int i = 1; i < m_fieldHeight; ++i)
			for( int j = 1; j < m_fieldWidth; ++j)
			{
				if ((empty == m_edgesH[i][j]))
				{
					return 0;
				}
			}
		
		// OK. All edges marked. 
		//Check fot all vertex marked.
		for( int i = 0; i < m_fieldHeight; ++i)
			for( int j = 0; j < m_fieldWidth; ++j)
			{
				if ((empty == m_vertex[i][j]))
				{
					return 0;
				}
			}
		
		int max = m_counters[0];
		int player_tag = 0;
		for( int i = 1; i < m_numberOfPlayers + 1; ++i)
			if ( m_counters[i] > max)
			{
				max = m_counters[i];
				player_tag = i;
			}
		
		return player_tag;
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
	int m_current_player_tag;
	EventCallback m_send_event;
	int m_edgesV[][];  // List of vertical edges.
	int m_edgesH[][];  // List of horizontal edges.
	int m_vertex[][];
	int m_counters[];    // List of number of marked vertex.
	static public int border = -1;
	static public int empty  =  0;
}
