package Weapons;

import java.awt.Rectangle;

import Players.Player;
import Tiles.Tile;
import processing.core.PApplet;

/** 
 * This class represents a Bullet. It is the projectile shot by weapons.
 * @author Julian
 * @version 5/20
 */
public class Bullet {
    private final double DEFAULT_SPEED; 
	private double vx, vy, worldX, worldY, damage, health; 
	private int width, height; 
	private Rectangle dimensions;
	private double distanceTraveled;
	
	/** 
	 * Creates a new instance of a Bullet object with initial point at (x,y)
	 * @param x To set first point's x coordinate
	 * @param y To set first point's x coordinate
	 * @param vx Velocity X of bullet
	 * @param vy Velocity Y of bullet
	 * @param damage Damage of bullet
	 * @param health Health of bullet
	 * @param width Width of bullet
	 * @param height Height of bullet
	 * @param speed Speed of bullet
	 */
	public Bullet(double x, double y, double vx, double vy, double damage, double health, int width, int height, double speed)
	{
		this.worldX = x;
		this.worldY = y; 
		this.damage = damage;
		this.health = health;
		this.vx = vx;
		this.vy = vy; 
		this.width = width;
		this.height = height;
		DEFAULT_SPEED = speed;
	}
	
	/** 
	 * @return Damage of bullet
	 */
	public double getDamage() {
		return damage;
	}
	/** 
	 * @return Speed of bullet
	 */
	public double getSpeed() {
		return DEFAULT_SPEED;
	}
	
	/** 
	 * @return Vx of bullet
	 */
	public double getvX() {
		return vx;
	}
	
	/** 
	 * @return Vy of bullet
	 */
	public double getvY() {
		return vy;
	}

	/** 
	 * @return Health of bullet
	 */
	public double getHealth() {
		return health;
	}
	
	/** 
	 * @return X loc of bullet
	 */
	public double getWorldX() {
		return worldX;
	}
	
	/** 
	 * @return Width of bullet
	 */
	public int getWidth() {
		return width;
	}
	
	/** 
	 * @return Height of bullet
	 */
	public int getHeight() {
		return height;
	}
	
	/** 
	 * @return Y loc of bullet
	 */
	public double getWorldY() {
		return worldY;
	}
	
	/** 
	 * Moves the bullet by vx and vy
	 */
	public void move()
	{
		this.worldX += vx;
		this.worldY += vy;
		
		distanceTraveled += Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
	}
	
	/** 
	 * Draws the bullet on the PApplet p
	 * @param p PApplet surface to draw on
	 * @param player Player within the program
	 */
	public void draw(PApplet p, Player player)
	{

		double screenX = worldX - player.getWorldX() + player.getScreenX();
		double screenY = worldY - player.getWorldY() + player.getScreenY();
		
		p.rect((float)screenX, (float)screenY, (float)width, (float)height);
		dimensions = new Rectangle((int)worldX, (int)worldY, 5, 5);
		move();
	}

	/** 
	 * Checks whether or not the bullet intersects with player
	 * @param player the Player that is being tested for intersection
	 * @return Returns whether or not the bullet intersects
	 */
	public boolean intersects(Player player)
	{
		Rectangle r = player.getBulletHitBox();
		

		int playerLeftWorldX = (int)(player.getWorldX() + r.x);
		int playerTopWorldY = (int) (player.getWorldY() + r.y);

		Rectangle dimension = new Rectangle(playerLeftWorldX, playerTopWorldY, r.width, r.height);
		
		if(dimensions.intersects(dimension))
		{
			return true;
		}
		
		return false;
	}	
	
	/** 
	 * Sets velocity of the bullet
	 * @param run The delta X from mouseX to X
	 * @param rise The delta Y from mouseY to Y
	 */
	public void setVelocity(double run, double rise) {
		double currentSpeed = Math.sqrt(Math.pow(rise, 2) + Math.pow(run, 2));
		if (currentSpeed > DEFAULT_SPEED) {
			rise /= currentSpeed/DEFAULT_SPEED;
			run /= currentSpeed/DEFAULT_SPEED;
		} else {
			rise *= DEFAULT_SPEED/currentSpeed;
			run *=  DEFAULT_SPEED/currentSpeed;
		}
		this.vx = run;
		this.vy = rise;
	}
	
	/** 
	 * @return The dimensions of the bullet
	 */
	public Rectangle getDimensions()
	{
		return dimensions;
	}
	
	/** 
	 * Damages the player
	 * @param p Damages player if bullet intersects
	 * @return True if bullet intersected
	 */
	public boolean damagePlayer(Player p)
	{
		p.setDataChanged(true);
		if(intersects(p))
		{
			if(!p.getJustSpawned())
			{
				p.loseHealth(damage);
				p.setDataChanged(true);
			}
			if (p.getHealth() <= 0)
				p.setDead(true);
			
			return true;
			
			
//			System.out.println("damaged " + p.getHealth());

		}
		return false;
	}
	
	/** 
	 * @return The distance traveled
	 */
	public double getDistanceTraveled()
	{
		return distanceTraveled;
	}
	
}
