package Screens;

/**
 * 
 * @author Stephen
 */
public interface Screen {

//	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
//	public Screen(int width, int height) {
//		this.DRAWING_WIDTH = width;
//		this.DRAWING_HEIGHT = height;
//	}
	
	public void setup();
	
	public void draw();
	
	public void keyPressed();
	
	public void keyReleased();
	
	public void mousePressed();
	
	public void mouseMoved();
	
	public void mouseDragged();
	
	public void mouseReleased();
}
