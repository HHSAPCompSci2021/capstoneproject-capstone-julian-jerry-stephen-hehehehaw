package Players;
import java.awt.Rectangle;
import java.util.ArrayList;

import FireBaseStuff.BulletData;
import FireBaseStuff.PlayerData;
import Tiles.TileManager;
import Weapons.*;
import Weapons.Weapon;
import processing.core.PApplet;
import processing.core.PImage;

/** 
 * This class represents a Player. It represents a player within the game.
 * @author Jerry and Julian
 */
public class Player {
	public final static String fileSeparator = System.getProperty("file.separator");
	private Weapon weapon;
	private boolean dead;
	private boolean emote;
	private Avatar avatar;
//	private double vision;
	private double speed;
	private double defaultSpeed;
	private double realDefaultSpeed;
	private double health; 
	private Rectangle bulletHitBox;
	private Rectangle tileHitBox;
	private double initHealth;
	private boolean dataUpdated;
	private PlayerData data;
	private boolean justSpawned;
	private double spawnCounter;
	
	private float screenX, screenY;
	private float worldX, worldY;
	
	private boolean north;
	private boolean south;
	private String username;
	private boolean west;
	private boolean east;
	
	private boolean collisionOn = false;
	private boolean slowed, speedBuffed, damageBuffed, magBuffed;
	private int slowCD, speedCD, dmgCD, magCD;

//	private int cd = 0;
	private Collider collide;
	
	private ArrayList<PImage> emotes;
	private PImage activeEmote;
	private PApplet p;
	
	private double emoteInitWidth;
	private double emoteInitHeight;
	private int emoteCounter;
	
	private String uniqueID;
	
	//1 = KOTH, 2 = deathmatch
	private int gameDecision; 
	private ArrayList<Integer> powerUpList;
	/** 
	 * The type of powerup in each of the row and columns
	 */
	public int powerUpRow1, powerUpColumn1, powerUpRow2, powerUpColumn2, powerUpRow3, powerUpColumn3, powerUpRow4, powerUpColumn4;
	private int tileSize;
	
	private int killCount;
	private int deathCount;
	private double points;

	private ArrayList<Bullet> incoming = new ArrayList<Bullet>();
	private ArrayList<Bullet> outgoing = new ArrayList<Bullet>();
	

	/** 
	 * Creates a new instance of a Player object 
	 * @param username The username of the player object
	 * @param in The ArrayList of incoming bullets
	 * @param out The ArrayList of outgoing bullets
	 * @param powrUpList The ArrayList of power ups
	 * @param uniqueID The uniqueID of the player
	 * @param cl The collider of the object
	 * @param xS The x location of the object on the screen
	 * @param yS The y location of the object on the screen
	 * @param x The x location of the player on the world
	 * @param y The y location of the player on the world
	 * @param pa PApplet surface to draw on
	 * @param w Weapon of the player
	 * @param vision Vision of the player
	 * @param speed Speed of the player
	 * @param health Health of the player
	 * @param images Images of the player (animations)
	 * @param tileSize Size of the tiles
	 */
	public Player(String username, ArrayList<Bullet> in,  ArrayList<Bullet> out, ArrayList<Integer> powrUpList, String uniqueID, Collider cl, float xS, float yS, float x, float y, PApplet pa, Weapon w, double vision, double speed, double health, PImage[] images, int tileSize)
	{
		this.tileSize = tileSize;
		this.username = username;
		emote = false;

		spawnCounter = 0;
		collisionOn = false;
		incoming = in;
		outgoing = out;
		powerUpList = powrUpList;
		this.uniqueID = uniqueID;
		data = new PlayerData();
		dataUpdated = false;
		
		powerUpRow1 = -1;
		powerUpColumn1 = -1;
		powerUpRow2 = -1;
		powerUpColumn2 = -1;
		powerUpRow3 = -1;
		powerUpColumn3 = -1;
		powerUpRow4 = -1;
		powerUpColumn4 = -1;;
		
		collide = cl;
		worldX = x;
		worldY = y;
		screenX = xS;
		screenY = yS;
		
		weapon = w;
		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7], 1, 0);

		this.speed = speed * w.getSpeed();
		defaultSpeed = speed * w.getSpeed();

		realDefaultSpeed = speed * w.getSpeed();
		this.health = health;
		initHealth = health;
		tileHitBox = new Rectangle((int)(tileSize * 0.2), (int)(tileSize * 0.35), (int)(tileSize * 0.6), (int)(tileSize * 0.55));
		bulletHitBox = new Rectangle(0, 0, tileSize, tileSize);
		emotes = new ArrayList<PImage>();
		emotes.add(pa.loadImage("Assets" + fileSeparator + "BlueAvatar" + fileSeparator + "StandingBlueAvatar.png"));
		p = pa;
		emoteCounter = 0;
		
		gameDecision = 0; 
		dead = false;
		if (health <=  0)
			dead = true;
		

		killCount = 0;
		deathCount = 0;
		points = 0;
		slowCD = 0;
		speedCD = 0;
		
		justSpawned = true;
	}

	/** 
	 * Creates a new instance of a Player object 
	 * @param username The unique ID of the player object
	 * @param xS The x coordinate of the player in teh screen
	 * @param yS The y coordinate of the player in the screen
	 * @param xW The x coordinate of the player in the world
	 * @param yW The y coordinate of the player in the world
	 * @param pa The PApplet surface to drawn on
	 * @param images The images of the player (animations)
	 * @param tileSize The tile size of the tiles
	 */
	public Player(String username, float xS, float yS, float xW, float yW, PApplet pa, PImage[] images, int tileSize) { //placeholder for testing purposes
		
		this.username = username;
		spawnCounter = 0;
		

		powerUpRow1 = -1;
		powerUpColumn1 = -1;
		powerUpRow2 = -1;
		powerUpColumn2 = -1;
		powerUpRow3 = -1;
		powerUpColumn3 = -1;
		powerUpRow4 = -1;
		powerUpColumn4 = -1;
		worldX = xW;
		worldY = yW;
		
		screenX = xS;
		screenY = yS;
		defaultSpeed = 5;
		realDefaultSpeed = 5;
		
		
		speed = 5;
		avatar = new Avatar("up", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7], 1, 0);
		//dimensions = new Rectangle(0, 0, (int)(tileSize * 2), (int)(tileSize * 2)); //notsure if this should be worldX or screenX
		justSpawned = true;

	}

	/** 
	 * Creates a new instance of a Player object 
	 * @param uniqueID The unique ID of the player object
	 * @param data The data of the current player
	 * @param p The PApplet surface that the player will be on
	 * @param images The images of the player (animations)
	 * @param c The collider object which tests for collsiions
	 * @param tileSize The tileSize of the tiles
	 * @param tM The tile manager of the program
	 */
	public Player(String uniqueID, PlayerData data, PApplet p, PImage[] images, Collider c, int tileSize, TileManager tM) {

		killCount = data.killCount;
		deathCount = data.deathCount;
		points = data.points;
		
		gameDecision = data.gameDecision;
		emoteCounter = data.emoteCounter;
		slowed = data.slowed;
		speedBuffed = data.speedBuffed;
		damageBuffed = data.damageBuffed;
		magBuffed = data.magBuffed;
		slowCD = data.slowCD;
		speedCD = data.speedCD;
		dmgCD = data.dmgCD;
		magCD = data.magCD;
		
		username = data.username;
		emote = data.emote;
		
		collide = c;
		collisionOn = data.collisionOn;
		this.uniqueID = uniqueID;
		this.data = data;
		this.p = p;
		ArrayList<Bullet> blI = new ArrayList<Bullet>();
		ArrayList<Bullet> blO = new ArrayList<Bullet>();
		
		for (BulletData b: data.incBullets) {
			blI.add(new Bullet(b.worldX, b.worldY, b.vx, b.vy, b.damage, b.health, b.width, b.height, b.defaultSpeed));
			
		}
		incoming = blI;
		
		for (BulletData b: data.outBullets) {
			blO.add(new Bullet(b.worldX, b.worldY, b.vx, b.vy, b.damage, b.health, b.width, b.height, b.defaultSpeed));
		}
		outgoing = blO;
		
		tileHitBox = new Rectangle((int)(tileSize * 0.2), (int)(tileSize * 0.35), (int)(tileSize * 0.6), (int)(tileSize * 0.55));
		bulletHitBox = new Rectangle(0, 0, tileSize, tileSize);
		
		powerUpList = data.powerUpList;
		powerUpRow1 = data.powerUpRow1;
		powerUpColumn1 = data.powerUpColumn1;

		powerUpRow2 = data.powerUpRow2;
		powerUpColumn2 = data.powerUpColumn2;

		powerUpRow3 = data.powerUpRow3;
		powerUpColumn3 = data.powerUpColumn3;

		powerUpRow4 = data.powerUpRow4;
		powerUpColumn4 = data.powerUpColumn4;
		
		worldX = data.worldX;
		worldY = data.worldY;
		screenX = data.screenX;
		screenY = data.screenY;
		east = data.east;
		west = data.west;
		north = data.north;
		south = data.south;
		health = data.health;
		initHealth = data.health;
		dead = data.dead;
		switch (data.weapon) {
		case 0:
			setWeapon(new Shotgun());
			break;
		case 1: 
			setWeapon(new Sniper());
			break;
		case 2:
			setWeapon(new Submachine());
			break;
		case 3: 
			setWeapon(new Knife());
			break;
			
		}

		justSpawned = true;
		spawnCounter = 0;

	
		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7],  data.spriteNum, data.spriteCounter);


		avatar.spriteCounter = data.spriteCounter;
		avatar.spriteNum = data.spriteNum;
		
		syncWithDataObject(data, tM, c);
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	* Returns the current active emote counter
	* @return emoteCounter
	*/
	public double getEmoteCounter() {
		return emoteCounter;
	}
	
	/**
	* Changes the current active emote
	* @post emoteCounter is incremented by 1
	*/
	public void incrementEmoteCounter() {
		emoteCounter++;
	}
	
	/**
	* Gets the incoming bullets
	* @return ArrayList of bullets that are incoming
	*/
	public ArrayList<Bullet> getInc(){
		return incoming;
	}

	/**
	* Sets the incoming bullets
	* @param o the ArrayList of bullets that are incoming
	*/
	public void setInc(ArrayList<Bullet> i) {
		incoming = i;
	}
	
	/**
	* Gets the outgoing bullets
	* @return ArrayList of bullets that are outgoing
	*/
	public ArrayList<Bullet> getOut(){
		return outgoing;
	}

	/**
	* Sets the outgoing bullets
	* @param o the ArrayList of bullets that are outgoing in the program
	*/
	public void setOut(ArrayList<Bullet> o) {
		outgoing = o;
	}
	
	/**
	* Gets the emote of the player
	* @return The emote of the player that is currently active
	*/
	public boolean getEmote() {
		return emote;
	}
	
	/**
	* Sets the powerup at this row
	* @param int The type of power up
	*/
	public void setR1(int r) {
		powerUpRow1 = r;
	}
	
	/**
	* Sets the powerup at this column
	* @param int The type of power up
	*/
	public void setC1(int c) {
		
		powerUpColumn1 = c;
	}
	
	/**
	* Gets the avatar of the player
	* @return Avatar the avatar of the player
	*/
	public Avatar getAvatar()
	{
		return avatar;
	}
	
	/**
	* Sets the powerup at this row
	* @param int The type of power up
	*/
	public void setR2(int r) {
		powerUpRow2 = r;
	}
	
	/**
	* Sets the powerup at this column
	* @param int The type of power up
	*/
	public void setC2(int c) {
		
		powerUpColumn2 = c;
	}
	
	/**
	* Sets the powerup at this row
	* @param int The type of power up
	*/
	public void setR3(int r) {
		powerUpRow3 = r;
	}
	
	/**
	* Sets the powerup at this column
	* @param int The type of power up
	*/
	public void setC3(int c) {
		
		powerUpColumn3 = c;
	}
	
	/**
	* Sets the powerup at this row
	* @param int The type of power up
	*/
	public void setR4(int r) {
		powerUpRow4 = r;
	}
	
	/**
	* Sets the powerup at this column
	* @param int The type of power up
	*/
	public void setC4(int c) {
		
		powerUpColumn4 = c;
	}
	
	/**
	* Returns the powerup at this row
	* @return int The type of power up
	*/
	public int getR1(){
		return powerUpRow1;
	}
	
	/**
	* Returns the powerup at this column
	* @return int The type of power up
	*/
	public int getC1() {
		return powerUpColumn1;
	}
	
	/**
	* Returns the powerup at this row
	* @return int The type of power up
	*/
	public int getR2(){
		return powerUpRow2;
	}
	
	/**
	* Returns the powerup at this column
	* @return int The type of power up
	*/
	public int getC2() {
		return powerUpColumn2;
	}
	/**
	* Returns the powerup at this row
	* @return int The type of power up
	*/
	public int getR3(){
		return powerUpRow3;
	}
	
	/**
	* Returns the powerup at this column
	* @return int The type of power up
	*/
	public int getC3() {
		return powerUpColumn3;
	}
	
	/**
	* Returns the powerup at this row
	* @return int The type of power up
	*/
	public int getR4(){
		return powerUpRow4;
	}
	
	/**
	* Returns the powerup at this column
	* @return int The type of power up
	*/
	public int getC4() {
		return powerUpColumn4;
	}
	
	
	/**
	* Sets the images of the player
	* @param images the Images of the player
	*/
	public void setImages(PImage[] images) {

		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7], 1, 0);
	}
	
	/**
	* Gets the current weapon instance of the Player
	* @return int 0 is Shotgun, 1 is Sniper, 2 is Submachine, 3 is Sniper
	*/
	public int getWeaponInt() {
		if (weapon instanceof Shotgun) 
			return 0;
		if (weapon instanceof Sniper)
			return 1;
		if (weapon instanceof Submachine)
			return 2;
		return 3;
		
	}
	
	/**
	* Sets the player to dead
	* @return d THe boolean of whether or not the player is dead
	*/
	public void setDead(boolean d) {
		dead = d;
	}
	
	/**
	* Checks if player is dead
	* @return dead
	*/
	public boolean getDead() {
		return dead;
	}
	
	
	/**
	* Gets the player data 
	* @return PlayerData The data of the current player
	*/
	public PlayerData getDataObject() {

		data.killCount = killCount;
		data.deathCount = deathCount;
		data.points = points;
		data.gameDecision = gameDecision;
		data.emoteCounter = emoteCounter;
		data.slowed = slowed;
		data.speedBuffed = speedBuffed;
		data.damageBuffed = damageBuffed;
		data.magBuffed = magBuffed;
		data.slowCD = slowCD;
		data.speedCD = speedCD;
		data.dmgCD = dmgCD;
		data.magCD = magCD;

		data.username = username;
		data.emote = emote;

		data.spriteNum = avatar.spriteNum;
		data.spriteCounter = avatar.spriteCounter;
		if (data.spriteCounter > (int)(65 * Math.pow(0.8835, speed + 8))) {
			dataUpdated = true;
			if (data.spriteNum == 1)
				data.spriteNum = 2;
			else if (data.spriteNum == 2) {
				data.spriteNum = 1;
			}
			data.spriteCounter = 0;
		
		}
		
		
		ArrayList<BulletData> blI = new ArrayList<BulletData>();
		ArrayList<BulletData> blO = new ArrayList<BulletData>();
		
		for (Bullet b: incoming) {
			
			BulletData bD = new BulletData();
			bD.worldX = b.getWorldX();
			bD.worldY = b.getWorldY();
			bD.defaultSpeed = b.getSpeed();
			bD.damage = b.getDamage();
			bD.vx = b.getvX();
			bD.vy = b.getvY();
			bD.health = b.getHealth();
			bD.width = b.getWidth();
			bD.height = b.getHeight();
			blI.add(bD);
			
		}
		data.incBullets = blI;
		
		for (Bullet b: outgoing) {
			
			BulletData bD = new BulletData();
			bD.worldX = b.getWorldX();
			bD.worldY = b.getWorldY();
			bD.defaultSpeed = b.getSpeed();
			bD.damage = b.getDamage();
			bD.vx = b.getvX();
			bD.vy = b.getvY();
			bD.health = b.getHealth();
			bD.width = b.getWidth();
			bD.height = b.getHeight();
			blO.add(bD);
			
		}
		data.outBullets = blO;

		data.collisionOn = collisionOn;
		data.dead = dead;
		
		dataUpdated = false;
		data.powerUpRow1 = powerUpRow1;
		data.powerUpColumn1 = powerUpColumn1;

		data.powerUpRow2 = powerUpRow2;
		data.powerUpColumn2 = powerUpColumn2;

		data.powerUpRow3 = powerUpRow3;
		data.powerUpColumn3 = powerUpColumn3;

		data.powerUpRow4 = powerUpRow4;
		data.powerUpColumn4 = powerUpColumn4;
		
		data.powerUpList = powerUpList;
		data.worldX = worldX;
		data.worldY = worldY;
		data.screenX = screenX;
		data.screenY = screenY;
		data.west = west;
		data.east = east;
		data.south = south;
		data.north = north;
		data.health = health;
		data.weapon = getWeaponInt();
		data.collisionOn = collisionOn;
		return data;
	}
	
	/**
	* Syncs the data with the other user 
	* @param dataThe data of the other player
	* @param tm The tile manager of the other player
	* @param c Collider to check collisions with the other player's bullets
	*/
	public void syncWithDataObject(PlayerData data, TileManager tM, Collider c) {

		killCount = data.killCount;
		deathCount = data.deathCount;
		points = data.points;
		gameDecision = data.gameDecision;
		emoteCounter = data.emoteCounter;
		username = data.username;
		
		slowed = data.slowed;
		speedBuffed = data.speedBuffed;
		damageBuffed = data.damageBuffed;
		magBuffed = data.magBuffed;
		slowCD = data.slowCD;
		speedCD = data.speedCD;
		dmgCD = data.dmgCD;
		magCD = data.magCD;
		emote = data.emote;
		
		avatar.spriteNum = data.spriteNum;
		avatar.spriteCounter = data.spriteCounter;
		if (avatar.spriteCounter > (int)(65 * Math.pow(0.8835, speed + 8))) {
			dataUpdated = true;
			if (avatar.spriteNum == 1)
				avatar.spriteNum = 2;
			else if (avatar.spriteNum == 2) {
				avatar.spriteNum = 1;
			}
			avatar.spriteCounter = 0;
		
		}
		
		
		collisionOn = data.collisionOn;
		ArrayList<Bullet> blI = new ArrayList<Bullet>();
		ArrayList<Bullet> blO = new ArrayList<Bullet>();
		
		for (BulletData b: data.incBullets) {
			blI.add(new Bullet(b.worldX, b.worldY, b.vx, b.vy, b.damage, b.health, b.width, b.height, b.defaultSpeed));
			
		}
		incoming = blI;
		
		for (BulletData b: data.outBullets) {
			blO.add(new Bullet(b.worldX, b.worldY, b.vx, b.vy, b.damage, b.health, b.width, b.height, b.defaultSpeed));
		}
		outgoing = blO;
		dataUpdated = false;

		powerUpRow1 = data.powerUpRow1;
		powerUpColumn1 = data.powerUpColumn1;

		powerUpRow2 = data.powerUpRow2;
		powerUpColumn2 = data.powerUpColumn2;

		powerUpRow3 = data.powerUpRow3;
		powerUpColumn3 = data.powerUpColumn3;

		powerUpRow4 = data.powerUpRow4;
		powerUpColumn4 = data.powerUpColumn4;
		powerUpList = data.powerUpList;
		health = data.health;
		
		worldX = (float) data.worldX;
		worldY = (float) data.worldY;
		screenX = data.screenX;
		screenY = data.screenY;
		west = data.west;
		east =data.east;
		south = data.south;
		north = data.north;
		dead = data.dead;
		if (data.health <= 0)
			dead = true;
		
		
		powerUpList = data.powerUpList;
		tM.changePowerUpList(powerUpList);
		

		
		
	}
	
	/**
	* Sets the game mode of the player
	* @param int The decision of the player: 
	*/
	public void setGameMode(int gameMode)
	{
		gameDecision = gameMode;
	}
	
	/**
	* Returns true if direction of player is north
	* @return west
	*/
	public boolean getN() {
		return north;
	}
	
	/**
	* Returns true if direction of player is south
	* @return west
	*/
	public boolean getS() {
		return south;
	}
	
	/**
	* Returns true if direction of player is east
	* @return west
	*/
	public boolean getE() {
		return east;
	}
	
	/**
	* Returns true if direction of player is west
	* @return west
	*/
	public boolean getW() {
		return west;
	}
	
	public void setCollisions(boolean t) {
		collisionOn = t;
	}
	
	/**
	* Returns the bullet hit box
	* @return Rectangle of the hit box of the bullet
	*/
	public Rectangle getBulletHitBox() {
		return bulletHitBox;
	}
	
	/**
	* Sets the username of the player
	* @param name New username of the player
	*/
	public void setUsername(String name)
	{
		username = name;
	}
	
	
	/** 
	 * Draws a new instance of a World object 
	 * @param p object type PApplet to draw the line 
	 * @pre Properties affecting the World are set on p
	 */
	public void draw(PApplet p) {

	

		p.push();
		p.fill(0);
		p.textAlign(p.CENTER);
		p.fill(0,255, 0);
		p.textSize(20);
		p.text(username, screenX + tileSize/2, screenY + (tileSize) * 1.5f);
		p.pop();
		
		
//		System.out.println(this + ":" + gameDecision);
	//	System.out.println(worldX + " " + worldY)
	//	System.out.println(" Speed: " + speed + " speedBuffed: " + speedBuffed + " speedCD: " + speedCD);
		if(justSpawned == true)
		{
			spawnCounter++;
			if(spawnCounter == 100) //adjust as necessary (spawn protect timer)
			{
				justSpawned = false;
				spawnCounter = 0;
			}
		}
		
		collisionOn = false;
		if (collide != null) {

			int whichOne = collide.checkTile(this);
			if (whichOne != -1)
				System.out.println(whichOne);
			
				switch (whichOne) {
				case 15:
					break;
				case 17:
					System.out.println("slowed");
					slowed = true;
					break;
				case 19:
					System.out.println("dmgupped");
					damageBuffed = true;
					break;
					
				case 20:
					System.out.println("magUpped");
					magBuffed = true;
					break;

				case 21:
					System.out.println("speedBuffed");
					speedBuffed = true;
					break;
				
				}
				
				if (slowed) {

					System.out.println("slow buffed, CD left: " + slowCD);
					slowCD++;

					if (slowCD >= 30) {
						slowed = false;
						slowCD = 0;
						speed = defaultSpeed;					
						}
					
				}
				

				if (speedBuffed) {
					
					System.out.println("speed buffed, CD left: " + speedCD);
					speedCD++;
					
					if (speedCD >= 450) {
						speedBuffed = false;
						speedCD = 0;
						speed = getRealDefaultSpeed();
						defaultSpeed = getRealDefaultSpeed();
					}
				}
				if (damageBuffed) {

					System.out.println("dmg buffed, CD left: " + dmgCD);
					dmgCD++;
					
					if (dmgCD >= 400) {
						dmgCD = 0;
						damageBuffed = false;
						getWeapon().setDamage((int)(getWeapon().getDamage()));
						
					}
					
				}
				if (magBuffed){
					System.out.println("mag buffed, CD left: " + magCD);
					magCD++;
					
					if (magCD >= 400) {
						magCD = 0;
						magBuffed = false;
						getWeapon().setMagSize(getWeapon().getMagSize());
					}
					
				}
				
				
			if (!collisionOn) 
				moveObject();
			
			dataUpdated = true;
			
			
		} 
		
		p.fill(0);
		avatar.draw(p, screenX, screenY);
		
		
		avatar.spriteCounter++;
		if (avatar.spriteCounter > (int)(65 * Math.pow(0.8835, speed + 8))) {
			dataUpdated = true;
			if (avatar.spriteNum == 1)
				avatar.spriteNum = 2;
			else if (avatar.spriteNum == 2) {
				avatar.spriteNum = 1;
			}
			avatar.spriteCounter = 0;
		
		}


		if(activeEmote != null && emote)
		{
			p.push();
			if(activeEmote.width >= emoteInitWidth || activeEmote.height >= emoteInitHeight)
			{
				emoteCounter++;
				if(emoteCounter >= 20)
				{
					activeEmote = null;
					emoteCounter = 0;
					emote = false;
					dataUpdated = true;
				}
			
			}
			else {
				
				activeEmote.resize(activeEmote.width+10, activeEmote.height+10);
				p.image(activeEmote, screenX+5*tileHitBox.width/4, screenY-tileHitBox.height/2);
			}
			
			if(activeEmote != null )
			{
				p.image(activeEmote, screenX+5*tileHitBox.width/4, screenY-tileHitBox.height/2);
			}
			
			p.pop();
		}
		
		dataUpdated = true;
			
		
	}
	
	/**
	* Gets the current gam emode decision
	* @return int 1 if deathmatch, 0 if koth
	*/
	public int returnGameMode()
	{
		return gameDecision;
	}
	
	
	/**
	* Gets the current weapon of player
	* @return weapon The weapon of the player
	*/
	public Weapon getWeapon()
	{
		return weapon;
	}
	
	/**
	* Sets the weapon of the player
	* @param w Weapon of the player
	*/
	public void setWeapon(Weapon w)
	{
		weapon = w;
		dataUpdated = true;
	}
	
	/**
	* Gets worldX
	* @return x The new worldX
	*/
	public float getWorldX()
	{
		return worldX;
	}
	
	/**
	* Sets world X
	* @param x The new worldX
	*/
	public void setWorldX(int x) {
		worldX = x;
		dataUpdated = true;
	}
	
	/**
	* Gets worldY
	* @return y The new worldY
	*/
	public float getWorldY()
	{
		return worldY;
	}	
	
	/**
	* Sets world Y
	* @param y The new worldY
	*/
	public void setWorldY(int y) {
		worldY = y;
		dataUpdated = true;
	}
	
	/**
	* Gets screen X
	* @return screenX
	*/
	public float getScreenX()
	{
		return screenX;
	}
	
	/**
	* Sets screen X
	* @param x The new screenY
	*/
	public void setScreenX(float x) {
		screenX = x;
	}
	
	/**
	* Sets screen Y
	* @param y The new screenY
	*/
	public void setScreenY(float y) {
		screenY = y;
	}
	
	/**
	* Gets screen Y
	* @return screenY
	*/
	public float getScreenY()
	{
		return screenY;
	}
	
	/**
	* Gets the width of the bullet hit box
	* @return Returns the width of the bullet hit box 
	*/
	public double getWidth()
	{
		return bulletHitBox.width/2;
	}
	
	/**
	* Checks if player has just spawned
	* @return Boolean if player has just spawned
	*/
	public boolean getJustSpawned()
	{
		dataUpdated = true;
		return justSpawned;
		
	}
	
	/**
	* Changes the layout of tile grid
	* @param tM New TileManager to base grid off of
	*/
	public void changeTileGrid(TileManager tM) {

		if (getR1() > 0 && getC1() > 0 ) {
			tM.getMap()[getC1()][getR1()] = 22;

		}
		if (getR2() > 0 && getC2() > 0) {
			tM.getMap()[getC2()][getR2()] = 22;

		}
		if (getR3() > 0 && getC3() > 0) {
			tM.getMap()[getC3()][getR3()] = 22;

		}
		if (getR4() > 0 && getC4() > 0) {
			tM.getMap()[getC4()][getR4()] = 22;

		}
		setR1(-1);
		setC1(-1);
		setR2(-1);
		setC2(-1);
		setR3(-1);
		setC3(-1);
		setR4(-1);
		setC4(-1);
		
	}
	
	/**
	* Sets a boolean if the player has just spawned in order to prevent spawn killing
	* @param spawn The boolean of whether or not 
	*/
	public void justSpawned(boolean spawn) {
		justSpawned = spawn;
	}
	
	/**
	* Returns the height of the bullet hit box
	* @return Returns the height of the bulletHitBox
	*/
	public double getHeight()
	{
		return bulletHitBox.height/2;
	}
	
	/**
	* Gets the tile hit box
	* @return Rectangle dimensions of the tile hit box
	*/
	public Rectangle getTileHitBox()
	{
		return tileHitBox;
	}
	
	/**
	* Shoots the current weapon the player is holding
	* @return ArrayList<Bullet> Bullets of the weapon that was shot
	* @post Changes dataUpdated to true
	*/
	public ArrayList<Bullet> shoot(int x, int y) {
		
		dataUpdated = true;
		return weapon.shoot(x,y, this); 
	}
	
	/**
	* Returns the original speed of player
	* @return defaultSpeed of player
	*/
	public double getSpeed()
	{
		return defaultSpeed;
	}
	
	/**
	* Returns the default speed of the player
	* @return realDefaultSpeed of the player 
	*/
	public double getRealDefaultSpeed() {
		return realDefaultSpeed;
	}
	
	/**
	* Returns the health of the player
	* @return The health of the player
	*/
	public double getHealth()
	{
		return health;
	}
	
	/**
	* Damages the player
	* @param damage The amount of damage to apply to the player's health
	*/
	public void loseHealth(double damage)
	{
		health -= damage;
	}
	
	/**
	* Sets speed of player
	* @param sped New speed of the player
	*/
	public void setSpeedUp(double sped) {
		speed = sped;
		defaultSpeed = sped;
	}
	
	/**
	* Sets speed of player
	* @param sped New speed of the player
	*/
	public void setSpeedDown(double sped) {
		speed = sped;
	}
	
	/**
	* Heals the player
	* @param health Healths the player by a certain health
	*/
	public void heal(int health) {
		this.health += health;
		if (this.health > 100)
			this.health = 100;
	}
	
	/**
	* Returns the initial health of the player
	* @return initHealth of the player
	*/
	public double getInitHealth()
	{
		return initHealth;
	}
	
	/**
	* Emotes the player
	*/
	public void emote()
	{
		activeEmote = p.loadImage("Assets" + fileSeparator + "hehehaha.png");
		emoteInitWidth = activeEmote.width;
		emoteInitHeight = activeEmote.height;
		activeEmote.resize(60, 60);
		
		emote = true;
		dataUpdated = true;

	}
	
	/**
	* Checks if the unique ID matches the passed in ID
	* @param uid The ID to test 
	*/
	public boolean idMatch(String uid) {
		return this.uniqueID.equals(uid);
	}
	
	/**
	* Moves the player within the "world"
	*/
	public void moveObject() {
		if (!collisionOn) {
		  worldX += (east?  speed : 0) - (west?  speed : 0); //ternary condition, if east is true add 20, if east is false add 0
		  worldY += (south? speed : 0) - (north? speed : 0);
		}
		  dataUpdated = true;
	}
	
	/**
	* Sets direction of the Player
	* @param k The key that was clicked to set the direction of the player 
	* @param decision The decision of the player
	*/
	public void setDirection(int k, boolean decision) {
		
		dataUpdated = true;
	 if      (k == 'w'    || k == 'W')   {
		 north = decision;
//		 avatar.setDir("up");
	 }
	 else if (k == 's'  || k == 'S')   {
		 south = decision;
	//	 avatar.setDir("down");
	 }
		 else if (k == 'a'  || k == 'A')   {
		 west  = decision;
		// avatar.setDir("left");
		 }
	 else if (k == 'd' || k == 'D')   {
	 	east  = decision;
		 //avatar.setDir("right");
	 }
	
	
	
	
	}
	
	/**
	* Returns whether or not data has been changed
	* @return Boolean if data has been changed
	*/
	public boolean isDataChanged() {
		// TODO Auto-generated method stub
		return dataUpdated;
	}
	
	/**
	* Indicates that the data has been updated
	* @param t Boolean whether or not data has been changed
	*/
	public void setDataChanged(boolean t) {
		dataUpdated = true;
	}

	/**
	* Returns the powerup list of the game
	* @return Array of power ups within the game 
	*/
	public int[] getPowerUpList() {
		int[] arr = new int[powerUpList.size()];
		for (int i = 0; i < powerUpList.size(); i++) {
			arr[i] = powerUpList.get(i);
		}
		return arr;
	}
	
	/**
	* Gets the kill count of the player
	* @return killCount of the player
	*/
	public int getKillCount() {
		return killCount;
	}
	
	/**
	* Gets death count of the Player
	* @return deathCount
	*/
	public int getDeathCount() {
		return deathCount;
	}
	
	/**
	* Gets the points of the player
	* @return Points
	*/
	public int getPoints() {
		return (int) points;
	}
	
	/**
	* Increments the kill count of the player in Death Match game mode
	* @param x The amount of points to increment by 
	*/
	public void incrementKillCount(int x) {
		killCount += x;
	}
	
	/**
	* Increments the death count of the player in Death Match game mode
	* @param x The amount of points to increment by 
	*/
	public void incrementDeathCount(int x) {
		deathCount += x;
	}
	
	/**
	* Increments the points of the player in KOTH game mode
	* @param x The amount of points to increment by 
	*/
	public void incrementPoints(double x) {
		points += x;
	}

	/**
	* Activates the emote of the player
	* @param b Boolean to set emote to true
	*/
	public void setEmote(boolean b) {
		emote = b;
		
	}

	/**
	* Changes the current emote of the player
	* @param i The index of the new emote 
	* 	
	*/
	public void setEmoteCounter(int i) {
		emoteCounter = i;
		
	}
	
	/**
	* Gets the username of the Player
	* @return Username of the player
	*/
	public String getUsername() {

		return username;
	}
	
	/**
	* Changes username of the Player 
	*/
	public void changeUsername() {
		if (username == null)
			username = "";
		username += "1";
		
	}
}
