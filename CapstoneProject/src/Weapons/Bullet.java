package Weapons;

import Players.Player;
import Tiles.Tile;
import processing.core.PApplet;

public class Bullet {
	private double vx, vy, x, y, damage, health; 
	private int width, height; 
	
	public Bullet(double x, double y, double vx, double vy, double damage, double health)
	{
		this.x = x;
		this.y = y; 
		this.damage = damage;
		this.health = health;
		this.vx = vx;
		this.vy = vy; 
	}
	
	public void move()
	{
		this.x += vx;
		this.y += vy;
	}
	
	public void draw(PApplet p)
	{
		p.rect((float)x, (float)y, (float)width, (float)height);
	}
	
	public boolean intersects(Tile t)
	{
		return false;
	}
	
	
	public boolean intersects(Player p)
	{
		return false;
	}
	
}
