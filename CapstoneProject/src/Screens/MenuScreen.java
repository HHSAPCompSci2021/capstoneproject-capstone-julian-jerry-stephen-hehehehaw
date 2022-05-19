package Screens;

import java.awt.Point;
import java.awt.Rectangle;
import Instructions.*;
import processing.core.PImage;
import processing.sound.SoundFile;

// 
public class MenuScreen implements Screen {

	public final static String fileSeparator = System.getProperty("file.separator");
	
	private MainMenu surface;
	private Rectangle startButton;
	private Rectangle instButton;
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	private final float BUTTON_WIDTH = 0.2f;
	private final float BUTTON_HEIGHT = 0.1f;
	private PImage bg; 
//	private SoundFile menuClick;

	public MenuScreen(MainMenu surface, int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;
//		button = new Rectangle(800/2-100,600/2-50,200,100);
		startButton = new Rectangle((int)(DRAWING_WIDTH*(1-BUTTON_WIDTH*1.5)/2), (int)(DRAWING_HEIGHT*(1-2.5*BUTTON_HEIGHT)/2), (int)(width*BUTTON_WIDTH*1.5), (int)(height*BUTTON_HEIGHT*1.5));
		instButton = new Rectangle((int)(DRAWING_WIDTH*(1-BUTTON_WIDTH*1.5)/2), (int)(DRAWING_HEIGHT*(1+2.5*BUTTON_HEIGHT)/2), (int)(width*BUTTON_WIDTH*1.5), (int)(height*BUTTON_HEIGHT*1.5));
	}
	
	public void setup() {
//		menuClick = new SoundFile(surface, "Assets" + fileSeparator + "Music" + fileSeparator + "Menu Select.wav");
		bg = surface.loadImage("Assets" + fileSeparator + "Background" + fileSeparator + "b6.png");

	}
	
	// add one extra button for the Instructions screen
	// connect the frame size to that in Main and the first button should lead to the World class
	public void draw() {
//		System.out.println("You are on the 1st Screen");
		
		// start button
		surface.image(bg, 0, 0, surface.width, surface.height*2);
//		surface.background(0);
		surface.rect(startButton.x, startButton.y, startButton.width, startButton.height, 10, 10, 10, 10);
		String str = "Start!";
		float w = surface.textWidth(str);
		
		// instructions button		
		surface.rect(instButton.x, instButton.y, instButton.width, instButton.height, 10, 10, 10, 10);
		String str2 = "Instructions";
		float w2 = surface.textWidth(str);
		surface.textSize(30);
		surface.fill(0);
		surface.text(str, startButton.x+startButton.width*4/7-w/2, startButton.y+startButton.height*4/7);
		surface.text(str2, instButton.x+instButton.width*3/7-w2/2, instButton.y+instButton.height*4/7);
		
		String str3 = "HEEHAW Brawls";
		surface.textSize(50);
		surface.fill(255);
		surface.text(str3, (int)(DRAWING_WIDTH*0.33), (int)(DRAWING_HEIGHT*0.2));
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (startButton.contains(p))
		{
			World.menuClick.play();
			surface.switchScreen(ScreenSwitcher.GAMEMODE_SELECTION_SCREEN);
		}
		else if (instButton.contains(p))
		{
			World.menuClick.play();
			surface.switchScreen(ScreenSwitcher.INSTRUCTION_SCREEN);
		}
			
	}
	
	public void mouseMoved() {}
	
	public void mouseDragged() {}
	
	public void mouseReleased() {}
	
	public void keyReleased() {}
	
	public void keyPressed() {}

}

