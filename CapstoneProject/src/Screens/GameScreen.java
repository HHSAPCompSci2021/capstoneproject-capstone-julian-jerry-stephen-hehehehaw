package Screens;


import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import Instructions.*;

// "Start" option
public class GameScreen extends Screen {
	
	private MainMenu surface;
	
	private Rectangle screenRect;

//	private Mario mario;
//	private List<Sprite> obstacles;

	public GameScreen(MainMenu surface) {
		super(800,600);
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
		
		surface.background(0,255,255);   

		System.out.println("You are on the gameplay Screen");

	}

	
}