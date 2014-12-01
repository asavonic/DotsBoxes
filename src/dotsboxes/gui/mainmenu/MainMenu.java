package dotsboxes.gui.mainmenu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
	public MainMenu(Container parent)
	{
		m_parent = parent;
		
		setLayout(new BorderLayout());
		m_loginPanel.setPreferredSize(new Dimension(220, 100));
		add( m_loginPanel, BorderLayout.LINE_END);
		
		m_menuPanel.setPreferredSize(new Dimension(200, 100));
		add( m_menuPanel, BorderLayout.CENTER);
		
		
	}
	
	Container m_parent;
	
	MenuPanel m_menuPanel = new MenuPanel(this);
	LoginPanel m_loginPanel = new LoginPanel(this);
}
