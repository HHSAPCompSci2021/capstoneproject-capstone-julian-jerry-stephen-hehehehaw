package Weapons;

import java.util.ArrayList;

import Players.Player;

/** 
 * This class represents a Sniper. It can perform various actions such as shooting and reloading
 * @author Julian
 * @version 5/20
 */
public class Sniper extends Weapon{
	private double coolDownTimer;
	
	public Sniper() {
		super(5, 70, 20, 1, 95, 1000);
		coolDownTimer = 50;
		// TODO Auto-generated constructor stub
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
			
			justShot = true;
			return bullets;
		}
		
	
		return new ArrayList<Bullet>();

		
	}

	@Override
	public void reload() {
		reloadCounter++;
		
		if(reloadCounter >= reloadTime)
		{
			ammo = magazineSize;
			reloadCounter = 0;
			justShot = false;
		}
		
	}
	
	public double getAmmo()
	{
		return ammo;
	}
	
	public void incrementCoolDown()
	{
		coolDown++;
		
		
		if(coolDown == coolDownTimer)
		{
			justShot = false;
			coolDown = 0;
		}
		
	}
	
	public boolean getJustShot()
	{
		return justShot;
	}

}
