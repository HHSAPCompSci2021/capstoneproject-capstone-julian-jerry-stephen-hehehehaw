import processing.core.PApplet;

public class World extends PApplet{
	
	private final int originalTileSize = 16;
	private final int scale = 5;
	int tileSize = scale * originalTileSize;
	
	int maxScreenCol = 16;
	int maxScreenRow = 9;
	public int screenWidth = maxScreenCol * tileSize;
	public int screenHeight = maxScreenRow * tileSize;
	float x, y;
	
	
	public World() {
		
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {

	}
	
	
	public void draw() { 
		background(255);  
		fill(255);
		textAlign(CENTER);
		
		fill(0);
		rect(x, y, 100, 100);
		fill(255);
		
		

		
		
	}
	
	
	public void mousePressed() {
		
	}

	
	public void mouseDragged() {
		
	}
	
	public void keyPressed() {
		if (key == 'w' || key == 'W') {
			y-=10;
		}
		if (key == 'a' || key == 'A') {
			x-=10;
		}
		if (key == 's' || key == 'S') {
			y+=10;			
		}
		if (key == 'd' || key == 'D') {
			x+=10;
		}
	
	}
	

}

