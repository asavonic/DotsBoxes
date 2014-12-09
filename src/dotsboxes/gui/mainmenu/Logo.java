package dotsboxes.gui.mainmenu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Container;

import dotsboxes.utils.Debug;

public class Logo extends JPanel 
{
	
	 private BufferedImage image;
	 
	 
	Logo(Container parent)
	{
		 try 
		 {                
	          image = ImageIO.read(new File("Eagle.bmp"));
	     } catch (IOException ex) {
	            Debug.log("Eagle not found.");
	     }
	}
	
	protected void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}
}
