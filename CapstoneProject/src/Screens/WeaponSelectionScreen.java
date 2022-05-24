package Screens;


import java.awt.Dimension;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Instructions.*;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/**
 * This class represents the screen where the player can select a weapon to begin the game with
 * (shotgun, sniper, smg, or knife). 
 * @author Stephen
 * @version 5/21
 */
public class WeaponSelectionScreen implements Screen {
	
	private MainMenu surface;
	private int weaponChoice;
	
	private Rectangle gameStarter;
	private Rectangle backButton;
	private Rectangle shotgun;
	private Rectangle sniper;
	private Rectangle submachine;
	private Rectangle knife;
	private int shade;
	
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	private final float BUTTON_WIDTH = 0.1f;
	private final float BUTTON_HEIGHT = 0.1f;
	private final float W_BUTTON_WIDTH = 1.5f*BUTTON_WIDTH;
	private final float W_BUTTON_HEIGHT = 4.5f*BUTTON_HEIGHT;
	
	/**
	 * Creates a new WeaponSelectionScreen object
	 * @param surface - the MainMenu (extending PApplet) to draw the objects with
	 * @param width - the width of the MenuScreen when it is displayed
	 * @param height - the height of the MenuScreen when it is displayed
	 */
	public WeaponSelectionScreen(MainMenu surface, int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;
		weaponChoice = 0;
		shade = 0;
		gameStarter = new Rectangle((int)(DRAWING_WIDTH*(1-BUTTON_WIDTH*4)/2), (int)(DRAWING_HEIGHT*(1-1.5*BUTTON_HEIGHT)), (int)(width*BUTTON_WIDTH*4), (int)(height*BUTTON_HEIGHT));
		backButton = new Rectangle((int)(DRAWING_WIDTH*0.015), (int)(DRAWING_HEIGHT*0.03), (int)(width*BUTTON_WIDTH), (int)(height*BUTTON_HEIGHT/2));
		shotgun = new Rectangle((int)(2*DRAWING_WIDTH/15), (int)(DRAWING_HEIGHT*(1-4*BUTTON_HEIGHT)/2), (int)(width*W_BUTTON_WIDTH), (int)(height*W_BUTTON_HEIGHT));
		sniper = new Rectangle((int)(5*DRAWING_WIDTH/15), (int)(DRAWING_HEIGHT*(1-4*BUTTON_HEIGHT)/2), (int)(width*W_BUTTON_WIDTH), (int)(height*W_BUTTON_HEIGHT));
		submachine = new Rectangle((int)(8*DRAWING_WIDTH/15), (int)(DRAWING_HEIGHT*(1-4*BUTTON_HEIGHT)/2), (int)(width*W_BUTTON_WIDTH), (int)(height*W_BUTTON_HEIGHT));
		knife = new Rectangle((int)(11*DRAWING_WIDTH/15), (int)(DRAWING_HEIGHT*(1-4*BUTTON_HEIGHT)/2), (int)(width*W_BUTTON_WIDTH), (int)(height*W_BUTTON_HEIGHT));
	}

	/**
	 * Returns the weapon selected from the screen
	 * @return weaponChoice the chosen weapon represented by an int
	 */
	public int getWeaponChoice() {
		return weaponChoice;
	}
	
	/**
	 * Initializes variables and performs tasks executing once when the program begins
	 */
	public void setup() {
		
	}

	/** 
	 * Draws this WeaponSelectionScreen using the MainMenu
	 */
	public void draw() {
		surface.background(0,200,200);

		// draw buttons
		surface.rect(backButton.x, backButton.y, backButton.width, backButton.height, 10, 10, 10, 10);
		surface.rect(gameStarter.x, gameStarter.y, gameStarter.width, gameStarter.height, 10, 10, 10, 10);
		
		if(shade == 1) surface.fill(155);
		else surface.fill(255);
		surface.rect(shotgun.x, shotgun.y, shotgun.width, shotgun.height, 10, 10, 10, 10);
		
		if(shade == 2) surface.fill(155);
		else surface.fill(255);
		surface.rect(sniper.x, sniper.y, sniper.width, sniper.height, 10, 10, 10, 10);
		
		if(shade == 3) surface.fill(155);
		else surface.fill(255);
		surface.rect(submachine.x, submachine.y, submachine.width, submachine.height, 10, 10, 10, 10);
		
		if(shade == 4) surface.fill(155);
		else surface.fill(255);
		surface.rect(knife.x, knife.y, knife.width, knife.height, 10, 10, 10, 10);
		
		// draw text
		surface.textAlign(surface.CENTER);
		
		surface.fill(0);
		String title = "<Choose your weapon>";
		surface.textSize(70);
		surface.text(title, (int)(DRAWING_WIDTH/2), (int)(shotgun.y-gameStarter.height*1.3));
		
		String str = "Confirm Start Game";
		float w = surface.textWidth(str);
		surface.textSize(20);
		surface.text(str, gameStarter.x+gameStarter.width/2, gameStarter.y+gameStarter.height/2);
		
		String g1 = "Shotgun";
		surface.textSize(20);
		surface.text(g1, shotgun.x+shotgun.width/2,shotgun.y+shotgun.height/2);
		
		String g2 = "Sniper";
		surface.text(g2, sniper.x+sniper.width/2,sniper.y+sniper.height/2);
		
		String g3 = "Submachine";
		surface.text(g3, submachine.x+submachine.width/2,submachine.y+submachine.height/2);
		
		String g4 = "Knife";
		surface.text(g4, knife.x+knife.width/2,knife.y+knife.height/2);
		
		String str0 = "Back";
		float w0 = surface.textWidth(str0);
		surface.text(str0, backButton.x+backButton.width/2, backButton.y+backButton.height/2);
		
//		System.out.println("You are on the pre-gameplay Screen");
	}

	/**
	* Tracks when the mouse is pressed and updates the screen/weapon selection based on the buttons pressed
	*/
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (backButton.contains(p)) {
			World.menuClick.play();

			surface.switchScreen(ScreenSwitcher.GAMEMODE_SELECTION_SCREEN);
			shade = 0;
		}
		else if(shotgun.contains(p)) {
			World.menuClick.play();

			weaponChoice = 1;
			shade = 1;
		}
		else if(sniper.contains(p)) {
			World.menuClick.play();

			weaponChoice = 2;
			shade = 2;
		}
		else if(submachine.contains(p)) {
			World.menuClick.play();

			weaponChoice = 3;
			shade = 3;
		}
		else if(knife.contains(p)) {
			World.menuClick.play();

			weaponChoice = 4;
			shade = 4;
		}
		else if(gameStarter.contains(p)) {
			World.menuClick.play();

			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
			shade = 0;
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