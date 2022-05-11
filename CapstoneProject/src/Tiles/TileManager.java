package Tiles;

import Players.Player;
import processing.core.PApplet;
import processing.core.PImage;

public class TileManager {
	
//	private Tile[] tiles;
	private int[][] tileDesignator;
	private PImage redBrick1, redBrick2, redBrick3, redBrick4, redBrick5, 
	redBrickWall1, stoneWall1, stoneWall2,
	stoneBrick1, stoneBrick2, stoneBrick3, stoneBrick4, stoneBrick5, stoneBrick6,
	spikeTile, tarPitTile, gasTile;
	
	private final int originalTileSize;
	private final int scale;
	private final int tileSize;

// tiles length should be 17	
	public void setTiles(PImage[] tiles)//, PImage wallTile, PImage spikeTile, PImage tarPitTile, PImage gasTile)
	{
		redBrick1 = tiles[0];
		redBrick2 = tiles[1];
		redBrick3 = tiles[2];
		redBrick4 = tiles[3];
		redBrick5 = tiles[4];
		redBrickWall1 = tiles[5];
		stoneWall1 = tiles[6];
		stoneWall2 = tiles[7];
		stoneBrick1 = tiles[8];
		stoneBrick2 = tiles[9];
		stoneBrick3 = tiles[10];
		stoneBrick4 = tiles[11];
		stoneBrick5 = tiles[12];
		stoneBrick6 = tiles[13];
//		spikeTile = tiles[14];
//		tarPitTile = tiles[15];
//		gasTile = tiles[16];
		
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
						
						
//					case 1:
//						p.image(wallTile, screenX, screenY);
//						break;
//					case 2:
//						p.image(spikeTile, screenX, screenY);
//						break;
//					case 3:
//						p.image(tarPitTile, screenX, screenY);
//						break;
//					case 4:
//						p.image(gasTile, screenX, screenY);
//						break;
//	//				case 5:
//					
//						break;
				
				}
				
			}
		}
		
	}
}
