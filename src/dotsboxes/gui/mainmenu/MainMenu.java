package dotsboxes.gui.mainmenu;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JPanel;

public class MainMenu extends JPanel {
	public MainMenu(Container parent)
	{
		m_parent = parent;
		
		setLayout(new BorderLayout());
		add( m_menuPanel, BorderLayout.CENTER);
		add( m_loginPanel, BorderLayout.LINE_END);
	}
	
	Container m_parent;
	
	MenuPanel m_menuPanel = new MenuPanel(this);
	LoginPanel m_loginPanel = new LoginPanel(this);
}
