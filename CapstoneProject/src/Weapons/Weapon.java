package Weapons;

import java.util.ArrayList;

import Players.Player;

public abstract class Weapon {
	protected double magazineSize;
	protected double reloadTime;
	protected double sightRange;
	protected double fireRate;

	public Weapon(double mag, double reloadTime, double sight)
	{
		magazineSize = mag;
		this.reloadTime = reloadTime; 
		sightRange = sight;
//		this.fireRate = fireRate;
		
	}
	
	public abstract ArrayList<Bullet> shoot(int x, int y, Player p);
	
	
	public abstract void reload();
	
}
