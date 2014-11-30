package dotsboxes.gui.mainmenu;

import java.awt.Button;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import dotsboxes.EventManager;
import dotsboxes.callbacks.EventCallback;
import dotsboxes.events.Event;
import dotsboxes.events.EventType;
import dotsboxes.events.GUI_NewGameRequest;
import dotsboxes.utils.Debug;

public class MenuPanel extends JPanel implements EventCallback {

	public MenuPanel(Container parent) 
	{	
		m_parent = parent;
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
		
		JButton game_start_button = new JButton("Lets start game!");
		game_start_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				EventManager.NewEvent( new dotsboxes.events.Event(EventType.GUI_game_Start), m_this);
			}
		});
		
		add(Box.createVerticalGlue());
		
		add(game_start_button);
		game_start_button.setAlignmentX(CENTER_ALIGNMENT);
		game_start_button.setAlignmentY(CENTER_ALIGNMENT);
		
		if ( Debug.isEnabled() ) {
			JButton debug_call_field = new JButton("debug call field");
			debug_call_field.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					EventManager.NewEvent(new GUI_NewGameRequest( 2, 0, 6, 8), m_this);
				}
			});
			add(debug_call_field);	
			debug_call_field.setAlignmentX(CENTER_ALIGNMENT);
			//debug_call_field.setAlignmentY(CENTER_ALIGNMENT);
		}
		
		
		JButton exit_button = new JButton("Exit");
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewEvent( new dotsboxes.events.Event(EventType.GUI_game_exit), m_this);
			}
		});
		
		add(exit_button);
		exit_button.setAlignmentX(CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
	}

	Container m_parent;
	MenuPanel m_this = this;
	
	@Override
	public void HandleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}
}
