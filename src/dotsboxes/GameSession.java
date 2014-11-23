package dotsboxes;
/**
 * @file   GameSession.java
 * @brief  This file implement game logic ( add edge, add player's mark, win, lose, ...)
 */
import java.util.Vector;

import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.GameTurnEvent;
import dotsboxes.utils.Debug;

public class GameSession implements EventCallback
{

	/**
	 * @name    GameSession
	 * @brief   Constructor GameSession.
	 * Init game logic objects.
	 * @param field_size - size of game field.
	 * @param players_number - number of players.
	 */
	GameSession( int field_height, int field_width, int players_number, int current_player_tag )
	{
		m_fieldHeight      = field_height;
		m_fieldWidth       = field_width;
		m_numberOfPlayers  = players_number;
		
		
		EventManager.Subscribe( EventType.game_Turn, this); //Subscribe on game_Turn event.
		EventManager.Subscribe( EventType.game_Start, this); 
		
		current_player_tag = current_player_tag;
		m_edgesV   = new int[m_fieldHeight + 1][m_fieldWidth];
		m_edgesH   = new int[m_fieldHeight][m_fieldWidth + 1];
		m_vertex   = new int[m_fieldHeight][m_fieldWidth];
		m_counters = new int[m_numberOfPlayers + 1];
		m_history  = new Vector<Event>();
		
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
	}
	
	private void SendGUIEvent(Event event)
	{
		event.Cast(EventType.GUI_game_Turn);
		EventManager.NewEvent(event, this);
	}
	
	private void SendEvent(Event event)
	{
		event.Cast(EventType.game_Turn);
		EventManager.NewEvent(event, this);
	}
	
	
	private void GUITurn(Event ev)
	{
		GameTurnEvent game_event = (GameTurnEvent) ev; 
		if (game_event.isEdgeChanged())
		{
			boolean result = AddEdge( game_event.getI(), game_event.getJ(), game_event.getVert(), game_event.getPlrTag());
			if (result) 
			{
				SendEvent(ev);
				m_history.add(ev);
			}
		}
		else 
		{
			boolean result = AddMark( game_event.getI(), game_event.getJ(), game_event.getPlrTag());
			if (result)
			{
				SendEvent(ev);
				m_history.add(ev);
			}
		}
	}
	
	private void Turn(Event ev)
	{
		GameTurnEvent game_event1 = (GameTurnEvent) ev; 
		if (m_history.contains(ev))    // I can do that better.
		{
			Debug.log("Turn already exist.");
			return;
		}
		if (game_event1.isEdgeChanged())
		{
			boolean result = AddEdge( game_event1.getI(), game_event1.getJ(), game_event1.getVert(), game_event1.getPlrTag());
			if (result)
			{
				SendGUIEvent(ev);
				m_history.add(ev);
			}
		}
		else 
		{
			boolean result = AddMark( game_event1.getI(), game_event1.getJ(), game_event1.getPlrTag());
			if (result)
			{
				SendGUIEvent(ev);
				m_history.add(ev);
			}
		}
	}
	
	private void CheckOurTurn()
	{
		
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
		
		switch(ev.GetType())
		{
		case GUI_game_Turn:
			GUITurn(ev);
			break;
		case game_Turn:
			Turn(ev);
			break;
		case game_Start:
			
			break;
		default:
			Debug.log("Unknown event in GameSession!");
			return;
		}	
		
		Debug.log("Event handled.");
	}
	
	private boolean AddEdge( int i, int j, boolean vert, int player_tag)
	{
		int max_number_height = ( vert ) ? (m_fieldHeight + 1) : m_fieldHeight;
		int max_number_width = ( !vert ) ? (m_fieldWidth + 1) : m_fieldWidth;
		
		if( player_tag <= 0 || player_tag > m_numberOfPlayers)
		{
			Debug.log("Invalid value(player)! Player tag = " + player_tag + ".");
			return false;
		}
		if( i < 0 || j < 0 || i > max_number_height || j > max_number_width)
		{
			Debug.log("Invalid value(edge)! Player tag = " + player_tag + ". i = " + i + "(max = " + max_number_height + "); j = " + j + "(max = " + max_number_width + ").");
			return false;
		}
		
		if ( vert )
		{
			if (0 == m_edgesV[i][j])
			{
				m_edgesV[i][j] = player_tag;
				Debug.log("Player " + player_tag + " mark edge [" + i + "][" + j + "](vertical).");
				return true;
			}
			else 
			{
				Debug.log("Player " + player_tag + " try to mark edge [" + i + "][" + j + "] (vertical) but edge busy already.");
				return false;
			}
		} else 
		{
			if (0 == m_edgesH[i][j])
			{
				m_edgesH[i][j] = player_tag;
				Debug.log("Player " + player_tag + " mark edge [" + i + "][" + j + "](horizontal).");
				return true;
			}
			else 
			{
				Debug.log("Player " + player_tag + " try to mark edge [" + i + "][" + j + "] (horizontal) but edge busy already.");
				return false;
			}
		}
	}
	
	private boolean AddMark( int i, int j, int player_tag)
	{
		if (player_tag <= 0 || player_tag > m_numberOfPlayers)
		{
			Debug.log("Invalid value(player)! Player tag = " + player_tag + ".");
			return false;
		}
		if (i < 0 || j < 0 || i > m_fieldHeight || j > m_fieldWidth)
		{
			Debug.log("Invalid value (mark)! Player tag = " + player_tag + ". i = " + i + "(max = " + m_fieldHeight + "); j = " + j + "(max = " + m_fieldWidth + ").");
			return false;
		}
		if (((player_tag != m_edgesV[i][j])     & (border != m_edgesV[i][j]))     || 
			((player_tag != m_edgesH[i][j])     & (border != m_edgesH[i][j]))     ||  //Check for edges around.
			((player_tag != m_edgesV[i + 1][j]) & (border != m_edgesV[i + 1][j])) ||
			((player_tag != m_edgesH[i][j + 1]) & (border != m_edgesH[i][j + 1])) )
		{
			Debug.log("Player " + player_tag + " try to mark vertex [" + i + "][" + j + "] but edges around this vertex doesn't mark as him.");
			return false;
		}
		if (0 == m_vertex[i][j])
		{
			m_vertex[i][j] = player_tag;
			m_counters[player_tag] += 1;
			Debug.log("Player " + player_tag + " mark vertex [" + i + "][" + j + "].");
			return true;
		}
		else 
		{
			Debug.log("Player " + player_tag + " try to mark edge [" + i + "][" + j + "] but edge busy already.");
			return false;
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
	
	public void Draw()
	{
		Debug.log("========================================");
		
		Debug.log("Nuber of players = " + m_numberOfPlayers);
		Debug.log("Height = " + m_fieldHeight);
		Debug.log("Width = " + m_fieldWidth);
		Debug.log("");
		
		String horizontalLines  = new String();
		String hor_lin = new String("---------");
		String ver_lin = new String("|");
		for( int j = 0; j < m_fieldWidth; ++j )
		{
			horizontalLines += hor_lin;
		}
		
		for( int i = 0; i < m_fieldHeight; ++i )
		{
			String horizontalValue = new String("    ");		
			for( int j = 0; j < m_fieldWidth; ++j )
			{
				horizontalValue += ver_lin;
				if (border == m_edgesV[i][j])
				{
					horizontalValue += String.valueOf(m_edgesV[i][j]);
				}
				else
				{
					horizontalValue += " ";
					horizontalValue += String.valueOf(m_edgesV[i][j]);
				}
				horizontalValue += ver_lin;
				horizontalValue += "  ";
			}
			Debug.log(horizontalLines);
			Debug.log(horizontalValue);
			Debug.log(horizontalLines);
			
			horizontalValue = new String();
			for( int j = 0; j < m_fieldHeight; ++j )
			{
				horizontalValue += ver_lin;
				horizontalValue += " ";
				horizontalValue += String.valueOf(m_edgesH[i][j]);
				horizontalValue += ver_lin;
				horizontalValue += "*";
				horizontalValue += String.valueOf(m_vertex[i][j]);
			}
			horizontalValue += ver_lin;
			horizontalValue += String.valueOf(m_edgesH[i][m_fieldHeight]);
			horizontalValue += ver_lin;
			
			Debug.log(horizontalValue);
		}
		String horizontalValue = new String("    ");
		for( int j = 0; j < m_fieldWidth; ++j )
		{
			horizontalValue += ver_lin;
			if (border == m_edgesV[m_fieldHeight][j])
			{
				horizontalValue += String.valueOf(m_edgesV[m_fieldHeight][j]);
			}
			else
			{
				horizontalValue += " ";
				horizontalValue += String.valueOf(m_edgesV[m_fieldHeight][j]);
			}
			horizontalValue += ver_lin;
			horizontalValue += "  ";
		}
		Debug.log(horizontalLines);
		Debug.log(horizontalValue);
		Debug.log(horizontalLines);
		
		Debug.log("");
		Debug.log("========================================");
		
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
	int m_edgesV[][];  // List of vertical edges.
	int m_edgesH[][];  // List of horizontal edges.
	int m_vertex[][];
	int m_counters[];    // List of number of marked vertex.
	Vector<Event> m_history;
	static public int border = -1;
	static public int empty  =  0;
}
