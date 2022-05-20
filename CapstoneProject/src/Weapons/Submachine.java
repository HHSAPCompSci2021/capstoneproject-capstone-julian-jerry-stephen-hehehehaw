package Weapons;

import java.util.ArrayList;

import Players.Player;

/** 
 * This class represents a Submachine gun. It can perform various actions such as shooting and reloading
 * @author Julian
 * @version 5/20
 */
public class Submachine extends Weapon{
		
	public Submachine() {
		super(35, 55, 10, 1.3, 6, 1000);
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
		if (ammo >= 1)
		{
			ammo--;
			
			Bullet bullet = new Bullet(p.getWorldX() + p.getWidth(), p.getWorldY() + p.getHeight(), 0, 0, damage, 1, 8, 8, 25);
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
