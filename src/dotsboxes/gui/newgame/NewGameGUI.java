package dotsboxes.gui.newgame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import dotsboxes.EventManager;
import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.utils.Debug;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.AbstractListModel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;

public class NewGameGUI extends JTabbedPane implements EventCallback {

	public NewGameGUI() {
		initialize();
	}
	
	JPanel m_FindGamePanel = new FindNewGame(this);
	JPanel m_CreateGamePanel = new CreateNewGame(this);
	
	public void initialize()
	{
		//setLayout( new CardLayout() );
		m_FindGamePanel.setVisible(true);
		m_CreateGamePanel.setVisible(true);
		addTab( "Find game", m_FindGamePanel );
		addTab( "Create new game", m_CreateGamePanel );
		
		
	}
	
	@Override
	public void HandleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
}
