package Weapons;

import java.util.ArrayList;

import Players.Player;

public abstract class Weapon {
	protected int magazineSize;
	private final int defaultMagSize;
	private final double defaultDamage;
	protected double reloadTime;
	protected double sightRange;
//	protected double fireRate;
	protected double movementSpeed;
	protected int reloadCounter;
	protected double ammo;
	protected boolean justShot = false;
	protected double coolDown;
	protected double damage;

	/**
	* Weapon constructor, creates a weapon object
	* 
	* @param mag Magazine size of weapon
	* @param reloadTime Reload time of weapon
	* @param sight Sight of the Player when holding the weapon
	* @param movement Movement of the weapon
	*/
	public Weapon(int mag, double reloadTime, double sight, double movement, double dmg)
	{
		magazineSize = mag;
		defaultMagSize = mag;
		this.reloadTime = reloadTime; 
		sightRange = sight;
		movementSpeed = movement;
		ammo = magazineSize;
		damage = dmg;
		defaultDamage = dmg;
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
	public int getMagSize()
	{
		return defaultMagSize;
	}
	
	public void setMagSize(int index) {
		magazineSize = index;
	}
//	public void changeAmmo(double index) {
//		ammo = magazineSize * index;
//	}
	
	public void setDamage(int index) {
		damage = index;
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
	
	public double getDamage() {
		return defaultDamage;
	}
}
