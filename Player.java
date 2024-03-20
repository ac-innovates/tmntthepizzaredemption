import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player {

	// Fields
	private int xPos, yPos;
	private int WIDTH, HEIGHT, speed, health, turtleChoice, upBnd, downBnd, leftBnd, rightBnd;
	private ImageIcon imgDonnyRightMap, imgRalphRightMap, imgMikeyRightMap, imgLeoRightMap, 
	imgDonnyLeftMap, imgRalphLeftMap, imgMikeyLeftMap, imgLeoLeftMap, 
	imgDonnyEmote, imgRalphEmoteLeft, imgRalphEmoteRight, imgMikeyEmoteLeft, imgMikeyEmoteRight,
	imgLeoEmote, imgMikeyAttackRight, imgMikeyIdleRight, imgMikeyIdleLeft, imgMikeyAttackLeft,
	imgRalphAttackRight, imgRalphAttackLeft, imgRalphIdleRight, imgRalphIdleLeft,
	imgDonnyAttackRight, imgDonnyAttackLeft, imgDonnyIdleRight, imgDonnyIdleLeft,
	imgLeoAttackRight, imgLeoAttackLeft, imgLeoIdleRight, imgLeoIdleLeft;
	private boolean isLeft, attacking, blocking;
	private Timer moveToMapTimer;
	private Rectangle2D healthBar, healthBg, playerMask, nullMask;

	// Constructor
	public Player (int width, int height) {

		// Initialize Images
		Image newImage1 = new ImageIcon("images/turtle/mikeyStandRight.gif").getImage().getScaledInstance(69, 100, Image.SCALE_DEFAULT);
		imgMikeyIdleRight = new ImageIcon(newImage1);
		Image newImage2 = new ImageIcon("images/turtle/mikeyStandLeft.gif").getImage().getScaledInstance(69, 100, Image.SCALE_DEFAULT);
		imgMikeyIdleLeft = new ImageIcon(newImage2);
		Image newImage3 = new ImageIcon("images/turtle/mikeyAttackRight.gif").getImage().getScaledInstance(107, 100, Image.SCALE_DEFAULT);
		imgMikeyAttackRight = new ImageIcon(newImage3);
		Image newImage4 = new ImageIcon("images/turtle/mikeyAttackLeft.gif").getImage().getScaledInstance(107, 100, Image.SCALE_DEFAULT);
		imgMikeyAttackLeft = new ImageIcon(newImage4);

		// Ralph's images
		Image newImage5 = new ImageIcon("images/turtle/ralphStandRight.gif").getImage().getScaledInstance(69, 100, Image.SCALE_DEFAULT);
		imgRalphIdleRight = new ImageIcon(newImage5);
		Image newImage6 = new ImageIcon("images/turtle/ralphStandLeft.gif").getImage().getScaledInstance(69, 100, Image.SCALE_DEFAULT);
		imgRalphIdleLeft = new ImageIcon(newImage6);
		Image newImage7 = new ImageIcon("images/turtle/ralphAttackRight.gif").getImage().getScaledInstance(134, 100, Image.SCALE_DEFAULT);
		imgRalphAttackRight = new ImageIcon(newImage7);
		Image newImage8 = new ImageIcon("images/turtle/ralphAttackLeft.gif").getImage().getScaledInstance(134, 100, Image.SCALE_DEFAULT);
		imgRalphAttackLeft = new ImageIcon(newImage8	);

		// Leo's images
		Image newImage9 = new ImageIcon("images/turtle/leoStandRight.gif").getImage().getScaledInstance(67, 100, Image.SCALE_DEFAULT);
		imgLeoIdleRight = new ImageIcon(newImage9);
		Image newImage10 = new ImageIcon("images/turtle/leoStandLeft.gif").getImage().getScaledInstance(67, 100, Image.SCALE_DEFAULT);
		imgLeoIdleLeft = new ImageIcon(newImage10);
		Image newImage11= new ImageIcon("images/turtle/leoAttackRight.gif").getImage().getScaledInstance(166, 100, Image.SCALE_DEFAULT);
		imgLeoAttackRight = new ImageIcon(newImage11);
		Image newImage12 = new ImageIcon("images/turtle/leoAttackLeft.gif").getImage().getScaledInstance(166, 100, Image.SCALE_DEFAULT);
		imgLeoAttackLeft = new ImageIcon(newImage12);	

		// Donny's images
		Image newImage13 = new ImageIcon("images/turtle/donnyStandRight.gif").getImage().getScaledInstance(68, 120, Image.SCALE_DEFAULT);
		imgDonnyIdleRight = new ImageIcon(newImage13);
		Image newImage14 = new ImageIcon("images/turtle/donnyStandLeft.gif").getImage().getScaledInstance(68, 120, Image.SCALE_DEFAULT);
		imgDonnyIdleLeft = new ImageIcon(newImage14);
		Image newImage15= new ImageIcon("images/turtle/donnyAttackRight.gif").getImage().getScaledInstance(90, 95, Image.SCALE_DEFAULT);
		imgDonnyAttackRight = new ImageIcon(newImage15);
		Image newImage16 = new ImageIcon("images/turtle/donnyAttackLeft.gif").getImage().getScaledInstance(90, 95, Image.SCALE_DEFAULT);
		imgDonnyAttackLeft = new ImageIcon(newImage16);

		//player map right images
		Image newImage17 = new ImageIcon("images/turtle/donnyRight.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgDonnyRightMap = new ImageIcon(newImage17);	
		Image newImage18 = new ImageIcon("images/turtle/ralphRight.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgRalphRightMap = new ImageIcon(newImage18);	
		Image newImage19 = new ImageIcon("images/turtle/leoRight.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgLeoRightMap = new ImageIcon(newImage19);	
		Image newImage20 = new ImageIcon("images/turtle/mikeyRight.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgMikeyRightMap = new ImageIcon(newImage20);	

		//player map left images
		Image newImage21 = new ImageIcon("images/turtle/donnyLeft.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgDonnyLeftMap = new ImageIcon(newImage21);	
		Image newImage22 = new ImageIcon("images/turtle/ralphLeft.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgRalphLeftMap = new ImageIcon(newImage22);	
		Image newImage23 = new ImageIcon("images/turtle/leoLeft.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgLeoLeftMap = new ImageIcon(newImage23);	
		Image newImage24 = new ImageIcon("images/turtle/mikeyLeft.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgMikeyLeftMap = new ImageIcon(newImage24);	


		// Initialize Image Emote
		Image newImage25 = new ImageIcon("images/turtle/donnyEmote.gif").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		imgDonnyEmote = new ImageIcon(newImage25);	
		Image newImage26 = new ImageIcon("images/turtle/ralphEmoteLeft.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgRalphEmoteLeft = new ImageIcon(newImage26);
		Image newImage27 = new ImageIcon("images/turtle/ralphEmoteRight.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgRalphEmoteRight = new ImageIcon(newImage27);
		Image newImage28 = new ImageIcon("images/turtle/leoEmote.gif").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
		imgLeoEmote = new ImageIcon(newImage28);
		Image newImage29 = new ImageIcon("images/turtle/mikeyEmoteLeft.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgMikeyEmoteLeft = new ImageIcon(newImage29);
		Image newImage30 = new ImageIcon("images/turtle/mikeyEmoteRight.gif").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		imgMikeyEmoteRight = new ImageIcon(newImage30);


		// Initialize other variables
		WIDTH = width;
		HEIGHT = height;

		xPos = 220;
		yPos = 220;

		speed = 15;
		health = 100;
		healthBar = new Rectangle2D.Double(10,10,100,20); 
		healthBg = new Rectangle2D.Double(8,8,105,25);
		isLeft = false;
		
		playerMask = new Rectangle2D.Double(0,0,0,0);
		nullMask = new Rectangle2D.Double(0,0,0,0);
		
		turtleChoice = -1;
		
	}

	// Methods
	
	//setting turtle image based on choice of user
	public void setTurtleChoice(int choice) {
		turtleChoice = choice;
	}

	//returning x position of player
	public int getX () {
		return xPos;
	}
	
	//returning y position of player
	public int getY () {
		return yPos;
	}
	
	//setting x position of player
	public void setX(int x) {
		xPos = x;
	}
	
	//setting y position of player
	public void setY(int y) {
		yPos = y;
	}
	
	//returning image based on if player is going left or right on map
	public ImageIcon getImg (boolean left) {

		// Returning left facing turtles
		if (left == true) {
			if (turtleChoice == 1)
				return imgDonnyLeftMap;
			else if (turtleChoice == 2)
				return imgLeoLeftMap;
			else if (turtleChoice == 3)
				return imgRalphLeftMap;
			else
				return imgMikeyLeftMap;
		}

		// Returning right facing turtles
		else  {
			if (turtleChoice == 1)
				return imgDonnyRightMap;
			else if (turtleChoice == 2)
				return imgLeoRightMap;
			else if (turtleChoice == 3)
				return imgRalphRightMap;
			else
				return imgMikeyRightMap;
		}
		
	}
	
	//returning image based on character, direction, and attack
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
				if (attacking == true) {
					return imgLeoAttackLeft; 
				}
				else {
					return imgLeoIdleLeft;
				}
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
	
	//returning emoting image
	public ImageIcon getImgEmote (boolean left) {

		if (turtleChoice == 1)
			return imgDonnyEmote;
		else if (turtleChoice == 2)
			return imgLeoEmote;
		else if (turtleChoice == 3) {
			if (left == true)
				return imgRalphEmoteLeft;
			else
				return imgRalphEmoteRight;
		}
		else {
			if (left == true)
				return imgMikeyEmoteLeft; 
			else 
				return imgMikeyEmoteRight;
		}

	}
	
	//moving player
	public void move (boolean goLeft, boolean goRight, boolean goUp, boolean goDown, int pixels, boolean attack, int level) {
		//setting boundaries for player movement based on each level
		if (level == 1) {
			upBnd = 180; 
			leftBnd = 0; 
			rightBnd = 600; 
			downBnd = 260; 
		}
		
		if (level == 2) {
			upBnd = 250;
			downBnd = 335; 
			leftBnd = 0;
			rightBnd = 692;
		}
		
		else if (level == 3) {
			upBnd = 20;
			leftBnd = 0;
			rightBnd = 788 - getImg().getIconWidth();
			downBnd = 450 - getImg().getIconHeight();
		}
		
		else if (level == 4) {
			upBnd = 50;
			leftBnd = 100;
			rightBnd = 710;
			downBnd = 700 - 342;
		}
		
		//moving based on keys that are pressed and boundary checking
		if (goLeft == true) {
			isLeft = true;
			if (xPos > leftBnd)
				xPos -= pixels; 
		}
		if (goRight == true) {
			isLeft = false;
			if (xPos + 5 <= rightBnd - imgMikeyRightMap.getIconWidth())
				xPos += pixels; 
		}
		if (goUp == true) {
			if (yPos > upBnd)
				yPos -= pixels; 
		}
		if (goDown == true) {
			if (yPos <= downBnd - imgMikeyRightMap.getIconHeight() + 50)
				yPos += pixels; 
		}
		
		if (attack == true) {
			attacking = true;
		}
		else {
			attacking = false;
		}
		
		//updating the location of the player's mask
		updatePlayerMask(); 


	}
	
	//updating health based on damage taken
	public void updateHealth(int damage) {
		health -= damage; 
	}
	
	//setting player health
	public void setHealth(int hp) {
		health = hp; 
	}
	
	//returning value of player health
	public int playerHealth() {
		return health;
	}
	
	//returning player health bar
	public Rectangle2D health()  {
		healthBar = new Rectangle2D.Double(10,10,health,20); 
		return healthBar; 
	}
	
	//returning background of player health bar
	public Rectangle2D healthBg() {
		return healthBg;
	}
	
	//returning if player is attacking
	public boolean isAttacking () {
		return attacking;
	}
	
	//returning if player is blocking
	public boolean isBlocking () {
		return blocking;
	}

	//updating player mask based on location of player
	public void updatePlayerMask() {
		playerMask = new Rectangle2D.Double(xPos, yPos, getImg().getIconWidth(), getImg().getIconHeight());
	}
	
	//returning player's mask regular
	public Rectangle2D playerMask() {
		return playerMask; 
	}
	
	//returning player's mask when attacking
	public Rectangle2D playerAttackMask() {
		if (attacking == true) {
			return playerMask; 
		}
		else {
			return nullMask; 
		}
	}



}
