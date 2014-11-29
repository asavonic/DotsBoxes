package dotsboxes.gui;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dotsboxes.EventManager;
import dotsboxes.events.EventType;
import dotsboxes.events.GameTurnEvent;
import dotsboxes.game.TurnDesc;
import dotsboxes.utils.Debug;

public class Field extends JPanel
{
	JFrame m_frame;
	
	int m_num_players;
	int m_fieldWidth;
	int m_fieldHeight;
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
    
	Button button_1 = new Button("Back to menu.");
	
	public Field(JFrame frame) 
	{
		m_frame = frame;
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewAnonimEvent( new dotsboxes.events.Event(EventType.GUI_back_to_Menu));
			}
		});
		
		ButtonResize();
		
		this.add(button_1);
		
		
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
		button_1.setBounds(this.getWidth() - 100, 0, 100, 30);
	}
	
	public void Init(int width, int height, int num_players)
	{
		m_num_players = num_players;
		m_fieldWidth = width;
		m_fieldHeight = height;
	
		if( m_fieldWidth > m_fieldHeight)
		{
			m_edgesH   = new int[m_fieldHeight + 1][m_fieldWidth];
			m_edgesV   = new int[m_fieldHeight][m_fieldWidth + 1];
		}
		else{if( m_fieldWidth < m_fieldHeight)
		{
			m_edgesH   = new int[m_fieldHeight][m_fieldWidth + 1];
			m_edgesV   = new int[m_fieldHeight + 1][m_fieldWidth];
		}
		else
		{
			m_edgesH   = new int[m_fieldHeight + 1][m_fieldWidth];
			m_edgesV   = new int[m_fieldHeight][m_fieldWidth + 1];		
		}
		}
		m_vertex   = new int[m_fieldWidth][m_fieldHeight];
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
                		m_edgesV[i][j] = 1;//TODO
                		TurnDesc tDesc = new TurnDesc(i, j, 1, true);//TODO player tag.
                		EventManager.NewAnonimEvent(new dotsboxes.events.GameTurnEvent(EventType.GUI_game_Turn, true, tDesc));
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
                		m_edgesH[i][j] = 1;//TODO
                		TurnDesc tDesc = new TurnDesc(i, j, 1, false);//TODO player tag.
                		EventManager.NewAnonimEvent(new dotsboxes.events.GameTurnEvent(EventType.GUI_game_Turn, true, tDesc));
                		Debug.log("Horizontal.");
                		this.repaint();
                		return;
                	}
                }
        	}
        }
		
		for( int i = 0; i < m_fieldWidth; ++i) //Squere
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
					m_vertex[i][j] = 1;
					TurnDesc tDesc = new TurnDesc(i, j, 1, false);//TODO player tag.
            		EventManager.NewAnonimEvent(new dotsboxes.events.GameTurnEvent(EventType.GUI_game_Turn, false, tDesc));
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
        ButtonResize();
        int x = this.getX();
        int y = this.getY();
        int width = this.getWidth();
        int height = this.getHeight();

        
        DrawBox(g2, x, y, width, height);   
     }
		
	public void DrawBox(Graphics2D g2, int x, int y, int width, int height)
	{
		field_begin_x = x + m_fatLine; // Start field.
		field_begin_y = y + m_fatLine + button_1.getHeight(); 
		field_width = width - 2 * m_fatLine  - (width % m_fieldWidth);
		field_height = height - 3 * m_fatLine - (height % m_fieldHeight) - button_1.getHeight();
		
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(m_fatLine));
        g2.drawRect(field_begin_x, field_begin_y, field_width, field_height);
       
        squre_width = field_width / m_fieldWidth;
        squre_height = field_height / m_fieldHeight;
        
        
        for( int i = 1; i < m_fieldWidth; i++)
        {
        	int x_pos = field_begin_x + i * squre_width;
            g2.drawLine( x_pos, field_begin_y, x_pos, field_begin_y + field_height);
            
            for(int j = 0; j < m_fieldHeight; ++j)
            {
            	switch(m_edgesV[j][i])
            	{
            	case 0:
            		break;
            	case 1:
            		g2.setColor(Color.blue);
            		g2.drawLine( x_pos, field_begin_y + j * squre_height, x_pos, field_begin_y + (j + 1) * squre_height);
            		g2.setColor(Color.black);
            		break;
            	}
            	
            }
        }
        
        for( int i = 1; i < m_fieldHeight; i++)
        {
        	int y_pos = field_begin_y + i * squre_height;
            g2.drawLine( field_begin_x, y_pos, field_begin_x + field_width, y_pos);
            
            for(int j = 0; j < m_fieldWidth; ++j)
            {
            	switch(m_edgesH[i][j])
            	{
            	case 0:
            		break;
            	case 1:
            		g2.setColor(Color.blue);
            		g2.drawLine( field_begin_x + j * squre_width, y_pos, field_begin_x + (j + 1) * squre_width, y_pos);
            		g2.setColor(Color.black);
            		break;
            	}
            	
            }
        }
        
        for( int i = 0; i < m_fieldWidth; ++i)
        {
        	for( int j = 0; j < m_fieldHeight; ++j)
        	{
        		int x_pos = field_begin_x + i * squre_width;
        		int y_pos = field_begin_y + j * squre_height;
        		
        		switch(m_vertex[i][j])
        		{
        		case 0:
        			break;
        		case 1:
        			g2.setColor(Color.red);
        			g2.fillRect(x_pos, y_pos, squre_width, squre_height);
            		g2.setColor(Color.black);
        			break;
        		}
        	}
        }
	}
}
