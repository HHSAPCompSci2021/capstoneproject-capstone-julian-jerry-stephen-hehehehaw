package Weapons;

import java.util.ArrayList;

import Players.Player;

public class Shotgun extends Weapon{
	
	public Shotgun() {
		super(2, 40, 10, 0.9, 30, 1000);
		// TODO Auto-generated constructor stub
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

	@Override
	public ArrayList<Bullet> shoot(int x, int y, Player p) {
		if(ammo >= 1)
		{
			ammo--;
			
			Bullet bullet = new Bullet(p.getWorldX() + p.getWidth(), p.getWorldY() + p.getHeight(), 0, 0, damage, 1, 15, 15, 30);
			Bullet bullet2 = new Bullet(p.getWorldX() + p.getWidth(), p.getWorldY() + p.getHeight(), 0, 0, damage, 1, 15, 15, 30);
			Bullet bullet3 = new Bullet(p.getWorldX() + p.getWidth(), p.getWorldY() + p.getHeight(), 0, 0, damage, 1, 15 ,15, 30);

			bullet.setVelocity(x - p.getScreenX(), y - p.getScreenY());
			bullet2.setVelocity(x - p.getScreenX() -  40 * (Math.random()), y - p.getScreenY() -  40 * (Math.random()));
			bullet3.setVelocity(x - p.getScreenX() +  40 * (Math.random()), y - p.getScreenY() +  40 * (Math.random()));

			
			ArrayList<Bullet> bullets = new ArrayList<Bullet>();
			
			bullets.add(bullet);
			bullets.add(bullet2);

			bullets.add(bullet3);

			
		
			return bullets;
		}
	
	
		return new ArrayList<Bullet>();
	}


	@Override
	public double getAmmo() {
		return ammo;
	}

}
