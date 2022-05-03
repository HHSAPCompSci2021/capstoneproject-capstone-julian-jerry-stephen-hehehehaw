import processing.core.PApplet;

public class World extends PApplet{
	
	private final int originalTileSize = 16;
	private final int scale = 5;
	int tileSize = scale * originalTileSize;
	
	int maxScreenCol = 16;
	int maxScreenRow = 9;
	public int screenWidth = maxScreenCol * tileSize;
	public int screenHeight = maxScreenRow * tileSize;
	
	
	public World() {
		
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {

	}
	
	
	public void draw() { 
		background(0);  
		fill(255);
		textAlign(CENTER);
		
		

		
		
	}
	
	
	public void mousePressed() {
		
	}
	
	
	public void mouseDragged() {
		
	}
	
	
}

