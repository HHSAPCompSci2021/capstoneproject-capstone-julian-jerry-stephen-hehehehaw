package Instructions;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.Sound;
import processing.sound.SoundFile;
import Screens.*;

/**
 * This class represents the main menu that manages all the screens and switches the screens 
 * based on user input (buttons clicked). 
 * @author Stephen
 *
 */
public class MainMenu extends PApplet implements ScreenSwitcher {

	/** the ratio of the width of the screen */
	public float ratioX;
	
	/** the ratio of the height of the screen */
	public float ratioY;
	
	private ArrayList<Integer> keys;
	
	private Screen activeScreen;
	boolean incrementing = false;
	private ArrayList<Screen> screens;

	private int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	private int weapon;

	private static boolean isFirst;
	
	private World world;
	GamemodeSelectionScreen screen1;
	WeaponSelectionScreen screen2;
	DeathScreen screen3;
	
	private SoundFile mainMenuSound;
	private SoundFile inGameSound;

	/**
	 * Returns the world screen of this MainMenu object
	 * @return world the world object that the MainMenu has
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * Creates a MainMenu passing in a DatabaseReference
	 * @param postsRef - a DatabaseReference that uses Firebase
	 */
	public MainMenu(DatabaseReference postsRef) {
		
		world = new World(this, postsRef);
		isFirst = true;
		
		DRAWING_WIDTH = world.screenWidth;
		DRAWING_HEIGHT = world.screenHeight;
		
		screens = new ArrayList<Screen>();
		keys = new ArrayList<Integer>();
		
		MenuScreen screen0 = new MenuScreen(this, DRAWING_WIDTH, DRAWING_HEIGHT);
		screens.add(screen0);
		
		screen1 = new GamemodeSelectionScreen(this, DRAWING_WIDTH, DRAWING_HEIGHT);
		screens.add(screen1);
		
		screen2 = new WeaponSelectionScreen(this, DRAWING_WIDTH, DRAWING_HEIGHT);
		screens.add(screen2);		
		
		screens.add(world);
		
		
		
		Instructions iScreen = new Instructions(this, DRAWING_WIDTH, DRAWING_HEIGHT);
		screens.add(iScreen);
		
		screen3 = new DeathScreen(this, DRAWING_WIDTH, DRAWING_HEIGHT);
		screens.add(screen3);
		
		activeScreen = screens.get(0);
		
	}

	/**
	 * Initializes variables and performs tasks executing once when the program begins
	 */
	public void setup() {
		mainMenuSound = new SoundFile(this, "Assets/Music/Level1.wav");
		mainMenuSound.amp(0.2f);
		inGameSound = new SoundFile(this, "Assets/Music/Level3.wav");
		inGameSound.amp(0.2f);

		
		
		for (Screen s : screens) {
			s.setup();
		}
		
		mainMenuSound.play();
	}
	
	/** 
	 * Draws the screen using the PApplet
	 */
	public void draw() {
		this.frameRate(30);
		ratioX = (float)width/DRAWING_WIDTH;
		ratioY = (float)height/DRAWING_HEIGHT;
		
		push();
		
		if (activeScreen != world)
			scale(ratioX, ratioY);
		//add check for world
		activeScreen.draw();
		
		if(activeScreen == world)
		{			
			incrementing = true;
		}
		if (incrementing) {
				if (world.getIncrement())
						world.incrementGameTimer();
				if(!world.getGameStatus())
				{
					incrementing = false;	
						if(world.getPlayerGameMode() == 2)
							if (world.players.size() > 0)
								activeScreen = new PostMatchScreenDeathMatch(this, DRAWING_WIDTH, DRAWING_HEIGHT, world.me, world.players.get(0));
							else activeScreen = new PostMatchScreenKOTH(this, DRAWING_WIDTH, DRAWING_HEIGHT, world.me, null);
						
					else if (world.getPlayerGameMode() == 1)
							if (world.players.size() > 0)
								activeScreen = new PostMatchScreenKOTH(this, DRAWING_WIDTH, DRAWING_HEIGHT, world.me, world.players.get(0));
							else activeScreen = new PostMatchScreenKOTH(this, DRAWING_WIDTH, DRAWING_HEIGHT, world.me, null);
						
				}
			
		}
		
		pop();
	}
	
	/**
	* Tracks the keys pressed
	*/
	public void keyPressed() {
		activeScreen.keyPressed();
		keys.add(keyCode);
		if (key == ESC) 
			key = 0;
	}

	/**
	* Tracks the keys released
	*/
	public void keyReleased() {
		activeScreen.keyReleased();
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	/**
	 * Returns a boolean representing whether a specified key code was pressed or not
	 * @param code - the key code represented by an Integer object
	 * @return boolean representing whether the key code was pressed or not
	 */
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	/**
	* Tracks when the mouse is pressed
	*/
	public void mousePressed() {
		activeScreen.mousePressed();
	}

	/**
	* Tracks when the mouse is moved
	*/
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	/**
	* Tracks when the mouse is dragged
	*/
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	/**
	 * Tracks when the mouse is released
	 */
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	/**
	 * Converts the assumed coordinates of a Point to an actual Point
	 * @param assumed - assumed coordinates of the Point
	 * @return Point that was converted from assumed
	 */
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}
	
	/**
	 * Converts the actual coordinates of a Point to an assumed Point
	 * @param actual - actual coordinates of the Point
	 * @return Point that was converted from actual
	 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	/**
	 * Switches the screen to the given screen (as specified in the ScreenSwitcher class)
	 * @param i - the screen to switch to
	 */
	public void switchScreen(int i) {
		
		if(activeScreen == screen1)
		{
			int decision = screen1.getChosenGamemode();
			world.setPlayerGameMode(decision);
			world.myUserRef.setValue(world.me.getDataObject(), new CompletionListener(){
				//set value bullet object
				@Override
				public void onComplete(DatabaseError arg0, DatabaseReference arg1) {
				//	currentlySending = false;
				}
				
			});
		}
		
		activeScreen = screens.get(i);
		
		if(i <= 2)
			isFirst = true;
		if(i == 3) {
			if(isFirst) {
				world.changeWeapon(screen2.getWeaponChoice());
				isFirst = false;
			}
			else {
				world.changeWeapon(screen3.getWeaponChoice());
				world.resetHealth();
				if(!screen3.isAlive()) {
					isFirst = true;
				}
			}
		}
		
		if(activeScreen instanceof World)
		{
			if(!inGameSound.isPlaying())
			{
				mainMenuSound.stop();
				inGameSound.loop();
			}
			
		}
		else if(activeScreen instanceof MenuScreen){
			if(!mainMenuSound.isPlaying())
			{
				inGameSound.stop();
				mainMenuSound.loop();
			}
		}
//		System.out.println(screen1.getChosenGamemode());
	}
	
	
	

}
