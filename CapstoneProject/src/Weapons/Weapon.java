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

	/**
	* Weapon constructor, creates a weapon object
	* 
	* @param mag Magazine size of weapon
	* @param reloadTime Reload time of weapon
	* @param sight Sight of the Player when holding the weapon
	* @param movement Movement of the weapon
	*/
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
	/**
	* Shoots the weapon, and returns the bullets that were shot
	* 
	* @param x Beginning X location
	* @param y Beginning Y location
	* @param p Player that the weapon belongs to
	*/
	public abstract ArrayList<Bullet> shoot(int x, int y, Player p);
	
	/**
	* Reloads the weapon
	*/
	public abstract void reload();
	
	/**
	* @return Returns the ammo
	*/
	public abstract double getAmmo();
	
	/**
	* @return returns magazine size
	*/
	public double getMagSize()
	{
		return magazineSize;
	}

	/**
	* @return Returns movement speed
	*/
	public double getSpeed() {
		return movementSpeed;
	}
	
	/**
	* @return returns the reloadCounter
	*/
	public double getReloadCounter()
	{
		return reloadCounter;
	}
	
	/**
	* @return Returns the reloadTime
	*/
	public double getReloadTime()
	{
		return reloadTime;
	}
	
}
