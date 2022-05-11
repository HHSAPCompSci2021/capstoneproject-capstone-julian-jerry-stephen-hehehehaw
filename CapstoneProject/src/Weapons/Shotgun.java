package Weapons;

import java.util.ArrayList;

import Players.Player;

public class Shotgun extends Weapon{
	private int reloadCounter = 0;
	private double ammo = magazineSize;
	private boolean justShot = false;
	private double coolDown = 0;
	
	public Shotgun() {
		super(2, 2, 10);
		// TODO Auto-generated constructor stub
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

	@Override
	public ArrayList<Bullet> shoot(int x, int y, Player p) {
		if(ammo >= 1)
		{
			ammo--;
			
			Bullet bullet = new Bullet(p.getScreenX() + p.getWidth(), p.getScreenY() + p.getHeight(), 0, 0, 50, 1);
			Bullet bullet2 = new Bullet(p.getScreenX() + p.getWidth(), p.getScreenY() + p.getHeight(), 0, 0, 50, 1);
			Bullet bullet3 = new Bullet(p.getScreenX() + p.getWidth(), p.getScreenY() + p.getHeight(), 0, 0, 50, 1);

			bullet.setVelocity(x - p.getScreenX(), y - p.getScreenY());
			bullet2.setVelocity(x - p.getScreenX(), y - p.getScreenY() +  (y - p.getScreenY()) * (Math.random()-0.65));
			bullet3.setVelocity(x - p.getScreenX(), y - p.getScreenY() +  (y - p.getScreenY()) * (Math.random()+0.65));

			
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
