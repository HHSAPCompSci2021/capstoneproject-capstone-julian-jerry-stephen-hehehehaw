package Weapons;

public abstract class Weapon {
	protected double magazineSize;
	protected double reloadTime;
	protected double sightRange;
	protected double fireRate;

	public abstract void shoot(int x, int y);
	
	
	public abstract void reload();
	
}
