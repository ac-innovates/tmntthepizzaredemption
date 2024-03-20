import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Boss implements ActionListener {

	// Fields
	private ImageIcon imgLeftIdle, imgRightIdle, imgLeftInvul, imgRightInvul, imgLeftAtk, imgRightAtk, 
	background, daggerLeft, daggerRight;
	private int actionMove, actionAttack;
	private Random rnd;
	private boolean isLeft, atk, invulnerable, drawInvul, timerRunning, attacking; 
	private int xPos, yPos, bulletXPos, bulletYPos, speed, dirBullet, health, healthTwo;
	private Timer actionTimer, moveTimer, invulTimer, daggerTimer, attackTimer;
	private Rectangle2D daggerMask, bossMask, bossAttackMask, healthBar, healthBg, healthBarTwo, healthBgTwo;

	// Constructors
	public Boss () {

		// Assign default values to fields
		rnd = new Random();
		actionTimer = new Timer (1900, this);
		daggerTimer = new Timer (50, this);
		moveTimer = new Timer(500, this);
		invulTimer = new Timer (4000, this);
		attackTimer = new Timer (100, this);

		gameStart();

		// Assign values to images

		Image newImage = new ImageIcon("images/backgrounds/bossLevel.jpg").getImage().getScaledInstance(1200, 700, Image.SCALE_DEFAULT);
		background = new ImageIcon(newImage);	

		Image newImage2 = new ImageIcon("images/boss/daggerLeft.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		daggerLeft = new ImageIcon(newImage2);	

		Image newImage3 = new ImageIcon("images/boss/daggerRight.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
		daggerRight = new ImageIcon(newImage3);	

		Image newImage4 = new ImageIcon("images/boss/shredderStandRight.gif").getImage().getScaledInstance(248, 480, Image.SCALE_DEFAULT);
		imgRightIdle = new ImageIcon(newImage4);	

		Image newImage5 = new ImageIcon("images/boss/shredderStandLeft.gif").getImage().getScaledInstance(248, 480, Image.SCALE_DEFAULT);
		imgLeftIdle = new ImageIcon(newImage5);	

		Image newImage6 = new ImageIcon("images/boss/shredderPunchLeft.gif").getImage().getScaledInstance(585, 480, Image.SCALE_DEFAULT);
		imgLeftAtk = new ImageIcon(newImage6);	

		Image newImage7 = new ImageIcon("images/boss/shredderPunchRight.gif").getImage().getScaledInstance(585, 480, Image.SCALE_DEFAULT);
		imgRightAtk = new ImageIcon(newImage7);	

		Image newImage8 = new ImageIcon("images/boss/shredderInvulnerableLeft.gif").getImage().getScaledInstance(224, 580, Image.SCALE_DEFAULT);
		imgLeftInvul = new ImageIcon(newImage8);

		Image newImage9 = new ImageIcon("images/boss/shredderInvulnerableRight.gif").getImage().getScaledInstance(207, 530, Image.SCALE_DEFAULT);
		imgRightInvul = new ImageIcon(newImage9);	

		updateMask();

	}

	// Methods

	// Moves the boss left or right randomly
	public void randomMove () {
		moveTimer.start();
		// Only running if a timer is not running
		if (timerRunning != true) {
			// If actionMove is 0 move left
			if (actionMove == 0 && xPos >= 300) {
				xPos -= speed;
				isLeft = true;
			}
			else 
				actionMove = 1;

			// If actionMove is 1 move right
			if (actionMove == 1 && xPos <= 800) {
				xPos += speed;
				isLeft = false;
			}
			else
				actionMove = 0;
		}
	}

	// Makes the boss do a random action
	public void randomAction () {

		// Randomizing action
		actionAttack = rnd.nextInt(10);

		// Throwing knife
		if (actionAttack == 0 || actionAttack == 1 || actionAttack == 2) {
			if (bulletXPos <= -50 || bulletXPos >= 1250) {
				updateBulletY(yPos + 80);
				updateBulletX(xPos);
				dirBullet = -1;
				daggerTimer.start();
			}
		}

		// Punching
		else if (actionAttack == 3 || actionAttack == 4 || actionAttack == 5 || actionAttack == 6 || actionAttack == 7 || actionAttack  == 8) {
			if (isLeft == true)
				xPos -= 310;
			atk = true;
			actionTimer.start();
			timerRunning = true;
		}

		// Activating invul
		else if (invulnerable == false && actionAttack == 9) {
			yPos -= 130;
			invulnerable = true;
			drawInvul = true;
			actionTimer.start();
			timerRunning = true;
			invulTimer.start();

		}

	}

	// Return image of boss
	public ImageIcon getImg () {

		// Returning left images
		if (isLeft == true) {
			if (atk == true)
				return imgLeftAtk;
			if (drawInvul == true)
				return imgLeftInvul;
			else
				return imgLeftIdle;
		}

		// Returning right images
		else if (isLeft == false) {
			if (atk == true)
				return imgRightAtk;
			if (drawInvul == true)
				return imgRightInvul;
			else
				return imgRightIdle;
		}
		else
			return imgRightIdle;

	}

	// Return background image
	public ImageIcon getBG () {
		return background;
	}

	// Return x position of boss
	public int getX () {
		return xPos;
	}

	// return y position of boss
	public int getY () {
		return yPos;
	}

	// Methods for bullet

	// Return x position of boss
	public int getBulletX () {
		return bulletXPos;
	}

	// return y position of boss
	public int getBulletY () {
		return bulletYPos;
	}

	// Force the boss do run every action once
	public void stopDagger () {
		daggerTimer.stop();
		bulletXPos = - 150;

	}

	public void updateBulletX (int pos) {
		bulletXPos = pos;
	}

	public void updateBulletY (int pos) {
		bulletYPos = pos;
	}

	public ImageIcon getImgBullet () {

		// Assigning the direction
		if (dirBullet == -1) {
			if (isLeft == true)
				dirBullet = 0;
			else
				dirBullet = 1;
		}

		// Returning left image if DIR == left, returning right image otherwise
		if (dirBullet == 0)
			return daggerLeft;
		else 
			return daggerRight; 

	}


	public void moveBulletX (int pixels) {
		if (dirBullet == 0)
			bulletXPos -= pixels;
		else
			bulletXPos += pixels;
	}

	public void updateMask () {
		daggerMask = new Rectangle2D.Double(bulletXPos, bulletYPos, daggerLeft.getIconWidth(), daggerLeft.getIconHeight());

		if (isLeft == false)
			bossAttackMask = new Rectangle2D.Double(xPos + 100, yPos, imgLeftAtk.getIconWidth(), imgLeftAtk.getIconHeight());
		else
			bossAttackMask = new Rectangle2D.Double(xPos, yPos, imgLeftAtk.getIconWidth() - 100, imgLeftAtk.getIconHeight());

		if (drawInvul == true)
			bossMask = new Rectangle2D.Double(xPos, yPos, imgLeftAtk.getIconWidth()/2, imgLeftInvul.getIconHeight());
		else
			bossMask = new Rectangle2D.Double(xPos, yPos, imgLeftIdle.getIconWidth(), imgLeftIdle.getIconHeight());

	}

	public Rectangle2D getMask () {	
		return bossMask;		
	}

	public Rectangle2D getAtkMask () {
		return bossAttackMask;
	}

	public Rectangle2D getDaggerMask () {
		return daggerMask;
	}

	public boolean isInvul () {
		return invulnerable;
	}

	public boolean isAttacking () {
		return attacking;
	}

	public boolean activatingInvul () {
		return drawInvul;
	}

	public void changeDIR () {
		if (actionMove == 0)
			actionMove = 1;
		else if (actionMove == 1)
			actionMove = 0;
	}

	public void updateHealth(int damage) {
		if (healthTwo > 0) {
			healthTwo -= damage;
		}
		else
			health -= damage; 

		healthBar = new Rectangle2D.Double(700,10,health,20); 
		healthBarTwo = new Rectangle2D.Double(700, 50, healthTwo, 20);
	}

	public void setHealth(int hp) {
		health = hp; 
		healthTwo = hp;
	}

	public int getHealth() {
		return health;
	}

	public Rectangle2D getHealthOne()  {
		return healthBar; 
	}

	public Rectangle2D getHealthTwo () {
		return healthBarTwo;
	}

	public Rectangle2D healthBg() {
		return healthBg;
	}

	public Rectangle2D healthBgTwo () {
		return healthBgTwo;
	}

	public void actionPerformed(ActionEvent e) {

		// Timer for attack and invulnerability
		if (e.getSource() == actionTimer) {

			if (atk == true) {
				if (isLeft == true)
					xPos += 330;
				atk = false;
				attacking = true;
				timerRunning = false;
				actionTimer.stop();
				attackTimer.start();
			}
			if (invulnerable == true) {
				yPos += 130;
				timerRunning = false;
				drawInvul = false;
				actionTimer.stop();
			}
		}

		// Randomizing moving left or right
		if (e.getSource() == moveTimer) {
			actionMove = rnd.nextInt(2);
		}

		// turning off invulnerability
		if (e.getSource() == invulTimer) {
			invulnerable = false;
			invulTimer.stop();
		}

		// Turning off attacking
		if (e.getSource() == attackTimer) {
			attacking = false;
			attackTimer.stop();
		}

		// Moving dagger
		if (e.getSource() == daggerTimer) {
			moveBulletX(15);
		}

	}

	public void autoMove() {
		xPos -= 10;
	}

	public void gameStart() {
		isLeft = true;
		xPos = 1210;
		yPos = 220;
		bulletXPos = -100;
		bulletYPos = 1300;
		timerRunning = false;
		atk = false;
		invulnerable = false;

		speed = 10;
		actionAttack = 2;
		health = 400;
		healthTwo = 400;

		healthBar = new Rectangle2D.Double(700,10,400,20); 
		healthBg = new Rectangle2D.Double(700,8,400,25);

		healthBarTwo = new Rectangle2D.Double(700, 50, healthTwo, 20);
		healthBgTwo = new Rectangle2D.Double(700,50,400,25);
	}


}
