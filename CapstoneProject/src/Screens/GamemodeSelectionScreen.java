package Screens;


import java.awt.Dimension;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import processing.sound.Sound;
import processing.sound.SoundFile;

import javax.swing.JFrame;

import Instructions.*;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/**
 * This class represents the screen where the player can select the gamemode (king of the hill or deathmatch). 
 * @author Stephen
 */
public class GamemodeSelectionScreen implements Screen {
	
		private MainMenu surface;
		private int chosenGamemode;
		
		private Rectangle proceed;
		private Rectangle backButton;
		private Rectangle kOTH;
		private Rectangle deathMatch;
		private int shade;
		
		private final int DRAWING_WIDTH, DRAWING_HEIGHT;
		private final float BUTTON_WIDTH = 0.1f;
		private final float BUTTON_HEIGHT = 0.1f;
		private final float W_BUTTON_WIDTH = 1.5f*BUTTON_WIDTH;
		private final float W_BUTTON_HEIGHT = 4.5f*BUTTON_HEIGHT;
		
//		private SoundFile menuClick;
		
		/**
		 * Creates a new GamemodeSelectionScreen object
		 * @param surface - the MainMenu (extending PApplet) to draw the objects with
		 * @param width - the width of the MenuScreen when it is displayed
		 * @param height - the height of the MenuScreen when it is displayed
		 */
		public GamemodeSelectionScreen(MainMenu surface, int width, int height) {
			this.DRAWING_WIDTH = width;
			this.DRAWING_HEIGHT = height;
			this.surface = surface;
			chosenGamemode = 0;
			shade = 0;
			proceed = new Rectangle((int)(DRAWING_WIDTH*(1-BUTTON_WIDTH*4)/2), (int)(DRAWING_HEIGHT*(1-1.5*BUTTON_HEIGHT)), (int)(width*BUTTON_WIDTH*4), (int)(height*BUTTON_HEIGHT));
			backButton = new Rectangle((int)(DRAWING_WIDTH*0.015), (int)(DRAWING_HEIGHT*0.03), (int)(width*BUTTON_WIDTH), (int)(height*BUTTON_HEIGHT/2));
			kOTH = new Rectangle((int)(2*DRAWING_WIDTH/15), (int)(DRAWING_HEIGHT*(1-5.2*BUTTON_HEIGHT)/2), (int)(2*width*W_BUTTON_WIDTH), (int)(1.2*height*W_BUTTON_HEIGHT));
			deathMatch = new Rectangle((int)(8*DRAWING_WIDTH/15), (int)(DRAWING_HEIGHT*(1-5.2*BUTTON_HEIGHT)/2), (int)(2*width*W_BUTTON_WIDTH), (int)(1.2*height*W_BUTTON_HEIGHT));
		}

		/**
		 * Returns the gamemode selected from the screen
		 * @return weaponChoice the chosen gamemode represented by an int
		 */
		public int getChosenGamemode() {
			return chosenGamemode;
		}
		
		/**
		 * Initializes variables and performs tasks executing once when the program begins
		 */
		public void setup() {
//			menuClick = new SoundFile(surface, "Assets" + fileSeparator + "Music" + fileSeparator + "Menu Select.wav");

		}

		/** 
		 * Draws this GamemodeSelectionScreen using the MainMenu
		 */
		public void draw() {
			surface.background(0,200,200);

			// draw buttons
			surface.rect(backButton.x, backButton.y, backButton.width, backButton.height, 10, 10, 10, 10);
			surface.rect(proceed.x, proceed.y, proceed.width, proceed.height, 10, 10, 10, 10);
			
			if(shade == 1) surface.fill(155);
			else surface.fill(255);
			surface.rect(kOTH.x, kOTH.y, kOTH.width, kOTH.height, 10, 10, 10, 10);
			
			if(shade == 2) surface.fill(155);
			else surface.fill(255);
			surface.rect(deathMatch.x, deathMatch.y, deathMatch.width, deathMatch.height, 10, 10, 10, 10);
			
			// draw text
			surface.textAlign(surface.CENTER);
			
			surface.fill(0);
			String title = "<Choose a gamemode>";
			surface.textSize(70);
			surface.text(title, (int)(DRAWING_WIDTH/2), (int)(kOTH.y*1.05-proceed.height));
			
			String str = "Click to Proceed";
			float w = surface.textWidth(str);
			surface.textSize(20);
			surface.text(str, proceed.x+proceed.width/2, proceed.y+proceed.height/2);
			
			String g1 = "King of the Hill";
			surface.textSize(20);
			surface.text(g1, kOTH.x+kOTH.width/2,kOTH.y+kOTH.height/2);
			
			String g2 = "Deathmatch";
			surface.text(g2, deathMatch.x+deathMatch.width/2,deathMatch.y+deathMatch.height/2);
			
			String str0 = "Back";
			float w0 = surface.textWidth(str0);
			surface.text(str0, backButton.x+backButton.width/2, backButton.y+backButton.height/2);
			
//			System.out.println("You are on the pre-gameplay Screen");
		}
		
		/**
		 * The contents of this function is called every time the mouse button is pressed
		 */
		public void mousePressed() {
			Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
			if (backButton.contains(p)) {
				World.menuClick.play();
				surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
				shade = 0;
			}
			else if(proceed.contains(p)) {
				World.menuClick.play();

				surface.switchScreen(ScreenSwitcher.WEAPON_SELECTION_SCREEN);
				shade = 0;
			}
			else if(kOTH.contains(p)) {
				World.menuClick.play();

				chosenGamemode = 1;
				shade = 1;
			}
			else if(deathMatch.contains(p)) {
				World.menuClick.play();

				chosenGamemode = 2;
				shade = 2;
			}
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
