import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class Pizza {

	// Fields
	public ImageIcon pizza;
	public int xPos, yPos, width, height;
	public Rectangle2D pizzaMask;
	public Random rnd;
	
	// Constructors
	public Pizza () {
		
		width = 658;
		height = 376;
		xPos = -100;
		yPos = -100;
		rnd = new Random();
		
		Image newImage1 = new ImageIcon("images/level/pizza.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		pizza = new ImageIcon (newImage1);
		pizzaMask = new Rectangle2D.Double(xPos, yPos, pizza.getIconWidth(), pizza.getIconHeight());
		
	}
	
	// Methods
	
	// Returns img of pizza
	public ImageIcon getImg () {
		pizzaMask = new Rectangle2D.Double(xPos, yPos, pizza.getIconWidth(), pizza.getIconHeight());
		return pizza;
	}
	
	// Return xPos of pizza
	public int getX () {
		return xPos;
	}
	
	// Return yPos of pizza
	public int getY () {
		return yPos;
	}
	
	// Update xPos of pizza
	public void setX (int x) {
		xPos = x;
	}
	
	// Update yPos of pizza
	public void setY (int y) {
		yPos = y;
	}
	
	// return mask of pizza
	public Rectangle2D getMask () {
		return pizzaMask;
	}
	
	//randomly spawning pizza at different locations
	public void randomSpawn () {
		xPos = rnd.nextInt(width - pizza.getIconWidth());
		yPos = rnd.nextInt(356 - pizza.getIconHeight()) + 20;
		pizzaMask = new Rectangle2D.Double(xPos, yPos, pizza.getIconWidth(), pizza.getIconHeight());
	}
	
}
