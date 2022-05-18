package Players;
import java.awt.Rectangle;
import java.util.ArrayList;

import FireBaseStuff.PlayerData;
import Weapons.*;
import Weapons.Weapon;
import processing.core.PApplet;
import processing.core.PImage;

public class Player {
	public final static String fileSeparator = System.getProperty("file.separator");
	private Weapon weapon;
	private boolean dead;
	public Avatar avatar;
//	private double vision;
	private double speed;
	private double defaultSpeed;
	private double realDefaultSpeed;
	private double health; 
	private Rectangle dimensions;
	private double initHealth;
	private boolean dataUpdated;
	private PlayerData data;
	
	private float screenX, screenY;
	private float worldX, worldY;
	
	private boolean north;
	private boolean south;
	private boolean west;
	private boolean east;
	
	private boolean collisionOn = false;
	private boolean slowed, slowed2, speedBuffed, damageBuffed, magBuffed;
	private int slowCD, speedCD, dmgCD, magCD;

//	private int cd = 0;
	private Collider c;
	
	private ArrayList<PImage> emotes;
	private PImage activeEmote;
	private PApplet p;
	
	private double emoteInitWidth;
	private double emoteInitHeight;
	private double emoteCounter;
	
	private String uniqueID;
	private ArrayList<Integer> powerUpList;

	public int powerUpRow1, powerUpColumn1, powerUpRow2, powerUpColumn2, powerUpRow3, powerUpColumn3, powerUpRow4, powerUpColumn4;
	

	
	
	public Player(ArrayList<Integer> powrUpList, String uniqueID, Collider cl, float xS, float yS, float x, float y, PApplet pa, Weapon w, double vision, double speed, double health, PImage[] images, int tileSize)
	{
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
		
		c = cl;
		worldX = x;
		worldY = y;
		screenX = xS;
		screenY = yS;
		
		weapon = w;
		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7]);

		this.speed = speed * w.getSpeed();
		defaultSpeed = speed * w.getSpeed();

		realDefaultSpeed = speed * w.getSpeed();
		this.health = health;
		initHealth = health;
		dimensions = new Rectangle((int)(tileSize * 0.2), (int)(tileSize * 0.35), (int)(tileSize * 0.6), (int)(tileSize * 0.55));
		
		emotes = new ArrayList<PImage>();
		emotes.add(pa.loadImage("Assets" + fileSeparator + "BlueAvatar" + fileSeparator + "StandingBlueAvatar.png"));
		p = pa;
		emoteCounter = 0;
		
		
		
	}
	
/*
 * 
 */
	public Player(float xS, float yS, float xW, float yW, PApplet pa, PImage[] images, int tileSize) { //placeholder for testing purposes
		

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
		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7]);
		//dimensions = new Rectangle(0, 0, (int)(tileSize * 2), (int)(tileSize * 2)); //notsure if this should be worldX or screenX
		
	}

	/*
	 * 
	 */
	public Player(String uniqueID, PlayerData data, PApplet p, PImage[] images, Collider c, int tileSize) {
		this.uniqueID = uniqueID;
		this.data = data;
		this.p = p;
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
		speed = data.speed;
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

		
		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7]);

//		
		syncWithDataObject(data);
		
		// TODO Auto-generated constructor stub
	}
	
	public void setPowerUpRow(int r) {
		powerUpRow1 = r;
	}
	public void setPowerUpCol(int c) {
		
		powerUpColumn1 = c;
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

		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7]);
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
		data.speed = realDefaultSpeed;
		data.weapon = getWeaponInt();
		return data;
	}
	
	public void syncWithDataObject(PlayerData data) {
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
		
		worldX = (float) data.worldX;
		worldY = (float) data.worldY;
		screenX = data.screenX;
		screenY = data.screenY;
		west = data.west;
		east =data.east;
		south = data.south;
		north = data.north;
		
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
	
	public void setCollisions(boolean t) {
		collisionOn = t;
	}
	public Rectangle getDimensions() {
		return dimensions;
	}
	
	public void draw(PApplet p) {
	//	System.out.println(worldX + " " + worldY)
		collisionOn = false;
		if (c != null) {

			int whichOne= c.checkTile(this);
			if (whichOne != -1)
				//System.out.println("whichOne: " + whichOne); 			
			//	System.out.println(" Speed: " + speed + " speedBuffed: " + speedBuffed + " speedCD: " + speedCD);

				switch (whichOne) {
				case 17:
					slowed = true;
					
					
					break;
				case 21:
					speedBuffed = true;
					break;
				case 19:
					damageBuffed = true;
					break;
					
				case 20:
					magBuffed = true;
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
					speedCD ++;
					
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
		}else 
			moveObject();
		
		p.fill(0);
		avatar.draw(p, screenX, screenY);
		dataUpdated = true;
		avatar.spriteCounter++;
		if (avatar.spriteCounter > (int)(65 * Math.pow(0.8835, speed + 8))) {
			dataUpdated = true;
			if (avatar.spriteNum == 1)
				avatar.spriteNum = 2;
			else if (avatar.spriteNum == 2) {
				avatar.spriteNum = 1;
			}
			avatar.spriteCounter = 0;
		
			syncWithDataObject(getDataObject());
		}


		if(activeEmote != null)
		{
			p.push();
			if(activeEmote.width >= emoteInitWidth || activeEmote.height >= emoteInitHeight)
			{
				emoteCounter++;
				if(emoteCounter >= 20)
				{
					activeEmote = null;
					emoteCounter = 0;
				}
			
			}
			else {
				
				activeEmote.resize(activeEmote.width+10, activeEmote.height+10);
				p.image(activeEmote, screenX+5*dimensions.width/4, screenY-dimensions.height/2);
			}
			
			if(activeEmote != null)
			{
				p.image(activeEmote, screenX+5*dimensions.width/4, screenY-dimensions.height/2);
			}
			
			p.pop();
		}
		
	
			
		
	}
	
	public Weapon getWeapon()
	{
		return weapon;
	}
	
	public void setWeapon(Weapon w)
	{
		weapon = w;
//		dataUpdated = true;
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
		return dimensions.width/2;
	}
	
	public double getHeight()
	{
		return dimensions.height/2;
	}
	
	public Rectangle getRectangle()
	{
		return dimensions;
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
		dataUpdated = true;

	}
	public boolean idMatch(String uid) {
		return this.uniqueID.equals(uid);
	}
	

	public void moveObject() {
	  worldX += (east?  speed : 0) - (west?  speed : 0); //ternary condition, if east is true add 20, if east is false add 0
	  worldY += (south? speed : 0) - (north? speed : 0);
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

	public int[] getPowerUpList() {
		int[] arr = new int[powerUpList.size()];
		for (int i = 0; i < powerUpList.size(); i++) {
			arr[i] = powerUpList.get(i);
		}
		return arr;
	}
	

	
	
}
