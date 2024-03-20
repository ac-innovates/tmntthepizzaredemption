/* ICS3U1 CPT: Pizza Redemption
 * Noah Balatbat & Areen Charania
 * Mr. Conway
 * This program is a game in which players must recover a stolen pizza as a turtle from the Teenage Mutant Ninja Turtles franchise.
 * The game opens with an introduction to the story, as well as the controls for the game. The player is moved using the WASD keys, 
 * attack using the J key and shield using the K key (only in the boss level). Also, the button E can be used to emote in the world
 * map. After players traverse through the story and control screens, they are brought to the turtle select screen. They can scroll
 * through four character descriptions of each turtle, and type in who they would like to play as in the end. Once their turtle has
 * been chosen, they are teleported to the world map. They can only enter the first level at this point, in which they are teleported
 * to the level one map if they interact with its location icon. In level one, players fight against enemy ninjas of the Foot clan 
 * who throw shuriken projectiles at them. If they are hit, they will lose some health, indicated by the healthbar on the top left
 * of the screen. If the player hits an enemy twice, they are eliminated. Once all enemies in level one are defeated, they are 
 * teleported back to the world map gaining access to level two. If they interact with level two's location icon, they are teleported
 * into its map. Within this map, the enemies number increases to seven and they begin to track the player and move closer towards them. 
 * Once all the enemies in this level are defeated, the user is teleported back to the world map. They gain access to level three, 
 * which they must cross a bridge to get to. Both bridges prompt players for whether they want to enter or not, and if they click yes
 * the players automatically cross the bridge. On the right bridge, however, there is a random chance that they may die midway through
 * crossing. Once players enter the level through the location icon, they are teleported into a map. The objective has changed, as
 * they must collect twenty pizzas to win the level. There are still enemies which attack the player, and will respawn on death. Once
 * the player has collected all the pizzas, they are able brought back to the world map with access to level four. Once the player
 * enters level four through its location icon, they see two turrets. Both turrets are able to fire a laser which can track the 
 * players movement within a certain radius. THe player must destroy both turrets to beat the level while dodging its lasers and 
 * the Foot clans shurikens. Once the player beats level four, they teleport to the world map one final time, with access to the 
 * boss level. The boss level is a 1 versus 1 against Shredder. Shredders movement is randomized, as he moves left or right depending
 * on a number randomly generated every half second. His actions are also randomized, as a random number is generated to decide what
 * he does. He can attack the enemy using a punch or can throw a dagger, or can turn himself invulnerable for a few seconds. Once the
 * healthbar is depleted, the win screen is drawn. In each level (1-4) a crown appears after the level is completed to provide story
 * information and to congratulate the player on his win. 
 * For input/output, a JOptionPane using input dialog was used to ask the user which character they would like to use for their quest.
 *  If the user enters a name that is not in the options, either their nickname or their full name, the user must re-enter a valid 
 * name. Multiple if statements are used throughout the program for pressing and releasing keys, moving and clicking the mouse, 
 * checking for collisions, checking if all enemies have been killed, etc. The Random class was used to randomize the spawn point of 
 * enemies. A direction variable was used with random numbers between 1-4 to identify whether the enemies will enter from the 
 * left(1), right(2), up(3), or down(4). It is also used to randomize the chance of death on the bridge, to randomize the spawn 
 * point of the health potion falling from the sky, etc. For loops are used in our program to assign values to arrays(array of 
 * objects, array of ImageIcons, to spawn and move the array of objects enemies, for drawing the array of objects enemies and much 
 * more. A while loop was used to check whether or not the user input was not valid. The String class was used for the story in the 
 * beginning to have a typing effect using the charAt() function and was also used for user input by ignoring the case. Try catch 
 * statements were used for the custom fonts. Arrays were used for both ImageIcons (the level backgrounds, character profiles, 
 * story) and for an array of objects (enemies). We had multiple self made methods in the main class such as levelWon(), 
 * maskUpdate(), and bridgeExitUp(). There are multiple different classes for the Player, Enemy, EnemyBullet, WorldMap, Boss, 
 * and PlayerForBoss. JOptionPanes were used to ask the user to confirm if they want to select a player and then asking for their 
 * input. JFrames use both Graphics and Animations, and the characters move with keys and buttons can be clicked with the mouse.

 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	// Global Variables
	private Player player;
	private WorldMap map;
	private JFrame frame;
	private Enemy [] enemy; 
	private Random rnd;
	private Boss boss;
	private PlayerForBoss playerBoss;
	private Pizza [] pizza;
	private int WIDTH = 600, HEIGHT = 600;
	private ImageIcon gameOver, titleScreen, playButton, bigPlayButton, winScreen, crown, 
	leftScroll, rightScroll, leftScrollBigger, rightScrollBigger, healthPlus;
	private ImageIcon [] levelIcon; 
	private ImageIcon [] exclamationIcon;
	private ImageIcon [] levelMap;
	private ImageIcon [] characterProfile;
	private boolean [] enemyDeathBooleanCounter;
	private Turret [] turret;
	private ImageIcon [] story; 
	private String [] stringArray;
	private String [] stringTwoArray; 
	private String newStr, newStr2; 
	private Font f, fontStory, fontPizza; 
	private FontMetrics fm; 
	private boolean left, right, up, down;
	private Timer playerMapTimer, bridgeTimer, levelTravelTimer, playerLevelTimer, bossMoveTimer, 
	bossActionTimer, enemyTimer, playerInvulnerableTimer, levelStartTimer, bossHitTimer, 
	playerHitTimer, bossPassiveDamageTimer, cooldownTimer, enemyInvulnerableTimer, pizzaTimer, turretTimer, 
	turretInvulnerableTimer, healthSpeedTimer, healthPlusTimer, healthInvulnerableTimer, stringTimer;
	private boolean goDownBridge, goUpBridge, goDownBridgeTwo, goUpBridgeTwo, playButtonHover, 
	leftScrollHover, rightScrollHover, emote,levelOneTravel, levelTwoTravel, levelThreeTravel, 
	levelFiveTravel, levelFiveTravelDown, enemySpawn, playerInvulnerable, attack, block, gameWon, levelTwoWon, bossAutoMove, 
	cooldown, validTurtleChoice, levelOneWon, enemyInvulnerable, pizzaSpawn, levelThreeWon, levelFourTravel, 
	levelFourWon, turretInvulnerable, healthOnScreen, isFallen, healthInvulnerable, turretSpawn;
	private int level, speed, teleportBridge, deathChance, turtleChoice, seconds, profile, 
	enemyDeathCounter, pizzaCollectCounter, pizzasCollected, healthMovement, healthSpawn, storyPage
	, stringCounter, stringCounter2;
	private Rectangle2D playerMask, bridgeOneEnter, bridgeOneExit, bridgeTwoEnter, bridgeTwoExit,
	bridgeTwoHalfway, exclaimOneMask, exclaimTwoMask, exclaimThreeMask, exclaimFourMask, exclaimFiveMask,
	crownMask, healthMask;
	private int characterConfirm;
	private String turtleChoiceString;
	/** 
	 * The numbers which correspond with the turtle goes as follows:
	 * 1 = DONNY
	 * 2 = LEO
	 * 3 = RALPH
	 * 4 = MIKEY
	 */

	public static void main(String[] args) {
		new Main();
	}

	public Main () {

		// Focusing towards mouse listener, mouse motion listener and keyboard listener
		addKeyListener(this); 
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true); 
		requestFocus(); 

		// Assign Default Values
		player = new Player(WIDTH, HEIGHT);
		map = new WorldMap(WIDTH, HEIGHT);
		boss = new Boss();
		
		//timer values
		playerBoss = new PlayerForBoss();
		playerMapTimer = new Timer (50, this);
		bridgeTimer = new Timer (50, this);
		levelTravelTimer = new Timer (50, this);
		playerMapTimer = new Timer (50, this);
		playerLevelTimer = new Timer (25, this);
		playerHitTimer = new Timer (1000, this);
		bossHitTimer = new Timer(500, this);
		bossPassiveDamageTimer = new Timer(500, this);
		turretTimer = new Timer (10, this);

		enemyTimer = new Timer(100, this);  
		playerInvulnerableTimer = new Timer (500, this); 
		levelStartTimer = new Timer (1000, this); 
		enemyInvulnerableTimer = new Timer (500, this);
		turretInvulnerableTimer = new Timer (250, this);
		healthInvulnerableTimer = new Timer(800, this); 
		stringTimer = new Timer (40, this); 

		bossMoveTimer = new Timer (50, this);
		bossActionTimer = new Timer (2500, this);
		cooldownTimer = new Timer(200, this);

		cooldownTimer = new Timer(200, this);
		healthPlusTimer = new Timer(10000, this);
		healthSpeedTimer = new Timer(50, this); 

		pizzaTimer = new Timer (100, this);

		rnd = new Random();
		left = false;
		right = false;
		up = false;
		down = false;
		
		//ImageIcon values
		gameOver = new ImageIcon("images/backgrounds/gameoverscreen.jpeg");
		winScreen = new ImageIcon("images/backgrounds/winnerScreen.png");
		titleScreen = new ImageIcon("images/backgrounds/titleScreen.jpg");
		bigPlayButton = new ImageIcon("images/backgrounds/playButton.jpg");

		//scaling the health image
		Image newImage7 = new ImageIcon("images/level/health.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		healthPlus = new ImageIcon(newImage7);

		//scaling the button scroll images
		Image newImage = new ImageIcon("images/backgrounds/leftScroll.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
		leftScroll = new ImageIcon(newImage);
		Image newImage2= new ImageIcon("images/backgrounds/rightScroll.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
		rightScroll = new ImageIcon(newImage2);

		//scalling the bigger button scroll images after a hover
		Image newImage3= new ImageIcon("images/backgrounds/leftScroll.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
		leftScrollBigger = new ImageIcon(newImage3);
		Image newImage4= new ImageIcon("images/backgrounds/rightScroll.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
		rightScrollBigger = new ImageIcon(newImage4);

		//setting initial count down timer to 3
		seconds = 3;

		//setting font which is later changed in the program to the custom font
		f = new Font("Arial", Font.BOLD, 150);
		
		//initializing font metrics
		fm = getFontMetrics(f); 

		//initializing ImageIcon arrays
		levelIcon = new ImageIcon [5];
		exclamationIcon = new ImageIcon[5];
		levelMap = new ImageIcon[5];
		characterProfile = new ImageIcon[5];
		story = new ImageIcon[6]; 


		//assigning values to each index of ImageIcon array
		for (int i = 0; i < 5; i++) {
			levelIcon[i] = new ImageIcon("images/level/EnterL" + (i + 1) + "B.jpg");
			exclamationIcon[i] = new ImageIcon("images/level/ExcMark" + (i + 1) + ".jpg");
			levelMap[i] = new ImageIcon("images/backgrounds/Level" + (i + 1) + "BG.jpg");
		}

		//assigning values to each index with character profiles
		for (int i = 0; i < 4; i++) {
			Image newImage5 = new ImageIcon("images/backgrounds/characterProfile" + (i + 1) + ".png").getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
			characterProfile[i] = new ImageIcon(newImage5);
		}
		
		//assigning each index to a different page of the story
		for (int i = 0; i < 6; i++) {
			story[i] = new ImageIcon("images/story/story"+ (i+1) + ".png"); 
		}
		
		//classes used for level 3
		turret = new Turret[2];
		pizza = new Pizza [20];

		//array of objects for the two turrets
		for (int i = 0; i < 2; i++) {
			turret[i] = new Turret();
		}

		//array of objects for the 20 pizzas
		for (int i = 0; i < pizza.length; i++) {
			pizza[i] = new Pizza();
		}

		//array of objects of enemies
		enemy = new Enemy[15];
		
		//boolean array for checking enemy deaths
		enemyDeathBooleanCounter = new boolean [15];
		
		//assigning array of objects
		for (int i = 0; i < enemy.length; i++) {
			enemy[i] = new Enemy();
		}
		
		//text for each story page
		stringArray = new String [] {"As the turtles wait for their pizza order, they hear",
				"\"Oh no! What do we do?\"", "\"Worry not, turtles. We will split up and find Shredder", "NOTE: Who you choose to play will be the turtle you"
				, "\"Okay team, lets go!\"", ""};
		
		//second line of text for each story page
		stringTwoArray = new String [] {" a doorbell. When they open it, they saw that their pizza was stolen!\r\n"
				+ "", "", "and our missing pizzas!\" - Splinter",
				"take with you on your pizza redemption quest!", "", ""};

		//string variables for text
		newStr = "";
		newStr2 = ""; 

		//scaling play button image
		Image newImage6 = new ImageIcon("images/backgrounds/bigPlayButton.jpg").getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH);
		playButton = new ImageIcon(newImage6);

		//scaling crown image
		Image newImage1 = new ImageIcon("images/level/crown.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		crown = new ImageIcon(newImage1);

		//default mask values
		crownMask = new Rectangle2D.Double(430,175,30,30);
		healthMask = new Rectangle2D.Double(0,0,0,0); 

		//custom font 80f
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new File
					("images/Gemstone.ttf")).deriveFont(80f);
			GraphicsEnvironment ge =
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new
					File ("images/Gemstone.ttf")));
		} catch (Exception e) {}

		//custom font 16f
		try {
			fontStory = Font.createFont(Font.TRUETYPE_FONT, new File
					("images/Gemstone.ttf")).deriveFont(16f);
			GraphicsEnvironment ge =
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new
					File ("images/Gemstone.ttf")));
		} catch (Exception e) {}

		//custom font 20f
		try {
			fontPizza = Font.createFont(Font.TRUETYPE_FONT, new File
					("images/Gemstone.ttf")).deriveFont(20f);
			GraphicsEnvironment ge =
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new
					File ("images/Gemstone.ttf")));
		} catch (Exception e) {}

		// Assigning Mask Values
		maskUpdate();

		// Create Frame
		frame = new JFrame ();
		frame.setContentPane(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		setBackground(Color.BLACK);

		// Starting the game
		gameStart();

	}

	public void actionPerformed(ActionEvent e) {
		//story pages
		if (level == -2) {
			stringTimer.start(); 
			
			if (e.getSource() == stringTimer) {
				//adding a letter to string based on timer to give a typing effect
				if(stringCounter <= stringArray[storyPage].length()-1) { 
					newStr += stringArray[storyPage].charAt(stringCounter); 
					stringCounter++; 
				}
				
				////adding a letter to second line of string based on timer to give a typing effect
				if (newStr.length() == stringArray[storyPage].length()) {
					if(stringCounter2 <= stringTwoArray[storyPage].length()-1) { 
						newStr2 += stringTwoArray[storyPage].charAt(stringCounter2); 
						stringCounter2++; 
					}
				}
				else {
					stringTimer.start();	
				}
				
				//stopping timer once last page is reached
				if (storyPage == 5) {
					stringTimer.stop();
				}
			}
		}
		
		//timer for player movement
		if (e.getSource() == playerLevelTimer) {
			
			//passing in important values for movement, different for player and player in boss level
			if (level < 5)
				player.move(left, right, up, down, 5, attack, level);
			else {

				playerBoss.move(left, right, attack, block);

				// Checking whether player hit boss
				if (playerBoss.getMask().intersects(boss.getMask()) && playerBoss.isAttacking() && !boss.isInvul()) {
					playerHitTimer.start();
				}

				// Checking whether boss dagger hits player
				if (playerBoss.getMask().intersects(boss.getDaggerMask()) && playerBoss.isBlocking() == false) {
					playerBoss.updateHealth(10);
					boss.stopDagger();
				}

				// Checking whether boss hits player
				if (playerBoss.getMask().intersects(boss.getAtkMask()) && boss.isAttacking() == true 
						&& player.isBlocking() == false && cooldown == false) {
					bossHitTimer.start();
				}

				// Passive damage to player if they are intersecting
				if (playerBoss.getMask().intersects(boss.getMask()) || playerBoss.isAttacking() == true 
						|| playerBoss.isBlocking() == true) {
					bossPassiveDamageTimer.start();
				}

				// Ending game if either of their healths are zero
				if (playerBoss.getHealth() <= 0 || boss.getHealth() <= 0) {
					if (playerBoss.getHealth() <= 0) {
						gameWon = false;
						gameStop();
					}
					else if (boss.getHealth() <= 0) {
						gameWon = true;
						gameStop();
					}
				}

			}
		}

		// Subtracting health from boss if player is still hitting him
		if (e.getSource() == playerHitTimer) {
			if (playerBoss.getMask().intersects(boss.getMask()))
				boss.updateHealth(40);
			playerHitTimer.stop();
		}

		// Subtracting health from player if boss is still hitting him
		if (e.getSource() == bossHitTimer) {
			if (block == true) {
			}
			else if (block == false) {
				playerBoss.updateHealth(60);
			}
			cooldown = true;
			cooldownTimer.start();
			bossHitTimer.stop();
		}
		
		//giving a split moment for boss to cool down
		if (e.getSource() == cooldownTimer) {
			cooldown = false;
			cooldownTimer.stop();
		}

		// Passively subtracting health from player if they are intersecting
		if (e.getSource() == bossPassiveDamageTimer) {
			if (!playerBoss.getMask().intersects(boss.getMask()) || playerBoss.isAttacking() == true 
					|| playerBoss.isBlocking() == true)
				bossPassiveDamageTimer.stop();
			else
				playerBoss.updateHealth(1);
		}

		//timer for player movement in the map
		if (e.getSource() == playerMapTimer) {
			map.mapMove(left, right, up, down);

			// Checking if player wants to enter the bridge
			if(playerMask.intersects(bridgeOneEnter)) {
				// If they intersect the bridge, the player is prompted to go on the bridge or not
				playerMapTimer.stop();
				teleportBridge = JOptionPane.showConfirmDialog(null, "Would you like to go down the bridge?", "Bridge Travel", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
				// If they choose to go on the bridge the bridge timer is started
				if (teleportBridge == JOptionPane.YES_OPTION) {
					map.setMapX(-160);
					goDownBridge = true;
					bridgeTimer.start();
					emote = true;
				}
				// If they choose to  not go on the bridge the map timer is started again
				else {
					playerMapTimer.start();
					map.updateMapY(25);
					movementOff();

				}
			}

			// Checking if player wants to exit the bridge
			if(playerMask.intersects(bridgeOneExit)) {
				playerMapTimer.stop();
				// If they intersect the bridge, the player is prompted to go on the bridge or not
				teleportBridge = JOptionPane.showConfirmDialog(null, "Would you like to go up the bridge?", "Bridge Travel", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
				// If they choose to go on the bridge the bridge timer is started
				if (teleportBridge == JOptionPane.YES_OPTION) {
					map.setMapX(-160);
					goUpBridge = true;
					bridgeTimer.start();
					emote = true;
				}
				// If they choose to  not go on the bridge the map timer is started again
				else {
					playerMapTimer.start();
					map.updateMapY(-speed*3);
					movementOff();

				}
			}

			// Checking if player tries to go onto broken bridge (50/50 Death)
			if (playerMask.intersects(bridgeTwoExit) || playerMask.intersects(bridgeTwoEnter)) {
				playerMapTimer.stop();
				//Warning user about chance of death on bride
				teleportBridge = JOptionPane.showConfirmDialog(null, "Master Splinter has warned that going on this bridge is highly dangerous.\nWould you like to go on the bridge?", "Bridge Travel", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
				if (teleportBridge == JOptionPane.YES_OPTION) {
					//going down bridge
					if (playerMask.intersects(bridgeTwoEnter)) {
						goDownBridgeTwo = true;
						emote = true;
					}
					//going up bridge
					else if (playerMask.intersects(bridgeTwoExit)) {
						goUpBridgeTwo = true;
						emote = true;
					}
					bridgeTimer.start();
				}
				else {
					playerMapTimer.start();
					movementOff();
					if (playerMask.intersects(bridgeTwoEnter)) {
						map.updateMapY(speed*3);
					}
					else if (playerMask.intersects(bridgeTwoExit)) {
						map.updateMapY(-speed*3);
					}
				}

			}


		}

		// Timer for bridge
		if (e.getSource() == bridgeTimer) {

			// Checking if they are trying to move down first bridge
			if (goDownBridge == true) {
				map.updateMapY(-speed);
				// Taking them out of the bridge
				if (playerMask.intersects(bridgeOneExit)) {
					bridgeExitDown();
				}
			}
			// Checking if they are trying to move up first bridge
			else if (goUpBridge == true) {
				map.updateMapY(speed);
				//Taking them out of the bridge
				if (playerMask.intersects(bridgeOneEnter)) {
					bridgeExitUp();

				}
			}

			// Player going on broken bridge
			if (goUpBridgeTwo == true || goDownBridgeTwo == true) {
				// Randomizing whether they live or die
				deathChance = rnd.nextInt(5);
				// Checking whether they are trying to go up second bridge
				if (goUpBridgeTwo == true) {
					map.updateMapY(speed);
					// Checking whether they will live or die on the bridge
					if (deathChance == 0) {
						bridgeDeath();
					}
					// Taking them off bridge if they survived
					else {
						if (playerMask.intersects(bridgeTwoEnter)) 
							bridgeExitUp();
					}
				}
				// CHecking whether thy are trying to go down second bridge
				else if (goDownBridgeTwo == true) {
					map.updateMapY(-speed);
					// Checking whether they live or die on the bridge
					if (deathChance == 0) {

						bridgeDeath();
					}
					// Taking them off bridge if they survived
					else {
						if (playerMask.intersects(bridgeTwoExit))
							bridgeExitDown();
					}
				}
			}

		}

		if (e.getSource() == levelTravelTimer) {

			// Teleporting the player to first level
			if (levelOneTravel == true) {
				//setting important variable based on level 1
				level = 1;
				HEIGHT = 360;
				frame.setSize(WIDTH, HEIGHT);
				frame.setLocationRelativeTo(null);
				player.setX(20);
				player.setY(200);
				movementOff();
				levelOneTravel = false;  
				levelTravelTimer.stop();
			}


			// Teleporting player to second level
			if (levelTwoTravel == true) {
				//setting important variable based on level 2
				level = 2;
				frame.setSize(692, 450);
				frame.setLocationRelativeTo(null);
				player.setX(40);
				player.setY(325);
				movementOff();
				levelTwoTravel = false;  
				levelTravelTimer.stop();
			}

			// Teleporting the player to third level
			if (levelThreeTravel == true) {
				//setting important variable based on level 3
				level = 3;
				frame.setSize(788, 450);
				frame.setLocationRelativeTo(null);
				player.setX(40);
				player.setY(250);
				movementOff();
				levelThreeTravel = false;  
				levelTravelTimer.stop();
			}

			// Teleporting the player to fourth level
			if (levelFourTravel == true) {
				//setting important variable based on level 4
				level = 4;
				frame.setSize(800, 427);
				frame.setLocationRelativeTo(null);
				player.setX(400);
				player.setY(300);
				movementOff();
				levelFourTravel = false;  
				levelTravelTimer.stop();
			}

			// Teleporting player to fifth level
			if (levelFiveTravel == true) {
				if (levelFiveTravelDown == true) {
					if (!playerMask.intersects(map.getMaskDown())) {
						emote = true;
						map.updateMapY(-speed);
					}
					else {
						//setting important variable based on level 5
						level = 5;
						emote = false;
						WIDTH = 1200;
						HEIGHT = 700;
						movementOff();
						frame.setSize(WIDTH, HEIGHT);
						frame.setLocationRelativeTo(null);
						levelFiveTravelDown = false;
						playerBoss.goToBoss();
						left = false;
						right = false;
					}
				}
				else if (levelFiveTravelDown == false) {

				}
			}
		}

		// Spawning level one enemies
		if (level == 1) {
			//countdown 3,2,1 timer
			levelStartTimer.start();

			//reducing seconds for countdown 3,2,1
			if (e.getSource() == levelStartTimer) {
				seconds--; 
			}
			
			//spawning the enemies in random locations
			if (!enemySpawn && level == 1) {
				for (int i = 0; i < 5; i++) {
					enemy[i].enemySpawn(level); 

					enemySpawn = true;
				}
			}
			//when count down over level starts and enemies start moving
			if (seconds == 0) {
				levelStartTimer.stop(); 
				enemyTimer.start(); 
				playerLevelTimer.start();
			}

			//moving the enemies
			if (e.getSource() == enemyTimer) {
				for (int i = 0; i < 5; i++) {
					if (enemy[i].getHealth() > 0) {
						enemy[i].enemyMove(level); 

						//deducting health from player if intersecting with enemy
						if (enemy[i].getBulletMask().intersects(player.playerMask()) && playerInvulnerable == false) {
							player.updateHealth(10);
							playerInvulnerable = true;
							playerInvulnerableTimer.start();
						}
						
						//ensuring health only goes down once when player attacks enemy
						if (enemy[i].enemyMask().intersects(player.playerAttackMask()) && enemyInvulnerable == false) {
							enemyInvulnerable = true; 
							enemy[i].updateHealth(20); 
							enemyInvulnerableTimer.start(); 
						}		
					}
					else {
						//pushing enemies and bullets way off the screen once dead
						enemy[i].setX(-100); 
						enemy[i].setY(-100);
						enemy[i].bulletSetY(-100); 
						enemy[i].bulletSetY(-100);
					}
				}
			}
			
			//timer to ensure health only goes down once
			if (e.getSource() == enemyInvulnerableTimer) {
				enemyInvulnerable = false; 
				enemyInvulnerableTimer.stop(); 
			}

			//timer for health so that health only goes down once
			if (e.getSource() == playerInvulnerableTimer) {
				playerInvulnerable = false;
				if (player.playerHealth() == 0) {
					gameStop();
				}
				playerInvulnerableTimer.stop(); 
			}

			//checking how many enemies are dead to see if player won
			for (int i = 0; i < 5; i++) {
				if (enemy[i].getHealth() <= 0) {
					if (enemyDeathBooleanCounter[i] == false) {
						enemyDeathCounter += 1;
						enemyDeathBooleanCounter[i] = true; 
					}

				}
			}

			//Checking once all 5 enemies have been killed
			if (enemyDeathCounter >= 5) {
				enemyTimer.stop();
				levelOneWon = true; 

				//crown and player intersection to leave level 
				if(player.playerMask().intersects(crownMask) && levelOneWon == true && level!= 0) {
					playerLevelTimer.stop(); 
					attack = false;
					JOptionPane.showMessageDialog(null, "Level Complete!\n\nOh no! Shredder is gone already! I must keep going!", "LEVEL WINNER", JOptionPane.PLAIN_MESSAGE, crown);
					levelWon();
				}

			}

		}

		if (level == 2) {

			//countdown 3,2,1 timer
			levelStartTimer.start();

			//reducing seconds for countdown 3,2,1
			if (e.getSource() == levelStartTimer) {
				seconds--; 
			}
			
			//spawning enemies in random locations
			if (!enemySpawn && level == 2) {
				for (int i = 0; i < 8; i++) {
					enemy[i].enemySpawn(level); 

					enemySpawn = true;
				}
			}
			//when count down over level starts and enemies start moving
			if (seconds == 0) {
				levelStartTimer.stop(); 
				enemyTimer.start(); 
				playerLevelTimer.start();
				healthPlusTimer.start(); 
			}

			//moving the enemies
			if (e.getSource() == enemyTimer) {
				for (int i = 0; i < 8; i++) {
					if (enemy[i].getHealth() > 0) {
						//making the enemies move faster towards player if player is within their radius
						if (enemy[i].enemyBounds().intersects(player.playerMask())) {
							if (player.getX() < enemy[i].getX()) {
								enemy[i].changeX(-3);
							}
							else {
								enemy[i].changeX(3);
							}
							if (player.getY() < enemy[i].getY()) {
								enemy[i].changeY(-3);
							}
							else {
								enemy[i].changeY(3); 
							}
						}
						//moving enemies
						enemy[i].enemyMove(level); 

						//deducting health from player if intersecting with enemy
						if (enemy[i].getBulletMask().intersects(player.playerMask()) && playerInvulnerable == false) {
							player.updateHealth(5);
							playerInvulnerable = true;
							playerInvulnerableTimer.start();
						}
						
						//deducting health from enemy if attacked by player
						if (enemy[i].enemyMask().intersects(player.playerAttackMask()) && enemyInvulnerable == false) {
							enemyInvulnerable = true; 
							enemy[i].updateHealth(20); 
							enemyInvulnerableTimer.start(); 
						}		
					}
					else {
						//setting enemy far off the screen once dead
						enemy[i].setX(-100); 
						enemy[i].setY(-100);
						enemy[i].bulletSetY(-100); 
						enemy[i].bulletSetY(-100);
					}
				}
			}

			//timer for health so that enemy health only goes down once after attacked
			if (e.getSource() == enemyInvulnerableTimer) {
				enemyInvulnerable = false; 
				enemyInvulnerableTimer.stop(); 
			}

			//timer for health so that health only goes down once
			if (e.getSource() == playerInvulnerableTimer) {
				playerInvulnerable = false;
				if (player.playerHealth() == 0) {
					gameStop();
				}
				playerInvulnerableTimer.stop(); 
			}
			
			//health potion timer where added health falls from screen ever 10 seconds
			if (e.getSource() == healthPlusTimer) {
				healthSpeedTimer.start(); 
				healthOnScreen = true; 
				healthPlusTimer.stop();
				isFallen = false; 
				healthMovement = -20; 
			}

			//timer for speede of health potion
			if (e.getSource() == healthSpeedTimer) {
				if (isFallen == false) {
					healthSpawn = rnd.nextInt(600)+1;
					isFallen = true; 
				}
				else { 
					//moving the health potion
					if(healthMovement < 440) {
						healthMovement += 10;
					}
					else { 
						healthPlusTimer.start();
					}
				}

			}
			
			//if health potion goes off screen on bottom, bringing it back to top by resetting interval timer
			if (healthMovement + healthPlus.getIconHeight() >= 400) {
				healthPlusTimer.start();  
			}


			//deducting health from player if intersecting with enemy
			if (healthMask.intersects(player.playerMask()) && healthInvulnerable == false) {
				player.updateHealth(-5);
				healthInvulnerable = true;
				healthInvulnerableTimer.start();
				healthPlusTimer.start();  
				healthOnScreen = false;
			}
			
			//ensuring health is only added once when intersecting
			if (e.getSource() == healthInvulnerableTimer) {
				healthInvulnerable = false; 
				healthInvulnerableTimer.stop(); 
			}

			//checking how many enemies have died
			for (int i = 0; i < 8; i++) {
				if (enemy[i].getHealth() <= 0) {
					if (enemyDeathBooleanCounter[i] == false) {
						enemyDeathCounter += 1;
						enemyDeathBooleanCounter[i] = true; 
					}

				}
			}

			//Checking once all 8 enemies have been killed
			if (enemyDeathCounter >= 8 ) {
				enemyTimer.stop();
				healthPlusTimer.stop();
				healthSpeedTimer.stop(); 
				healthInvulnerableTimer.stop();
				levelTwoWon = true; 

				//crown and player intersection to leave level 
				if(player.playerMask().intersects(crownMask) && levelTwoWon == true && level != 0) {
					playerLevelTimer.stop(); 
					player.setX(1000);
					player.setY(1000);
					attack = false;
					JOptionPane.showMessageDialog(null, "Level Complete!\n\nThere's even more foot clan soldiers than before! I am getting hungry though...", "LEVEL WINNER", JOptionPane.PLAIN_MESSAGE, crown);
					levelWon();
				}

			}

		}

		// Spawning level 3 enemies and pizzas
		if (level == 3) {
			//countdown 3,2,1 timer
			levelStartTimer.start();

			//reducing seconds for countdown 3,2,1
			if (e.getSource() == levelStartTimer) {
				seconds--; 
			}
			
			//spawning enemies in random locations 
			if (!enemySpawn && level == 3 && seconds == 0) {
				for (int i = 0; i < 5; i++) {
					enemy[i].enemySpawn(level); 
					enemySpawn = true;
				}
			}
			
			
			if (level == 3) {
				if (seconds == 0) {
					
					//spawning and setting enemy health 
					for (int i = 0; i < 5; i++) {
						if (enemy[i].getHealth() <= 0) {
							enemy[i].enemySpawn(level); 
							enemy[i].setHealth(40);
						}
					}
				}
			}

			// Spawning pizzas
			if (!pizzaSpawn && level == 3 && pizzasCollected <= 19) {
				pizza[pizzasCollected].randomSpawn();
				pizzaSpawn = true;
			}

			//when count down over level starts and enemies start moving
			if (seconds == 0) {
				levelStartTimer.stop(); 
				enemyTimer.start(); 
				playerLevelTimer.start();
				pizzaTimer.start();
				playerLevelTimer.start();
			}

			//moving the enemies
			if (e.getSource() == enemyTimer) {
				for (int i = 0; i < 5; i++) {
					if (enemy[i].getHealth() > 0) {
						enemy[i].enemyMove(level); 

						//deducting health from player if intersecting with enemy
						if (enemy[i].getBulletMask().intersects(player.playerMask()) && playerInvulnerable == false) {
							player.updateHealth(10);
							playerInvulnerable = true;
							playerInvulnerableTimer.start();
						}

						if (enemy[i].enemyMask().intersects(player.playerAttackMask()) && enemyInvulnerable == false) {
							enemyInvulnerable = true; 
							enemy[i].updateHealth(20); 
							enemyInvulnerableTimer.start(); 
						}		

					}
				}
			}

			// Checking if pizza and player are intersecting
			if (e.getSource() == pizzaTimer) {
				if (player.playerMask().intersects(pizza[pizzasCollected].getMask())) {
					pizzaCollectCounter -= 1;
					pizza[pizzasCollected].setX(-100);
					pizza[pizzasCollected].setY(-100);
					pizzasCollected++;
					pizzaSpawn = false;
				}
			}
			
			//timer for health so enemy health only goes down once
			if (e.getSource() == enemyInvulnerableTimer) {
				enemyInvulnerable = false; 
				enemyInvulnerableTimer.stop(); 
			}

			//timer for health so that player health only goes down once
			if (e.getSource() == playerInvulnerableTimer) {
				playerInvulnerable = false;
				if (player.playerHealth() == 0) {
					gameStop();
				}
				playerInvulnerableTimer.stop(); 
			}

			//Checking once all 20 pizzas have been killed
			if (pizzaCollectCounter == 0) {
				enemyTimer.stop();
				pizzaTimer.stop();
				levelThreeWon = true; 

				//crown and player intersection to leave level 
				if(player.playerMask().intersects(crownMask) && levelThreeWon == true && level != 0) {
					playerLevelTimer.stop(); 
					pizzaTimer.stop();
					playerInvulnerableTimer.stop(); 
					enemyInvulnerableTimer.stop(); 
					JOptionPane.showMessageDialog(null, "Level Complete!\n\nYum! I feel energized again! Let's keep looking for shredder!\nCowabunga!", "LEVEL WINNER", JOptionPane.PLAIN_MESSAGE, crown);
					levelWon();
				}

			}

		}

		// Spawning level 4 enemies and turrets
		if (level == 4) {
			//countdown 3,2,1 timer
			levelStartTimer.start();

			//reducing seconds for countdown 3,2,1
			if (e.getSource() == levelStartTimer) {
				seconds--; 
			}
			
			//randomly spawning enemies
			if (!enemySpawn && level == 4) {
				for (int i = 0; i < 5; i++) {
					enemy[i].enemySpawn(level); 

					enemySpawn = true;
				}
			}
			
			//level 4
			if (level == 4) {
				
				//randomly spawning enemies and setting health
				for (int i = 0; i < 5; i++) {
					if (enemy[i].getHealth() <= 0) {
						enemy[i].enemySpawn(level); 
						enemy[i].setHealth(40);
					}
				}
			}

			// Spawning turrets
			if (!turretSpawn && level == 4) {
				turret[0].setX(590);
				turret[0].setY(180);
				turret[1].setX(200);
				turret[1].setY(170);
				turret[0].getRadius();
				turret[1].getRadius();
				turretSpawn = true;
			}

			//when count down over level starts and enemies start moving
			if (seconds == 0) {
				levelStartTimer.stop(); 
				enemyTimer.start(); 
				playerLevelTimer.start();
				pizzaTimer.start();
				playerLevelTimer.start();
				turretTimer.start();
			}

			//moving the enemies
			if (e.getSource() == enemyTimer) {
				for (int i = 0; i < 4; i++) {
					if (enemy[i].getHealth() > 0) {
						enemy[i].enemyMove(level); 

						//deducting health from player if intersecting with enemy
						if (enemy[i].getBulletMask().intersects(player.playerMask()) && playerInvulnerable == false) {
							player.updateHealth(5);
							playerInvulnerable = true;
							playerInvulnerableTimer.start();
						}
						
						//deducting health from enemy if player attacks them
						if (enemy[i].enemyMask().intersects(player.playerAttackMask()) && enemyInvulnerable == false) {
							enemyInvulnerable = true; 
							enemy[i].updateHealth(20); 
							enemyInvulnerableTimer.start(); 
						}		

					}
				}

				// player and turret + bullet intersects
				for (int i = 0; i < 2; i++) {
					// deducting health from turret if intersecting with player
					if (turret[i].getMask().intersects(player.playerMask()) && turretInvulnerable == false && attack == true) {
						turret[i].updateHealth(2);
						turretInvulnerable = true;
						turretInvulnerableTimer.start();

						// Taking turret way off screen if its health is = 0
						if (turret[i].getHealth() <= 0) {
							turret[i].setY(-1000);
						}
					}

					// deducting health from player if player and bullet intersect
					if (turret[i].getBulletUnder().intersects(player.playerMask()) && playerInvulnerable == false) {
						player.updateHealth(5);
						playerInvulnerable = true;
						turret[i].updateBulletX(-1000);
						playerInvulnerableTimer.start();
					}

				}

			}
			
			//ensuring turret health only goes down once when player attacks
			if (e.getSource() == turretInvulnerableTimer) {
				turretInvulnerable = false; 
				turretInvulnerableTimer.stop(); 
			}

			// Checking if turret radius and player are intersecting
			if (e.getSource() == turretTimer) {
				for (int i = 0; i < 2; i++) {
					
					//shooting bullets in direction of player if player inside their radius
					if (turret[i].turretRadius.intersects(player.playerMask())) {
						if (turret[i].getX() < player.getX()) {
							turret[i].setDirX(2);

						}
						if (turret[i].getX() > player.getX()) {
							turret[i].setDirX(1);

						}
						if (turret[i].getY() < player.getY()) {
							turret[i].setDirY(4);

						}
						if (turret[i].getY() > player.getY()) {
							turret[i].setDirY(3);

						}
						turret[i].startTimer();



					}
				}
			}
			//timer for enemy health so health only goes down once
			if (e.getSource() == enemyInvulnerableTimer) {
				enemyInvulnerable = false; 
				enemyInvulnerableTimer.stop(); 
			}

			//timer for health so that player health only goes down once
			if (e.getSource() == playerInvulnerableTimer) {
				playerInvulnerable = false;
				if (player.playerHealth() == 0) {
					gameStop();
				}
				playerInvulnerableTimer.stop(); 
			}

			//Checking once both turrets have been destroyed
			if (turret[1].getHealth() <= 0 && turret[0].getHealth() <= 0) {
				enemyTimer.stop();
				turretTimer.stop();
				levelFourWon = true; 

				//crown and player intersection to leave level 
				if(player.playerMask().intersects(crownMask) && levelFourWon == true) {
					player.setX(-500);
					player.setY(-500);
					playerLevelTimer.stop(); 
					JOptionPane.showMessageDialog(null, "Level Complete!\nWe found his location! Lets go team!", "LEVEL WINNER", JOptionPane.PLAIN_MESSAGE, crown);
					levelWon();
				}

			}

		}
		
		//level 5
		if (level == 5) {

			//countdown 3,2,1 timer
			levelStartTimer.start();

			//reducing seconds for countdown 3,2,1
			if (e.getSource() == levelStartTimer) {
				seconds--; 
				if (seconds == 0) {
					bossAutoMove = true;
					levelStartTimer.stop();
					bossMoveTimer.start();
					bossActionTimer.start();
					playerLevelTimer.start();
				}
			}

			if (seconds <= 0) {
				// Automoving boss
				if (bossAutoMove == true) {
					boss.autoMove();
					if (boss.getX() <= 1000)
						bossAutoMove = false;
				}

				// Moving boss
				if (e.getSource() == bossMoveTimer) {
					boss.randomMove();
				}

				// Making boss do random action
				if (e.getSource() == bossActionTimer) {
					boss.randomAction();
				}
			}

		}

		// Updating boundary masks, bridge masks and player mask
		map.maskUpdate();
		maskUpdate();
		
		if (level == 5) {
			
			//updating masks
			playerBoss.updateBossMask();
			boss.updateMask();
		}

		// Repainting
		repaint();

	}


	public void paint (Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;

		// Drawing win screen
		if (level == -5) {
			g2.drawImage(winScreen.getImage(), 0, 0, this);
		}
		// Drawing lose screen
		if (level == - 4) {
			g2.drawImage(gameOver.getImage(), 0, 0, this);
		}

		// Drawing Title Screen
		if (level == -3) {
			g2.drawImage(titleScreen.getImage(), 0, 0, this);
			if (playButtonHover == true) {
				g2.drawImage(bigPlayButton.getImage(), WIDTH/2 - bigPlayButton.getIconWidth()/2, 485, this);
			}
			else if (playButtonHover == false) {
				g2.drawImage(playButton.getImage(), WIDTH/2 - playButton.getIconWidth()/2, 490, this);
			}

		}

		// story pages
		if (level == -2) {
			
			//drawing story pages
			if (storyPage < 7) {
				g2.drawImage(story[storyPage].getImage(), 0,0, this); 

				//setting font design
				g2.setColor(Color.WHITE); 
				g2.setFont(fontStory);
				if (storyPage < 4) {
					g2.drawString(newStr, 20, 500);
					g2.drawString(newStr2, 20, 530);
				}
				
				//changing location of text for specific page
				else if (storyPage == 4) {
					g2.setColor(Color.BLACK); 
					g2.drawString(newStr, 275, 130); 
				}

				// Drawing right scroll button
				if (rightScrollHover == true)
					g2.drawImage(rightScrollBigger.getImage(), 470, 467, this);
				else
					g2.drawImage(rightScroll.getImage(), 480, 477, this);

			}
		}

		// Drawing character selection screen
		if (level == - 1) {
			if (profile < 4)
				g2.drawImage(characterProfile[profile].getImage(), 75, 0, this);

			// Drawing left scroll button
			if (profile != 0) {
				if (leftScrollHover == true)
					g2.drawImage(leftScrollBigger.getImage(), 30, 467, this);
				else
					g2.drawImage(leftScroll.getImage(), 40, 477, this);
			}

			// Drawing right scroll button
			if (rightScrollHover == true)
				g2.drawImage(rightScrollBigger.getImage(), 470, 467, this);
			else
				g2.drawImage(rightScroll.getImage(), 480, 477, this);

		}

		// Drawing world map
		if (level == 0) {
			g2.drawImage(map.getWorldMapOne().getImage(), map.getXOne(), map.getYOne(), this);
			g2.drawImage(map.getWorldMapTwo().getImage(), map.getXTwo(), map.getYTwo(), this);

			// Checking whether player is on level one exclamation
			if (!playerMask.intersects(exclaimOneMask))
				g2.drawImage(exclamationIcon[0].getImage(), map.getXOne() + 115, map.getYOne() + 150, this);
			else if (playerMask.intersects(exclaimOneMask))
				g2.drawImage(levelIcon[0].getImage(), map.getXOne() + 65, map.getYOne() + 150, this);

			// Checking whether player is on level two exclamation
			if (!playerMask.intersects(exclaimTwoMask))
				g2.drawImage(exclamationIcon[1].getImage(), map.getXOne() + 495, map.getYOne() +  110, this);
			else if (playerMask.intersects(exclaimTwoMask)) 
				g2.drawImage(levelIcon[1].getImage(), map.getXOne() + 445, map.getYOne() + 110, this);

			// Checking whether player is on level three exclamation
			if (!playerMask.intersects(exclaimThreeMask))
				g2.drawImage(exclamationIcon[2].getImage(), map.getXTwo() + 75, map.getYTwo() + 580, this); 
			else if (playerMask.intersects(exclaimThreeMask))
				g2.drawImage(levelIcon[2].getImage(), map.getXTwo() + 25, map.getYTwo() + 580, this);

			// Checking whether player is on level four exclamation
			if (!playerMask.intersects(exclaimFourMask))
				g2.drawImage(exclamationIcon[3].getImage(), map.getXTwo() + 45, map.getYTwo() + 110, this);
			else if (playerMask.intersects(exclaimFourMask))
				g2.drawImage(levelIcon[3].getImage(), map.getXTwo() - 5, map.getYTwo() + 110, this);

			// Checking whether player is on boss level exclamation
			if (!playerMask.intersects(exclaimFiveMask))
				g2.drawImage(exclamationIcon[4].getImage(), map.getXTwo() + 535, map.getYTwo() + 370, this);
			else if (playerMask.intersects(exclaimFiveMask))
				g2.drawImage(levelIcon[4].getImage(), map.getXTwo() + 485, map.getYTwo() + 360, this);

			// Drawing the player
			if (emote == false)
				g2.drawImage(player.getImg(left).getImage(), player.getX(), player.getY(), this);
			else {
				// Centering emote depending on who it is
				if (turtleChoice == 2)
					g2.drawImage(player.getImgEmote(left).getImage(), player.getX() - 20, player.getY() - 20, this);
				else
					g2.drawImage(player.getImgEmote(left).getImage(), player.getX(), player.getY(), this);

			}

			if (levelFiveTravelDown == true)
				g2.drawImage(boss.getImg().getImage(), boss.getX(), boss.getY(), this);


		}

		// Drawing level one
		if (level == 1) {
			g2.drawImage(levelMap[0].getImage(), 0,0, this); 
			g2.drawImage(player.getImg().getImage(), player.getX(), player.getY(), this);

			if (seconds != 0) {
				g2.setFont(f);
				g2.setColor(Color.WHITE);
				g2.drawString(Integer.toString(seconds), (getWidth() / 2 - fm.stringWidth(Integer.toString(seconds)) / 2) +20, (getHeight() /2 + fm.getAscent() /2) -30);
			}

			//Drawing the health bar and background of player
			g2.setColor(Color.BLACK);
			g2.fill(player.healthBg());
			g2.setColor(Color.RED);
			g2.fill(player.health());

			//drawing the enemies
			for (int i = 0; i < 5; i++) {
				if (enemy[i].getHealth() > 0) { 
					g2.drawImage(enemy[i].getImg().getImage(), enemy[i].getX(), enemy[i].getY(), this); 

					g2.drawImage(enemy[i].enemyBullet().getImage(), enemy[i].bulletGetX(), enemy[i].bulletGetY(), this);

					//healthbar and background of enemies
					g2.setColor(Color.BLACK);
					g2.fill(enemy[i].healthBg());
					g2.setColor(Color.RED);
					g2.fill(enemy[i].health()); 
				}
			}
			if (levelOneWon == true) {
				g2.drawImage(crown.getImage(), 420, 170, this); 
			}
		}

		// Drawing level two		
		if (level == 2) {
			g2.drawImage(levelMap[1].getImage(), 0, 0, this);
			g2.drawImage(player.getImg().getImage(), player.getX(), player.getY(), this);
			
			//drawing seconds count down
			if (seconds != 0) {
				g2.setFont(f);
				g2.setColor(Color.WHITE);
				g2.drawString(Integer.toString(seconds), (getWidth() / 2 - fm.stringWidth(Integer.toString(seconds)) / 2) +20, (getHeight() /2 + fm.getAscent() /2) -30);
			}
			
			//drawing health potions while they are within screen boundaries
			if (healthOnScreen == true) {
				g2.drawImage(healthPlus.getImage(), healthSpawn, healthMovement, this) ; 	
			}
			
			//mask of health potion 
			healthMask = new Rectangle2D.Double(healthSpawn, healthMovement, healthPlus.getIconWidth(), healthPlus.getIconHeight()); 

			//allowing player to move once count down is over
			if (seconds <= 0) {
				playerLevelTimer.start();
			}

			//Drawing the health bar and background of player
			g2.setColor(Color.BLACK);
			g2.fill(player.healthBg());

			g2.setColor(Color.RED);
			g2.fill(player.health());


			//drawing the enemies
			for (int i = 0; i < 8; i++) {
				if (enemy[i].getHealth() > 0) { 
					g2.drawImage(enemy[i].getImg().getImage(), enemy[i].getX(), enemy[i].getY(), this); 
					
					//drawing enemy bullets
					g2.drawImage(enemy[i].enemyBullet().getImage(), enemy[i].bulletGetX(), enemy[i].bulletGetY(), this);

					//healthbar of enemies
					g2.setColor(Color.BLACK);
					g2.fill(enemy[i].healthBg());

					g2.setColor(Color.RED);
					g2.fill(enemy[i].health()); 
				}
			}
			
			//drawing crown once enemy wins level two
			if (levelTwoWon == true) {
				crownMask = new Rectangle2D.Double(400,280,30,30);
				g2.drawImage(crown.getImage(), 400, 280, this);
			}
		}

		// Drawing level three
		if (level == 3) {
			g2.drawImage(levelMap[2].getImage(), 0, 0, this);
			g2.drawImage(player.getImg().getImage(), player.getX(), player.getY(), this);
			
			//drawing count down timer
			if (seconds != 0) {
				g2.setFont(f);
				g2.setColor(Color.WHITE);
				g2.drawString(Integer.toString(seconds), (getWidth() / 2 - fm.stringWidth(Integer.toString(seconds)) / 2) +20, (getHeight() /2 + fm.getAscent() /2) -30);
			}
			
			//text for pizzas left
			g2.setFont(fontPizza);
			g2.setColor(Color.white);

			g2.drawString("Pizzas Left: " + pizzaCollectCounter, getWidth() - 140, 20);

			//Drawing the health bar and background of player
			g2.setColor(Color.BLACK);
			g2.fill(player.healthBg());

			g2.setColor(Color.RED);
			g2.fill(player.health());


			//drawing the enemies
			if (pizzaCollectCounter != 0 && seconds <= 0) {
				for (int i = 0; i < 5; i++) {
					if (enemy[i].getHealth() > 0) { 
						g2.drawImage(enemy[i].getImg().getImage(), enemy[i].getX(), enemy[i].getY(), this); 
						
						//drawing enemy bullets
						g2.drawImage(enemy[i].enemyBullet().getImage(), enemy[i].bulletGetX(), enemy[i].bulletGetY(), this);

						//healthbar of enemies
						g2.setColor(Color.BLACK);
						g2.fill(enemy[i].healthBg());

						g2.setColor(Color.RED);
						g2.fill(enemy[i].health()); 
					}
				}
			}
			// Drawing the pizzas
			if (pizzasCollected < 20) {
				g2.drawImage(pizza[pizzasCollected].getImg().getImage(), pizza[pizzasCollected].getX(), pizza[pizzasCollected].getY(), this);
			}
			
			//checking if level 3 was won by player to draw crown 
			if (levelThreeWon == true) {
				g2.drawImage(crown.getImage(), 420, 247, this); 
				crownMask = new Rectangle2D.Double(420,247,30,30);
			}
		}

		// Drawing level four
		if (level == 4) {
			
			//drawing level 4 map
			g2.drawImage(levelMap[3].getImage(), 0,0, this); 
			
			//drawing player
			g2.drawImage(player.getImg().getImage(), player.getX(), player.getY(), this);

			if (seconds != 0) {
				g2.setFont(f);
				g2.setColor(Color.WHITE);
				g2.drawString(Integer.toString(seconds), (getWidth() / 2 - fm.stringWidth(Integer.toString(seconds)) / 2) +20, (getHeight() /2 + fm.getAscent() /2) -30);
			}

			//Drawing the health bar and background of player
			g2.setColor(Color.BLACK);
			g2.fill(player.healthBg());

			g2.setColor(Color.RED);
			g2.fill(player.health());

			//drawing the enemies
			if (levelFourWon == false && seconds <= 0) {
				for (int i = 0; i < 5; i++) {
					if (enemy[i].getHealth() > 0) { 
						g2.drawImage(enemy[i].getImg().getImage(), enemy[i].getX(), enemy[i].getY(), this); 
						
						//drawing enemy bullets
						g2.drawImage(enemy[i].enemyBullet().getImage(), enemy[i].bulletGetX(), enemy[i].bulletGetY(), this);

						//healthbar of enemies
						g2.setColor(Color.BLACK);
						g2.fill(enemy[i].healthBg());

						g2.setColor(Color.RED);
						g2.fill(enemy[i].health()); 
					}
				}

				// Drawing the turrets
				for (int i = 0; i < 2; i++) {
					
					//drawing turret bullets
					g2.setColor(new Color(139, 0, 0));
					g2.fill(turret[i].bulletUnder);

					g2.setColor(Color.red);
					g2.fill(turret[i].bulletOver);

					//healthbar of enemies
					g2.setColor(Color.BLACK);
					g2.fill(turret[i].healthBg());

					g2.setColor(Color.RED);
					g2.fill(turret[i].health()); 

				}

				g2.drawImage(turret[0].getImgLeft().getImage(), turret[0].getX(), turret[0].getY(), this);
				g2.drawImage(turret[1].getImgRight().getImage(), turret[1].getX(), turret[1].getY(), this);

			}

			if (levelFourWon == true) {
				g2.drawImage(crown.getImage(), 420, 247, this); 
				crownMask = new Rectangle2D.Double(420,247,30,30);

			}
		}

		// Drawing level five
		if (level == 5) {
			// Draw Background and player + boss health
			g2.drawImage(boss.getBG().getImage(), 0, 0, this);

			g2.setColor(Color.BLACK);
			g2.fill(playerBoss.healthBg());
			g2.fill(boss.healthBg());
			g2.fill(boss.healthBgTwo());

			g2.setColor(Color.RED);
			g2.fill(playerBoss.getHealthBar());
			g2.fill(boss.getHealthOne());
			g2.fill(boss.getHealthTwo());
			
			//drawing boss shield and invulnerability
			g2.setColor(Color.BLUE);
			if (playerBoss.isBlocking() == true)
				g2.fill(playerBoss.getMask());
			if (boss.isInvul() == true || boss.activatingInvul() == true)
				g2.fill(boss.getMask());

			// Drawing player, boss, player shield, boss shield and boss projectile
			if (!boss.isAttacking())
				g2.drawImage(boss.getImg().getImage(), boss.getX(), boss.getY(), this);
			g2.drawImage(boss.getImgBullet().getImage(), boss.getBulletX(), boss.getBulletY(), this);
			g2.drawImage(playerBoss.getImg().getImage(), playerBoss.getX(), playerBoss.getY(), this);
			if (boss.isAttacking())
				g2.drawImage(boss.getImg().getImage(), boss.getX(), boss.getY(), this);
			
			//drawing count down timer
			if (seconds > 0) {
				g2.setFont(f);
				g2.setColor(Color.WHITE);
				g2.drawString(Integer.toString(seconds), (getWidth() / 2 - fm.stringWidth(Integer.toString(seconds)) / 2) +20, (getHeight() /2 + fm.getAscent() /2) -30);
			}
			else if (seconds == 0)
				g2.drawString("GO!", (getWidth() / 2 - fm.stringWidth(Integer.toString(seconds)) / 2) +20, (getHeight() /2 + fm.getAscent() /2) -30);
		}

	}



	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {

		// Checking whether user presses WASD to move
		if (e.getKeyCode() == KeyEvent.VK_W)
			up = true;
		if (e.getKeyCode() == KeyEvent.VK_S)
			down = true;
		if (e.getKeyCode() == KeyEvent.VK_A)
			left = true;
		if (e.getKeyCode() == KeyEvent.VK_D)
			right = true;

		// Checking whether user wants to emote
		if (e.getKeyCode() == KeyEvent.VK_E)
			emote = true;

		// Checking whether player wants to enter level one
		if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimOneMask) && level == 0 && !levelOneWon) {
			levelOneTravel = true;
			playerMapTimer.stop();
			levelTravelTimer.start();
		}
		// Stopping them from re-entering if they beat the level already
		else if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimOneMask) && level == 0 && levelOneWon) {
			levelFinishMessage();
		}

		// Checking whether user wants to enter level two
		if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimTwoMask) && level == 0 && !levelTwoWon && levelOneWon) {
			levelTwoTravel = true;
			playerMapTimer.stop();
			levelTravelTimer.start();
		}
		// Stopping them from re-entering if they beat the level already
		else if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimTwoMask) && level == 0 && levelTwoWon) {
			levelFinishMessage();
		}
		// Printing that they need to complete the previous level to enter this one
		else if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimTwoMask) && level == 0 && !levelTwoWon && !levelOneWon) {
			movementOff();
			JOptionPane.showMessageDialog(null, "Must complete level 1 to enter level 2!", "Incomplete Levels", JOptionPane.INFORMATION_MESSAGE);
		}



		// Checking whether user wants to enter level three
		if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimThreeMask) && level == 0 && !levelThreeWon && levelTwoWon) {
			levelThreeTravel = true;
			playerMapTimer.stop();
			levelTravelTimer.start();
		}
		// Stopping them from re-entering if they beat the level already
		if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimThreeMask) && level == 0 && levelThreeWon) {
			levelFinishMessage();
		}
		// Telling them to complete previous levels to access this level
		else if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimThreeMask) && level == 0 && !levelTwoWon) {
			movementOff();
			JOptionPane.showMessageDialog(null, "Must complete level 2 to unlock level 3!", "Incomplete Levels", JOptionPane.INFORMATION_MESSAGE);
		}

		// Checking whether user wants to enter level four
		if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimFourMask) && level == 0 && !levelFourWon && levelThreeWon) {
			levelFourTravel = true;
			playerMapTimer.stop();
			levelTravelTimer.start();
		}
		// Stopping them from re-entering if they beat the level already
		else if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimFourMask) && level == 0 && levelFourWon) {
			levelFinishMessage();
		}
		// Telling them to complete previous levels to access this level
		else if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimFourMask) && level == 0 && !levelThreeWon) {
			movementOff();
			JOptionPane.showMessageDialog(null, "Must complete level 3 to unlock level 4!", "Incomplete Levels", JOptionPane.INFORMATION_MESSAGE);
		}

		// Checking whether user wants to enter boss level
		if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimFiveMask) && level == 0 && levelFourWon)  {
			levelFiveTravel = true;
			levelFiveTravelDown = true;
			map.setMapX(-885);
			playerMapTimer.stop();
			levelTravelTimer.start();
		}
		// Telling them to complete previous levels to access this level
		else if (e.getKeyCode() == KeyEvent.VK_SPACE && playerMask.intersects(exclaimFiveMask) && level == 0) {
			movementOff();
			JOptionPane.showMessageDialog(null, "Must complete level 4 to unlock boss level!", "Incomplete Levels", JOptionPane.INFORMATION_MESSAGE);
		}

		// Checking whether user attacked or blocked in level 5

		if (e.getKeyCode() == KeyEvent.VK_J && block == false)
			attack = true;
		if (e.getKeyCode() == KeyEvent.VK_K && block == false) 
			block = true;


	}

	public void keyReleased(KeyEvent e) {

		// Checking whether user releases WASD to move
		if (e.getKeyCode() == KeyEvent.VK_W)
			up = false;
		if (e.getKeyCode() == KeyEvent.VK_S)
			down = false;
		if (e.getKeyCode() == KeyEvent.VK_A)
			left = false;
		if (e.getKeyCode() == KeyEvent.VK_D)
			right = false;

		if (e.getKeyCode() == KeyEvent.VK_E)
			emote = false;

		if (e.getKeyCode() == KeyEvent.VK_J)
			attack = false;
		if (e.getKeyCode() == KeyEvent.VK_K) 
			block = false;

	}

	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		//checking if player hovers over play button on title screen
		if (level == -3) {
			if (e.getX() >= WIDTH/2 - playButton.getIconWidth()/2 && e.getX() <= 225 + playButton.getIconWidth() 
			&& e.getY() >= 490 && e.getY() <= 490 + playButton.getIconWidth()) {
				playButtonHover = true;

			}
			else if (e.getX() <= WIDTH/2 - bigPlayButton.getIconWidth()/2 || e.getX() >= 225 + bigPlayButton.getIconWidth() 
			|| e.getY() <= 490 || e.getY() >= 490 + playButton.getIconWidth()) {
				playButtonHover = false;
			}
		}

		if (level == -2) {
			// Checking if they hover right button
			if (e.getX() >= 480 && e.getX() <= 550 && e.getY() >= 467 && e.getY() <= 537) {
				rightScrollHover = true;
			}
			else {
				rightScrollHover = false;
			}
		}

		if (level == -1) {

			// Checking if they hover left button
			if (e.getX()  >= 30 && e.getX() <= 100 && e.getY() >= 467 && e.getY() <= 537) {
				leftScrollHover = true;
			}
			else {
				leftScrollHover = false;
			}

			// Checking if they hover right button
			if (e.getX()  >= 480 && e.getX() <= 550 && e.getY() >= 467 && e.getY() <= 537) {
				rightScrollHover = true;
			}
			else {
				rightScrollHover = false;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		//checking if play button is pressed on title screen
		if (level == -3) {
			if (e.getX() >= WIDTH/2 - playButton.getIconWidth()/2 && e.getX() <= 225 + playButton.getIconWidth() 
			&& e.getY() >= 490 && e.getY() <= 490 + playButton.getIconWidth()) {
				level = -2;
			}
		}
		
		//checking if right button is pressed on story pages
		if (level == -2) {
			if (e.getX() >= 480 && e.getX() <= 550 && e.getY() >= 467 && e.getY() <= 537) {
				if (storyPage < 5) {
					storyPage += 1;
					newStr = ""; 
					stringCounter = 0; 
					newStr2 = "";
					stringCounter2 = 0;
					System.out.print(storyPage); 
				}
				else if (storyPage == 5) {
					level = -1;
				}
			}
		}
		
		//profile selection
		if (level == -1) {

			if (profile != 0) {
				// Checking if they hover left scroll button
				if (e.getX()  >= 30 && e.getX() <= 100 && e.getY() >= 467 && e.getY() <= 537) {
					profile -= 1;
				}
			}

			// Checking if they hover right button
			if (e.getX()  >= 480 && e.getX() <= 550 && e.getY() >= 467 && e.getY() <= 537) {
				profile += 1;
				if (profile == 4) {
					// Asking player if they are sure that they are ready to choose
					characterConfirm = JOptionPane.showConfirmDialog(null, "Are you ready to select your character?", "Character Select", JOptionPane.YES_NO_CANCEL_OPTION);
					if (characterConfirm == JOptionPane.YES_OPTION) {
						while (validTurtleChoice == false) {
							// Asking player what character they would play
							turtleChoiceString = JOptionPane.showInputDialog(null, "Which character will you play?", "Character Select", JOptionPane.QUESTION_MESSAGE);
							// Bringing them back to select screen if they press the X
							if (turtleChoiceString == null) {
								profile = 0;
								break;
							}
							// Checking if they wrote donny's name
							else if (turtleChoiceString.equalsIgnoreCase("donny") == true || turtleChoiceString.equalsIgnoreCase("donatello") == true) {
								turtleChoice = 1;
								validTurtleChoice = true;
							}

							// Checking if they wrote leo's name
							else if (turtleChoiceString.equalsIgnoreCase("leo") || turtleChoiceString.equalsIgnoreCase("leonoardo")) {
								turtleChoice = 2;
								validTurtleChoice = true;
							}

							// Checking if they wrote raph's name
							else if (turtleChoiceString.equalsIgnoreCase("raph") || turtleChoiceString.equalsIgnoreCase("raphael")) {
								turtleChoice = 3;
								validTurtleChoice = true;
							}

							// Checking if they wrote mikey's name
							else if (turtleChoiceString.equalsIgnoreCase("mikey") || turtleChoiceString.equalsIgnoreCase("michaelango")) {
								turtleChoice = 4;
								validTurtleChoice = true;
							}
							else {
								turtleChoiceString = "";
								JOptionPane.showMessageDialog(null, "Invalid turtle option. Try again.", "Character Select", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						// Showing them who they picked and starting the level
						if (validTurtleChoice == true) {
							// Telling player who they selected
							JOptionPane.showMessageDialog(null, "You chose " + turtleChoiceString.toUpperCase() + "!", "Character Select", JOptionPane.INFORMATION_MESSAGE);
							player.setTurtleChoice(turtleChoice);
							playerBoss.setTurtleChoice(turtleChoice);
							level = 0;
						}

					}
					else
						profile -= 1;
				}
			}

		}

	}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	//starting game values
	public void gameStart() {
		player.setX(220);
		player.setY(220);

		map.reset();

		speed = 10;
		deathChance = -1;
		level = -3;
		turtleChoice = -1;
		validTurtleChoice = false;
		profile = 0;
		seconds = 3;
		pizzaCollectCounter = 20;
		pizzasCollected = 0;

		player.setHealth(100); 
		for (int i = 0; i < enemy.length; i++) {
			enemy[i].setHealth(40);
		}

		movementOff();
		turtleChoiceString = "";

		playerMapTimer.start();
	}

	//stopping all timers
	public void allTimersStop() {
		playerMapTimer.stop();
		bridgeTimer.stop();
		levelTravelTimer.stop();
		playerLevelTimer.stop();
		bossMoveTimer.stop();
		bossActionTimer.stop();
		enemyTimer.stop();
		playerInvulnerableTimer.stop();
		levelStartTimer.stop();
		bossHitTimer.stop();
		playerHitTimer.stop();
		bossPassiveDamageTimer.stop();
		cooldownTimer.stop();
		enemyInvulnerableTimer.stop();
		pizzaTimer.stop();
		turretTimer.stop();
		turretInvulnerableTimer.stop();
		healthSpeedTimer.stop();
		healthPlusTimer.stop();
		healthInvulnerableTimer.stop();
	}

	public void gameStop() {

		// Turning off all timers
		allTimersStop();

		// Turning off boolean variables


		// Resetting frame 
		HEIGHT = 600;
		WIDTH = 600;
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);

		// Drawing win screen if they've won, lose screen if they've lost
		if (gameWon == true)
			level = - 5;
		else if (gameWon == false)
			level =- 4;

		repaint();
	}

	//dying on bridge
	public void bridgeDeath() {
		if (playerMask.intersects(bridgeTwoHalfway)) {
			JOptionPane.showMessageDialog(null, "Oh no! Master Splinter was right!"
					+ "\nThe bridge collapses and the turtles fall into the sewers, not able to recover the pizza."
					+ "\nShredder laughs maniacally and eats the pizza as the turtles wallow with hungry tears.");	
			gameWon = false;
			gameStop();
		}
	}
	
	//exiting up the bridge
	public void bridgeExitUp() {
		bridgeTimer.stop();
		playerMapTimer.start();
		goUpBridge = false;
		emote = false;
		goUpBridgeTwo = false;
		map.updateMapY(speed*8);
		up = false;
		left = false;
		right = false;
	}

	//exiting down the bridge
	public void bridgeExitDown() {
		bridgeTimer.stop();
		playerMapTimer.start();
		goDownBridge = false;
		emote = false;
		goDownBridgeTwo = false;
		map.updateMapY(-80);
		down = false;
		left = false;
		right = false;
	}
	
	//updating masks
	public void maskUpdate() {
		playerMask = new Rectangle2D.Double(player.getX(), player.getY(), player.getImg(left).getIconWidth(), player.getImg(left).getIconHeight());
		bridgeOneEnter = new Rectangle2D.Double(map.getXOne() + 415, map.getYTwo() + 375, 20, 10);
		bridgeOneExit = new Rectangle2D.Double(map.getXOne() + 415, map.getYTwo() + 520, 20, 10);
		bridgeTwoEnter = new Rectangle2D.Double(map.getXOne() + 565, map.getYTwo() + 375, 25, 10);
		bridgeTwoExit = new Rectangle2D.Double(map.getXOne() + 565, map.getYTwo() + 520, 25, 10);
		bridgeTwoHalfway = new Rectangle2D.Double(map.getXOne() + 565, map.getYTwo() + 450, 25, 10);
		exclaimOneMask = new Rectangle2D.Double(map.getXOne() + 115, map.getYOne() + 150, 10, 40);
		exclaimTwoMask = new Rectangle2D.Double(map.getXOne() + 495, map.getYOne() + 100, 10, 40);
		exclaimThreeMask = new Rectangle2D.Double(map.getXTwo() + 75, map.getYTwo() + 580, 10, 40);
		exclaimFourMask = new Rectangle2D.Double(map.getXTwo() + 45, map.getYTwo() + 110, 10, 40);
		exclaimFiveMask = new Rectangle2D.Double(map.getXTwo() + 535, map.getYTwo() + 360, 10, 40);
	}
	
	//method for if player wins a level
	public void levelWon() {
		//resetting all values and bringing them back to world map
		map.reset();

		for (int i = 0; i < enemyDeathBooleanCounter.length; i++) {
			enemyDeathBooleanCounter[i] = false;
		}

		player.setX(220);
		player.setY(220);

		speed = 10;
		deathChance = -1;
		level = 0;
		movementOff();
		playerLevelTimer.stop();
		playerMapTimer.start();
		enemyDeathCounter = 0;
		seconds = 3; 

		player.setHealth(100); 
		for (int i = 0; i < 15; i++) {
			enemy[i].setHealth(40);
		}
		enemySpawn = false;
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
	}
	
	//text if a level has already been finished
	public void levelFinishMessage() {
		movementOff();
		JOptionPane.showMessageDialog(null, "You have already beaten this level! Keep advancing " + turtleChoiceString.toUpperCase() + "!");
	}
	
	//turning off player movement
	public void movementOff () {
		up = false;
		down = false;
		right = false;
		left = false;
		attack = false;
	}	
}