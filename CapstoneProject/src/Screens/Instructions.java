package Screens;


import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import Instructions.*;
import processing.core.PApplet;

/**
 * This class represents the Instructions screen that contains instructions on how to play the game. 
 * @author Stephen
 */
public class Instructions implements Screen {
	
	private MainMenu surface;
	private Rectangle backButton;
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	private final float BUTTON_WIDTH = 0.2f;
	private final float BUTTON_HEIGHT = 0.1f;
	
	/**
	 * Creates an Instructions object
	 * @param surface - the MainMenu (extending PApplet) to draw the objects with
	 * @param width - the width of the Instructions screen
	 * @param height - the height of the Instructions screen
	 */
	public Instructions(MainMenu surface, int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;
		backButton = new Rectangle((int)(DRAWING_WIDTH*0.015), (int)(DRAWING_HEIGHT*0.03), (int)(width*BUTTON_WIDTH/2), (int)(height*BUTTON_HEIGHT/2));
	}

	/**
	 * Initializes variables and performs tasks executing once when the program begins
	 */
	public void setup() {
		
	}

	/** 
	 * Draws this Instructions screen using the MainMenu
	 */
	public void draw() {
		surface.background(200,0,200);
		
		surface.fill(255);
		surface.rect(backButton.x, backButton.y, backButton.width, backButton.height, 10, 10, 10, 10);
		
		surface.fill(0);
		surface.text("", DRAWING_WIDTH, DRAWING_HEIGHT);
	//	surface.textAlign(surface.CENTER);
//		System.out.println("You are on the instructions Screen");
		
		String str = "Instructions: \nUse the WASD keys to move the sprite, and click to shoot. \nYou can set the screen to fullscreen mode if preferred."
				+ "\nThere are traps on the map that slow you down and that deal damage"
				+ "\nThere are also powerups that provide buffs."
				+ "\nA green arrow points you to direction of the other player for ease of access."
				+ "\nOur game has two different gamemodes: deathmatch and king of the hill. \n\n"
				+ "Deathmatch: Timed mode of two players 1v1 in an arena just battling it out raw. \n"
				+ "Most kills in the amount of alloted time wins.\n"
				+ "\nKing of the Hill (KOTH): Longer timed mode of two players. \nThe \"hill\" will rotate over time from northwest, northeast, southwest, southeast, then middle.\n"
				+ "The text at the top of the screen indicates which hill to head to.\n When a hill is \"on\", the tiles will light up blue, and standing on the hill will give points.\n"
				+ "Kill the other player while protecting the hill and preventing your opponent from gaining points."
				+ "\nMost points at the end of the alloted time wins.";
		float w = surface.textWidth(str);
		surface.textAlign(PApplet.LEFT);
		surface.textSize(22);
		surface.fill(0);
		surface.text(str, (int)(DRAWING_WIDTH*0.1), (int)(DRAWING_HEIGHT*0.2));
		
		surface.textSize(20);
		String str0 = "Back";
		float w0 = surface.textWidth(str0);
		surface.text(str0, backButton.x+backButton.width/2, backButton.y+backButton.height/2);
	}
	
	/**
	 * The contents of this function is called every time the mouse button is pressed
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (backButton.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
	}
	/**
	 * The contents of this function is called every time the mouse is moved
	 */
	public void mouseMoved() {}
	/**
	 * The contents of this function is called every time the mouse button is dragged
	 */
	public void mouseDragged() {}
	/**
	 * The contents of this function is called every time the mouse button is released
	 */
	public void mouseReleased() {}
	/**
	 * The contents of this function is called every time a key is pressed
	 */
	public void keyReleased() {}
	/**
	 * The contents of this function is called every time a key is released
	 */
	public void keyPressed() {}
}