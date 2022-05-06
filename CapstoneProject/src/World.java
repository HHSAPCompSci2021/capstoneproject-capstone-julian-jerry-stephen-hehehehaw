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
	boolean north;
	boolean south;
	boolean west;
	boolean east;
	
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
			 
		moveObject();
			//  confineToEdges();
		fill(0);
		rect(x, y, 100, 100);
	}
			 
	public	void keyPressed() {
			  final int k = keyCode;
			  setDirection(k, true);
			}
			 
	public void keyReleased() {
			  setDirection(keyCode, false);
			}
			 
	public void setDirection(int k, boolean decision) {
			  if      (k == 'w'    || k == 'W')   north = decision;
			  else if (k == 's'  || k == 'S')   south = decision;
			  else if (k == 'a'  || k == 'A')   west  = decision;
			  else if (k == 'd' || k == 'D')   east  = decision;
			}
			 
	public void moveObject() {
			  x += (east?  20 : 0) - (west?  20 : 0); //ternary condition, if east is true add 20, if east is false add 0
			  y += (south? 20 : 0) - (north? 20 : 0);
			}
//			 
//			static final void confineToEdges() {
//			  x = constrain(x, ww, gw);
//			  y = constrain(y, hh, gh);
//			}
	
		

		
	
	
	
	public void mousePressed() {
		
	}

	
	public void mouseDragged() {
		
	}

	
	

}

