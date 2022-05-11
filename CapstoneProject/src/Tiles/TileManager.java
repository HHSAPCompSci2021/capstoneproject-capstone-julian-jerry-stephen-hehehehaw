package Tiles;

import Players.Player;
import processing.core.PApplet;
import processing.core.PImage;

public class TileManager {
	
//	private Tile[] tiles;
	private int[][] tileDesignator;
	private PImage floorTile, wallTile, spikeTile, tarPitTile, gasTile;
	private final int originalTileSize;
	private final int scale;
	private final int tileSize;

	
	public void setTiles(PImage floorTile)//, PImage wallTile, PImage spikeTile, PImage tarPitTile, PImage gasTile)
	{
		this.floorTile = floorTile;
//		this.wallTile = wallTile;
//		this.spikeTile = spikeTile;
//		this.tarPitTile = tarPitTile;
//		this.gasTile = gasTile;
	}
		
	
	public TileManager(int ogTileSize, int scale, int[][] tileGrid) {
		this.scale = scale;
		originalTileSize = ogTileSize;
		tileDesignator = tileGrid;
		tileSize = this.scale * originalTileSize;
		
		 		
	
	}
	public int getTileSize() {
		return tileSize;
	}
	
	public void draw(PApplet p, Player player) {
		for (int worldCol 	 = 0; worldCol < tileDesignator.length; worldCol++) {
			for (int worldRow = 0; worldRow < tileDesignator[worldCol].length; worldRow++) {
				
				int worldX = worldCol * tileSize;
				int worldY = worldRow * tileSize;
				float screenX = worldX - player.getWorldX() + player.getScreenX();
				float screenY = worldY - player.getWorldY() + player.getScreenY();
				
				if (worldX + tileSize> player.getWorldX() - player.getScreenX() 
						&& worldX - tileSize < player.getWorldX() + player.getScreenX() 
						&& worldY + tileSize > player.getWorldY() - player.getScreenY() 
						&& worldY - tileSize < player.getWorldY() + player.getScreenY() )
				switch (tileDesignator[worldCol][worldRow]) {
				
					case 0:
						p.image(floorTile, screenX, screenY);//, tileSize, tileSize);
						break;//need like 20 cases for different types of floor tiles
					case 1:
						p.image(wallTile, screenX, screenY);
						break;
					case 2:
						p.image(spikeTile, screenX, screenY);
						break;
					case 3:
						p.image(tarPitTile, screenX, screenY);
						break;
					case 4:
						p.image(gasTile, screenX, screenY);
						break;
	//				case 5:
//					
//						break;
				
				}
				
			}
		}
		
	}
}
