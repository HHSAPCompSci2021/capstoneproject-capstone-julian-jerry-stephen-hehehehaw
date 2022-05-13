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

// "Start" option
public class PreGameScreen implements Screen {
	
	private MainMenu surface;
	
	private Rectangle screenRect;
	private Rectangle gameStarter;
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
//	private Mario mario;
//	private List<Sprite> obstacles;

	public PreGameScreen(MainMenu surface, int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		gameStarter = new Rectangle(800/2-100, 600/2, 200, 100);
		
//		obstacles = new ArrayList<Sprite>();
//		obstacles.add(new Sprite(0,250,100,50));
	}


//	public void spawnNewMario() {
//		mario = new Mario(surface.loadImage("img/mario.png"), DRAWING_WIDTH/2-Mario.MARIO_WIDTH/2,50);
//	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		surface.background(0,255,255);   
		
		surface.rect(gameStarter.x, gameStarter.y, gameStarter.width, gameStarter.height, 10, 10, 10, 10);
		String str = "Start!";
		float w = surface.textWidth(str);
		surface.text(str, gameStarter.x+gameStarter.width/2-w/2, gameStarter.y+gameStarter.height/2);
		
		System.out.println("You are on the pre-gameplay Screen");

	}

	public void mousePressed() {
//		GameScreen g = new GameScreen(surface);
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (gameStarter.contains(p))
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
//			g.createWorld();
//			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
	}
	
	public void mouseMoved() {}
	
	public void mouseDragged() {}
	
	public void mouseReleased() {}
	
	public void keyPressed() {}

	public void keyReleased() {}
}