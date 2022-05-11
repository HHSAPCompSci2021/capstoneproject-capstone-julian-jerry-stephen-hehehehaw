package Weapons;

import java.util.ArrayList;

import Players.Player;

public class Submachine extends Weapon{
	private int reloadCounter = 0;
	private double ammo = magazineSize;
	
	
	public Submachine() {
		super(25, 3, 10);
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
			bullet.setVelocity(x - p.getScreenX(), y - p.getScreenY() +  (y - p.getScreenY()) * (Math.random()-0.65));
			
			ArrayList<Bullet> bullets = new ArrayList<Bullet>();
			
			bullets.add(bullet);
			
		
			return bullets;
		}
	
	
		return new ArrayList<Bullet>();
	}


	@Override
	public double getAmmo() {
		return ammo;

	}

}
