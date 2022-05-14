package Instructions;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Players.Player;
import Players.PlayerHUD;
//import Players.PlayerHUD;
import Screens.*;
import Weapons.Bullet;
import Weapons.Shotgun;
import Weapons.Sniper;
import Weapons.Submachine;
import Weapons.Weapon;
import processing.core.PApplet;
import processing.core.PImage;
import Tiles.*;

public class World implements Screen {
	
	public final static String fileSeparator = System.getProperty("file.separator");
	public final static String lineSeparator = System.getProperty("line.separator");
	public final static String userDir = System.getProperty("user.dir");
	private PApplet p;
	
	
//put walls around the map to add borders
	private int tileGrid[][];
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private PlayerHUD hud = new PlayerHUD();
	private TileManager tM;
	

	private imageReaderToRGB reader = new imageReaderToRGB();
	
	private int maxScreenCol = 16;
	private int maxScreenRow = 9;
	
	/**
	* Represents the screen's width
	*/
	public int screenWidth;
	
	/**
	* Represents the screen's height
	*/
	public int screenHeight;
	
	private PImage[] playerImage = new PImage[8];
	private PImage[] playerImage2 = new PImage[8];
	private PImage[] tileImage = new PImage[15]; //should be 17 once the remaining tile sprites for traps are made
//	private final int maxWorldCol = 100;
//	private final int maxWorldRow = 100;
//	private final int worldWidth = maxWorldCol * tM.getTileSize();
//	private final int worldHeight = maxWorldRow * tM.getTileSize();
	private BufferedImage image;
	Player player;
	
	public World(PApplet p) {
		try {
			image = ImageIO.read(new File("Assets" + fileSeparator + "map.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader = new imageReaderToRGB();
		
		tileGrid = reader.ImageToArr(image);
		
		tM = new TileManager(16, 5, tileGrid);
		screenWidth = maxScreenCol * tM.getTileSize();
		screenHeight = maxScreenRow * tM.getTileSize();
		this.p = p;
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		
//		frameRate(999);
		
		playerImage2[0] = p.loadImage("Assets" + fileSeparator + "BlueAvatar" + fileSeparator + "StandingBlueAvatar.png");
		playerImage2[1] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "StandingBlueAvatar.png");
		playerImage2[2] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "StandingBlueAvatar.png");
		playerImage2[3] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "StandingBlueAvatar.png");
		playerImage2[4] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "StandingBlueAvatar.png");
		playerImage2[5] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "StandingBlueAvatar.png");
		playerImage2[6] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "StandingBlueAvatar.png");
		playerImage2[7] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "StandingBlueAvatar.png");

		
		playerImage[0] = p.loadImage("Assets" + fileSeparator + "BlueAvatar" + fileSeparator + "Backwards1.png");
		playerImage[1] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Forward1.png");
		playerImage[2] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Backwards2.png");
		playerImage[3] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Forward2.png");
		playerImage[4] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Left1.png");
		playerImage[5] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Right1.png");
		playerImage[6] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Left2.png");
		playerImage[7] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Right2.png");

		player =  new Player(screenWidth/2 - tM.getTileSize()/2, screenHeight/2 - tM.getTileSize()/2, tM.getTileSize() * 50, tM.getTileSize() * 2, p, new Sniper(), 5.0, 5.0, 100, playerImage);
		player.setWeapon(new Sniper());
//		player.setWeapon(new Shotgun());
//		player.setWeapon(new Submachine());


		tileImage[0] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrick1.png");
		tileImage[1] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrick2.png");
		tileImage[2] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrick3.png");
		tileImage[3] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrick4.png");
		tileImage[4] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrick5.png");
		
		
		tileImage[5] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "redbrickwall.png");
		tileImage[6] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonewall1.png");
		tileImage[7] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonewall2.png");
	
		tileImage[8] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick1.png");
		tileImage[9] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick2.png");
		tileImage[10] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick3.png");
		tileImage[11] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick4.png");
		tileImage[12] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick5.png");
		tileImage[13] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "stonebrick6.png");
		tileImage[14] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "arenatile1.png");
		
		
		tM.setTiles(tileImage);
		
	}
	
	/**
	* Draws the entire in-game perspective
	* 
	* @post Changes background to (220, 220, 220)
	* @post Changes PApplet's text alignment to Center
	*/
	public void draw() { 		
		p.background(220,220,220);  
		p.textAlign(p.CENTER);

		tM.draw(p, player);
		player.draw(p);
		
		p.push();
		for(Bullet b : bullets)
		{
			p.fill(0, 255, 0);
			b.draw(p);
		}
		p.pop();
		
		player.draw(p);
		if(player.getWeapon().getAmmo() == 0)
		{
			player.getWeapon().reload();
		}
		
		hud.draw(p, screenWidth, screenHeight, player, new Player(screenWidth-screenWidth/10 - tM.getTileSize()/2, 2*screenHeight/3 - tM.getTileSize()/2, 0, tM.getTileSize() * 20, p, playerImage2));
	}
			 
	/**
	* Tracks the keys pressed that moves the player
	*/
	public void keyPressed() {
			  final int k = p.keyCode;
			  player.setDirection(k, true);
			  player.avatar.setDirection(k, true);
			}
			
	/**
	* Tracks the keys released
	*/
	public void keyReleased() {
			  player.setDirection(p.keyCode, false);
			}


	/**
	* Tracks when the mouse is pressed and shoots the player 
	*/
	public void mousePressed() {
		for(Bullet b : player.shoot(p.mouseX, p.mouseY))
		{
			bullets.add(b);
		}
	}

	/**
	* Tracks when the mouse is dragged
	*/
	public void mouseDragged() {
		
	}
	/**
	* Tracks when the mouse is released
	*/
	public void mouseReleased() {
		
	}
	/**
	* Tracks when the mouse is moved
	*/
	public void mouseMoved() {
		
	}

	
	

}
