package dotsboxes;
import java.awt.EventQueue;

import javax.swing.JFrame;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.factories.FormFactory;

import dotsboxes.events.EventType;
import dotsboxes.events.GameTurnEvent;
import dotsboxes.game.TurnDesc;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Event;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import java.awt.event.ActionListener;

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


public class GUI {

	JFrame frame;
	/**
	 * @wbp.nonvisual location=276,-21
	 */
	//private final JPanel panel = new JPanel();
	//private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	int m_currentPane;
	Panel Menu = new Panel();
	Panel Field = new Panel();
	Panel Config = new Panel();
	
	public void ShowMenu()
	{
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
		Field.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 756, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Menu.setBounds(178, 77, 309, 383);
		frame.getContentPane().add(Menu);
		Menu.setLayout(new GridLayout(0, 1, 0, 0));
		
		Button StartGame = new Button("Lets start game!");
		StartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewAnonimEvent( new dotsboxes.events.Event(EventType.GUI_to_the_Game));
			}
		});
		Menu.add(StartGame);
		
		Button button = new Button("Exit");
		Menu.add(button);
		
		
		Field.setBounds(10, 10, 661, 462);
		frame.getContentPane().add(Field);
		Field.setLayout(null);
		
		Button button_1 = new Button("Back to menu.");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EventManager.NewAnonimEvent( new dotsboxes.events.Event(EventType.GUI_back_to_Menu));
			}
		});
		button_1.setBounds(547, 10, 104, 23);
		Field.add(button_1);
		
		
		Config.setBounds(0, 0, 10, 10);
		frame.getContentPane().add(Config);
		
		m_currentPane = 1;
	}
}
