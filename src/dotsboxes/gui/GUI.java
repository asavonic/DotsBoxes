package dotsboxes.gui;
import java.awt.EventQueue;

import javax.swing.*;

import dotsboxes.gui.Field;
import dotsboxes.gui.mainmenu.MainMenu;
import dotsboxes.gui.newgame.NewGameGUI;

import java.awt.*;
import java.awt.geom.*;

import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.EventType;
import dotsboxes.events.GUI_NewGameRequest;
import dotsboxes.events.GameStartEvent;
import dotsboxes.events.GameTurnEvent;
import dotsboxes.events.NewGameAccept;
import dotsboxes.events.SleepEvent;
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

import java.awt.FlowLayout;

import javax.swing.JInternalFrame;

import java.awt.Panel;
import java.awt.Button;

import javax.swing.JLabel;


public class GUI implements EventCallback{

	public JFrame m_frame = new JFrame();

	public GUI() 
	{
		m_this = this;
		initialize();
	
		EventManager.Subscribe( EventType.GUI_back_to_Menu, this); 
		EventManager.Subscribe( EventType.game_Start, this); 
		EventManager.Subscribe( EventType.GUI_game_Start, this); 
	}

	MainMenu Menu = new MainMenu(m_frame);
	Field Field = new Field(m_frame);
	Panel Config = new Panel();
	NewGameGUI NewGameGUI = new NewGameGUI();
	GUI m_this;
	
	public void ShowMenu()
	{
		NewGameGUI.setVisible(false);
		Menu.setBounds(m_frame.getBounds());
		Menu.repaint();
		Menu.setVisible(true);	
		Config.setVisible(false);
		Field.setVisible(false);
	}
	
	public void ShowConfig()
	{
		NewGameGUI.setVisible(false);
		Menu.setVisible(false);
		Config.setVisible(true);
		Field.setVisible(false);
	}
	
	public void ShowField()
	{
		NewGameGUI.setVisible(false);
		Menu.setVisible(false);
		Config.setVisible(false);
		Field.setBounds(m_frame.getBounds());
		Field.repaint();
		Field.setVisible(true);	
	}
	
	public void ShowNewGameGUI()
	{
		Menu.setVisible(false);
		Config.setVisible(false);
		Field.setVisible(false);
		NewGameGUI.setBounds(m_frame.getBounds());
		NewGameGUI.repaint();
		NewGameGUI.setVisible(true);	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		//Field.Init(6, 8, 3); // TODO Get from event.

		m_frame.addComponentListener( new ComponentListener()
		{  
				@Override
				public void componentResized(ComponentEvent e) {
					Menu.setSize(m_frame.getWidth(), m_frame.getHeight());
					Menu.repaint();
					Field.setSize(m_frame.getWidth(), m_frame.getHeight());
					Config.setSize(m_frame.getWidth(), m_frame.getHeight());
					Field.repaint();
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
		

		m_frame.add(Menu);
		Field.setBounds(m_frame.getBounds());
		
		Field.setLayout(null);
		m_frame.add(Field);
		
		
		
		Config.setBounds(0, 0, 10, 10);
		m_frame.add(Config);
		
		NewGameGUI.setBounds(m_frame.getBounds());
		m_frame.add(NewGameGUI);
		
		
		Menu.setVisible(false);
		Config.setVisible(false);
		Field.setVisible(false);
		NewGameGUI.setVisible(false);
		m_frame.setVisible(true);
	}

	@Override
	public void HandleEvent(dotsboxes.events.Event event) 
	{
		switch ( event.GetType() ) 
		{
		case GUI_back_to_Menu: 
			this.ShowMenu();
			EventManager.KillEvents(EventType.show_history);
			break;
		case game_Start:
			this.ShowField();
			break;
		case GUI_game_Start:
			this.ShowNewGameGUI();
			break;
		}
		
	}
}
