package Players;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;

public class Avatar {
	private PImage up1, down1, up2, down2, left1, right1, left2, right2;
	private String direction;
	
	public Avatar(String direction, PImage up1, PImage down1, PImage up2, PImage down2, PImage left1, PImage right1, PImage left2, PImage right2) {
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
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public void setDir(String dir)
	{
		direction = dir;
	}
	
	
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
	
	public void draw(PApplet p, float x, float y) {
		
		PImage image = null;
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
