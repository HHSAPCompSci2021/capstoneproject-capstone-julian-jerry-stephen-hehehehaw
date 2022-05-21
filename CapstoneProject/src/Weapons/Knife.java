package Weapons;

import java.util.ArrayList;

import Players.Player;

/** 
 * This class represents a Knife. It can perform various actions such as stabbing.
 * @author Julian
 * @version 5/20
 */
public class Knife extends Weapon{
	
	/**
	 * Creates a new Knife object
	 */
	public Knife() {
		super(1000000, 0, 20, 1.6, 100, 120);
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
		if(ammo >= 1 && justShot == false)
		{
			ammo--;
			
			Bullet bullet = new Bullet(p.getWorldX() + p.getWidth(), p.getWorldY() + p.getHeight(), 0, 0, damage, 1, 20, 20, 50);
			bullet.setVelocity(x - p.getScreenX(), y - p.getScreenY());
			
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
