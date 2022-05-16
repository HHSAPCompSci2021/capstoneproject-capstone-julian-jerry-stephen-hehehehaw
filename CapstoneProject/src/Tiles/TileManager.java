package Tiles;

import java.util.ArrayList;

import Players.Player;
import processing.core.PApplet;
import processing.core.PImage;

public class TileManager {
	
//	private Tile[] tiles;
	private int[][] tileDesignator;
	private Tile redBrick1, redBrick2, redBrick3, redBrick4, redBrick5, 
	redBrickWall1, stoneWall1, stoneWall2,
	stoneBrick1, stoneBrick2, stoneBrick3, stoneBrick4, stoneBrick5, stoneBrick6,
	spikeTile, tarPitTile, healthPowerUp, damagePowerUp, ratePowerUp, speedPowerUp, arenaTile1, arenaTile2;
	
	private ArrayList<Tile> tilesList = new ArrayList<Tile>();
	
	private final int originalTileSize;
	private final int scale;
	private final int tileSize;

	public ArrayList<Tile> getTilesList() {
		return tilesList;
	}
// tiles length should be 17	
	public void setTiles(PImage[] tiles)//, PImage wallTile, PImage spikeTile, PImage tarPitTile, PImage gasTile)
	{
		spikeTile = new Tile();
		tarPitTile = new Tile();
		healthPowerUp = new Tile();
		damagePowerUp = new Tile();
		ratePowerUp = new Tile();
		speedPowerUp = new Tile();
		arenaTile2 = new Tile();
		redBrick1 = new Tile();
		redBrick2 = new Tile();
		redBrick3 = new Tile();
		redBrick4 = new Tile();
		redBrick5 = new Tile();
		redBrickWall1 = new Tile();
		stoneBrick1 = new Tile();
		stoneBrick2 = new Tile();
		stoneBrick3 = new Tile();
		stoneBrick4 = new Tile();
		stoneBrick5 = new Tile();
		stoneBrick6 = new Tile();
		stoneWall1 = new Tile();
		stoneWall2 = new Tile();
		arenaTile1 = new Tile();
		
		tilesList.add(redBrick1);
		tilesList.add(redBrick2);
		tilesList.add(redBrick3);
		tilesList.add(redBrick4);
		tilesList.add(redBrick5);
		tilesList.add(stoneBrick1);
		tilesList.add(stoneBrick2);
		tilesList.add(stoneBrick3);
		tilesList.add(stoneBrick4);
		tilesList.add(stoneBrick5);
		tilesList.add(stoneBrick6);
		tilesList.add(redBrickWall1);
		tilesList.add(stoneWall1);
		tilesList.add(stoneWall2);
		tilesList.add(arenaTile1);
		tilesList.add(arenaTile2);//tile [15]
		tilesList.add(spikeTile);//16
		tilesList.add(tarPitTile);//17
		tilesList.add(healthPowerUp);//18
		tilesList.add(damagePowerUp);//19
		tilesList.add(ratePowerUp);//20
		tilesList.add(speedPowerUp);//21
		redBrick1.setImage(tiles[0]);
		redBrick2.setImage(tiles[1]);
		redBrick3.setImage(tiles[2]);
		redBrick4.setImage(tiles[3]);
		redBrick5.setImage(tiles[4]);
		redBrickWall1.setImage(tiles[5]);
		redBrickWall1.setSolid();
		stoneWall1.setImage(tiles[6]);
		stoneWall1.setSolid();
		stoneWall2.setImage(tiles[7]);
		stoneWall2.setSolid();
		stoneBrick1.setImage(tiles[8]);
		stoneBrick2.setImage(tiles[9]);
		stoneBrick3.setImage(tiles[10]);
		stoneBrick4.setImage(tiles[11]);
		stoneBrick5.setImage(tiles[12]);
		stoneBrick6.setImage(tiles[13]);
		arenaTile1.setImage(tiles[14]);
		arenaTile2.setImage(tiles[15]);
		arenaTile2.setImage(tiles[16]);
		arenaTile2.setImage(tiles[17]);
		arenaTile2.setImage(tiles[18]);
		arenaTile2.setImage(tiles[19]);
		arenaTile2.setImage(tiles[20]);
		arenaTile2.setImage(tiles[21]);
		
	}
		
	
	public TileManager(int ogTileSize, int scale, int[][] tileGrid) {
		this.scale = scale;
		originalTileSize = ogTileSize;
		tileDesignator = tileGrid;
		tileSize = this.scale * originalTileSize;
		
		
		 		
	
	}
	
	public int[][] getMap(){
		return tileDesignator;
	}
	public int getTileSize() {
		return tileSize;
	}
	
	public void draw(PApplet p, Player player) {
		for (int worldRow = 0; worldRow < tileDesignator.length; worldRow++) {
			for (int worldCol = 0; worldCol < tileDesignator[worldRow].length; worldCol++) {
				
				int worldX = worldCol * tileSize;
				int worldY = worldRow * tileSize;
				float screenX = worldX - player.getWorldX() + player.getScreenX();
				float screenY = worldY - player.getWorldY() + player.getScreenY();
				
				if (worldX + tileSize> player.getWorldX() - player.getScreenX() 
						&& worldX - tileSize < player.getWorldX() + player.getScreenX() 
						&& worldY + tileSize > player.getWorldY() - player.getScreenY() 
						&& worldY - tileSize < player.getWorldY() + player.getScreenY() )
						p.image(tilesList.get(tileDesignator[worldCol][worldRow]).getImage(), screenX, screenY);

				
				}
				
			}
		}
		
	}
