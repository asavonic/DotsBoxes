package dotsboxes.gui;
import java.awt.EventQueue;

import javax.swing.*;

import dotsboxes.gui.Field;

import java.awt.*;
import java.awt.geom.*;

import dotsboxes.events.EventType;
import dotsboxes.events.GameTurnEvent;
import dotsboxes.game.TurnDesc;
import dotsboxes.utils.Debug;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Color;
import java.awt.Event;
//import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;

import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.JRadioButton;

import java.awt.Insets;
import java.awt.BorderLayout;

import dotsboxes.*;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;









//import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;

import javax.swing.JInternalFrame;

import java.awt.Panel;
import java.awt.Button;

import javax.swing.JLabel;


public class GUI {

	public JFrame m_frame = new JFrame();

	public GUI() {
		initialize();
	}

	Panel Menu = new Panel();
	Field Field = new Field(m_frame);
	Panel Config = new Panel();
	
	public void ShowMenu()
	{
		Menu.setBounds(m_frame.getBounds());
		Menu.repaint();
		Menu.setVisible(true);	
		Config.setVisible(false);
		Field.setVisible(false);
	}
	
	public void ShowConfig()
	{
		Menu.setVisible(false);
		Config.setVisible(true);
		Field.setVisible(false);
	}
	
	public void ShowField()
	{
		Menu.setVisible(false);
		Config.setVisible(false);
		Field.setBounds(m_frame.getBounds());
		Field.repaint();
		Field.setVisible(true);	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		Field.Init(6, 8, 3); // TODO Get from event.
		
		

		
		
		
		m_frame.addComponentListener( new ComponentListener()
		{  
				@Override
				public void componentResized(ComponentEvent e) {
					Menu.setSize(m_frame.getWidth(), m_frame.getHeight());
					Menu.repaint();
					Field.setSize(m_frame.getWidth(), m_frame.getHeight());
					Config.setSize(m_frame.getWidth(), m_frame.getHeight());
					//button_1.setBounds(m_frame.getWidth() - 100, m_frame.getY() + 10, 80, 30);
				}

				@Override
				public void componentMoved(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void componentShown(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void componentHidden(ComponentEvent e) {
					// TODO Auto-generated method stub
					
				}
		});
		
		m_frame.setBounds(10, 10, 756, 505);
		Menu.setBounds(m_frame.getBounds());
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		
		
		Menu.setLayout(new GridLayout(0, 1, 0, 0));
		
		Button StartGame = new Button("Lets start game!");
		StartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewAnonimEvent( new dotsboxes.events.Event(EventType.game_Start_GUI_Request));
			}
		});
		Menu.add(StartGame);
		
		Button button = new Button("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewAnonimEvent( new dotsboxes.events.Event(EventType.GUI_game_exit));
			}
		});
		Menu.add(button);
		m_frame.add(Menu);
		Field.setBounds(m_frame.getBounds());
		
		Field.setLayout(null);
		m_frame.add(Field);
		
		
		
		
		
		Config.setBounds(0, 0, 10, 10);
		m_frame.add(Config);
		
		
		Menu.setVisible(false);
		Config.setVisible(false);
		Field.setVisible(false);
		m_frame.setVisible(true);
	}
}
