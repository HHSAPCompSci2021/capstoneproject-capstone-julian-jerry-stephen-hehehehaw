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

public class MainMenu extends PApplet implements ScreenSwitcher {

	public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
	
	private Screen activeScreen;
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


	public World getWorld() {
		return world;
	}

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
	
	public void draw() {
		ratioX = (float)width/DRAWING_WIDTH;
		ratioY = (float)height/DRAWING_HEIGHT;
		
		push();
		
		if (activeScreen != world)
			scale(ratioX, ratioY);
		//add check for world
		activeScreen.draw();
		
		if(activeScreen == world)
		{			
				if (world.getGameStatus())
						world.incrementGameTimer();
					if(!world.getGameStatus())
					{
						if(world.getPlayerGameMode() == 2)
							activeScreen = new PostMatchScreenDeathMatch(this, DRAWING_WIDTH, DRAWING_HEIGHT);
						else if (world.getPlayerGameMode() == 1)
							activeScreen = new PostMatchScreenKOTH(this, DRAWING_WIDTH, DRAWING_HEIGHT);
						
					}
			
		}
		
		pop();
	}
	
	public void keyPressed() {
		activeScreen.keyPressed();
		keys.add(keyCode);
		if (key == ESC) 
			key = 0;
	}

	public void keyReleased() {
		activeScreen.keyReleased();
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

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
