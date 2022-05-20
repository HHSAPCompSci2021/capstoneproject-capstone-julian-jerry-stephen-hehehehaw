package Instructions;

import juliannth.shapes.Shape;
import juliannth.shapes.Line;
import processing.core.PApplet;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;

import FireBaseStuff.PlayerData;
import Players.Avatar;
//import FireBase.DrawingSurface.UserChangeListener;
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
	
	private boolean killUp;
	public final static String fileSeparator = System.getProperty("file.separator");
	public final static String lineSeparator = System.getProperty("line.separator");
	public final static String userDir = System.getProperty("user.dir");
	public PApplet p;
	public static SoundFile menuClick;
	public static SoundFile loseHealth;
	public static SoundFile collectPowerUp;


	private SoundFile heHeHaHa;
	private SoundFile shotGunShot;
	private SoundFile subMachineShot;
	private SoundFile sniperShotSound;
	
	private SoundFile knifeSound;
	private int soundCounter = 0;
	
	private int hill;
//	private int player1SpawnX, player1SpawnY, player2SpawnX, player2SpawnY;
	
	private final int SPAWN1X, SPAWN1Y, SPAWN2X, SPAWN2Y, SPAWN3X, SPAWN3Y, SPAWN4X, SPAWN4Y;
	

	
//put walls around the map to add borders
	private int tileGrid[][];
	private Collider cC1, cC2;
	
	private ArrayList<Bullet> bulletsOut = new ArrayList<Bullet>();
	private ArrayList<Bullet> bulletsIn = new ArrayList<Bullet>();
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
	private boolean gameActive;
	private double gameTimer;

	private int gamemode; // 1 for KoTH, 2 for Deathmatch
	private boolean increment;
	
	private ArrayList<Integer> keysDown;
	public Player me;
	public ArrayList<Player> players;
	
	private DatabaseReference roomRef;  // This is the database entry for the whole room
	public DatabaseReference myUserRef;  // This is the database entry for just our user's data. This allows us to more easily update ourselves.
//	private boolean currentlySending;
	
	private MainMenu surface;
	private Rectangle backButton;
	private Rectangle instructions;
	private Rectangle scoreBoard;
	private String un;
	private boolean spawn;
	
	private final float BUTTON_WIDTH = 0.1f;
	private final float BUTTON_HEIGHT = 0.1f;
	
	private PImage[] playerImage = new PImage[8];
	private PImage[] playerImage2 = new PImage[8];
	private PImage[] tileImage = new PImage[23]; 
//	private final int maxWorldCol = 100;
//	private final int maxWorldRow = 100;
//	private final int worldWidth = maxWorldCol * tM.getTileSize();
//	private final int worldHeight = maxWorldRow * tM.getTileSize();
	private BufferedImage image;
	private ArrayList<Integer> powerUpList;

	
	public World(MainMenu p, DatabaseReference roomRef) {

		players = new ArrayList<Player>();
		
		this.roomRef = roomRef;
		
		surface = p;
		try {
			image = ImageIO.read(new File("Assets" + fileSeparator + "map.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gameActive = false;
		

		increment = true;
		
		

		
		reader = new imageReaderToRGB();
		
		tileGrid = reader.ImageToArr(image);
		

		powerUpList = TileManager.getPowerUpList();
		tM = new TileManager(16, 5, tileGrid);
		tM.changePowerUpList(powerUpList);
		screenWidth = maxScreenCol * tM.getTileSize();
		screenHeight = maxScreenRow * tM.getTileSize();
		this.p = p;
		
		backButton = new Rectangle((int)(screenWidth*0.015), (int)(screenHeight*0.03), (int)(screenWidth*BUTTON_WIDTH), (int)(screenHeight*BUTTON_HEIGHT/2));
		scoreBoard = new Rectangle((int)(screenWidth*0.8), 0, (int)(screenWidth*BUTTON_WIDTH), (int)(screenHeight*BUTTON_HEIGHT/2));
		
		cC1 = new Collider(tM.getTileSize(), tM, true);
		cC2= new Collider(tM.getTileSize(), tM, false);
		playerShoot = false;
		
		SPAWN1X = tM.getTileSize() * 50;
		SPAWN1Y = tM.getTileSize() * 2;
		SPAWN2X = tM.getTileSize() * 98;
		SPAWN2Y = tM.getTileSize() * 50;
		SPAWN3X = tM.getTileSize() * 50;
		SPAWN3Y = tM.getTileSize() * 98;
		SPAWN4X = tM.getTileSize() * 2;
		SPAWN4Y = tM.getTileSize() * 50;
	}
	
	public void updateGamemode(int i) {
		gamemode = i;
	}
	
	public void changeWeapon(int w) {
		if(w == 1)
			me.setWeapon(new Shotgun());
		else if(w == 2)
			me.setWeapon(new Sniper());	
		else if(w == 3)
			me.setWeapon(new Submachine());
		else if(w == 4)
			me.setWeapon(new Knife());
	}
	
	public void resetHealth() {
		me.heal(999);
	}
	
	// The statements in the setup() function 
	// execute once when the program beginsas
	public void setup() {
		
		gameActive = false;
		menuClick = new SoundFile(surface, "Assets" + fileSeparator + "Music" + fileSeparator + "Menu Select.wav");
		menuClick.amp(0.5f);
		heHeHaHa = new SoundFile(p, "Assets" + fileSeparator + "Music" + fileSeparator + "HeHeHeHa.wav");
		heHeHaHa.amp(0.7f);
		shotGunShot = new SoundFile(p, "Assets" + fileSeparator + "Music" + fileSeparator + "ShotgunShot.wav");
		shotGunShot.amp(0.3f);
		subMachineShot = new SoundFile(p, "Assets" + fileSeparator + "Music" + fileSeparator + "Submachine.wav");
		subMachineShot.amp(0.2f);
		sniperShotSound = new SoundFile(p, "Assets" + fileSeparator + "Music" + fileSeparator + "SniperShot.wav");
		knifeSound = new SoundFile(p, "Assets" + fileSeparator + "Music" + fileSeparator + "KnifeSlash.wav");
		loseHealth = new SoundFile(surface, "Assets" + fileSeparator + "Music" + fileSeparator + "Pain Sound Effect.wav");
		collectPowerUp = new SoundFile(surface, "Assets" + fileSeparator + "Music" + fileSeparator + "CollectPowerupSound.wav");
		collectPowerUp.amp(0.3f);


	
		
		p.frameRate(30);
		
		playerImage2[0] = p.loadImage("Assets" + fileSeparator + "RedAvatar" + fileSeparator + "Forward1.png");
		playerImage2[1] = p.loadImage("Assets"  + fileSeparator + "RedAvatar" + fileSeparator + "Backward1.png");
		playerImage2[2] = p.loadImage("Assets"  + fileSeparator + "RedAvatar" + fileSeparator + "Forward2.png");
		playerImage2[3] = p.loadImage("Assets"  + fileSeparator + "RedAvatar" + fileSeparator + "Backward2.png");
		playerImage2[4] = p.loadImage("Assets"  + fileSeparator + "RedAvatar" + fileSeparator + "Left1.png");
		playerImage2[5] = p.loadImage("Assets"  + fileSeparator + "RedAvatar" + fileSeparator + "Right1.png");
		playerImage2[6] = p.loadImage("Assets"  + fileSeparator + "RedAvatar" + fileSeparator + "Left2.png");
		playerImage2[7] = p.loadImage("Assets"  + fileSeparator + "RedAvatar" + fileSeparator + "Right2.png");

		
		playerImage[0] = p.loadImage("Assets" + fileSeparator + "BlueAvatar" + fileSeparator + "Backwards1.png");
		playerImage[1] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Forward1.png");
		playerImage[2] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Backwards2.png");
		playerImage[3] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Forward2.png");
		playerImage[4] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Left1.png");
		playerImage[5] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Right1.png");
		playerImage[6] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Left2.png");
		playerImage[7] = p.loadImage("Assets"  + fileSeparator + "BlueAvatar" + fileSeparator + "Right2.png");

		


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
		tileImage[22] = p.loadImage("Assets" + fileSeparator + "Tiles" + fileSeparator + "powerUpLoader.png");
		
		gameTimer = 0;
		
		tM.setTiles(tileImage);
//		player.setWeapon(new Sniper());
//		player.setWeapon(new Shotgun());
//		player.setWeapon(new Submachine());

		un = JOptionPane.showInputDialog("Enter your epic gamertag:" );
		
		myUserRef = roomRef.child("users").push();
	
		me =  new Player(un, bulletsIn, bulletsOut, powerUpList, myUserRef.getKey(), cC1, screenWidth/2 - tM.getTileSize()/2, screenHeight/2 - tM.getTileSize()/2, tM.getTileSize() * 50, tM.getTileSize() * 2, p, new Sniper(), 5.0, 12.5, 100, playerImage, tM.getTileSize());
		
	//	System.out.println(me.getWorldX());
		myUserRef.setValueAsync(me.getDataObject());
	//	System.out.println(me.getDataObject().worldX);
		
		roomRef.child("users").addChildEventListener(new UserChangeListener());
		
		Runtime.getRuntime().addShutdownHook(new Thread()  // This code runs when the program exits.
	    {
	      public void run()
	      {
	    	  if (players.size() == 0)
					roomRef.removeValueAsync();
				else
					myUserRef.removeValueAsync();
	      }
	    });
		

		
		
		
		
		
	
	}

	/**
	* Draws the entire in-game perspective
	* 
	* @post Changes background to arena background
	* @post Changes PApplet's text alignment to Center
	* 
	*/
	public void draw() {
		//if (add check for if player decisions are the same)
	if (players.size() > 0) {
	Player p2 = players.get(0);
	if (me.returnGameMode() == p2.returnGameMode() && me.returnGameMode() != 0) {
//		System.out.println("me choice: " + me.returnGameMode() + " p2 choice: " +  p2.returnGameMode());
		gameActive = true;
	}
	else gameActive = false;
	if (gameActive) {

		if(gameTimer >= 5000)
		{
			gameActive = false;
			gameTimer = 0;
		}

		
		if (me.getDead()) {
			me.setDead(false);
			spawn = false;		
		}else if(me.getHealth() <= 0) {
			surface.switchScreen(ScreenSwitcher.DEATH_SCREEN);
			me.setDead(true);
			me.justSpawned(true);
		}
		else {
			increment = true;
		
		while (me.getUsername().equals(p2.getUsername()) || me.getUsername() == "") {
			me.changeUsername();
		}
		
		int i = (int)(Math.random() * 4);
		if (me.getUsername().compareTo(p2.getUsername()) > 0) {
			if (!spawn) {
					
			switch (i) {
			case 0:
				me.setWorldX(SPAWN1X);
				me.setWorldY(SPAWN1Y);
				break;
			case 1:
				me.setWorldX(SPAWN2X);
				me.setWorldY(SPAWN2Y);
				break;
			case 2:				
				me.setWorldX(SPAWN3X);
				me.setWorldY(SPAWN3Y);
				break;
			case 3:
				me.setWorldX(SPAWN4X);
				me.setWorldY(SPAWN4Y);
				break;
			}

			spawn = true;
			}
		} else {
			if (!spawn) {
				i++;
				if (i == 4)
					i = 0;
				switch (i) {
				case 2:
					me.setWorldX(SPAWN1X);
					me.setWorldY(SPAWN1Y);
					break;
				case 3:
					me.setWorldX(SPAWN2X);
					me.setWorldY(SPAWN2Y);
					break;
				case 0:				
					me.setWorldX(SPAWN3X);
					me.setWorldY(SPAWN3Y);
					break;
				case 1:
					me.setWorldX(SPAWN4X);
					me.setWorldY(SPAWN4Y);
					break;
				}
				spawn = true;
			}
			
		}
			
		//System.out.println(p.frameRate);
		p.background(220,220,220);  
		p.textAlign(p.CENTER);

	
		tM.draw(p, me);
		
	
		
		if(me.getWeapon().getAmmo() <= 0){

			me.getWeapon().reload();
			
			
		}
		
		
		
		surface.rect(scoreBoard.x, scoreBoard.y, scoreBoard.width, scoreBoard.height, 10, 10, 10, 10);
		p.textSize(20);
		p.textAlign(p.CENTER);
		p.fill(0);
		surface.text("" + me.getUsername() + me.getKillCount(), scoreBoard.x+scoreBoard.width/6, scoreBoard.y+2*scoreBoard.height/6);
		p.textSize(40);
		p.fill(0, 0, 255);
		surface.text("Game Ends In: " + gameTimer + "/5000", p.width/3, 30);
		p.fill(255);
	//	p.fill(220, 220, 220);

		bulletsIn = p2.getOut();
		me.setInc(bulletsIn);

		myUserRef.setValue(me.getDataObject(), new CompletionListener() {
			//set value bullet object
			@Override
			public void onComplete(DatabaseError arg0, DatabaseReference arg1) {
			//	currentlySending = false;
			}
			
		});		
		
			if (p2.getDead()) {
				p.textSize(80);
				p.textAlign(p.CENTER);
		//		System.out.println(increment);
				if(killUp) {
					me.incrementKillCount(1);
					killUp = false;
				}
				p.text("Other Player is Dead", me.getScreenX(), me.getScreenY() - 80); 
				
			} else {		
				increment = true;
				killUp = true;
				
			float screenX = p2.getWorldX() - me.getWorldX() + me.getScreenX();
			float screenY = p2.getWorldY() - me.getWorldY() + me.getScreenY();
	//		p2.setImages(playerImage2);	
			
	
			if (p2.getN()) {
				p2.avatar.setDirection('w', true);
				
			}
			else if (p2.getS()) {
				p2.avatar.setDirection('s', true);
					
				}
			else if (p2.getW()) {
				p2.avatar.setDirection('a', true);
				
			}
			else if (p2.getE()) {
				p2.avatar.setDirection('d', true);
				
			}
			
			cC2.checkTile(p2);
			
			
			p2.setScreenX(screenX);
			p2.setScreenY(screenY);
			
			if (p2.getEmote()) {
		//		System.out.println("p2 emote: " + p2.getEmote() + " p2 counter: " + p2.getEmoteCounter());
				p2.emote();
				
				if (p2.getEmoteCounter() == 0) {
					heHeHaHa.play();
					p2.setEmote(false);
					p2.setEmoteCounter(0);
				}
				p2.incrementEmoteCounter();
//				if (p2.getEmoteCounter() >= 20)
//					p2.setEmoteCounter(0);
			}
			myUserRef.setValueAsync(me.getDataObject());
			p2.draw(p);	

		
			bulletsIn = p2.getOut();
			me.setInc(bulletsIn);
			
	
			if (p2.getR1() > 0 && p2.getC1() > 0 ) {
				tM.getMap()[p2.getC1()][p2.getR1()] = 22;

			}
			if (p2.getR2() > 0 && p2.getC2() > 0) {
				tM.getMap()[p2.getC2()][p2.getR2()] = 22;

			}
			if (p2.getR3() > 0 && p2.getC3() > 0) {
				tM.getMap()[p2.getC3()][p2.getR3()] = 22;

			}
			if (p2.getR4() > 0 && p2.getC4() > 0) {
				tM.getMap()[p2.getC4()][p2.getR4()] = 22;

			}
			cC2.checkTileCleanup(me);
			
		}
			p.fill(0);
		p.textSize(20);
		p.textAlign(p.LEFT);
		
		surface.text("" + p2.getUsername() + p2.getKillCount(), scoreBoard.x+scoreBoard.width/6, scoreBoard.y+4*scoreBoard.height/6);
		p.fill(255);

	
		myUserRef.setValue(me.getDataObject(), new CompletionListener() {
			//set value bullet object
			@Override
			public void onComplete(DatabaseError arg0, DatabaseReference arg1) {
			//	currentlySending = false;
			}
			
		});	

		
		me.setOut(bulletsOut);
		myUserRef.setValueAsync(me.getDataObject());
		me.draw(p);

		myUserRef.setValueAsync(me.getDataObject());
		
		for(Bullet b : bulletsOut)
		{
			p.fill(0, 255, 0);
			b.draw(p, me);
		}
		for(Bullet b : bulletsIn) {
			p.fill(0, 255, 0);
			b.draw(p, me);
			
		}
		
		
		for (int k = 0; k < bulletsIn.size(); k++) {
			
//			System.out.println("Incoming bullet, player health: " + me.getHealth());
			if (bulletsIn.get(k).damagePlayer(me)){
		//		System.out.println("damaged, health left: " + me.getHealth());
		//		me.getInc().remove(k);
				bulletsIn.remove(k);
				k--;
				me.setDataChanged(true);
			}else if (cC1.checkTiles(bulletsIn.get(k))) {
				bulletsIn.remove(k);
				k--;
				me.setDataChanged(true);
			}
		}
		
		for (int j = 0; j < bulletsOut.size(); j++) {
			if (bulletsOut.get(j).damagePlayer(p2)) {
				bulletsOut.remove(j);
				j--;
			}
			else if (cC1.checkTiles(bulletsOut.get(j))) {
				bulletsOut.remove(j);
				j--;
			}
		}
		
		myUserRef.setValue(me.getDataObject(), new CompletionListener() {
			//set value bullet object
			@Override
			public void onComplete(DatabaseError arg0, DatabaseReference arg1) {
			//	currentlySending = false;
			}
			
		});	
		
		
	
		
		
		
		hud.draw(p, screenWidth, screenHeight, me, new Player(un, screenWidth-screenWidth/10 - tM.getTileSize()/2, 2*screenHeight/3 - tM.getTileSize()/2, 0, tM.getTileSize() * 20, p, playerImage, tM.getTileSize()));

		surface.textAlign(surface.CENTER);
		surface.fill(255);
		surface.rect(backButton.x, backButton.y, backButton.width, backButton.height, 10, 10, 10, 10);
		surface.fill(0);
		surface.textSize(20);
		String str0 = "Quit";
		float w0 = surface.textWidth(str0);
		surface.text(str0, backButton.x+backButton.width/2, backButton.y+backButton.height/2);

		for(int w = 0; w < bulletsIn.size(); w++)
		{
			if(me.getWeapon().getMaxDistance() < bulletsIn.get(w).getDistanceTraveled())
			{
				bulletsIn.remove(w);
			}
		}



		for(int q = 0; q < bulletsOut.size(); q++)
		{
			if(me.getWeapon().getMaxDistance() < bulletsOut.get(q).getDistanceTraveled())
			{
				bulletsOut.remove(q);
			}
		}

		
		if(me.getWeapon() instanceof Sniper)
		{
			Sniper s = (Sniper)me.getWeapon();
			if(s.getJustShot())
			{
				s.incrementCoolDown();
			}
		}
		
		if(playerShoot)
		{
			
			if(me.getWeapon().getAmmo() <= 0)
			{
				playerShoot = false;
			}
		
			int oldNum = bulletsOut.size();
			
			for(Bullet b : me.shoot(p.mouseX, p.mouseY)) {	
				bulletsOut.add(b);
			}
			
			int newNum = bulletsOut.size();
			
			if(oldNum != newNum)
			{
				if(me.getWeapon() instanceof Shotgun && me.getWeapon().getAmmo() >= 0)
				{
					shotGunShot.play();
				}
				else if(me.getWeapon() instanceof Submachine && me.getWeapon().getAmmo() >= 0)
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
				else if(me.getWeapon() instanceof Sniper && me.getWeapon().getAmmo() >= 0)
				{
					
						sniperShotSound.play();
					
				}

				else if(me.getWeapon() instanceof Knife)
				{
					knifeSound.play();

			}
			
		
			
			
			
			if (!(me.getWeapon() instanceof Submachine))
			{
				playerShoot = false;
			}
			
					
			
		}

	}
		myUserRef.setValue(me.getDataObject(), new CompletionListener() {
			//set value bullet object
			@Override
			public void onComplete(DatabaseError arg0, DatabaseReference arg1) {
			//	currentlySending = false;
			}
			
		});	
		
		
		p.push();

		double p2X = p2.getWorldX();
		double p2Y = p2.getWorldY();
		double	angle = Math.atan(Math.abs(p2Y - me.getWorldY())/Math.abs(p2X - me.getWorldX()));
		
		
		if(me.getWorldY() < p2Y && me.getWorldX() > p2X)
		{
			angle = Math.PI + angle;
		}
//		
		if(me.getWorldY() > p2Y && me.getWorldX() > p2X)
		{
			angle = Math.PI - angle;
		}
//		
		if(me.getWorldY() < p2Y && me.getWorldX() < p2X)
		{
			angle = 2*Math.PI - angle;

		}

		
		Line line = Line.constructLineFromAngle((float)(screenWidth/2+me.getWidth()/10), (float)(screenHeight/2 - 4*me.getHeight()/2), angle * 180 / Math.PI, 20);
		
		Line line2 = Line.constructLineFromAngle(line.getX2(), line.getY2(), (angle-2.35)*(180/Math.PI), 10);
		Line line3 = Line.constructLineFromAngle(line.getX2(), line.getY2(), (angle+2.35)*(180/Math.PI), 10);

		line.setStrokeWeight(6);
		line.setStrokeColor(new Color(0, 255, 0));
		line2.setStrokeWeight(6);
		line2.setStrokeColor(new Color(0, 255, 0));
		line3.setStrokeWeight(6);
		line3.setStrokeColor(new Color(0, 255, 0));
		line.draw(p);
		line2.draw(p);
		line3.draw(p);
//		p.rect(SPAWN1X, BUTTON_HEIGHT, maxScreenCol, BUTTON_WIDTH);
		p.pop();
		
		
		}
	}


			myUserRef.setValue(me.getDataObject(), new CompletionListener() {
				//set value bullet object
				@Override
				public void onComplete(DatabaseError arg0, DatabaseReference arg1) {
				//	currentlySending = false;
				}
				
			});	
		}
		
		
	}
		
	public int getPlayerGameMode()
	{
		return me.returnGameMode();
	}
		
	public boolean getGameStatus()
	{
		return gameActive;
	
	}
	
	public boolean getIncrement() {
		return increment;
	}
	
	public void incrementGameTimer()
	{
		gameTimer++;
	}
	
	public void setGameStatus(boolean status)
	{
		gameActive = status;
	}
	
	public void setGameTimer(double timer)
	{
		gameTimer = 0;
	}
	
	/**
	* Tracks the keys pressed that moves the player
	*/
	public void keyPressed() {
		final int k = p.keyCode;

		
		me.setDirection(k, true);
		me.avatar.setDirection(k, true);
		
		//Currently selected emote will display
		if(p.key == 'e')
		{
			me.emote();
			heHeHaHa.play();
		}
		myUserRef.setValueAsync(me.getDataObject());
	}
	
	
	public void setPlayerGameMode(int gameMode) {
		me.setGameMode(gameMode);
	}
			
	/**
	* Tracks the keys released
	*/
	public void keyReleased() {
		
		me.setDirection(p.keyCode, false) ;
		me.avatar.setDirection(p.keyCode, false) ;
		myUserRef.setValueAsync(me.getDataObject());
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
	
	/**
	 * 
	 * Handles all changes to the "users" database reference. This part of the database contains information about the players currently in this room.
	 * Because Firebase uses a separate thread than most other processes we're using (both Swing and Processing),
	 * we need to have a strategy for ensuring that code is executed somewhere besides these methods.
	 * 
	 * @author john_shelby
	 *
	 */
	public class UserChangeListener implements ChildEventListener {

		private ConcurrentLinkedQueue<Runnable> tasks;
		
		public UserChangeListener() {  // This threading strategy will work with Processing programs. Just use this code inside your PApplet.
			tasks = new ConcurrentLinkedQueue<Runnable>();
			
			p.registerMethod("post", this);
		}
		
		
		public void post() {
			while (!tasks.isEmpty()) {
				Runnable r = tasks.remove();
				r.run();
			}
		}
		
		@Override
		public void onCancelled(DatabaseError arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onChildAdded(DataSnapshot arg0, String arg1) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					if (me.idMatch(arg0.getKey())) {  // Don't react to our own data
						return;
					}
					
					PlayerData data = arg0.getValue(PlayerData.class);
					Player player = new Player(arg0.getKey(), data, p, playerImage2, cC2, tM.getTileSize(), tM);
					players.add(player);
					
				}
				
			});
		}

		@Override
		public void onChildChanged(DataSnapshot arg0, String arg1) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					if (me.idMatch(arg0.getKey()))
						return;
					
					for (int i = 0; i < players.size(); i++) {
						Player player = players.get(i);
						if (player.idMatch(arg0.getKey())) {
							PlayerData data = arg0.getValue(PlayerData.class);
							player.syncWithDataObject(data, tM, cC1);
						}
					}
				}
				
			});
			
		}

		@Override
		public void onChildMoved(DataSnapshot arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onChildRemoved(DataSnapshot arg0) {
			tasks.add(new Runnable() {

				@Override
				public void run() {
					if (me.idMatch(arg0.getKey()))
						return;
					
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).idMatch(arg0.getKey())) {
							players.remove(i);
							break;
						}
					}
				}
				
			});
			
		}
		
	}
}

