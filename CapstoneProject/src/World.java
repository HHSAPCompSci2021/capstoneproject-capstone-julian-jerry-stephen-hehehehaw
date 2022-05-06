import Players.Player;
import processing.core.PApplet;

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
	
	Player player = new Player(this);
	public World() {
		
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		
		player.avatar.up1 = loadImage("Assets" + fileSeparator + "heeheeheehaw.png");
		player.avatar.up2 = loadImage("Assets"  + fileSeparator + "kingcrying.jpg");
		player.avatar.down1 = loadImage("Assets" + fileSeparator + "heeheeheehaw.png");
		player.avatar.down2 = loadImage("Assets" + fileSeparator + "kingcrying.jpg");
		player.avatar.left1 = loadImage("Assets" + fileSeparator + "heeheeheehaw.png");
		player.avatar.left2 = loadImage("Assets" + fileSeparator + "kingcrying.jpg");
		player.avatar.right1 = loadImage("Assets" + fileSeparator + "heeheeheehaw.png");
		player.avatar.right2 = loadImage("Assets" + fileSeparator + "kingcrying.jpg");
		

	}
	
	
	public void draw() { 
		background(255);  
		fill(255);
		textAlign(CENTER);
		player.draw();
	}
			 
	public	void keyPressed() {
			  final int k = keyCode;
			  player.setDirection(k, true);
			}
			 
	public void keyReleased() {
			  player.setDirection(keyCode, false);
			}


		

		
	
	
	
	public void mousePressed() {
		
	}

	
	public void mouseDragged() {
		
	}

	
	

}

