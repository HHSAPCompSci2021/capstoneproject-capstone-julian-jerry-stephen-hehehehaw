package Tiles;

import processing.core.PApplet;
import processing.core.PImage;

public class TileManager {
	
//	private Tile[] tiles;
	private int[][] tileDesignator;
	private PImage floorTile, wallTile, spikeTile, tarPitTile, gasTile;
	private final int originalTileSize;
	private final int scale;
	public int tileSize;

	
	public void setImage(PImage i)
	{
		floorTile = i;
	}
	
	public TileManager(int ogTileSize, int scale, int[][] tileGrid) {
		this.scale = scale;
		originalTileSize = ogTileSize;
		tileDesignator = new int[9][16]; //y, x
		tileSize = this.scale * originalTileSize;
		
		tileDesignator = tileGrid;
		
	
	}
	
	public void draw(PApplet p) {
		for (int y = 0; y < tileDesignator.length; y++) {
			for (int x = 0; x < tileDesignator[y].length; x++) {
				switch (tileDesignator[y][x]) {
				
				case 0:
					break;
				case 1:
					p.image(wallTile, x * tileSize, y * tileSize, tileSize, tileSize);
					break;
				case 2:
					p.image(spikeTile, x * tileSize, y * tileSize, tileSize, tileSize);
					break;
				case 3:
					p.image(tarPitTile, x * tileSize, y * tileSize, tileSize, tileSize);
					break;
				case 4:
					p.image(gasTile, x * tileSize, y * tileSize, tileSize, tileSize);
					break;
//				case 5:
//					p.image(floorTile, x * tileSize, y * tileSize, tileSize, tileSize);
//					break;
				
				}
				
			}
		}
		
	}
}
