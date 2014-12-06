package dotsboxes.gui.newgame;

import dotsboxes.EventManager;
import dotsboxes.events.EventType;
import dotsboxes.game.NewGameDesc;

import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FindNewGame extends JPanel {

	LinkedList<NewGameDesc> m_gamesList;
	
	DefaultListModel m_listModel = new DefaultListModel();
	JList m_listView = new JList(m_listModel);
	
	JTextArea m_gameDesc = new JTextArea("TEST GAME DESCRIPTOR");
	
	public FindNewGame(Container parent) {
		m_Parent = parent;
		this.setLayout( new GridLayout(0, 2));
		
		m_listView.addListSelectionListener( new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = m_listView.getSelectedIndex();
				//m_gameDesc.setText( GameDescToString(m_gamesList.get(index)) );
				m_gameDesc.setText( "TEST GAME DESCRIPTOR" );
			}
		});
		add(m_listView);
		add(m_gameDesc);
		
		Button join_button = new Button("Join game!");
		join_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewAnonimEvent( new dotsboxes.events.Event(EventType.gui_New_Game_Accept));
			}
		});
		
		add(join_button);
	}
	
	private String GameDescToString(NewGameDesc game)
	{
		String result = null;
		result += game.getGameName() + "\n";
		result += "Field size: " + game.getSizeFieldWidth() + " x " + game.getSizeFieldHeight() + "\n";
		result += "Number of players: " + (game.getNumLocalPlayers() + game.getNumRemotePlayers()) + "\n";
		
		return result;
	}
	
	public void Update()
	{
		m_listModel.removeAllElements();
		for(NewGameDesc game : m_gamesList) {
			m_listModel.addElement(game.getGameName());
		}
	}
	
	Container m_Parent;
}
