package Instructions;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;

public class Avatar {
	public PImage up1, down1, up2, down2, left1, right1, left2, right2;
	public String direction;
	PApplet p;
	
	public Avatar(PApplet pa) {
		direction = "down";
		p = pa;
		}
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
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
