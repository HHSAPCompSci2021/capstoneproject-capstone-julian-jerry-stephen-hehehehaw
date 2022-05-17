package Tiles;

import java.util.ArrayList;

import Players.Player;
import Weapons.*;
import processing.core.PApplet;
import processing.core.PImage;

public class TileManager {
	
//	private Tile[] tiles;
	private boolean spawnPowerUps = true;
	private int cd, cd2;
	private int[][] tileDesignator;
	private Tile redBrick1, redBrick2, redBrick3, redBrick4, redBrick5, 
	redBrickWall1, stoneWall1, stoneWall2,
	stoneBrick1, stoneBrick2, stoneBrick3, stoneBrick4, stoneBrick5, stoneBrick6,
	spikeTile, tarPitTile, healthPowerUp, damagePowerUp, ammoPowerUp, speedPowerUp, arenaTile1, arenaTile2;
	
	private ArrayList<Tile> tilesList = new ArrayList<Tile>();
	
	private int[] randList;
	
	private final int originalTileSize;
	private final int scale;
	private final int tileSize;

	public TileManager(int ogTileSize, int scale, int[][] tileGrid) {
		this.scale = scale;
		originalTileSize = ogTileSize;
		tileDesignator = tileGrid;
		tileSize = this.scale * originalTileSize;
		randList = new int[6];
		changePowerUpList();

	
	}
	

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
		ammoPowerUp = new Tile();
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
		tilesList.add(ammoPowerUp);//20
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
		spikeTile.setImage(tiles[16]);
		spikeTile.trap();
		tarPitTile.setImage(tiles[17]);
		tarPitTile.trap();
		healthPowerUp.setImage(tiles[18]);
		healthPowerUp.powerUp();
		damagePowerUp.setImage(tiles[19]);
		damagePowerUp.powerUp();
		ammoPowerUp.setImage(tiles[20]);
		ammoPowerUp.powerUp();
		speedPowerUp.setImage(tiles[21]);
		speedPowerUp.powerUp();
	}
		
	public void changePowerUpList() {
	
		for (int i = 0; i < randList.length; i ++) {
			randList[i] = (int) (Math.random() * 4 + 18);
		}
	}
	
	
	public int tileInteract(int tileNum, Player p) {
		switch (tileNum) {
		case 16:
			p.loseHealth(0.2);//test this number
			break;
		case 17:
			p.setSpeedDown(p.getSpeed() * 0.5);
			return 17;

		case 18: 
			p.heal(50);
			break;
		case 19: 
			p.getWeapon().setDamage((int)(p.getWeapon().getDamage()*1.2));
			
			return 19;
		case 20: 
			if (p.getWeapon() instanceof Submachine)
				p.getWeapon().setMagSize(p.getWeapon().getMagSize()*2);
			else 
				p.getWeapon().setMagSize(p.getWeapon().getMagSize()*3);

		//	System.out.println("Ammo : " + p.getWeapon().getAmmo() + " magazineSize: " + p.getWeapon().magazineSize);
			return 20;
			
		case 21:
			p.setSpeedUp(p.getRealDefaultSpeed() * 1.5);
			return 21;
		}
		return -1;
		
	}
	
	public int[][] getMap(){
		return tileDesignator;
	}
	public int getTileSize() {
		return tileSize;
	}
	
	public void draw(PApplet p, Player player) {
		
		if (spawnPowerUps) {
			
			changePowerUpList();
				
			tileDesignator[12][48] = randList[0];
			tileDesignator[13][49] = 18;
			
			tileDesignator[48][9] = 21;//randList[1];
			tileDesignator[49][10] = 18;
			
			tileDesignator[50][48] = randList[2];
			tileDesignator[51][49] = randList[3];
			
			tileDesignator[84][48] = randList[4];
			tileDesignator[85][49] = 18;
			
			tileDesignator[48][87] = randList[5];
			tileDesignator[49][88] = 18;
			
			cd = 0;
			spawnPowerUps = false;
		
			}		
		cd++;
		if (cd >= 1200) {
			spawnPowerUps = true;
		}

		
		
		
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
