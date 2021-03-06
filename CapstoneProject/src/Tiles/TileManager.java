package Tiles;

import java.util.ArrayList;

import Players.Player;
import Instructions.*;
import Weapons.*;
import processing.core.PApplet;
import processing.core.PImage;

/** 
 * This class represents the Tile Manager. It handles everything Tile related (intersections, creation, etc...) 
 * @author Jerry
 * @version 5/20
 */
public class TileManager {
	
//	private Tile[] tiles;
	private boolean spawnPowerUps = true;
	private int cd, cd2;
	private int[][] tileDesignator;
	private Tile redBrick1, redBrick2, redBrick3, redBrick4, redBrick5, 
	redBrickWall1, stoneWall1, stoneWall2,
	stoneBrick1, stoneBrick2, stoneBrick3, stoneBrick4, stoneBrick5, stoneBrick6,
	spikeTile, tarPitTile, healthPowerUp, damagePowerUp, ammoPowerUp, speedPowerUp, aT1, aTOn, powerUpLoader;
	
	private ArrayList<Tile> tilesList = new ArrayList<Tile>();
	
	private int[] randList;
	
	private final int originalTileSize;
	private final int scale;
	private final int tileSize;
	

	/**
	* Creates a TileManager object, initializing the tile gridm scale, and size
	* @param ogTileSize Initial size of tiles
	* @param scale Scale size of tile
	* @param tileGrid An ArrayList of the tile grid
	*/
	public TileManager(int ogTileSize, int scale, int[][] tileGrid) {
		this.scale = scale;
		originalTileSize = ogTileSize;
		tileDesignator = tileGrid;
		tileSize = this.scale * originalTileSize;
		randList = new int[6];

	
	}
	
	/**
	* @return An ArrayList of the tiles
	*/
	public ArrayList<Tile> getTilesList() {
		return tilesList;
	}
	
	/**
	* Initializes and adds tiles to the tiles ArrayList
	*/
	public void setTiles(PImage[] tiles)//, PImage wallTile, PImage spikeTile, PImage tarPitTile, PImage gasTile)
	{
		spikeTile = new Tile();
		tarPitTile = new Tile();
		healthPowerUp = new Tile();
		damagePowerUp = new Tile();
		ammoPowerUp = new Tile();
		speedPowerUp = new Tile();
		aTOn = new Tile();
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
		aT1 = new Tile();
		powerUpLoader = new Tile();
		
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
		tilesList.add(aT1);
		tilesList.add(aTOn);//tile [15]
		tilesList.add(spikeTile);//16
		tilesList.add(tarPitTile);//17
		tilesList.add(healthPowerUp);//18
		tilesList.add(damagePowerUp);//19
		tilesList.add(ammoPowerUp);//20
		tilesList.add(speedPowerUp);//21
		tilesList.add(powerUpLoader);//22
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
		aT1.setImage(tiles[14]);
		aTOn.setImage(tiles[15]);
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
		powerUpLoader.setImage(tiles[22]);
	//	aTOn.trap();
//		aT2.setImage(tiles[23]);
//		aT2.setImage(tiles[24]);
//		aT2.setImage(tiles[25]);
//		aT2.setImage(tiles[26]);
		
	}
		
	/**
	* Changes the power up list (assinged power ups)
	*/
	public void changePowerUpList(ArrayList<Integer> arr) {
		int[] ar = new int[randList.length];
		for (int i = 0; i< arr.size(); i++) {
			ar[i] = arr.get(i);
		}
	
		randList = ar;
	}
	
	/**
	 * Returns an arraylist of random integers to assign random power ups for tiles
	* @return An arraylist of random integers to assign power ups
	*/
	public static ArrayList<Integer> getPowerUpList() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < 6; i ++) {
			arr.add((int) (Math.random() * 3 + 19));
		}
		return arr;
	}
	
	/** 
	 * Handles the interaction of the player, and tiles within the interaction. This includes sound, 
	 * damage, and other power up affects
	 * @param tileNum The tile being tested
	 * @param p The player
	 * @param sound If sound should be played when interacting the tile
	* @return returns the reloadCounter
	*/
	public int tileInteract(int tileNum, Player p, boolean sound) {
		switch (tileNum) {
		case 15:
			return 15;
		case 16:
			if(!p.getJustSpawned())
			p.loseHealth(0.35);
			if (sound)
				if(!World.loseHealth.isPlaying())
					World.loseHealth.play();
			p.setDataChanged(true);
			return 0;
		case 17:
			p.setSpeedDown(p.getSpeed() * 0.5);
			if (sound)
			if(!World.collectPowerUp.isPlaying())
				World.collectPowerUp.play();
				p.setDataChanged(true);
			return 17;
		case 18: 
			p.heal(70);
			if (sound)
				if(!World.collectPowerUp.isPlaying())
					World.collectPowerUp.play();
			p.setDataChanged(true);
			break;
		case 19: 
			p.getWeapon().setDamage((int)(p.getWeapon().getDamage()*1.3));
			if (sound)
				if(!World.collectPowerUp.isPlaying())
					World.collectPowerUp.play();
			p.setDataChanged(true);
			return 19;
		case 20: 
			p.getWeapon().setMagSize(p.getWeapon().getMagSize()*2);
			if (sound)
				if(!World.collectPowerUp.isPlaying())
					World.collectPowerUp.play();
		//	System.out.println("Ammo : " + p.getWeapon().getAmmo() + " magazineSize: " + p.getWeapon().magazineSize);
			p.setDataChanged(true);
			return 20;
			
		case 21:
			p.setSpeedUp(p.getRealDefaultSpeed() * 1.5);
			if (sound)
			if(!World.collectPowerUp.isPlaying())
				World.collectPowerUp.play();
			
						p.setDataChanged(true);
			return 21;
		}
		return tileNum;
		
	}
	
	/**
	* @return Returns the 2D Array of the map
	*/
	public int[][] getMap(){
		return tileDesignator;
	}
	
	/**
	* @return The tile size 
	*/
	public int getTileSize() {
		return tileSize;
	}
	
	/** 
	 * Draws the tiles within the program, and randomly assigns powerups
	* @param p PApplet surface to draw on
	* @param player The current player
	*/
	public void draw(PApplet p, Player player, int hill) {
		
		if (spawnPowerUps) {
			changePowerUpList(getPowerUpList());
			
			tileDesignator[12][48] = randList[0];
			tileDesignator[13][49] = 18;
			
			tileDesignator[48][9] = randList[1];
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
						&& worldY - tileSize < player.getWorldY() + player.getScreenY() ) {
						
						switch (hill) {
						case 0:
							if (worldX < tileSize * 30 && worldY < tileSize * 30) {
							if (tileDesignator[worldCol][worldRow] == 14)
								tileDesignator[worldCol][worldRow] = 15;
							}else if (tileDesignator[worldCol][worldRow] == 15)
							tileDesignator[worldCol][worldRow] = 14;
							break;
						case 1: 
							if (worldX > tileSize * 70 && worldY < tileSize * 30) {
							if (tileDesignator[worldCol][worldRow] == 14)
								tileDesignator[worldCol][worldRow] = 15;
							}else if (tileDesignator[worldCol][worldRow] == 15)
								tileDesignator[worldCol][worldRow] = 14;
							break;
						case 2:
							if (worldX > tileSize * 70 && worldY > tileSize * 70) {
							if (tileDesignator[worldCol][worldRow] == 14)
								tileDesignator[worldCol][worldRow] = 15;
							}else if (tileDesignator[worldCol][worldRow] == 15)
								tileDesignator[worldCol][worldRow] = 14;
							break;
						case 3:
							if (worldX < tileSize * 30  && worldY > tileSize * 70) {
							if (tileDesignator[worldCol][worldRow] == 14) 
								tileDesignator[worldCol][worldRow] = 15;

							}else if (tileDesignator[worldCol][worldRow] == 15)
								tileDesignator[worldCol][worldRow] = 14;
							break;
						case 4:
							if (worldX > tileSize * 30 && worldY > tileSize * 30 && worldX < tileSize *  70 && worldY < tileSize* 70) {
							if (tileDesignator[worldCol][worldRow] == 14) 
								tileDesignator[worldCol][worldRow] = 15;

							}else if (tileDesignator[worldCol][worldRow] == 15)
								tileDesignator[worldCol][worldRow] = 14;
							break;
						}
						p.image(tilesList.get(tileDesignator[worldCol][worldRow]).getImage(), screenX, screenY);
						
				
				}
				}
				
			}
			
		
		}
		
	}
