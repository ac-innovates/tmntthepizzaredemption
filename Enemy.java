import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Enemy implements ActionListener {
	private ImageIcon enemy; 
	private int index, xPos, yPos, health, direction, xDir, yDir, bgWidth, bgHeight, level, yPos1, xPos1, eSpeedX,eSpeedY, bulletDirection;
	private final int RIGHT, LEFT, UP, DOWN; 
	private boolean isFired;
	private Random rnd; 
	private Rectangle2D healthBar, healthBg, enemyMask; 
	private Timer bulletTimer;
	private EnemyBullet bullet;
	private Ellipse2D enemyBounds; 

	public Enemy() {
		//random index so random images for the enemy show up 
		rnd = new Random(); 
		index = rnd.nextInt(5) + 1; 
		bullet = new EnemyBullet();
		bulletTimer = new Timer (100, this);

		//initializing image variable
		enemy = new ImageIcon("images/enemies/enemy"+index+".png");

		//default x and y positions of enemy
		xPos = 0;
		yPos = 0; 

		//assigning how much health each enemy has 
		health = 40; 

		//initializing rectangles for health and health background 
		healthBar = new Rectangle2D.Double(xPos + enemy.getIconWidth(), yPos + enemy.getIconHeight(), 45, 14);
		healthBg = new Rectangle2D.Double(xPos + enemy.getIconWidth(), enemy.getIconHeight()+15, enemy.getIconWidth(), 20);

		//initializing the enemy mask
		enemyMask = new Rectangle2D.Double(xPos, yPos, enemy.getIconWidth(), enemy.getIconHeight()); 

		//default direction value
		direction = 0;

		//initializing constants to different movement directions
		LEFT = 1; 
		RIGHT = 2; 
		UP = 3;
		DOWN = 4; 

		level = 0; 
		
		enemyBounds = new Ellipse2D.Double(0,0,0,0);


		
		//assigning background dimensions based on level
		if (level == 1) {
			bgWidth = 600;
			bgHeight = 360; 
		}


	}

	//getting the enemy image
	public ImageIcon getImg() {
		return enemy;
	}

	//setting location of enemy
	public void setLocation(int x, int y) { 
		xPos = x;
		yPos = y; 
	}


	//returning the x position of enemy
	public int getX() {
		return xPos; 
	}

	//returning the y position of enemy
	public int getY() {
		return yPos; 
	}

	//setting the x position of enemy
	public void setX(int x) {
		xPos = x; 
	}

	//setting the y position of enemy
	public void setY(int y) {
		yPos = y; 
	}
	
	//speeding or slowing down enemies on x axis
	public void changeX(int x) {
		xPos += x; 
	}
	
	//speeding or slowing down enemies on y axis
	public void changeY(int y) {
		yPos += y; 
	}
	
	//returning width of enemy
	public int getWidth() {
		return enemy.getIconWidth(); 
	}
	
	//returning height of enemy
	public int getHeight() {
		return enemy.getIconHeight();
	}
	
	//changing direction of enemy in x 
	public void changeDirX (int dir) {
		xDir = dir;
	}
	
	//changing direction of enemy in y
	public void changeDirY (int dir) {
		yDir = dir;
	}
	
	//spawning the enemy
	public void enemySpawn(int level) {	
		
		//setting background width and heights based on level
		if (level == 1) {
			bgWidth = 600;
			bgHeight = 340; 
		}
		else if (level == 2) {
			bgWidth = 692;
			bgHeight = 435; 
		}
		else if (level == 3) {
			bgWidth = 788;
			bgHeight = 450;
		}
		else if (level == 4) {
			bgWidth = 800;
			bgHeight = 427;
		}
		
		//randomly assigning position of enemy		
		yPos1 = rnd.nextInt(bgHeight)+30;
		xPos1 = rnd.nextInt(bgWidth)+10;

		//randomly assigning a direction in which the enemy will enter the screen from 
		direction = rnd.nextInt(4) + 1;

		//setting the location based on the direction 
		if (direction == 1) {
			setLocation(-80, yPos1);

			//assigning direction of image based on direction variable 
			xDir = RIGHT;

			//determining direction the enemy will move based on its y position 
			if (yPos1 < 300) {
				yDir = DOWN;
			}
			else if (yPos1 >= 300) {
				yDir = UP; 
			}
		} else if (direction == 2) {
			setLocation(690, yPos1);

			//assigning direction of image based on direction variable
			xDir = LEFT;

			//determining direction the enemy will move based on its y position 
			if (yPos1 < 300) {
				yDir = DOWN;
			}
			else if (yPos1 >= 300) {
				yDir = UP; 
			}
		} else if (direction == 3) {
			setLocation(xPos1, -80);

			//assigning direction of image based on direction variable
			yDir = DOWN;

			//determining direction the enemy will move based on its x position 
			if (xPos1 < 300) {
				xDir = RIGHT; 
			}
			else if (xPos1 >= 300) {
				xDir = LEFT;
			}

		} else if (direction == 4) {
			setLocation(xPos1 , 690);
			//assigning direction of image based on direction variable
			yDir = UP;

			//determining direction the enemy will move based on its y position
			if (xPos1 < 300) {
				xDir = RIGHT; 
			}
			else if (xPos1 >= 300) {
				xDir = LEFT;
			}
		}


	}

	//moving the enemy for level 1 
	public void enemyMove(int level) {
		//setting background width and heights based on level	
		if (level == 1) {
			bgWidth = 600;
			bgHeight = 320; 
		}
		else if (level == 2) {
			bgWidth = 692;
			bgHeight = 435; 
		}
		else if (level == 3) {
			bgWidth = 788;
			bgHeight = 450;
		}
		else if (level == 4) {
			bgWidth = 800;
			bgHeight = 427;
		}
		
		
		// Starting Timer 
		bulletTimer.start();
		

		//assigning random speeds to each enemy
		eSpeedX = rnd.nextInt(4) + 3; 
		eSpeedY = rnd.nextInt(4) + 3; 

		
		//moving the enemy left, since the direction is left and boundary checking 
		if (xDir == LEFT) {
			if (xPos > 0) {
				xPos -= eSpeedX; 
			}
			//moving enemy in opposite direction once it hits the boundary
			else if (xPos <= 0) {
				xDir = RIGHT;
			}
		}
		//moving the enemy right, since the direction is right and boundary checking
		else if (xDir == RIGHT) {
			if (xPos + enemy.getIconWidth() < bgWidth) {
				xPos += eSpeedX;
			}
			//moving enemy in opposite direction once it hits the boundary
			else if (xPos + enemy.getIconWidth() >= bgWidth) {
				xDir = LEFT; 
			}
		}

		//moving the enemy up, since the direction is up and boundary checking
		if (yDir == UP) {
			if (yPos > 0) {
				yPos -= eSpeedY; 
			}
			//moving enemy in opposite direction once it hits the boundary
			else if (yPos <= 0) {
				yDir = DOWN; 
			}
		}
		//moving the enemy down, since the direction is down and boundary checking
		else if (yDir == DOWN) {
			if (yPos + enemy.getIconHeight() < bgHeight) {
				yPos += eSpeedY; 
			}
			//moving enemy in opposite direction once it hits the boundary
			else if (yPos + enemy.getIconHeight() >= bgHeight) {
				yDir = UP;
			}
		}

		//mask for enemy 
		enemyMask = new Rectangle2D.Double(xPos, yPos, enemy.getIconWidth(), enemy.getIconHeight()); 

		//health bar drawn
		healthBg = new Rectangle2D.Double(xPos, yPos + enemy.getIconHeight(), 45, 14);
		healthBar = new Rectangle2D.Double(xPos+2, yPos + enemy.getIconHeight()+3, 40, 8);
		
		updateEnemyBounds();
		}

	//updating the health when the enemy gets attacked
	public void updateHealth(int damage) {
		health -= damage; 
	}
	
	//returning value of health
	public int getHealth() {
		return health; 
	}

	//setting the health to a specific value 
	public void setHealth(int hp) {
		health = hp; 
	}

	//drawing the health bar
	public Rectangle2D health()  {
		healthBar = new Rectangle2D.Double(xPos+2, yPos + enemy.getIconHeight()+3, health, 8);
		return healthBar; 
	}

	//drawing the background of the health bar
	public Rectangle2D healthBg() {
		healthBg = new Rectangle2D.Double(xPos, yPos + enemy.getIconHeight(), 45, 14);
		return healthBg;
	}

	//drawing the mask over the enemy for collision purposes 
	public Rectangle2D enemyMask() {
		enemyMask = new Rectangle2D.Double(xPos, yPos, enemy.getIconWidth(), enemy.getIconHeight()); 

		return enemyMask; 
	}
	
	//returning image of bullets
	public ImageIcon enemyBullet() {
		return bullet.getImg(); 		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//firing bullets
		if (e.getSource() == bulletTimer) {
			//resetting bullet location to enemy location
			if (isFired == false) {
				bullet.setX(xPos);
				bullet.setY(yPos);
				isFired = true; 
			}	
			//moving bullets and mask if bullets have been fired
			else if (isFired == true){
				bullet.bulletMask(); 
				bullet.move(); 
			}

		}
		
		//randomly setting bullet direction once bullet is off screen
		if (bullet.getX() < 0 || bullet.getX() + bullet.getWidth() >= bgWidth || bullet.getY() < 0 || bullet.getY() + bullet.getHeight() >= bgHeight) {  
			bullet.setBulletDirection(); 
			isFired = false;			
		}

	}
	
	//returning bullet's x position 
	public int bulletGetX() {
		return bullet.getX(); 
	}
	
	//returning bullet's y position 
	public int bulletGetY() {
		return bullet.getY(); 
	}
	
	//bullet's mask 
	public Rectangle2D getBulletMask() {
		return bullet.bulletMask(); 
	}
	
	//setting x position of bullet
	public void bulletSetX(int x) {
		bullet.setX(x); 
	}
	
	//setting y position of bullet
	public void bulletSetY(int y) {
		bullet.setY(y);
	}
	
	//updating enemy radius based on enemy movement 
	public void updateEnemyBounds() {
		enemyBounds = new Ellipse2D.Double(getX() - 100, getY() - 100, getWidth() + 200, getHeight() + 200);
	}
	
	//enemy radius 
	public Ellipse2D enemyBounds() {	
		return enemyBounds;
	} 
}

