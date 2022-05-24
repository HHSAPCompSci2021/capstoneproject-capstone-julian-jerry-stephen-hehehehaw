package Screens;

import java.awt.Point;
import java.awt.Rectangle;
import Instructions.*;
import processing.core.PImage;
import processing.sound.SoundFile;

/**
 * This class represents the first screen that shows up after joining a room. 
 * @author Stephen
 * @version 5/21
 */
public class MenuScreen implements Screen {

	private final static String fileSeparator = System.getProperty("file.separator");
	
	private MainMenu surface;
	private Rectangle startButton;
	private Rectangle instButton;
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	private final float BUTTON_WIDTH = 0.2f;
	private final float BUTTON_HEIGHT = 0.1f;
	private PImage bg; 
//	private SoundFile menuClick;

	/**
	 * Creates a new MenuScreen object
	 * @param surface - the MainMenu (extending PApplet) to draw the objects with
	 * @param width - the width of the MenuScreen when it is displayed
	 * @param height - the height of the MenuScreen when it is displayed
	 */
	public MenuScreen(MainMenu surface, int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;
		startButton = new Rectangle((int)(DRAWING_WIDTH*(1-BUTTON_WIDTH*1.5)/2), (int)(DRAWING_HEIGHT*(1-2.5*BUTTON_HEIGHT)/2), (int)(width*BUTTON_WIDTH*1.5), (int)(height*BUTTON_HEIGHT*1.5));
		instButton = new Rectangle((int)(DRAWING_WIDTH*(1-BUTTON_WIDTH*1.5)/2), (int)(DRAWING_HEIGHT*(1+2.5*BUTTON_HEIGHT)/2), (int)(width*BUTTON_WIDTH*1.5), (int)(height*BUTTON_HEIGHT*1.5));
	}
	
	/**
	 * Initializes variables and performs tasks executing once when the program begins
	 */
	public void setup() {
//		menuClick = new SoundFile(surface, "Assets" + fileSeparator + "Music" + fileSeparator + "Menu Select.wav");
		bg = surface.loadImage("Assets" + fileSeparator + "Background" + fileSeparator + "b6.png");

	}
	
	/** 
	 * Draws this MenuScreen using the MainMenu
	 */
	public void draw() {
//		System.out.println("You are on the 1st Screen");
		
		// start button
		surface.image(bg, 0, 0, surface.width, surface.height*2);
		surface.rect(startButton.x, startButton.y, startButton.width, startButton.height, 10, 10, 10, 10);
		String str = "Start!";
		float w = surface.textWidth(str);
		
		// instructions button		
		surface.rect(instButton.x, instButton.y, instButton.width, instButton.height, 10, 10, 10, 10);
		String str2 = "Instructions";
		float w2 = surface.textWidth(str);
		surface.textSize(30);
		surface.fill(0);
		surface.text(str, startButton.x+startButton.width*4/7-w/2, startButton.y+startButton.height*4/7);
		surface.text(str2, instButton.x+instButton.width*3/7-w2/2, instButton.y+instButton.height*4/7);
		
		String str3 = "HEEHAW Brawls";
		surface.textSize(50);
		surface.fill(255);
		surface.text(str3, (int)(DRAWING_WIDTH*0.33), (int)(DRAWING_HEIGHT*0.2));
	}
	
	/**
	* Tracks when the mouse is pressed and updates the screen selection based on the buttons pressed
	*/
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (startButton.contains(p))
		{
			World.menuClick.play();
			surface.switchScreen(ScreenSwitcher.GAMEMODE_SELECTION_SCREEN);
		}
		else if (instButton.contains(p))
		{
			World.menuClick.play();
			surface.switchScreen(ScreenSwitcher.INSTRUCTION_SCREEN);
		}
			
	}
	/**
	* Tracks when the mouse is moved
	*/
	public void mouseMoved() {}
	/**
	* Tracks when the mouse is dragged
	*/
	public void mouseDragged() {}
	/**
	 * Tracks when the mouse is released
	 */
	public void mouseReleased() {}
	/**
	* Tracks the keys released
	*/
	public void keyReleased() {}
	/**
	* Tracks the keys pressed
	*/
	public void keyPressed() {}

}

