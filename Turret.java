import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Turret implements ActionListener {

	// Fields
	public ImageIcon turretLeft, turretRight;
	public int xPos, yPos, bulletXPos, bulletYPos, bulletSpeed, dirX, dirY, bgWidth, bgHeight, bulletWidth, 
	bulletHeight, health;
	public Rectangle2D turretMask, bulletUnder, bulletOver, healthBg, healthBar;
	public Random rnd;
	public Timer bulletTimer, bulletFireTimer;
	public Ellipse2D turretRadius;
	public boolean isFired;
	public Player player;
	public final int left = 1, right = 2, up = 3, down = 4;

	// Constructors
	public Turret () {

		xPos = -300;
		yPos = -100;
		bulletXPos = -100;
		bulletYPos = -100;
		rnd = new Random();
		bulletTimer = new Timer (100, this);
		bulletFireTimer = new Timer (3000, this);
		bgWidth = 800;
		bgHeight = 427;

		Image newImage1 = new ImageIcon("images/enemies/turretLeft.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		turretLeft = new ImageIcon (newImage1);
		Image newImage2 = new ImageIcon("images/enemies/turretRight.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		turretRight = new ImageIcon (newImage2);

		turretMask = new Rectangle2D.Double(xPos, yPos, turretLeft.getIconWidth(), turretLeft.getIconHeight());

		bulletSpeed = 15;
		health = 80;

		//health bar drawn
		healthBg = new Rectangle2D.Double(xPos, yPos + turretLeft.getIconHeight(), 80, 14);
		healthBar = new Rectangle2D.Double(xPos+2, yPos + turretLeft.getIconHeight()+3, 80, 8);
		
		bulletUnder = new Rectangle2D.Double(bulletXPos, bulletYPos, 10, 10);
		bulletOver = new Rectangle2D.Double(bulletXPos + 2, bulletYPos + 2, 6, 6);
		turretRadius = new Ellipse2D.Double(getX() - 200, getY() - 200, 80 + 400, 80 + 400);


	}

	// Methods

	// Returns img of turret left
	public Ellipse2D getRadius () {
		turretRadius = new Ellipse2D.Double(getX() - 200, getY() - 200, 80 + 400, 80 + 400);
		return turretRadius;
	}

	public ImageIcon getImgLeft () {
		turretMask = new Rectangle2D.Double(xPos, yPos, turretLeft.getIconWidth(), turretLeft.getIconHeight());
		return turretLeft;
	}

	// Returns img of turret right
	public ImageIcon getImgRight () {
		turretMask = new Rectangle2D.Double(xPos, yPos, turretLeft.getIconWidth(), turretLeft.getIconHeight());
		return turretRight;
	}

	//drawing the health bar
	public Rectangle2D health()  {
		healthBar = new Rectangle2D.Double(xPos+2, yPos + turretLeft.getIconHeight()+3, health, 8);
		return healthBar; 
	}

	//drawing the background of the health bar
	public Rectangle2D healthBg() {
		healthBg = new Rectangle2D.Double(xPos, yPos + turretLeft.getIconHeight(), 80, 14);
		return healthBg;
	}

	// Return xPos of turret
	public int getX () {
		return xPos;
	}

	// Return yPos of turret
	public int getY () {
		return yPos;
	}

	// Update xPos of turret
	public void setX (int x) {
		xPos = x;
	}

	// Update yPos of turret
	public void setY (int y) {
		yPos = y;
	}

	// return mask of turret
	public Rectangle2D getMask () {
		return turretMask;
	}

	//updating the health when the turret gets attacked
	public void updateHealth(int damage) {
		health -= damage; 
	}

	// Return value of health
	public int getHealth () {
		return health;
	}

	// Return x position of bullet
	public int getBulletX () {
		return bulletXPos;
	}

	// return y position of bullet
	public int getBulletY () {
		return bulletYPos;
	}

	// Stopping bullet from moving
	public void stopBullet () {
		bulletTimer.stop();
		bulletXPos = - 150;
	}

	public void startTimer () {
		bulletTimer.start();

	}

	public void stopTimer() {
		bulletTimer.stop();
	}

	// Update xPos of the bullet
	public void updateBulletX (int pos) {
		bulletXPos = pos;
	}

	// Update yPos of the bullet
	public void updateBulletY (int pos) {
		bulletYPos = pos;
	}

	//moving the bullet in the x 
	public void moveBulletX () {
		if (dirX == left)
			bulletXPos -= bulletSpeed;
		if (dirX == right)
			bulletXPos += bulletSpeed;
	}
	
	//moving the bullet in the y
	public void moveBulletY () {
		if (dirY == up)
			bulletYPos -= bulletSpeed;
		if (dirY == down)
			bulletYPos += bulletSpeed;
	}
	
	//setting the direction in the x
	public void setDirX(int dir) {
		dirX = dir;
	}

	//setting the direction in the y
	public void setDirY(int dir) {
		dirY = dir;
	}

	public Rectangle2D getBulletUnder () {
		// Returning the rectangle of the bullet
		return bulletUnder;
	}
	
	//updating the bullet's mask and radius
	public void updateBullet () {
		turretRadius = new Ellipse2D.Double(getX() - 200, getY() - 200, 80 + 400, 80 + 400);
		bulletUnder = new Rectangle2D.Double(bulletXPos, bulletYPos, 30, 30);
		bulletOver = new Rectangle2D.Double(bulletXPos + 5, bulletYPos + 5, 20, 20);
	}

	public Rectangle2D getBulletOver () {
		// Returning the rectangle of the bullet
		return bulletOver;

	}
	
	//turret radius
	public Ellipse2D enemyBounds() {	
		return turretRadius;
	} 	


	@Override
	public void actionPerformed(ActionEvent e) {
			
		//timer for bullets fired
			if (e.getSource() == bulletTimer) {
				if (isFired == false) {
					//resetting location of bullet to turret's location
					updateBulletX(xPos);
					updateBulletY(yPos);
					isFired = true; 
				}	
				//moving bullet if it has been fired
				else if (isFired == true){
					updateBullet();
					moveBulletX(); 
					moveBulletY();
				}

			}
			
			//resetting once bullet is off screen
			if (getBulletX() < 0 || getBulletX() + bulletWidth >= bgWidth || getBulletY() < 0 || getBulletY() + bulletHeight >= bgHeight) {   
				bulletFireTimer.start();		
			}
			
			//timer for firing the bullet
			if (e.getSource() == bulletFireTimer) {
				isFired = false;
				bulletFireTimer.stop();
			}

		}
	

}



