package dotsboxes.gui.newgame;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpringLayout;

import dotsboxes.EventManager;
import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.GUI_NewGameRequest;

public class CreateNewGame extends JPanel implements EventCallback {
	
	JSpinner m_numLocalPlayers = new JSpinner();
	JSpinner m_numRemotePlayers = new JSpinner();
	
	JSpinner m_fieldWidth = new JSpinner();
	JSpinner m_fieldHeight = new JSpinner();
	
	public CreateNewGame(Container parent) {
		m_Parent = parent;
		
		SpringLayout layout = new SpringLayout();
		
		setLayout( layout );
		
		JLabel local_players_label = new JLabel("Local players: "); 
		
		add(local_players_label);
		add(m_numLocalPlayers);
		
		layout.putConstraint(SpringLayout.WEST, local_players_label,
                5,
                SpringLayout.WEST, parent);
		
		layout.putConstraint(SpringLayout.NORTH, local_players_label,
                5,
                SpringLayout.NORTH, parent);
		
        layout.putConstraint(SpringLayout.WEST, m_numLocalPlayers,
                5,
                SpringLayout.EAST, local_players_label);
        layout.putConstraint(SpringLayout.NORTH, m_numLocalPlayers,
                5,
                SpringLayout.NORTH, parent);
        
        JLabel remote_players_label = new JLabel("Remote players: "); 
		
		add(remote_players_label);
		
		layout.putConstraint(SpringLayout.WEST, remote_players_label,
                5,
                SpringLayout.WEST, parent);
		
		layout.putConstraint(SpringLayout.NORTH, remote_players_label,
                10,
                SpringLayout.SOUTH, local_players_label);
		
		layout.putConstraint(SpringLayout.WEST, m_numLocalPlayers,
                5,
                SpringLayout.EAST, remote_players_label);
		
		add(m_numRemotePlayers);
		layout.putConstraint(SpringLayout.WEST, m_numRemotePlayers,
                5,
                SpringLayout.EAST, remote_players_label);
		layout.putConstraint(SpringLayout.NORTH, m_numLocalPlayers,
                10,
                SpringLayout.SOUTH, m_numRemotePlayers);
		
		JLabel field_width_label = new JLabel("Field width: "); 
		add(field_width_label);
		
		layout.putConstraint(SpringLayout.WEST, field_width_label,
                30,
                SpringLayout.EAST, m_numLocalPlayers);
                
		layout.putConstraint(SpringLayout.NORTH, field_width_label,
                5,
                SpringLayout.NORTH, parent);
		
		add(m_fieldWidth);
		layout.putConstraint(SpringLayout.WEST, m_fieldWidth,
                5,
                SpringLayout.EAST, field_width_label);
                
		layout.putConstraint(SpringLayout.NORTH, m_fieldWidth,
                5,
                SpringLayout.NORTH, parent);

		JLabel field_height_label = new JLabel("Field height: "); 
		add(field_height_label);
		
		layout.putConstraint(SpringLayout.WEST, field_height_label,
                30,
                SpringLayout.EAST, m_numRemotePlayers);
                
		layout.putConstraint(SpringLayout.NORTH, field_height_label,
                5,
                SpringLayout.SOUTH, field_width_label);
		
		add(m_fieldHeight);
		layout.putConstraint(SpringLayout.WEST, m_fieldHeight,
                0,
                SpringLayout.WEST, m_fieldWidth);
                
		layout.putConstraint(SpringLayout.NORTH, m_fieldHeight,
                5,
                SpringLayout.SOUTH, m_fieldWidth);
		
		layout.putConstraint(SpringLayout.NORTH, field_height_label,
                3,
                SpringLayout.NORTH, m_fieldHeight);
		
		JButton join_button = new JButton("Create game!");
		join_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewEvent(new GUI_NewGameRequest( (int) m_numLocalPlayers.getValue(), 
															  (int) m_numRemotePlayers.getValue(), 
															  (int) m_fieldHeight.getValue(), 
															  (int) m_fieldWidth.getValue()), self);
			}
		});
		
		add(join_button);
		
		
		layout.putConstraint(SpringLayout.WEST, parent,
                5,
                SpringLayout.WEST, join_button);
		layout.putConstraint(SpringLayout.NORTH, join_button,
                30,
                SpringLayout.SOUTH, remote_players_label);
	}
	
	CreateNewGame self = this;
	Container m_Parent;

	@Override
	public void HandleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
}
