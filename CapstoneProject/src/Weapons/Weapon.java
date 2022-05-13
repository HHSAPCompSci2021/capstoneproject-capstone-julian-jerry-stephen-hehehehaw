package Weapons;

import java.util.ArrayList;

import Players.Player;

public abstract class Weapon {
	protected double magazineSize;
	protected double reloadTime;
	protected double sightRange;
	protected double fireRate;
	protected double movementSpeed;
	protected int reloadCounter;
	protected double ammo;
	protected boolean justShot = false;
	protected double coolDown;

	public Weapon(double mag, double reloadTime, double sight, double movement)
	{
		magazineSize = mag;
		this.reloadTime = reloadTime; 
		sightRange = sight;
		movementSpeed = movement;
		ammo = magazineSize;
		reloadCounter = 0;
		coolDown = 0;
//		this.fireRate = fireRate;
		
	}
	
	public abstract ArrayList<Bullet> shoot(int x, int y, Player p);
	
	public abstract void reload();
	
	public abstract double getAmmo();
	
	public double getMagSize()
	{
		return magazineSize;
	}


	public double getSpeed() {
		return movementSpeed;
	}
	
	public double getReloadCounter()
	{
		return reloadCounter;
	}
	
	public double getReloadTime()
	{
		return reloadTime;
	}
	
}
