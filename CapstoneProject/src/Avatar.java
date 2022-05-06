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
	
	public void draw(PApplet p, float x, float y) {
		
		PImage image = null;
		switch(direction) {
		case "up":
			image = up1;
			break;			
		case "down":
			image = down1;
			break;
		case "left":
			image = left1;
			break;
		case "right":
			image = right1;
			break;
		}
		
		p.image(image, x, y);
	}
	
}
