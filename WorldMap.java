import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class WorldMap {



	// Fields
	private Player player;
	private ImageIcon worldMapOne, worldMapTwo;
	private int mapOneX, mapOneY, mapTwoX, mapTwoY, speed;
	private Rectangle2D playerMask, mapMaskLeft, mapMaskRight, mapMaskUp, mapMaskDown, wallOne,
	wallTwoLeft, wallTwoRight, wallTwoDown, wallThreeRight, wallThreeLeft, wallThreeDown, wallFourLeft,
	wallFourDown, waterOneLeft, waterOneRight, waterOneDown, waterTwoDownB, waterTwoRight, waterTwoDown, 
	borderOneUp, borderOneDown, wallFiveRight, wallFiveRightTwo, wallFiveUp;


	// Constructors
	public WorldMap(int WIDTH, int HEIGHT) {

		// Creating player object and assigning map images
		player = new Player(WIDTH, HEIGHT);
		worldMapOne = new ImageIcon("images/backgrounds/map1fr.JPG");
		worldMapTwo = new ImageIcon("images/backgrounds/map2fr.JPG");

		// setting original map location and map moving speed
		mapOneX = WIDTH/2 - 100;
		mapOneY = HEIGHT/2 - 300;
		mapTwoX = mapOneX + worldMapOne.getIconWidth();
		mapTwoY = mapOneY;
		speed = 10;


		// Assigning Mask Values
		playerMask = new Rectangle2D.Double(player.getX() + 10, player.getY() + 10, player.getImg(true).getIconWidth(), player.getImg(true).getIconHeight() - 20);

		mapMaskLeft = new Rectangle2D.Double(mapOneX - 10, mapOneY - 10, 10, worldMapOne.getIconHeight());
		mapMaskRight = new Rectangle2D.Double(mapTwoX + worldMapTwo.getIconWidth(), mapTwoY, 10, worldMapTwo.getIconHeight());
		mapMaskUp = new Rectangle2D.Double(mapOneX, mapOneY - 10, 1200, 10);
		mapMaskDown = new Rectangle2D.Double(mapOneX, mapOneY + 660, 1200, 10);
		
		wallOne = new Rectangle2D.Double(mapOneX, mapOneY, 1200, 115);
		wallTwoLeft = new Rectangle2D.Double(mapOneX + 540, mapOneY, 10, 245);
		wallTwoRight = new Rectangle2D.Double(mapOneX + 600, mapOneY, 10, 245);	
		wallTwoDown = new Rectangle2D.Double(mapOneX + 550, mapOneY + 255, 50, 15);

		wallThreeLeft = new Rectangle2D.Double(mapTwoX + 165, mapTwoY, 10, 180);
		wallThreeRight = new Rectangle2D.Double(mapTwoX + 230, mapTwoY, 10, 180);
		wallThreeDown = new Rectangle2D.Double(mapTwoX + 175, mapTwoY + 190, 50, 10);

		wallFourLeft = new Rectangle2D.Double(mapTwoX + 550, mapTwoY, 10, 180);
		wallFourDown = new Rectangle2D.Double(mapTwoX + 560, mapTwoY + 190, 50, 10);
		
		wallFiveRight = new Rectangle2D.Double(mapTwoX + 20, mapOneY + 540, 10, 30);
		wallFiveRightTwo = new Rectangle2D.Double(mapTwoX + 90, mapTwoY + 595, 10, 60);
		wallFiveUp = new Rectangle2D.Double(mapTwoX + 15, mapOneY + 565, 60, 10);

		waterOneLeft = new Rectangle2D.Double(mapOneX + 155, mapTwoY + 75, 10, 120);
		waterOneRight = new Rectangle2D.Double(mapOneX + 375, mapTwoY + 75, 10, 120);
		waterOneDown = new Rectangle2D.Double(mapOneX + 165, mapTwoY + 205, 210, 10);

		waterTwoRight = new Rectangle2D.Double(mapOneX + 375, mapTwoY + 300, 10, 370);
		waterTwoDown = new Rectangle2D.Double(mapOneX + 155, mapTwoY + 285, 215, 10);
		waterTwoDownB = new Rectangle2D.Double(mapOneX, mapTwoY + 285, 150, 10);

		borderOneUp = new Rectangle2D.Double(mapOneX + 390, mapTwoY + 365, 800, 10);
		borderOneDown = new Rectangle2D.Double(mapOneX + 390, mapTwoY + 525, 300, 10);
	}

	// Methods
	
	// Moving the map 
	public void mapMove (boolean left, boolean right, boolean up, boolean down) {
		// Allowing player to move up if they don't hit any borders
		if (!playerMask.intersects(mapMaskUp) && !playerMask.intersects(wallOne)
				&& !playerMask.intersects(wallTwoDown) && !playerMask.intersects(wallThreeDown)
				&& !playerMask.intersects(wallFourDown) && !playerMask.intersects(waterOneDown)
				&& !playerMask.intersects(borderOneDown) && !playerMask.intersects(wallFiveUp)) {
			if (up == true) {
				mapOneY += speed;
				mapTwoY += speed;
			}
		}
		// Allowing player to move down if they don't hit any borders
		if (!playerMask.intersects(mapMaskDown) && !playerMask.intersects(waterTwoDown)
				&& !playerMask.intersects(waterTwoDownB) && !playerMask.intersects(borderOneUp)) {
			if (down == true) {
				mapOneY -= speed;
				mapTwoY -= speed;
			}
		}
		// Allowing player to move left if they don't hit any borders
		if (!playerMask.intersects(mapMaskLeft) && !playerMask.intersects(wallTwoRight)
				&& !playerMask.intersects(wallThreeRight) && !playerMask.intersects(waterOneRight)
				&& !playerMask.intersects(waterTwoRight)) {
			if (left == true) {
				mapOneX += speed;
				mapTwoX += speed;
			}
		}
		// Allowing player to move right if they don't hit any borders
		if (!playerMask.intersects(mapMaskRight) && !playerMask.intersects(wallTwoLeft)
				&& !playerMask.intersects(wallThreeLeft) && !playerMask.intersects(wallFourLeft)
				&& !playerMask.intersects(waterOneLeft) && !playerMask.intersects(wallFiveRightTwo)
				&& !playerMask.intersects(wallFiveRight)) {
			if (right == true) {
				mapOneX -= speed;
				mapTwoX -= speed;
			}
		}


	}

	// Updating all masks
	public void maskUpdate() {

		// Updating player mask
		playerMask = new Rectangle2D.Double(player.getX() + 10, player.getY() + 10, player.getImg(true).getIconWidth(), player.getImg(true).getIconHeight() - 20);
		
		// Updating main border mask
		mapMaskLeft = new Rectangle2D.Double(mapOneX - 10, mapOneY - 10, 10, worldMapOne.getIconHeight());
		mapMaskRight = new Rectangle2D.Double(mapTwoX + worldMapTwo.getIconWidth(), mapTwoY, 10, worldMapTwo.getIconHeight());
		mapMaskUp = new Rectangle2D.Double(mapOneX, mapOneY - 10, 1200, 10);
		mapMaskDown = new Rectangle2D.Double(mapOneX, mapOneY + 650, 1200, 10);

		// Updating wall one masks
		wallOne = new Rectangle2D.Double(mapOneX, mapOneY, 1200, 115);
		wallTwoLeft = new Rectangle2D.Double(mapOneX + 540, mapOneY, 10, 245);

		// Updating wall two masks
		wallTwoRight = new Rectangle2D.Double(mapOneX + 600, mapOneY, 10, 245);	
		wallTwoDown = new Rectangle2D.Double(mapOneX + 550, mapOneY + 255, 50, 15);

		// Updating wall three masks
		wallThreeLeft = new Rectangle2D.Double(mapTwoX + 165, mapTwoY, 10, 180);
		wallThreeRight = new Rectangle2D.Double(mapTwoX + 230, mapTwoY, 10, 180);
		wallThreeDown = new Rectangle2D.Double(mapTwoX + 175, mapTwoY + 190, 50, 10);

		// Updating wall four masks
		wallFourLeft = new Rectangle2D.Double(mapTwoX + 550, mapTwoY, 10, 180);
		wallFourDown = new Rectangle2D.Double(mapTwoX + 560, mapTwoY + 190, 50, 10);

		// Updating wall five masks
		wallFiveRight = new Rectangle2D.Double(mapTwoX + 40, mapOneY + 540, 10, 30);
		wallFiveRightTwo = new Rectangle2D.Double(mapTwoX + 90, mapTwoY + 595, 10, 60);
		wallFiveUp = new Rectangle2D.Double(mapTwoX + 30, mapOneY + 565, 60, 10);
		
		// Updating water one masks
		waterOneLeft = new Rectangle2D.Double(mapOneX + 155, mapTwoY + 75, 10, 120);
		waterOneRight = new Rectangle2D.Double(mapOneX + 375, mapTwoY + 75, 10, 120);
		waterOneDown = new Rectangle2D.Double(mapOneX + 165, mapTwoY + 205, 210, 10);

		// Updating water two masks
		waterTwoRight = new Rectangle2D.Double(mapOneX + 375, mapTwoY + 300, 10, 370);
		waterTwoDown = new Rectangle2D.Double(mapOneX + 155, mapTwoY + 285, 215, 10);
		waterTwoDownB = new Rectangle2D.Double(mapOneX, mapTwoY + 285, 150, 10);

		// Updating border masks
		borderOneUp = new Rectangle2D.Double(mapOneX + 390, mapTwoY + 365, 800, 10);
		borderOneDown = new Rectangle2D.Double(mapOneX + 390, mapTwoY + 525, 300, 10);

	}

	public Rectangle2D getMaskUp () {
		return mapMaskUp;
	}
	
	public Rectangle2D getMaskDown () {
		return mapMaskDown;
	}
	
	// Returns the x value of the first image of the map
	public int getXOne () {
		return mapOneX;
	}

	// Returns the x value of the second image of the map
	public int getXTwo () {
		return mapTwoX;
	}

	// Returns the y value of the first image of the map
	public int getYOne () {
		return mapOneY;
	}

	// Returns the y value of the second image of the map
	public int getYTwo () {
		return mapTwoY;
	}

	// Updates both x positions of the map by the passed in number value
	public void updateMapX(int pixel) {
		mapOneX += pixel;
		mapTwoX  += pixel;
	}
	
	// Updates both y positions of the map by the passed in number value
	public void updateMapY(int pixel) {
		mapOneY += pixel;
		mapTwoY += pixel;
	}
	
	// Sets the maps x position to a specified number
	public void setMapX (int location) {
		mapOneX = location;
		mapTwoX = mapOneX + 600;
	}
	
	// Sets the maps y position to a specified number
		public void setMapY (int location) {
			mapOneY = location;
			mapTwoY = mapOneY;
		}
 
	// Returns the first image icon of the map
	public ImageIcon getWorldMapOne() {
		return worldMapOne;
	}

	// Returns the second image icon of the map
	public ImageIcon getWorldMapTwo() {
		return worldMapTwo;
	}

	// Resets map to original location
	public void reset() {
		mapOneX = 600/2 - 100;
		mapOneY = 600/2 - 300;
		mapTwoX = mapOneX + worldMapOne.getIconWidth();
		mapTwoY = mapOneY;
	}
	
}
