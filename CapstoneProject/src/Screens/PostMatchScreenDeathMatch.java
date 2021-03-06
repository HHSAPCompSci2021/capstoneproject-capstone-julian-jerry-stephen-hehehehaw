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
 * This class represents the screen that shows up after a Deathmatch game ends, displaying the scores of each player, as well as who won. 
 * @author Stephen, Jerry
 * @version 5/21
 */
public class PostMatchScreenDeathMatch implements Screen {
	
	private MainMenu surface;
//	private Rectangle backButton;
	private final int DRAWING_WIDTH, DRAWING_HEIGHT;
	private final float BUTTON_WIDTH = 0.2f;
	private final float BUTTON_HEIGHT = 0.1f;
	private Player p1, p2, winnerPlayer;
	
	/**
	 * Creates a new PostMatchScreenDeathMatch object
	 * @param surface - the MainMenu (extending PApplet) to draw the objects with
	 * @param width - the width of the MenuScreen when it is displayed
	 * @param height - the height of the MenuScreen when it is displayed
	 * @param p1 - the first Player
	 * @param p2 - the second Player
	 */
	public PostMatchScreenDeathMatch(MainMenu surface, int width, int height, Player p1, Player p2) {
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
	 * Draws this PostMatchScreenDeathMatch screen using the MainMenu
	 */
	public void draw() {
		surface.background(200,0,200);
		
		surface.fill(255);
//		surface.rect(backButton.x, backButton.y, backButton.width, backButton.height, 10, 10, 10, 10);
		
		surface.fill(0);
		surface.text("", DRAWING_WIDTH, DRAWING_HEIGHT);
//		System.out.println("You are on the instructions Screen");
		if (p1 != null && p2 != null) {
		
		double killsP1 = p1.getKillCount();
		double killsP2 = p2.getKillCount();
		String winner = "";
		if(killsP1 > killsP2)
		{
			winner = p1.getUsername();
			winnerPlayer = p1;
		}
		else if(killsP1 < killsP2){
			winner = p2.getUsername();
			winnerPlayer = p2;
		}
		else {
			winner = "None! It's A Tie!";
			winnerPlayer = p1;
		}
		
		
		
		String str = "Game Summary: "
				+ "Winner: " + winner + "\n"
				+ "Kills: " + (winnerPlayer == null ? ("" + p1.getKillCount()): ("" + winnerPlayer.getKillCount())) + "\n"
				+ "Deaths Player 1: " + p2.getKillCount() + "\n"
				+ "Deaths Player 2: " + p1.getKillCount();
		
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