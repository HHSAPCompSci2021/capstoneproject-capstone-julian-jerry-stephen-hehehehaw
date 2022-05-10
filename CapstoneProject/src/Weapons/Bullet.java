package Weapons;

import java.awt.Rectangle;

import Players.Player;
import Tiles.Tile;
import processing.core.PApplet;

public class Bullet {
    private final double DEFAULT_SPEED = 1; 
	private double vx, vy, x, y, damage, health; 
	private int width, height; 
	private Rectangle dimensions;
	
	public Bullet(double x, double y, double vx, double vy, double damage, double health)
	{
		this.x = x;
		this.y = y; 
		this.damage = damage;
		this.health = health;
		this.vx = vx;
		this.vy = vy; 
		width = 5;
		height = 5;
	}
	
	public void move()
	{
		this.x += vx;
		this.y += vy;
	}
	
	public void draw(PApplet p)
	{
		p.rect((float)x, (float)y, (float)width, (float)height);
		dimensions = new Rectangle((int)x, (int)y, 10, 10);
		move();
	}

	
	public boolean intersects(Player p)
	{
		Rectangle dimension = p.getRectangle();
		
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
	
	public void loseHealth(int health)
	{
		
	}
	
}
