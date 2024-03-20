import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class PlayerForBoss implements ActionListener {

	// Fields
	private ImageIcon imgMikeyAttackRight, imgMikeyIdleRight, imgMikeyIdleLeft, imgMikeyAttackLeft,
	imgRalphAttackRight, imgRalphAttackLeft, imgRalphIdleRight, imgRalphIdleLeft, 
	imgLeoAttackRight, imgLeoAttackLeft, imgLeoIdleRight, imgLeoIdleLeft,
	imgDonnyAttackRight, imgDonnyAttackLeft, imgDonnyIdleRight, imgDonnyIdleLeft;
	private Rectangle2D playerMask, healthBar, healthBg;
	private int xPos, yPos, turtleChoice, health, speed;
	private boolean isLeft, attacking, blocking, travelling;
	private Timer moveToMapTimer, blockingTimer, blockingCooldownTimer;
	private Boss boss;
	private boolean cooldown;

	// Constructors
	public PlayerForBoss () {


		// Assigning Image Values

		// Mikey's images
		Image newImage1 = new ImageIcon("images/turtle/mikeyStandRight.gif").getImage().getScaledInstance(275, 400, Image.SCALE_DEFAULT);
		imgMikeyIdleRight = new ImageIcon(newImage1);
		Image newImage2 = new ImageIcon("images/turtle/mikeyStandLeft.gif").getImage().getScaledInstance(275, 400, Image.SCALE_DEFAULT);
		imgMikeyIdleLeft = new ImageIcon(newImage2);
		Image newImage3 = new ImageIcon("images/turtle/mikeyAttackRight.gif").getImage().getScaledInstance(428, 400, Image.SCALE_DEFAULT);
		imgMikeyAttackRight = new ImageIcon(newImage3);
		Image newImage4 = new ImageIcon("images/turtle/mikeyAttackLeft.gif").getImage().getScaledInstance(428, 400, Image.SCALE_DEFAULT);
		imgMikeyAttackLeft = new ImageIcon(newImage4);

		// Ralph's images
		Image newImage5 = new ImageIcon("images/turtle/ralphStandRight.gif").getImage().getScaledInstance(260, 400, Image.SCALE_DEFAULT);
		imgRalphIdleRight = new ImageIcon(newImage5);
		Image newImage6 = new ImageIcon("images/turtle/ralphStandLeft.gif").getImage().getScaledInstance(260, 400, Image.SCALE_DEFAULT);
		imgRalphIdleLeft = new ImageIcon(newImage6);
		Image newImage7 = new ImageIcon("images/turtle/ralphAttackRight.gif").getImage().getScaledInstance(531, 400, Image.SCALE_DEFAULT);
		imgRalphAttackRight = new ImageIcon(newImage7);
		Image newImage8 = new ImageIcon("images/turtle/ralphAttackLeft.gif").getImage().getScaledInstance(531, 400, Image.SCALE_DEFAULT);
		imgRalphAttackLeft = new ImageIcon(newImage8	);

		// Leo's images
		Image newImage9 = new ImageIcon("images/turtle/leoStandRight.gif").getImage().getScaledInstance(266, 400, Image.SCALE_DEFAULT);
		imgLeoIdleRight = new ImageIcon(newImage9);
		Image newImage10 = new ImageIcon("images/turtle/leoStandLeft.gif").getImage().getScaledInstance(266, 400, Image.SCALE_DEFAULT);
		imgLeoIdleLeft = new ImageIcon(newImage10);
		Image newImage11= new ImageIcon("images/turtle/leoAttackRight.gif").getImage().getScaledInstance(665, 400, Image.SCALE_DEFAULT);
		imgLeoAttackRight = new ImageIcon(newImage11);
		Image newImage12 = new ImageIcon("images/turtle/leoAttackLeft.gif").getImage().getScaledInstance(665, 400, Image.SCALE_DEFAULT);
		imgLeoAttackLeft = new ImageIcon(newImage12);		

		// Donny's images
		Image newImage13 = new ImageIcon("images/turtle/donnyStandRight.gif").getImage().getScaledInstance(270, 460, Image.SCALE_DEFAULT);
		imgDonnyIdleRight = new ImageIcon(newImage13);
		Image newImage14 = new ImageIcon("images/turtle/donnyStandLeft.gif").getImage().getScaledInstance(270, 460, Image.SCALE_DEFAULT);
		imgDonnyIdleLeft = new ImageIcon(newImage14);
		Image newImage15= new ImageIcon("images/turtle/donnyAttackRight.gif").getImage().getScaledInstance(360, 380, Image.SCALE_DEFAULT);
		imgDonnyAttackRight = new ImageIcon(newImage15);
		Image newImage16 = new ImageIcon("images/turtle/donnyAttackLeft.gif").getImage().getScaledInstance(360, 380, Image.SCALE_DEFAULT);
		imgDonnyAttackLeft = new ImageIcon(newImage16);

		// Setting default values to other variables
		moveToMapTimer = new Timer (25, this);
		blockingTimer = new Timer (5000, this);
		blockingCooldownTimer = new Timer (1000, this);
		boss = new Boss();
		health = 400;
		xPos = -200;

		yPos = 240;
		speed = 10;

		playerMask = new Rectangle2D.Double(xPos, yPos, getImg().getIconWidth(), getImg().getIconHeight());
		healthBar = new Rectangle2D.Double(10,10,health,20); 
		healthBg = new Rectangle2D.Double(8,8,410,25);




	}

	// Methods
	public void setTurtleChoice(int choice) {
		turtleChoice = choice;
	}

	public int getX () {
		return xPos;
	}

	public int getY () {
		return yPos;
	}

	public void setX(int x) {
		xPos = x;
	}

	public void setY(int y) {
		yPos = y;
	}

	public void updateHealth(int damage) {
		health -= damage; 
		healthBar = new Rectangle2D.Double(10,10,health,20); 
	}

	public void setHealth(int hp) {
		health = hp; 
	}

	public int getHealth() {
		return health;
	}

	public Rectangle2D getHealthBar () {
		return healthBar;
	}

	public Rectangle2D healthBg() {
		return healthBg;
	}

	public void goToBoss () {
		setX(-200);
		if (turtleChoice == 1)
			setY(180);
		else
			setY(240);
		travelling = true;
		moveToMapTimer.start();
	}

	public void move (boolean goLeft, boolean goRight, boolean attack, boolean block) {
		if (travelling == true) {
			goLeft = false;
			goRight = false;
		}

		if (goLeft == true && blocking == false && attacking == false) {
			isLeft = true;
			if (xPos > 0)
				xPos -= speed; 
		}
		if (goRight == true && xPos + getImg().getIconWidth() <= 1200
				&& blocking == false && attacking == false) {
			isLeft = false;
			if (xPos + 5 <= 1200 - getImg().getIconWidth())
				xPos += speed; 
		}

		if (attack == true) {
			attacking = true;
			if (turtleChoice == 1)
				setY(220);
		}
		else {
			attacking = false;
			if (turtleChoice == 1) {
				setY(180);
			}
		}

		if (block == true && cooldown == false) {
			blocking = true;
			blockingTimer.start();
		}
		else
			blocking = false;

	}

	public ImageIcon getImg () {

		// Returning image for Donny
		if (turtleChoice == 1) {
			// Returning left images if player is left
			if (isLeft == true) {
				if (attacking == true) 
					return imgDonnyAttackLeft;
				else
					return imgDonnyIdleLeft;
			}
			// Returning right images if player is right
			else {
				if (attacking == true)
					return imgDonnyAttackRight;
				else
					return imgDonnyIdleRight;
			}
		}

		// Returning image for Leo
		else if(turtleChoice == 2) {
			// Returning left images if player is left
			if (isLeft == true) {
				if (attacking == true)
					return imgLeoAttackLeft;
				else
					return imgLeoIdleLeft;
			}
			// Returning right images if player is right
			else {
				if (attacking == true)
					return imgLeoAttackRight;
				else
					return imgLeoIdleRight;
			}
		}

		// Returning image for Ralph
		else if (turtleChoice == 3) {
			// Returning left images if player is left
			if (isLeft == true) {
				if (attacking == true)
					return imgRalphAttackLeft;
				else
					return imgRalphIdleLeft;
			}
			// Returning right images if player is right
			else {
				if (attacking == true)
					return imgRalphAttackRight;
				else
					return imgRalphIdleRight;
			}
		}

		// Returning image for Mikey
		else {
			// Returning left images if player is left
			if (isLeft == true) {
				if (attacking == true)
					return imgMikeyAttackLeft;
				else
					return imgMikeyIdleLeft;
			}
			// Returning right images if player is right
			else {
				if (attacking == true)
					return imgMikeyAttackRight;
				else
					return imgMikeyIdleRight;
			}
		}

	}

	public void updateBossMask () {
		playerMask = new Rectangle2D.Double(xPos, yPos, getImg().getIconWidth(), getImg().getIconHeight());
	}

	public Rectangle2D getMask () {
		return playerMask;
	}

	public boolean isAttacking () {
		return attacking;
	}

	public boolean isBlocking () {
		return blocking;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == moveToMapTimer) {
			xPos += speed;
			if (xPos == 100) {
				moveToMapTimer.stop();
				travelling = false;
			}
		}

		// Player hits cooldown and cannot block for 2 seconds
		if (e.getSource() == blockingTimer) {
			cooldown = true;
			blocking = false;
			blockingCooldownTimer.start();
			blockingTimer.stop();
		}

		if (e.getSource() == blockingCooldownTimer) {
			cooldown = false;
			blockingCooldownTimer.stop();
		}

	}


}
