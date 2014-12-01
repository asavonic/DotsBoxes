package dotsboxes.gui.mainmenu;

import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LoginPanel extends JPanel {

	public LoginPanel(Container parent) 
	{
		SpringLayout layout = new SpringLayout();
		setLayout( layout );
		
		JLabel username_label = new JLabel("Username:");
		JLabel password_label = new JLabel("Password:");
		
		JTextField username_text = new JTextField("", 10);
		JPasswordField password_text = new JPasswordField(10);
		
		add(username_label);
		add(password_label);
		
		add(username_text);
		add(password_text);
		
		layout.putConstraint(SpringLayout.NORTH, username_text,
                5,
                SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, password_text,
                10,
                SpringLayout.SOUTH, username_text);
		
		layout.putConstraint(SpringLayout.WEST, password_text,
                0,
                SpringLayout.WEST, username_text);
		
		layout.putConstraint(SpringLayout.NORTH, username_label,
                3,
                SpringLayout.NORTH, username_text);
		
		layout.putConstraint(SpringLayout.NORTH, password_label,
                3,
                SpringLayout.NORTH, password_text);
		
		layout.putConstraint(SpringLayout.WEST, username_text,
                5,
                SpringLayout.EAST, username_label);
		
		layout.putConstraint(SpringLayout.WEST, password_text,
                5,
                SpringLayout.EAST, password_label);
		
		
	}

	Container m_parent;
}
