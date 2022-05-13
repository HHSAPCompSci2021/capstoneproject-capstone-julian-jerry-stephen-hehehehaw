package Screens;


import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import Instructions.*;

// "Start" option
public class Instructions extends Screen {
	
	private MainMenu surface;
	private Rectangle screenRect;
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
//	private Mario mario;
//	private List<Sprite> obstacles;

	public Instructions(MainMenu surface, int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
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
		
		// drawing stuff
		
		surface.background(255,0,0);
		surface.text("TEXT TEXT TEXT TEXT TEXT ", DRAWING_WIDTH, DRAWING_HEIGHT);
		System.out.println("You are on the instructions Screen");

	}
	
	public void mousePressed() {}
	
	public void mouseMoved() {}
	
	public void mouseDragged() {}
	
	public void mouseReleased() {}
	
	public void keyPressed() {}

	public void keyReleased() {}
	

	
}