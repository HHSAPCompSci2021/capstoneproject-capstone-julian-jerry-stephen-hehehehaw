package Screens;

/**
 * Represents a screen that shows up on the GUI when the program runs. 
 * @author Stephen
 * @version 5/21
 */
public interface Screen {

//	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
//	public Screen(int width, int height) {
//		this.DRAWING_WIDTH = width;
//		this.DRAWING_HEIGHT = height;
//	}
	
	/**
	 * Initializes variables and performs tasks executing once when the program begins
	 */
	public void setup();
	/** 
	 * Draws this WeaponSelectionScreen using the MainMenu
	 */
	public void draw();
	/**
	* Tracks the keys pressed
	*/
	public void keyPressed();
	/**
	* Tracks the keys released
	*/
	public void keyReleased();
	/**
	* Tracks when the mouse is pressed
	*/
	public void mousePressed();
	/**
	* Tracks when the mouse is moved
	*/
	public void mouseMoved();
	/**
	* Tracks when the mouse is dragged
	*/
	public void mouseDragged();
	/**
	 * Tracks when the mouse is released
	 */
	public void mouseReleased();
}
