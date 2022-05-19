package Weapons;

import java.awt.Rectangle;

import Players.Player;
import Tiles.Tile;
import processing.core.PApplet;

public class Bullet {
    private final double DEFAULT_SPEED; 
	private double vx, vy, worldX, worldY, damage, health; 
	private int width, height; 
	private Rectangle dimensions;
	private double distanceTraveled;
	
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
	
	public double getDamage() {
		return damage;
	}
	public double getSpeed() {
		return DEFAULT_SPEED;
	}
	public double getvX() {
		return vx;
	}
	public double getvY() {
		return vy;
	}

	public double getHealth() {
		return health;
	}
	public double getWorldX() {
		return worldX;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	
	public double getWorldY() {
		return worldY;
	}
	public void move()
	{
		this.worldX += vx;
		this.worldY += vy;
		
		distanceTraveled += Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
	}
	
	public void draw(PApplet p, Player player)
	{

		double screenX = worldX - player.getWorldX() + player.getScreenX();
		double screenY = worldY - player.getWorldY() + player.getScreenY();
		
		p.rect((float)screenX, (float)screenY, (float)width, (float)height);
		dimensions = new Rectangle((int)worldX, (int)worldY, 5, 5);
		move();
	}

	
	public boolean intersects(Player player)
	{
		Rectangle r = player.getDimensions();
		

		int playerLeftWorldX = (int)(player.getWorldX() + r.x);
		int playerTopWorldY = (int) (player.getWorldY() + r.y);

		Rectangle dimension = new Rectangle(playerLeftWorldX, playerTopWorldY, r.width, r.height);
		
		if(dimensions.intersects(dimension))
		{
			return true;
		}
		
		return false;
	}	
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
	
	public Rectangle getDimensions()
	{
		return dimensions;
	}
	
	public boolean damagePlayer(Player p)
	{
		p.setDataChanged(true);
		if(intersects(p))
		{
			p.loseHealth(damage);
			p.syncWithDataObject(p.getDataObject());
			System.out.println("damaged " + p.getHealth());
			return true;
		}
		return false;
	}
	
	public double getDistanceTraveled()
	{
		return distanceTraveled;
	}
	
}
