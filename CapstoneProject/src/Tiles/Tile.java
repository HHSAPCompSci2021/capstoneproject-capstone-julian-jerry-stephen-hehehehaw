package Tiles;

import java.awt.Rectangle;

import processing.core.PImage;

/** 
 * This class represents a Tile.
 * @author Jerry
 * @version 5/20
 */
public class Tile {

	private PImage image;
	private boolean collision = false;
	private boolean powerUp = false;
	private boolean trap = false;
	
	/** 
	 *Creates a tile
	 */
	public Tile() {
		
	}

	
	/** 
	 * Sets the tile as a powerUp tile.
	 */
	public void powerUp() {
		powerUp = true;
	}
	
	/** 
	 *@return Returns true if the tile is a power up tile
	 */
	public boolean isPowerUp() {
		return powerUp;
	}
	
	/** 
	 * Sets the tile as a trap tile.
	 */
	public void trap() {
		trap = true;
	}
	
	/** 
	 *@return Returns true if the tile is a trap tile
	 */
	public boolean isTrap() {
		return trap;
	}
	
	/** 
	 *@return Returns true if the tile is in a solid state
	 */
	public boolean solidState() {
		return collision;
	}
	/** 
	 *Sets the tile as a solid
	 */
	public void setSolid() {
		collision = true;
	}
	
	/** 
	 *@return PImage Image of the tile
	 */
	public PImage getImage() {
		return image;
	}
	/** 
	 *Sets the image of the tile
	 *@param i PImage that is the image of the tile that is drawn
	 */
	public void setImage(PImage i) {
		image = i;
	}
}
