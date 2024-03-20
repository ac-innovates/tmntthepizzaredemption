import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class EnemyBullet {
	// Fields
	private ImageIcon bulletImg;
	private int xPos, yPos, direction;
	private Random rnd; 
	private Rectangle2D bulletMask; 

	// Constructors
	public EnemyBullet () {
		Image newImage = new ImageIcon("images/enemies/shuriken1.gif").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		bulletImg = new ImageIcon(newImage);
		
		//Assigning initial position of the bullet
		xPos = - 100;
		yPos = - 100;

		rnd = new Random();  
		
		direction = rnd.nextInt(4) + 1;
		
		bulletMask = new Rectangle2D.Double(xPos,yPos,bulletImg.getIconWidth(), bulletImg.getIconHeight());
	}

	// Methods

	// Return bullet x position
	public int getX () {
		return xPos;
	}

	// Return bullet y position
	public int getY () {
		return yPos;
	}

	// Set bullet x position
	public void setX (int pos) {
		xPos = pos;
	}

	// Set bullet y position
	public void setY (int pos) {
		yPos = pos;
	}

	// Return image of bullet
	public ImageIcon getImg () {
		return bulletImg;
	}

	//Moving the bullet down 
	public void move () {
		
		if (direction == 1) {
			xPos += 10; 
		}
		else if (direction == 2) {
			xPos -= 10;
		}
		else if (direction == 3) {
			yPos -= 10; 
		}
		else if (direction == 4) {
			yPos += 10; 
		}
	}

	//Returning the width of the bullet
	public int getWidth () {
		return bulletImg.getIconWidth();
	}

	//Returning the height of the bullet
	public int getHeight () {
		return bulletImg.getIconHeight();
	}
	
	//randomly setting the direction the bullets will fire
	public void setBulletDirection () { 
		direction = rnd.nextInt(4)+1; 
	}
	
	//mask of bullet
	public Rectangle2D bulletMask() {
		bulletMask = new Rectangle2D.Double(xPos+20,yPos+20,bulletImg.getIconWidth()-42, bulletImg.getIconHeight()-42);
		
		return bulletMask; 
	}




}
