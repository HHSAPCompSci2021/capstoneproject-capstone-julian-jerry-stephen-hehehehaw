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
	private final float BUTTON_WIDTH = 0.2f;
	private final float BUTTON_HEIGHT = 0.1f;
	
	public PreGameScreen(MainMenu surface, int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		gameStarter = new Rectangle((int)(DRAWING_WIDTH*(1-BUTTON_WIDTH)/2), (int)(DRAWING_HEIGHT*(1-3*BUTTON_HEIGHT)/2), (int)(width*BUTTON_WIDTH), (int)(height*BUTTON_HEIGHT));
	}

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
		String str = "Confirm Start Game";
		float w = surface.textWidth(str);
		surface.textSize(20);
		surface.fill(0);
		surface.text(str, gameStarter.x+gameStarter.width*21/20-w/2, gameStarter.y+gameStarter.height/2);

//		System.out.println("You are on the pre-gameplay Screen");

	}

	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (gameStarter.contains(p))
			surface.switchScreen(ScreenSwitcher.GAME_SCREEN);
	}
	
	public void mouseMoved() {}
	
	public void mouseDragged() {}
	
	public void mouseReleased() {}
	
	public void keyReleased() {}
	
	public void keyPressed() {}
	
}