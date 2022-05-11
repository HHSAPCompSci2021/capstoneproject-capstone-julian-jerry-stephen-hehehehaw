package Weapons;

import java.util.ArrayList;

import Players.Player;

public class Sniper extends Weapon{
	private int reloadCounter = 0;
	private double ammo = magazineSize;
	private boolean justShot = false;
	private double coolDown = 0;
	
	public Sniper() {
		super(5, 4, 20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Bullet> shoot(int x, int y, Player p) {
			
		if(ammo >= 1 && justShot == false)
		{
			ammo--;
			
			Bullet bullet = new Bullet(p.getScreenX() + p.getWidth(), p.getScreenY() + p.getHeight(), 0, 0, 50, 1);
			bullet.setVelocity(x - p.getScreenX(), y - p.getScreenY());
			
			ArrayList<Bullet> bullets = new ArrayList<Bullet>();
			
			bullets.add(bullet);
			
			justShot = true;
			return bullets;
		}
		else if(ammo >= 1 && justShot == true)
		{
			coolDown++;
			
			if(coolDown == 5)
			{
				justShot = false;
				coolDown = 0;
			}
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
