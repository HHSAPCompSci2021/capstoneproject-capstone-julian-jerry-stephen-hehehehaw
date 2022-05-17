package Players;
import java.awt.Rectangle;
import java.util.ArrayList;

import Weapons.Bullet;
import Weapons.Weapon;
import processing.core.PApplet;
import processing.core.PImage;

public class Player {
	public final static String fileSeparator = System.getProperty("file.separator");
	private Weapon weapon;
	public Avatar avatar;
	private double vision;
	private double speed;
	private double defaultSpeed;
	private final double realDefaultSpeed;
	private double health; 
	private Rectangle dimensions;
	private double initHealth;
	
	private float screenX, screenY;
	private float worldX, worldY;
	
	private boolean north;
	private boolean south;
	private boolean west;
//	private int area;
	private boolean east;
	
	private boolean collisionOn = false;
	private boolean slowed, slowed2, speedBuffed, damageBuffed, magBuffed;
	private int slowCD, speedCD, dmgCD, magCD;

	private int cd = 0;
	private Collider c;
	
	private ArrayList<PImage> emotes;
	private PImage activeEmote;
	private PApplet p;
	
	private double emoteInitWidth;
	private double emoteInitHeight;
	private double emoteCounter;
	

	
	
	public Player(Collider cl, float xS, float yS, float x, float y, PApplet pa, Weapon w, double vision, double speed, double health, PImage[] images, int tileSize)
	{
		c = cl;
		worldX = x;
		worldY = y;
		screenX = xS;
		screenY = yS;
		
		weapon = w;
		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7]);
		this.vision = vision;
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
	public Player(float xS, float yS, float xW, float yW, PApplet pa, PImage[] images, int tileSize) { //placeholder for testing purposes
		
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

					if (slowCD >= 40) {
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
		avatar.spriteCounter++;
		if (avatar.spriteCounter > (int)(65 * Math.pow(0.8835, speed + 8))) {
			if (avatar.spriteNum == 1)
				avatar.spriteNum = 2;
			else if (avatar.spriteNum == 2) {
				avatar.spriteNum = 1;
			}
			avatar.spriteCounter = 0;
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
	}
	
	public float getWorldX()
	{
		return worldX;
	}
	
	public void setWorldX(int x) {
		worldX = x;
	}
	
	public float getWorldY()
	{
		return worldY;
	}	
	public void setWorldY(int y) {
		worldY = y;
	}
	public float getScreenX()
	{
		return screenX;
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
		return weapon.shoot(x,y, this); 
	}
	
	public double getVision()
	{
		return vision;
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

	}
	

	public void moveObject() {
	  worldX += (east?  speed : 0) - (west?  speed : 0); //ternary condition, if east is true add 20, if east is false add 0
	  worldY += (south? speed : 0) - (north? speed : 0);
	}
	
	public void setDirection(int k, boolean decision) {
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
	

	
	
}
