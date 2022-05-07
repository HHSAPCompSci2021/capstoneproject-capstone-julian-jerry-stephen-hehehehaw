import java.util.ArrayList;

import Players.Player;
import Tiles.TileManager;
import processing.core.PApplet;
import processing.core.PImage;

public class World extends PApplet{
	
	public final static String fileSeparator = System.getProperty("file.separator");
	public final static String lineSeparator = System.getProperty("line.separator");
	public final static String userDir = System.getProperty("user.dir");
	private final int originalTileSize = 16;
	private final int scale = 5;
	int tileSize = scale * originalTileSize;
	
	int maxScreenCol = 16;
	int maxScreenRow = 9;
	public int screenWidth = maxScreenCol * tileSize;
	public int screenHeight = maxScreenRow * tileSize;
	
	private PImage[] playerImage = new PImage[8];
	
	
	Player player;
	TileManager tM = new TileManager();
	
	public World() {
		
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {

		playerImage[0] = loadImage("Assets" + fileSeparator + "BlueAvatar" + fileSeparator + "Backwards1.png");
		playerImage[1] = loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Forward1.png");
		playerImage[2] = loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Backwards2.png");
		playerImage[3] = loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Forward2.png");
		playerImage[4] = loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Left1.png");
		playerImage[5] = loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Right1.png");
		playerImage[6] = loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Left2.png");
		playerImage[7] = loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Right2.png");

		player =  new Player(this, playerImage);
//		player.avatar.up1 = loadImage("Assets" + fileSeparator + "heeheeheehaw.png");
//		player.avatar.up2 = loadImage("Assets"  + fileSeparator + "kingcrying.jpg");
//		player.avatar.down1 = loadImage("Assets" + fileSeparator + "heeheeheehaw.png");
//		player.avatar.down2 = loadImage("Assets" + fileSeparator + "kingcrying.jpg");
//		player.avatar.left1 = loadImage("Assets" + fileSeparator + "heeheeheehaw.png");
//		player.avatar.left2 = loadImage("Assets" + fileSeparator + "kingcrying.jpg");
//		player.avatar.right1 = loadImage("Assets" + fileSeparator + "heeheeheehaw.png");
//		player.avatar.right2 = loadImage("Assets" + fileSeparator + "kingcrying.jpg");
		


		tM.setImage(loadImage("Assets" + fileSeparator + "heeheeheehaw.png"));
		
	}
	
	
	public void draw() { 
		background(255);  
//		fill(255);
		textAlign(CENTER);
		
		tM.draw(this);
		player.draw(this);
		
//		noFill();
//		rect(player.getRectangle().x, player.getRectangle().y, player.getRectangle().width, player.getRectangle().height);
	}
			 
	public	void keyPressed() {
			  final int k = keyCode;
			  player.setDirection(k, true);
			  player.avatar.setDirection(k, true);
			}
			 
	public void keyReleased() {
			  player.setDirection(keyCode, false);
			}


		

		
	
	
	
	public void mousePressed() {
		
	}

	
	public void mouseDragged() {
		
	}

	
	

}

