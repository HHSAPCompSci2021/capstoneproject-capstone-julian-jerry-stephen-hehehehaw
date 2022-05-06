package Tiles;

import processing.core.PApplet;
import processing.core.PImage;

public class TileManager {
	
	PApplet p;
	public Tile[] tiles;
	public int[][] tileDesignator;
	public PImage floorTile, wallTile, spikeTile, tarPitTile, gasTile;

	
	public TileManager(PApplet pa) {
		p = pa;
		tileDesignator = new int[9][16]; //y, x
		
		
		//get tile images in setup() in World Class
		
//		tiles[0] = new Tile();
//		tiles[0].image = floorTile;
//		
//		tiles[1] = new Tile();
//		tiles[1].image = wallTile;
//		
//		tiles[2] = new Tile();
//		tiles[2].image = spikeTile;
//		
//		tiles[3] = new Tile();
//		tiles[3].image = tarPitTile;
//		
//		tiles[4] = new Tile();
//		tiles[4].image = gasTile;
	}
	
	public void draw() {
		p.image(floorTile, 0, 0);
		
	}
}
