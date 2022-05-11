package Players;
import java.awt.Rectangle;
import java.util.ArrayList;

import Weapons.Bullet;
import Weapons.Weapon;
import processing.core.PApplet;
import processing.core.PImage;

public class Player {
	private Weapon weapon;
	public Avatar avatar;
	private double vision;
	private double speed;
	private double health; 
	private Rectangle dimensions;
	
	private float screenX, screenY;
	private float worldX, worldY;
	
	private boolean north;
	private boolean south;
	private boolean west;
	private boolean east;
	
	
	public Player(float xS, float yS, float x, float y, PApplet pa, Weapon w, double vision, double speed, double health, PImage[] images)
	{
		worldX = x;
		worldY = y;
		weapon = w;
		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7]);
		this.vision = vision;
		this.speed = speed;
		this.health = health;
		dimensions = new Rectangle((int)worldX+20, (int)worldY, 90, 95);
	}
	public Player(float xS, float yS, float xW, float yW, PApplet pa, PImage[] images) { //placeholder for testing purposes
		worldX = xW;
		worldY = yW;
		
		screenX = xS;
		screenY = yS;
		
		speed = 5;
		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7]);
		dimensions = new Rectangle((int)worldX+20, (int)worldY, 90, 95); //notsure if this should be worldX or screenX
	}
	
	public void draw(PApplet p) {
//need to implement smth to contain it within the bounds
		moveObject();
		p.fill(0);
		avatar.draw(p, screenX, screenY);
		avatar.spriteCounter++;
		if (avatar.spriteCounter > 13) {
			if (avatar.spriteNum == 1)
				avatar.spriteNum = 2;
			else if (avatar.spriteNum == 2) {
				avatar.spriteNum = 1;
			}
			avatar.spriteCounter = 0;
		}
		//not sure if this should be worldX or screenX yet
		dimensions.x = (int)worldX;
		dimensions.y = (int)worldY;


		
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
	
	public float getWorldY()
	{
		return worldY;
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
		return speed;
	}
	
	public double getHealth()
	{
		return health;
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
