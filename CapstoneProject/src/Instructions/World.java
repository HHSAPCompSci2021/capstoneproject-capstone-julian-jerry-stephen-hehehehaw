package Instructions;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Players.Collider;
import Players.Player;
import Players.PlayerHUD;
//import Players.PlayerHUD;
import Screens.*;
import Weapons.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.Sound;
import processing.sound.SoundFile;
import Tiles.*;

public class World implements Screen {
	
	public final static String fileSeparator = System.getProperty("file.separator");
	public final static String lineSeparator = System.getProperty("line.separator");
	public final static String userDir = System.getProperty("user.dir");
	private PApplet p;
	private SoundFile heHeHaHa;
	private SoundFile shotGunShot;
	private SoundFile subMachineShot;
	private int soundCounter = 0;


	
	
//put walls around the map to add borders
	private int tileGrid[][];
	private Collider cC;
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private PlayerHUD hud = new PlayerHUD();
	private TileManager tM;
	
	private imageReaderToRGB reader = new imageReaderToRGB();
	
	private int maxScreenCol = 16;
	private int maxScreenRow = 9;
	
	private boolean playerShoot;
	
	/**
	* Represents the screen's width
	*/
	public int screenWidth;
	
	/**
	* Represents the screen's height
	*/
	public int screenHeight;
	
	private MainMenu surface;
	private Rectangle backButton;
	private final float BUTTON_WIDTH = 0.1f;
	private final float BUTTON_HEIGHT = 0.1f;
	
	private PImage[] playerImage = new PImage[8];
	private PImage[] playerImage2 = new PImage[8];
	private PImage[] tileImage = new PImage[22]; 
//	private final int maxWorldCol = 100;
//	private final int maxWorldRow = 100;
//	private final int worldWidth = maxWorldCol * tM.getTileSize();
//	private final int worldHeight = maxWorldRow * tM.getTileSize();
	private BufferedImage image;
	
	private boolean dead1, dead2;
	Player player1, player2;
	
	public World(MainMenu p) {
		surface = p;
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
		
		backButton = new Rectangle((int)(screenWidth*0.015), (int)(screenHeight*0.03), (int)(screenWidth*BUTTON_WIDTH), (int)(screenHeight*BUTTON_HEIGHT/2));
		
		cC = new Collider(tM.getTileSize(), tM);
		playerShoot = false;
	}
	
	public void changeWeapon(int w) {
		if(w == 1)
			player1.setWeapon(new Shotgun());
		else if(w == 2)
			player1.setWeapon(new Sniper());
		else if(w == 3)
			player1.setWeapon(new Submachine());
		else if(w == 4)
			player1.setWeapon(new Knife());
	}
	
	public void resetHealth() {
		player1.heal(100);
	}
	
	// The statements in the setup() function 
	// execute once when the program beginsas
	public void setup() {
		heHeHaHa = new SoundFile(p, "Assets/Music/HeHeHeHa.wav");
		heHeHaHa.amp(0.7f);
		shotGunShot = new SoundFile(p, "Assets/Music/ShotgunShot.wav");
		shotGunShot.amp(0.3f);
		subMachineShot = new SoundFile(p, "Assets/Music/Submachine.wav");
		subMachineShot.amp(0.1f);

		
		p.frameRate(30);
		
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

		player1 =  new Player(cC, screenWidth/2 - tM.getTileSize()/2, screenHeight/2 - tM.getTileSize()/2, tM.getTileSize() * 50, tM.getTileSize() * 2, p, new Sniper(), 5.0, 12.5, 100, playerImage, tM.getTileSize());
//		player.setWeapon(new Sniper());
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
		tileImage[15] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "arenatile2.png");
		tileImage[16] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "spikeTrap.png");
		tileImage[17] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "slowTrap.png");
		tileImage[18] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "healthPowerUp.png");
		tileImage[19] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "damagePowerUp.png");
		tileImage[20] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "ratePowerUp.png");
		tileImage[21] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "speedPowerUp.png");
		
		
		
		
		tM.setTiles(tileImage);
		
	}

	/**
	* Draws the entire in-game perspective
	* 
	* @post Changes background to (220, 220, 220)
	* @post Changes PApplet's text alignment to Center
	*/
	public void draw() {
		if (dead1) {
			player1.setWorldX(tM.getTileSize() * 50);
			player1.setWorldY(tM.getTileSize() * 2);
			dead1 = false;
			
		}
		
		// check if player has lost all its health
		if(player1.getHealth() <= 0) {
			surface.switchScreen(ScreenSwitcher.DEATH_SCREEN);
			dead1 = true;
		}
		else {

		//System.out.println(p.frameRate);
		p.background(220,220,220);  
		p.textAlign(p.CENTER);

		tM.draw(p, player1);
		
		p.push();
		for(Bullet b : bullets)
		{
			p.fill(0, 255, 0);
			b.draw(p, player1);
		}
		
		for (int i = 0; i < bullets.size(); i++) {

			if (cC.checkTiles(bullets.get(i))) {
				bullets.remove(i);
				i--;
			}
		
//			if (bullets.get(i).damagePlayer(player2)) {
//				bullets.remove(i);
//				i--;
//			}
			
				
		}
			
		p.pop();


		
		player1.draw(p);
		
		if(player1.getWeapon().getAmmo() <= 0){

			player1.getWeapon().reload();
		}
		hud.draw(p, screenWidth, screenHeight, player1, new Player(screenWidth-screenWidth/10 - tM.getTileSize()/2, 2*screenHeight/3 - tM.getTileSize()/2, 0, tM.getTileSize() * 20, p, playerImage2, tM.getTileSize()));

		surface.textAlign(surface.CENTER);
		surface.fill(255);
		surface.rect(backButton.x, backButton.y, backButton.width, backButton.height, 10, 10, 10, 10);
		surface.fill(0);
		surface.textSize(20);
		String str0 = "Quit";
		float w0 = surface.textWidth(str0);
		surface.text(str0, backButton.x+backButton.width/2, backButton.y+backButton.height/2);

		
		if(playerShoot)
		{
			
			if(player1.getWeapon().getAmmo() <= 0)
			{
				playerShoot = false;
			}
		
			
			for(Bullet b : player1.shoot(p.mouseX, p.mouseY)) {	
				bullets.add(b);
			}
			
			if(player1.getWeapon() instanceof Shotgun && player1.getWeapon().getAmmo() >= 0)
			{
				shotGunShot.play();
			}
			else if(player1.getWeapon() instanceof Submachine && player1.getWeapon().getAmmo() >= 0)
			{
				if(soundCounter == 10)
				{
					subMachineShot.play();
					soundCounter = 0;
				}
		
				else {
					soundCounter++;
				}
			}
			else if(player1.getWeapon() instanceof Sniper && player1.getWeapon().getAmmo() >= 0)
			{
				if(soundCounter == 10)
				{
					subMachineShot.play();
					soundCounter = 0;
				}
		
				else {
					soundCounter++;
				}
			}
			
			if (!(player1.getWeapon() instanceof Submachine))
			{
				playerShoot = false;
			}
			
					
			
		}
		
		for(int i = 0; i < bullets.size(); i++)
		{
			if(player1.getWeapon().getMaxDistance() < bullets.get(i).getDistanceTraveled())
			{
				bullets.remove(i);
			}
		}
		
		}
	}
			 
	/**
	* Tracks the keys pressed that moves the player
	*/
	public void keyPressed() {
		final int k = p.keyCode;
		player1.setDirection(k, true);
		player1.avatar.setDirection(k, true);
		
		//Currently selected emote will display
		if(p.key == 'e')
		{
			player1.emote();
			heHeHaHa.play();
		}
	}
			
	/**
	* Tracks the keys released
	*/
	public void keyReleased() {
		player1.setDirection(p.keyCode, false) ;
		player1.avatar.setDirection(p.keyCode, false) ;
	}


	/**
	* Tracks when the mouse is pressed and shoots the player 
	*/
	public void mousePressed() {
		Point point = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (backButton.contains(point))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
		

		
		playerShoot = true;
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
		playerShoot = false;
	}
	/**
	* Tracks when the mouse is moved
	*/
	public void mouseMoved() {
		
	}
}

