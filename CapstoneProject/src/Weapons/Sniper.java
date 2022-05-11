package Weapons;

import java.util.ArrayList;

import Players.Player;

public class Sniper extends Weapon{
	private int reloadCounter = 0;
	private double ammo = magazineSize;
	
	public Sniper() {
		super(5, 4, 20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Bullet> shoot(int x, int y, Player p) {
			
		if(ammo >= 1)
		{
			ammo--;
			
			Bullet bullet = new Bullet(p.getWorldX() + p.getWidth(), p.getWorldY() + p.getHeight(), 0, 0, 50, 1);
			bullet.setVelocity(x - p.getWorldX(), y - p.getWorldY());
			
			ArrayList<Bullet> bullets = new ArrayList<Bullet>();
			
			bullets.add(bullet);
			
			
			return bullets;
		}
	
		return new ArrayList<Bullet>();

		
	}

	@Override
	public void reload() {
		reloadCounter++;
		
		if(reloadCounter == 150)
		{
			ammo = magazineSize;
			reloadCounter = 0;
		}
		
	}
	
	public double getAmmo()
	{
		return ammo;
	}

}
