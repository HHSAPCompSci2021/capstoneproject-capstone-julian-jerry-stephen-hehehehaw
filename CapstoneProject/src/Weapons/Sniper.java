package Weapons;

import java.util.ArrayList;

import Players.Player;

public class Sniper extends Weapon{

	public Sniper() {
		super(5, 4, 20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Bullet> shoot(int x, int y, Player p) {
			
		Bullet bullet = new Bullet(p.getX(), p.getY(), 0, 0, 50, 1);
		bullet.setVelocity(x - p.getX(), y - p.getY());
		
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		
		bullets.add(bullet);
		
		return bullets;
		
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}

}
