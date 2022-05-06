package Players;
import java.util.ArrayList;

import Weapons.Weapon;
import processing.core.PApplet;
import processing.core.PImage;

public class Player {
	PApplet p;
	private Weapon weapon;
	private Avatar avatar;
	private double vision;
	private double speed;
	private double health; 
	
	
	private float x, y;
	private boolean north;
	private boolean south;
	private boolean west;
	private boolean east;
	
	public Player(PApplet pa,Weapon w, double vision, double speed, double health, PImage[] images)
	{
		p = pa;
		weapon = w;
		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7]);
		this.vision = vision;
		this.speed = speed;
		this.health = health;
	}
	public Player(PApplet pa, PImage[] images) {
		p = pa;
		speed = 5;
		avatar = new Avatar("down", images[0], images[1], images[2], images[3], images[4], images[5], images[6], images[7]);
	
	}
	
	public void draw() {

		moveObject();
		p.fill(0);
		avatar.draw(p, x, y);
		avatar.spriteCounter++;
		if (avatar.spriteCounter > 13) {
			if (avatar.spriteNum == 1)
				avatar.spriteNum = 2;
			else if (avatar.spriteNum == 2) {
				avatar.spriteNum = 1;
			}
			avatar.spriteCounter = 0;
		}

		
	}
	
	public void shoot(int x, int y) {
		weapon.shoot(x,y); 
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
	  x += (east?  speed : 0) - (west?  speed : 0); //ternary condition, if east is true add 20, if east is false add 0
	  y += (south? speed : 0) - (north? speed : 0);
	}
	
	public void setDirection(int k, boolean decision) {
	 if      (k == 'w'    || k == 'W')   {
		 north = decision;
		 avatar.setDir("up");
	 }
	 else if (k == 's'  || k == 'S')   {
		 south = decision;
		 avatar.setDir("down");
	 }
		 else if (k == 'a'  || k == 'A')   {
		 west  = decision;
		 avatar.setDir("left");
		 }
	 else if (k == 'd' || k == 'D')   {
	 	east  = decision;
		 avatar.setDir("right");
	 }
	}
	
	
}
