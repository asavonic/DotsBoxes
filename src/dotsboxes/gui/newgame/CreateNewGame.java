package dotsboxes.gui.newgame;

import java.awt.Button;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;

import dotsboxes.EventManager;
import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.GUI_NewGameRequest;
import dotsboxes.game.NewGameDesc;

public class CreateNewGame extends JPanel implements EventCallback {
	
	JSpinner m_numLocalPlayers = new JSpinner( new SpinnerNumberModel(1, 1, 9, 1) );
	JSpinner m_numRemotePlayers = new JSpinner(new SpinnerNumberModel(0, 0, 9, 1) );
	
	JSpinner m_fieldWidth = new JSpinner( new SpinnerNumberModel(5, 1, 9, 1) );
	JSpinner m_fieldHeight = new JSpinner( new SpinnerNumberModel(5, 1, 9, 1) );
	
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
		
		
		layout.putConstraint(SpringLayout.NORTH, m_numRemotePlayers,
                5,
                SpringLayout.SOUTH, m_numLocalPlayers);
       
		
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
		
		JButton m_button_BackToMenu = new JButton("Back to menu.");
		this.add(m_button_BackToMenu);
		
		m_button_BackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewEvent( new dotsboxes.events.Event(EventType.GUI_back_to_Menu, 100), self);
			}
		});
		
		JButton join_button = new JButton("Create game!");
		join_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				NewGameDesc desc = new NewGameDesc(
								(int) m_fieldWidth.getValue(),
								(int) m_fieldHeight.getValue(), 
								(int) m_numLocalPlayers.getValue(), 
								(int) m_numRemotePlayers.getValue());
								
				EventManager.NewEvent(new GUI_NewGameRequest( desc), self);
			}
		});
		
		add(join_button);
		
		
		layout.putConstraint(SpringLayout.WEST, parent,
                5,
                SpringLayout.WEST, join_button);
		layout.putConstraint(SpringLayout.NORTH, join_button,
                30,
                SpringLayout.SOUTH, remote_players_label);
		
		layout.putConstraint(SpringLayout.WEST, parent,
                5,
                SpringLayout.WEST, m_button_BackToMenu);
		layout.putConstraint(SpringLayout.NORTH, m_button_BackToMenu,
                30,
                SpringLayout.SOUTH, join_button);
	}
	
	CreateNewGame self = this;
	Container m_Parent;

	@Override
	public void HandleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
}
