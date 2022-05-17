package Tiles;

import java.awt.Rectangle;

import processing.core.PImage;

public class Tile {

	private PImage image;
	private boolean collision = false;
	private boolean powerUp = false;
	private boolean trap = false;
	

	public Tile() {
		
	}

	public void powerUp() {
		powerUp = true;
	}
	
	public boolean isPowerUp() {
		return powerUp;
	}
	
	public void trap() {
		trap = true;
	}
	
	public boolean isTrap() {
		return trap;
	}
	public boolean solidState() {
		return collision;
	}
	
	public void setSolid() {
		collision = true;
	}
	
	public PImage getImage() {
		return image;
	}
	public void setImage(PImage i) {
		image = i;
	}
}
