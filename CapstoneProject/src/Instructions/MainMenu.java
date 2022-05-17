package Instructions;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

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

	public MainMenu() {
		world = new World(this);
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
	

	public static void main(String args[]) {
		MainMenu m = new MainMenu();
		
		World drawing = new World(m);
		PApplet.runSketch(new String[]{""}, m);
		PSurfaceAWT surf = (PSurfaceAWT) m.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(drawing.screenWidth, drawing.screenHeight);
		window.setMinimumSize(new Dimension(drawing.screenWidth, drawing.screenHeight));
		window.setMaximumSize(new Dimension(drawing.screenWidth + 1, drawing.screenHeight + 1));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	public void setup() {
		mainMenuSound = new SoundFile(this, "Assets/Music/Level1.wav");
		
		
		for (Screen s : screens) {
			s.setup();
		}
	}
	
	public void draw() {
		ratioX = (float)width/DRAWING_WIDTH;
		ratioY = (float)height/DRAWING_HEIGHT;
		
		push();
		
		if (activeScreen != world)
			scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		if(activeScreen instanceof World)
		{
			
		} else if(activeScreen instanceof MenuScreen) {
			mainMenuSound.loop();
		} else if(activeScreen instanceof WeaponSelectionScreen) {
			
		} else if(activeScreen instanceof Instructions) {
			
		} else if(activeScreen instanceof DeathScreen) {
			
		}
		
//		mainMenuSound.loop();
		
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
		
		System.out.println(screen1.getChosenGamemode());
	}

}
