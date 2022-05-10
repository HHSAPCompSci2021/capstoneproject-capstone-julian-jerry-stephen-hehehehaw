package Weapons;

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
	
	public abstract void shoot(int x, int y);
	
	
	public abstract void reload();
	
}
