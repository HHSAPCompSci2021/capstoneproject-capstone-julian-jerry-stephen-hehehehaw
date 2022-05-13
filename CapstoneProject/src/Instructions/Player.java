package Instructions;
import Weapons.Weapon;
import processing.core.PApplet;

public class Player {
	PApplet p;
	private Weapon weapon;
	public Avatar avatar;
	private double vision;
	private double speed;
	private double health; 
	
	
	float x, y;
	boolean north;
	boolean south;
	boolean west;
	boolean east;
	
	public Player(PApplet pa,Weapon w, double vision, double speed, double health, World world)
	{
		p = pa;
		weapon = w;
		avatar = new Avatar(p);
		this.vision = vision;
		this.speed = speed;
		this.health = health;
	}
	public Player(PApplet pa) {
		p = pa;
		speed = 20;
		avatar = new Avatar(p);
	
	}
	
	public void draw() {

		moveObject();
		p.fill(0);
		p.rect(x, y, 100, 100);
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
		 avatar.direction = "up";
	 }
	 else if (k == 's'  || k == 'S')   {
		 south = decision;
		 avatar.direction = "down";
	 }
		 else if (k == 'a'  || k == 'A')   {
		 west  = decision;
		 avatar.direction = "left";
		 }
	 else if (k == 'd' || k == 'D')   {
	 	east  = decision;
		 avatar.direction = "right";
	 }
	}
	
	
}
