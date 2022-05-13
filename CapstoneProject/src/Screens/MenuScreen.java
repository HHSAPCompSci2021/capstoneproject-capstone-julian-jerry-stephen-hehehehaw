package Screens;

import java.awt.Point;
import java.awt.Rectangle;
import Instructions.*;

// 
public class MenuScreen implements Screen {

	private MainMenu surface;
	
	private Rectangle startButton;
	private Rectangle instButton;
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	public MenuScreen(MainMenu surface, int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;

//		button = new Rectangle(800/2-100,600/2-50,200,100);
		startButton = new Rectangle(800/2-100,600/3,200,100);
		instButton = new Rectangle(800/2-100, 600/3*2,200,100);
	}
	
	public void setup() {
		
	}
	
	// add one extra button for the Instructions screen
	// connect the frame size to that in Main and the first button should lead to the World class
	public void draw() {
		System.out.println("You are on the 1st Screen");
		
		// start button
		surface.background(0);
		
		surface.rect(startButton.x, startButton.y, startButton.width, startButton.height, 10, 10, 10, 10);
		String str = "Start!";
		float w = surface.textWidth(str);		
		
		// instructions button		
		surface.rect(instButton.x, instButton.y, instButton.width, instButton.height, 10, 10, 10, 10);
		String str2 = "Instructions";
		float w2 = surface.textWidth(str);
		surface.fill(0);
		surface.text(str, startButton.x+startButton.width/2-w/2, startButton.y+startButton.height/2);
		surface.text(str2, instButton.x+instButton.width/2-w2/2, instButton.y+instButton.height/2);
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (startButton.contains(p))
			surface.switchScreen(ScreenSwitcher.PRE_GAME_SCREEN);
		else if (instButton.contains(p))
			surface.switchScreen(ScreenSwitcher.INSTRUCTION_SCREEN);
	}
	
	public void mouseMoved() {}
	
	public void mouseDragged() {}
	
	public void mouseReleased() {}
	
	public void keyReleased() {}
	
	public void keyPressed() {}

}

