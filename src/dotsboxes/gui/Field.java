package dotsboxes.gui;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dotsboxes.EventManager;
import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.GUI_CurrentPlayerChanged;
import dotsboxes.events.GameStartEvent;
import dotsboxes.events.GameTurnEvent;
import dotsboxes.game.TurnDesc;
import dotsboxes.utils.Debug;

public class Field extends JPanel implements EventCallback
{
	JFrame m_frame;
	Field m_this;
	
	final int DEBUG = -1;
	final int empty  = -2;
	
	boolean m_IsInit = false;
	int m_num_players;
	int m_fieldWidth;
	int m_fieldHeight;
	int m_current_player_tag;
	int m_fatLine = 13;
	
	int field_begin_x;
	int field_begin_y; 
	int field_width;
	int field_height;
	
	int squre_width;
    int squre_height;
    
    int[][] m_edgesV;
	int[][] m_edgesH;
	int[][] m_vertex;
    
	Button button_BackToMenu = new Button("Back to menu.");
	Button button_Clear = new Button("Clear.");
	JLabel currentPlayer = new JLabel("Text-Only Label");
	
	public Field(JFrame frame) 
	{
		m_frame = frame;
		m_this = this;
		
		
		EventManager.Subscribe( EventType.game_Start, this); 
		EventManager.Subscribe( EventType.game_Turn, this); 
		EventManager.Subscribe( EventType.GUI_current_player_changed, this); 
		
		button_BackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewEvent( new dotsboxes.events.Event(EventType.GUI_back_to_Menu), m_this);
			}
		});
		
		ButtonResize();
		
		this.add(button_BackToMenu);
		
		this.add(currentPlayer);
		
		button_Clear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				for( int i = 1; i < m_fieldWidth; i++)
		            for(int j = 0; j < m_fieldHeight; ++j)
		            	if( DEBUG == m_edgesV[j][i])
		            		m_edgesV[j][i] = 0;		            				
				for( int i = 1; i < m_fieldHeight; i++)
		            for(int j = 0; j < m_fieldWidth; ++j)
		            	if( DEBUG == m_edgesH[i][j])
		            		m_edgesH[i][j] = 0;

		        for( int i = 0; i < m_fieldWidth; ++i)
		        	for( int j = 0; j < m_fieldHeight; ++j)
		        		if( DEBUG == m_vertex[i][j])
		        			m_vertex[i][j] = 0;
		        m_this.repaint();
			}
		});
		
		this.add(button_Clear);
		
		ButtonResize();
		
		this.addMouseListener( new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) 
			{				
				
				DetectClick(e.getX(), e.getY());	
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	public void ButtonResize()
	{
		button_BackToMenu.setBounds(this.getWidth() - 100, 0, 100, 30);
		button_Clear.setBounds(this.getWidth() - 200, 0, 100, 30);
		
		currentPlayer.setBounds(this.getWidth() - 400, 0, 150, 30);
	}
	
	public void Init(int width, int height, int num_players, int begin_player_tag)
	{
		m_num_players = num_players;
		m_fieldWidth = width;
		m_fieldHeight = height;
		m_current_player_tag = begin_player_tag;
		
		m_edgesH   = new int[m_fieldHeight + 1][m_fieldWidth];
		m_edgesV   = new int[m_fieldHeight][m_fieldWidth + 1];		
		m_vertex   = new int[m_fieldHeight][m_fieldWidth];		

		for(int i = 0; i < m_fieldHeight + 1; ++i)
			for( int j = 0; j < m_fieldWidth; ++j)
				m_edgesH[i][j] = empty;
		
		for(int i = 0; i < m_fieldHeight; ++i)
			for( int j = 0; j < m_fieldWidth + 1; ++j)
				m_edgesV[i][j] = empty;
		
		for(int i = 0; i < m_fieldHeight; ++i)
			for( int j = 0; j < m_fieldWidth; ++j)
				m_vertex[i][j] = empty;
		
		m_IsInit = true;
	}
	
	public void DetectClick(int x, int y)
	{
		
		for( int j = 1; j < m_fieldWidth; j++) //Vertical
        {
        	int x_pos = field_begin_x + j * squre_width;
        	Rectangle rect = new Rectangle(x_pos - (m_fatLine / 2), field_begin_y, m_fatLine, field_height);
        	
        	if(rect.contains(x, y))
        	{
        		for( int i = 1; i <= m_fieldHeight; ++i)
                {
        			int y_pos = field_begin_y + i * squre_height;
        			Rectangle rect1 = new Rectangle( field_begin_x, y_pos - (m_fatLine / 2), field_width, m_fatLine);
                	
        			if(rect1.contains(x, y))
        				return;
        			
                	if(y_pos > y)
                	{
                		//j--;
                		i--;
                		//if(Debug.isEnabled())
                		//	m_edgesV[i][j] = DEBUG;//TODO
                		TurnDesc tDesc = new TurnDesc(i, j, true);
                		EventManager.NewEvent(new dotsboxes.events.GameTurnEvent(EventType.GUI_game_Turn, true, tDesc), this);
                		Debug.log("Vertical.");
                		this.repaint();
                		return;
                	}
                }
        	}
        }
		
		for( int i = 1; i < m_fieldHeight; i++) //Horizontal
        {
			int y_pos = field_begin_y + i * squre_height;
        	Rectangle rect = new Rectangle( field_begin_x, y_pos - (m_fatLine / 2), field_width, m_fatLine);
        	
        	if(rect.contains(x, y))
        	{
        		for( int j = 1; j <= m_fieldWidth; j++)
                {
        			int x_pos = field_begin_x + j * squre_width;
                	
        			Rectangle rect1 = new Rectangle(x_pos - (m_fatLine / 2), field_begin_y, m_fatLine, field_height);
                	
        			if(rect1.contains(x, y))
        				return;
        			
        			
                	if(x_pos > x)
                	{
                		j--;
                		//if(Debug.isEnabled())
                		//	m_edgesH[i][j] = DEBUG;//TODO
                		TurnDesc tDesc = new TurnDesc(i, j, false);
                		EventManager.NewEvent(new dotsboxes.events.GameTurnEvent(EventType.GUI_game_Turn, true, tDesc), this);
                		Debug.log("Horizontal.");
                		this.repaint();
                		return;
                	}
                }
        	}
        }
		
		for( int i = 0; i < m_fieldWidth; ++i) //Square
		{
			int squre_begin_x = field_begin_x + (m_fatLine / 2) + squre_width * i;
			int squre_width_not_border = squre_width - m_fatLine;
			
			for( int j = 0; j < m_fieldHeight; ++j)
			{
				int squre_begin_y = field_begin_y + (m_fatLine / 2) + squre_height * j;
				int squre_height_not_border = squre_height - m_fatLine;
				
				Rectangle rect = new Rectangle( squre_begin_x, squre_begin_y, squre_width_not_border, squre_height_not_border);
				
				if(rect.contains(x, y))
				{
					//if(Debug.isEnabled())
					//	m_vertex[i][j] = DEBUG;
					TurnDesc tDesc = new TurnDesc(i, j, false);
            		EventManager.NewEvent(new dotsboxes.events.GameTurnEvent(EventType.GUI_game_Turn, false, tDesc), this);
            		Debug.log("Squere.");
            		this.repaint();
            		return;
				}
			}
		}
	}
	
	public void paintComponent( Graphics g ) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        this.setSize(m_frame.getWidth(), m_frame.getHeight());
        this.setBounds(m_frame.getBounds());
        ButtonResize();
        int x = this.getX();
        int y = this.getY();
        int width = this.getWidth();
        int height = this.getHeight();

        
        DrawBox(g2, x, y, width, height);   
     }
		
	public void DrawBox(Graphics2D g2, int x, int y, int width, int height)
	{
		int miniFatLine = 5; // Diff between line width generic edge and user marked edge.
		
		if(!m_IsInit)
			return;
		field_begin_x = x + m_fatLine; // Start field.
		field_begin_y = y + m_fatLine + button_BackToMenu.getHeight(); 
		field_width = width - 2 * m_fatLine;
		field_width -= (field_width % m_fieldWidth);
		field_height = height - 3 * m_fatLine - button_BackToMenu.getHeight();
		field_height -= (field_height % m_fieldHeight);
		
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(m_fatLine));
        g2.drawRect(field_begin_x, field_begin_y, field_width, field_height);
       
        squre_width = field_width / m_fieldWidth;
        squre_height = field_height / m_fieldHeight;
        
        
        for( int i = 1; i < m_fieldWidth; i++)
        {
        	g2.setStroke(new BasicStroke(m_fatLine));
        	int x_pos = field_begin_x + i * squre_width;
            g2.drawLine( x_pos, field_begin_y, x_pos, field_begin_y + field_height);
            
            g2.setStroke(new BasicStroke(m_fatLine - miniFatLine));
            for(int j = 0; j < m_fieldHeight; ++j)
            {
            	switch(m_edgesV[j][i])
            	{
            	case empty:
            		break;
            	case DEBUG:
            		g2.setColor(Color.blue);
            		g2.drawLine( x_pos, field_begin_y + j * squre_height + m_fatLine - m_fatLine % 2 - miniFatLine, x_pos, field_begin_y + (j + 1) * squre_height  - m_fatLine - m_fatLine % 2 + miniFatLine);
            		g2.setColor(Color.black);
            		break;
            	default:
            		g2.setColor(Color.green);
            		g2.drawLine( x_pos, field_begin_y + j * squre_height + m_fatLine - m_fatLine % 2 - miniFatLine, x_pos, field_begin_y + (j + 1) * squre_height  - m_fatLine - m_fatLine % 2 + miniFatLine);
            		g2.setColor(Color.black);
            		break;
            	}
            	
            }
        }
        
        for( int i = 1; i < m_fieldHeight; i++)
        {
        	g2.setStroke(new BasicStroke(m_fatLine));
        	int y_pos = field_begin_y + i * squre_height ;
            g2.drawLine( field_begin_x, y_pos, field_begin_x + field_width, y_pos);
            
            g2.setStroke(new BasicStroke(m_fatLine - miniFatLine));
            for(int j = 0; j < m_fieldWidth; ++j)
            {
            	
            	switch(m_edgesH[i][j])
            	{
            	case empty:
            		break;
            	case DEBUG:
            		g2.setColor(Color.blue);
            		g2.drawLine( field_begin_x + j * squre_width + m_fatLine + m_fatLine % 2  - miniFatLine, y_pos, field_begin_x + (j + 1) * squre_width - m_fatLine + miniFatLine, y_pos);
            		g2.setColor(Color.black);
            		break;
            	default:
            		g2.setColor(Color.green);
            		g2.drawLine( field_begin_x + j * squre_width  + m_fatLine + m_fatLine % 2 - miniFatLine, y_pos, field_begin_x + (j + 1) * squre_width - m_fatLine + miniFatLine, y_pos);
            		g2.setColor(Color.black);
            		break;
            	}
            	
            }
        }
        
        for( int i = 0; i < m_fieldHeight; ++i)
        {
        	for( int j = 0; j < m_fieldWidth; ++j)
        	{
        		int x_pos = field_begin_x + j * squre_width + m_fatLine / 2 + m_fatLine % 2 ;
        		int y_pos = field_begin_y + i * squre_height + m_fatLine / 2 + m_fatLine % 2;
        		
        		switch(m_vertex[i][j])
        		{
        		case empty:
        			break;
        		case DEBUG:
        			g2.setColor(Color.red);
        			
        			g2.fillRect(x_pos, y_pos, squre_width - m_fatLine, squre_height - m_fatLine);
            		g2.setColor(Color.black);
        			break;
        		default:
        			g2.setColor(new Color((m_vertex[i][j] * 50) % 255, (m_vertex[i][j] * 80)%255, 80));
        			g2.fillRect(x_pos, y_pos, squre_width - m_fatLine, squre_height - m_fatLine);
            		g2.setColor(Color.black);
        			break;
        		}
        	}
        }
	}

	private void HandleGameTurnEvent(GameTurnEvent event)
	{
		if(event.isEdgeChanged())
		{
			if(event.getVert())
				m_edgesV[event.getI()][event.getJ()] = event.getPlrTag();
			else
				m_edgesH[event.getI()][event.getJ()] = event.getPlrTag();
		}
		else
			m_vertex[event.getJ()][event.getI()] = event.getPlrTag();
	}
	
	@Override
	public void HandleEvent(dotsboxes.events.Event ev) 
	{
		switch(ev.GetType())
		{
		case game_Turn:
			GameTurnEvent turnEvent = (GameTurnEvent)ev;
			HandleGameTurnEvent(turnEvent);
			this.repaint();
			break;
		case game_Start:
			GameStartEvent g = (GameStartEvent)ev;
			Init(g.getFieldWidth(), g.getFieldHeight(),  g.getNumPlayers(), g.getBeginPlayer());
			this.repaint();
			break;
		case GUI_current_player_changed:
			GUI_CurrentPlayerChanged event = (GUI_CurrentPlayerChanged) ev;
			currentPlayer.setText(event.getPlayerName());
			break;
		default:
			Debug.log("Unknown event in GameSession!");
			return;
		}
	}
}
