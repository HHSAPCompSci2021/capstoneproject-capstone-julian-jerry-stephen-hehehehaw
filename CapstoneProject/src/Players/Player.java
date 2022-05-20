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

public class Player {
	public final static String fileSeparator = System.getProperty("file.separator");
	private Weapon weapon;
	private boolean dead;
	private boolean emote;
	public Avatar avatar;
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
	private int slowCD = 0, speedCD = 0, dmgCD = 0, magCD = 0;

//	private int cd = 0;
	private Collider collide;
	
	private ArrayList<PImage> emotes;
	private PImage activeEmote;
	private PApplet p;
	
	private double emoteInitWidth;
	private double emoteInitHeight;
	private int emoteCounter;
	
	private String uniqueID;
	
	//false = KOTH, true = deathmatch, default is KOTH
	private boolean gameDecision; 
	private ArrayList<Integer> powerUpList;

	public int powerUpRow1, powerUpColumn1, powerUpRow2, powerUpColumn2, powerUpRow3, powerUpColumn3, powerUpRow4, powerUpColumn4;
	private int tileSize;
	
	private int killCount;
	private int deathCount;
	private int points;

	private ArrayList<Bullet> incoming = new ArrayList<Bullet>();
	private ArrayList<Bullet> outgoing = new ArrayList<Bullet>();
	

	
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
		
		gameDecision = false; 
		dead = false;
		if (health <=  0)
			dead = true;
		

		killCount = 0;
		deathCount = 0;
		points = 0;
		
		justSpawned = true;
	}
	
/*
 * 
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

	/*
	 * 
	 */
	public Player(String uniqueID, PlayerData data, PApplet p, PImage[] images, Collider c, int tileSize, TileManager tM) {

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
	
	public double getEmoteCounter() {
		return emoteCounter;
	}
	public void incrementEmoteCounter() {
		emoteCounter++;
	}
	
	public ArrayList<Bullet> getInc(){
		return incoming;
	}

	public void setInc(ArrayList<Bullet> i) {
		incoming = i;
	}
	
	public ArrayList<Bullet> getOut(){
		return outgoing;
	}

	public void setOut(ArrayList<Bullet> o) {
		outgoing = o;
	}
	public boolean getEmote() {
		return emote;
	}
	
	
	public void setR1(int r) {
		powerUpRow1 = r;
	}
	public void setC1(int c) {
		
		powerUpColumn1 = c;
	}
	public void setR2(int r) {
		powerUpRow2 = r;
	}
	public void setC2(int c) {
		
		powerUpColumn2 = c;
	}
	
	public void setR3(int r) {
		powerUpRow3 = r;
	}
	public void setC3(int c) {
		
		powerUpColumn3 = c;
	}
	
	public void setR4(int r) {
		powerUpRow4 = r;
	}
	public void setC4(int c) {
		
		powerUpColumn4 = c;
	}
	
	public int getR1(){
		return powerUpRow1;
	}
	public int getC1() {
		return powerUpColumn1;
	}
	public int getR2(){
		return powerUpRow2;
	}
	public int getC2() {
		return powerUpColumn2;
	}
	
	public int getR3(){
		return powerUpRow3;
	}
	public int getC3() {
		return powerUpColumn3;
	}
	
	public int getR4(){
		return powerUpRow4;
	}
	public int getC4() {
		return powerUpColumn4;
	}
	
	
	
	public void setImages(PImage[] images) {

		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7], 1, 0);
	}
	public int getWeaponInt() {
		if (weapon instanceof Shotgun) 
			return 0;
		if (weapon instanceof Sniper)
			return 1;
		if (weapon instanceof Submachine)
			return 2;
		return 3;
		
	}
	public void setDead(boolean d) {
		dead = d;
	}
	
	public boolean getDead() {
		return dead;
	}
	
	public PlayerData getDataObject() {
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
	
	public void syncWithDataObject(PlayerData data, TileManager tM, Collider c) {
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
	
	public void setGameMode(boolean gameMode)
	{
		gameDecision = gameMode;
	}
	
	public boolean getN() {
		return north;
	}
	public boolean getS() {
		return south;
	}
	public boolean getE() {
		return east;
	}
	public boolean getW() {
		return west;
	}
	
	public boolean returnGameMode()
	{
		return gameDecision;
	}
	
	public void setCollisions(boolean t) {
		collisionOn = t;
	}
	public Rectangle getBulletHitBox() {
		return bulletHitBox;
	}
	
	public void draw(PApplet p) {

	

		p.push();
		p.fill(0);
		p.textAlign(p.CENTER);
		p.textSize(20);
		p.text(username, screenX + tileSize/2, screenY + (tileSize) * 1.5f);
		p.pop();
		
		
//		System.out.println(this + ":" + gameDecision);
	//	System.out.println(worldX + " " + worldY)
	//	System.out.println(" Speed: " + speed + " speedBuffed: " + speedBuffed + " speedCD: " + speedCD);
		if(justSpawned == true)
		{
			spawnCounter++;
			if(spawnCounter == 50) //adjust as necessary (spawn protect timer)
			{
				justSpawned = false;
				spawnCounter = 0;
			}
		}
		
		collisionOn = false;
		if (collide != null) {

			int whichOne = collide.checkTile(this);
			if (whichOne != -1)
			//	System.out.println(whichOne);
			
				switch (whichOne) {
				case 17:
					slowed = true;
					break;
				case 19:
					damageBuffed = true;
					break;
					
				case 20:
					magBuffed = true;
					break;

				case 21:
		//			System.out.println("speed buffed");
					speedBuffed = true;
					break;
					
				case -1:
					break;
				}
				
				if (slowed) {
					slowCD++;

					if (slowCD >= 30) {
						slowed = false;
						slowCD = 0;
						speed = defaultSpeed;					}
					
				}
				

				if (speedBuffed) {
					speedCD++;
					
					if (speedCD >= 450) {
						speedBuffed = false;
						speedCD = 0;
						speed = getRealDefaultSpeed();
						defaultSpeed = getRealDefaultSpeed();
					}
				}
				if (damageBuffed) {
					dmgCD++;
					
					if (dmgCD >= 400) {
						dmgCD = 0;
						damageBuffed = false;
						getWeapon().setDamage((int)(getWeapon().getDamage()));
						
					}
					
				}
				if (magBuffed){
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
	
	public Weapon getWeapon()
	{
		return weapon;
	}
	
	public void setWeapon(Weapon w)
	{
		weapon = w;
		dataUpdated = true;
	}
	
	public float getWorldX()
	{
		return worldX;
	}
	
	public void setWorldX(int x) {
		worldX = x;
		dataUpdated = true;
	}
	
	public float getWorldY()
	{
		return worldY;
	}	
	public void setWorldY(int y) {
		worldY = y;
		dataUpdated = true;
	}
	public float getScreenX()
	{
		return screenX;
	}
	public void setScreenX(float x) {
		screenX = x;
	}
	public void setScreenY(float y) {
		screenY = y;
	}
	
	public float getScreenY()
	{
		return screenY;
	}
	
	public double getWidth()
	{
		return bulletHitBox.width/2;
	}
	
	public boolean getJustSpawned()
	{
		dataUpdated = true;
		return justSpawned;
		
	}
	
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
	
	public void justSpawned(boolean spawn) {
		justSpawned = spawn;
	}
	
	public double getHeight()
	{
		return bulletHitBox.height/2;
	}
	
	public Rectangle getTileHitBox()
	{
		return tileHitBox;
	}
	
	public ArrayList<Bullet> shoot(int x, int y) {
		
		dataUpdated = true;
		return weapon.shoot(x,y, this); 
	}
	
	public double getSpeed()
	{
		return defaultSpeed;
	}
	
	public double getRealDefaultSpeed() {
		return realDefaultSpeed;
	}
	
	
	public double getHealth()
	{
		return health;
	}
	
	public void loseHealth(double damage)
	{
		health -= damage;
	}
	public void setSpeedUp(double sped) {
		speed = sped;
		defaultSpeed = sped;
	}
	public void setSpeedDown(double sped) {
		speed = sped;
	}
	
	public void heal(int health) {
		this.health += health;
		if (this.health > 100)
			this.health = 100;
	}
	
	public double getInitHealth()
	{
		return initHealth;
	}
	
	public void emote()
	{
		activeEmote = p.loadImage("Assets" + fileSeparator + "hehehaha.png");
		emoteInitWidth = activeEmote.width;
		emoteInitHeight = activeEmote.height;
		activeEmote.resize(60, 60);
		
		emote = true;
		dataUpdated = true;

	}
	public boolean idMatch(String uid) {
		return this.uniqueID.equals(uid);
	}
	

	public void moveObject() {
		if (!collisionOn) {
		  worldX += (east?  speed : 0) - (west?  speed : 0); //ternary condition, if east is true add 20, if east is false add 0
		  worldY += (south? speed : 0) - (north? speed : 0);
		}
		  dataUpdated = true;
	}
	
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
	public boolean isDataChanged() {
		// TODO Auto-generated method stub
		return dataUpdated;
	}
	public void setDataChanged(boolean t) {
		dataUpdated = true;
	}

	public int[] getPowerUpList() {
		int[] arr = new int[powerUpList.size()];
		for (int i = 0; i < powerUpList.size(); i++) {
			arr[i] = powerUpList.get(i);
		}
		return arr;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getKillCount() {
		return killCount;
	}
	
	public int getDeathCount() {
		return deathCount;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void incrementKillCount() {
		killCount++;
	}
	
	public void incrementDeathCount() {
		deathCount++;
	}
	
	public void incrementPoints(int x) {
		points += x;
	}

	public void setEmote(boolean b) {
		emote = b;
		
	}

	public void setEmoteCounter(int i) {
		emoteCounter = i;
		
	}

	public String getUsername() {

		return username;
	}
	public void changeUsername() {
		username += 1;
	}
}
