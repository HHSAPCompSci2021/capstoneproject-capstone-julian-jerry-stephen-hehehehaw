package Screens;


import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import Instructions.*;
import Players.Player;

/**
 * This class represents the screen that shows up after a King of the Hill game ends, displaying the scores of each player, as well as who won. 
 * @author Stephen, Jerry
 * @version 5/21
 */
public class PostMatchScreenKOTH implements Screen {
	
	private MainMenu surface;
//	private Rectangle backButton;
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	private final float BUTTON_WIDTH = 0.2f;
	private final float BUTTON_HEIGHT = 0.1f;
	private Player p1, p2, winnerPlayer;
	
	/**
	 * Creates a new PostMatchScreenKOTH object
	 * @param surface - the MainMenu (extending PApplet) to draw the objects with
	 * @param width - the width of the MenuScreen when it is displayed
	 * @param height - the height of the MenuScreen when it is displayed
	 * @param p1 - the first Player
	 * @param p2 - the second Player
	 */
	public PostMatchScreenKOTH(MainMenu surface, int width, int height, Player p1, Player p2) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
		this.surface = surface;
//		backButton = new Rectangle((int)(DRAWING_WIDTH*0.015), (int)(DRAWING_HEIGHT*0.03), (int)(width*BUTTON_WIDTH/2), (int)(height*BUTTON_HEIGHT/2));
		this.p1 = p1;
		this.p2 = p2;
	}

	/**
	 * Initializes variables and performs tasks executing once when the program begins
	 */
	public void setup() {
		
	}

	/** 
	 * Draws this PostMatchScreenKOTH screen using the MainMenu
	 */
	public void draw() {
		surface.background(200,0,200);
		
		surface.fill(255);
//		surface.rect(backButton.x, backButton.y, backButton.width, backButton.height, 10, 10, 10, 10);
		
		surface.fill(0);
		surface.text("", DRAWING_WIDTH, DRAWING_HEIGHT);
//		System.out.println("You are on the instructions Screen");
		
		if (p1 != null && p2 != null) {
		double pointsP1 = p1.getPoints();
		double pointsP2 = p2.getPoints();
		String winner = "";
		if(pointsP1 > pointsP2)
		{
			winner = p1.getUsername();
			winnerPlayer = p1;
		}
		else if(pointsP1 < pointsP2){
			winner = p2.getUsername();
			winnerPlayer = p2;
		}
		else {
			winner = "None! It's A Tie!";
			winnerPlayer = null;
		}
		
		String str = "Game Summary: " + "\n"
				+ "Winner: " + winner + "\n"
				+ "Winner's Points: " + (winnerPlayer == null ? ("" + p1.getPoints()): ("" + winnerPlayer.getPoints())) + "\n"
				+ "Points Player 1: " + p1.getPoints() + "\n"
				+ "Points Player 2: " + p2.getPoints();
		
		float w = surface.textWidth(str);
		
		
		surface.textSize(50);
		surface.fill(0);
		surface.text(str, (int)(DRAWING_WIDTH*0.1), (int)(DRAWING_HEIGHT*0.2));
		}
		surface.textAlign(surface.CENTER);
		surface.textSize(20);
		String str0 = "Back";
		float w0 = surface.textWidth(str0);
//		surface.text(str0, backButton.x+backButton.width/2, backButton.y+backButton.height/2);
	}
	/**
	* Tracks when the mouse is pressed and updates the screen selection based on the buttons pressed
	*/
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
//		if (backButton.contains(p))
//			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
	}
	/**
	* Tracks when the mouse is moved
	*/
	public void mouseMoved() {}
	/**
	* Tracks when the mouse is dragged
	*/
	public void mouseDragged() {}
	/**
	 * Tracks when the mouse is released
	 */
	public void mouseReleased() {}
	/**
	* Tracks the keys released
	*/
	public void keyReleased() {}
	/**
	* Tracks the keys pressed
	*/
	public void keyPressed() {}
}