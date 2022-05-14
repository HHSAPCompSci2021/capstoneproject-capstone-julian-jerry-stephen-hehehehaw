package Tiles;

import java.awt.Rectangle;

import processing.core.PImage;

public class Tile {

	private PImage image;
	private boolean collision = false;

	public Tile() {
		
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
