package Weapons;

import java.util.ArrayList;

import Players.Player;

public class Knife extends Weapon{

	public Knife() {
		super(0, 0, 10);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Bullet> shoot(int x, int y, Player p) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public double getAmmo() {
		// TODO Auto-generated method stub
		return 0;
	}

}
