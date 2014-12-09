package dotsboxes.gui.newgame;

import dotsboxes.EventManager;
import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.RemoteNewGameRequest;
import dotsboxes.game.NewGameDesc;

import java.awt.Button;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FindNewGame extends JPanel implements EventCallback{


	LinkedList<RemoteNewGameRequest> m_gamesList = new LinkedList<RemoteNewGameRequest>();
	
	DefaultListModel<String> m_listModel = new DefaultListModel<String>();
	JList<String> m_listView = new JList<String>(m_listModel);
	
	JTextArea m_gameDesc = new JTextArea("TEST GAME DESCRIPTOR");
	
	public FindNewGame(Container parent) {
		m_Parent = parent;
		
		EventManager.Subscribe( EventType.remote_New_Game_Request, this );
		
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
		
		JButton m_button_BackToMenu = new JButton("Back to menu.");
		this.add(m_button_BackToMenu);
		
		m_button_BackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewEvent( new dotsboxes.events.Event(EventType.GUI_back_to_Menu, 100), self);
			}
		});
		
		
		
		Button join_button = new Button("Join game!");
		join_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				if ( m_listView.getSelectedIndex() == -1 ) {
					return;
				}
				
				EventManager.NewEvent( new dotsboxes.events.GUI_NewGameAccept( m_gamesList.get( m_listView.getSelectedIndex() ), 1 ), self );
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
		for(RemoteNewGameRequest game : m_gamesList) {
			m_listModel.addElement(game.getNewGameDesc().getGameName() + " from player " + game.getSender().getName() );
		}
	}

	Container m_Parent;


	@Override
	public void HandleEvent(Event event) {
		if ( event.GetType() == EventType.remote_New_Game_Request ) {
			RemoteNewGameRequest remote_game_request = (RemoteNewGameRequest) event;
			m_gamesList.add( remote_game_request );
			Update();
		}
	}

	FindNewGame self = this;


}
