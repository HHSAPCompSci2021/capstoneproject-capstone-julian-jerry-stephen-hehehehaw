import Weapons.Weapon;

public class Player {
	private Weapon weapon;
	private Avatar avatar;
	private double vision;
	private double speed;
	private double health; 
	
	
	public Player(Weapon w, Avatar a, double vision, double speed, double health)
	{
		weapon = w;
		avatar = a;
		this.vision = vision;
		this.speed = speed;
		this.health = health; 
	}
	
	public void shoot(int x, int y) {
		weapon.shoot(x,y); 
	}
	
	public double getVision()
	{
		return vision;
	}
	
	public double getSpeed()
	{
		return speed;
	}
	
	public double getHealth()
	{
		return health;
	}
	
	
	
	
	
	
	
}
