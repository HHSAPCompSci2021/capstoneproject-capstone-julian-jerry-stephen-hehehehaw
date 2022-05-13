package Screens;


import java.awt.Point;
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
	private Rectangle backButton;
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	private final float BUTTON_WIDTH = 0.2f;
	private final float BUTTON_HEIGHT = 0.1f;
	
	public Instructions(MainMenu surface, int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		backButton = new Rectangle((int)(DRAWING_WIDTH*0.015), (int)(DRAWING_HEIGHT*0.03), (int)(width*BUTTON_WIDTH/2), (int)(height*BUTTON_HEIGHT/2));
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
		
		surface.fill(255);
		surface.rect(backButton.x, backButton.y, backButton.width, backButton.height, 10, 10, 10, 10);
		
		surface.fill(0);
		surface.text("", DRAWING_WIDTH, DRAWING_HEIGHT);
//		System.out.println("You are on the instructions Screen");
		
		String str = "Instructions: \nUse the WASD keys to move the sprite, \nand click to shoot";
		float w = surface.textWidth(str);
		surface.textSize(50);
		surface.fill(0);
		surface.text(str, (int)(DRAWING_WIDTH*0.1), (int)(DRAWING_HEIGHT*0.2));
		
		String str4 = "Back";
		float w4 = surface.textWidth(str4);
		surface.textSize(20);
		surface.text(str4, backButton.x+backButton.width*4/7-w/2, backButton.y+backButton.height*4/7);
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (backButton.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
	}
	
	public void mouseMoved() {}
	
	public void mouseDragged() {}
	
	public void mouseReleased() {}
	
	public void keyReleased() {}
	
	public void keyPressed() {}
}