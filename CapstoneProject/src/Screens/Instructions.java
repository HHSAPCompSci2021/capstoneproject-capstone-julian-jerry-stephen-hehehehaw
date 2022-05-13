package Screens;


import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import Instructions.*;

// "Start" option
public class Instructions implements Screen {
	
	private MainMenu surface;
	private Rectangle screenRect;
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	public Instructions(MainMenu surface, int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
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
		surface.background(255,0,0);
		surface.fill(0);
		surface.text("", DRAWING_WIDTH, DRAWING_HEIGHT);
		System.out.println("You are on the instructions Screen");
		
		String str = "Instructions: \nUse the WASD keys to move the sprite, \nand click to shoot";
		float w = surface.textWidth(str);
		surface.textSize(50);
		surface.fill(0);
		surface.text(str, (int)(DRAWING_WIDTH*0.1), (int)(DRAWING_HEIGHT*0.2));

	}
	
	public void mousePressed() {}
	
	public void mouseMoved() {}
	
	public void mouseDragged() {}
	
	public void mouseReleased() {}
	
	public void keyReleased() {}
	
	public void keyPressed() {}
}