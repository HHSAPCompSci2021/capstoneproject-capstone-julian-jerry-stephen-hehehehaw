package Players;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;

/** 
 * This class represents a Player's avatar. 
 * @author Jerry and Julian
 */
public class Avatar {
	private PImage up1, down1, up2, down2, left1, right1, left2, right2;
	private String direction;
	private PImage image = null;
	
	/** 
	 * Creates a new instance of an Avatar object 
	 * @param direciton The direction of the avatar
	 * @param up1 Up1 image
	 * @param down1 down1 image
	 * @param up2 up2 image
	 * @param down2 down2 image
	 * @param left1 lef1 image
	 * @param right1 right1 image
	 * @param left2 left2 image
	 * @param right2 right2 image
	 * @param spriteNum SpriteNum
	 * @param spriteCounter Sprite counter to keep track of which sprite to use
	 */
	public Avatar(String direction, PImage up1, PImage down1, PImage up2, PImage down2, PImage left1, PImage right1, PImage left2, PImage right2, int spriteNum, int spriteCounter) {
		this.spriteNum = spriteNum;
		this.spriteCounter = spriteCounter;
		this.direction = direction;
		this.up1 = up1;
		this.up2 = up2; 
		this.down1 = down1;
		this.down2 = down2; 
		this.left1 = left1;
		this.left2 = left2;
		this.right1 = right1;
		this.right2 = right2;
	}
	
	/**
	* Tracks the sprite counter
	*/
	public int spriteCounter;
	
	/**
	* Tracks the sprite num
	*/
	public int spriteNum;
	
	
	public String getDir() {
		return direction;
	}
	private void setDir(String dir)
	{
		direction = dir;
	}
	
	/**
	* Tracks when the mouse is dragged
	*/
	public void setDirection(int k, boolean decision) {
	 if      (k == 'w'    || k == 'W')   {
		 setDir("up");
	 }
	 else if (k == 's'  || k == 'S')   {
		 setDir("down");
	 }
		 else if (k == 'a'  || k == 'A')   {
		 setDir("left");
		 }
	 else if (k == 'd' || k == 'D')   {
		 setDir("right");
	 }
	}
	
	/**
	* Draws the avatar based on the direction and sprite num
	* 
	* @param p PApplet the Avatar is going to be drawn on
	* @param x X location of Avatar
	* @param y Y location of Avatar
	* @post Avatar is drawn on the PApplet
	*/
	public void draw(PApplet p, float x, float y) {
		
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {

				image = up1;
			}

			if (spriteNum == 2) {

				image = up2;
			}
			break;			
		case "down":
			if (spriteNum == 1) {

				image = down1;
			}

			if (spriteNum == 2) {

				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {

				image = left1;
			}

			if (spriteNum == 2) {

				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {

				image = right1;
			}

			if (spriteNum == 2) {

				image = right2;
			}
			break;
		}
		
		p.image(image, x, y);
	}
	
}
