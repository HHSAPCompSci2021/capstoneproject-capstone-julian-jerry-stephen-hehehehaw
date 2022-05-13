package Weapons;

import java.util.ArrayList;

import Players.Player;

public class Sniper extends Weapon{
	
	
	public Sniper() {
		super(5, 150, 20, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Bullet> shoot(int x, int y, Player p) {
			
		if(ammo >= 1 && justShot == false)
		{
			ammo--;
			
			Bullet bullet = new Bullet(p.getScreenX() + p.getWidth(), p.getScreenY() + p.getHeight(), 0, 0, 50, 1, 20, 20, 18);
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
		
		if(reloadCounter == reloadTime)
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
