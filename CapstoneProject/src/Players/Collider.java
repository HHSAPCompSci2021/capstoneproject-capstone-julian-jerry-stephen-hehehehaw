package Players;

import java.awt.Rectangle;
import java.util.ArrayList;

import Instructions.World;
import Tiles.Tile;
import Tiles.TileManager;
import Weapons.Bullet;

public class Collider {
	int tileSize;
	TileManager tM;
	ArrayList<Tile> tL;
	int[][]map;
	
//	World world;
	public Collider(int tSize, TileManager tM){//World worldd) {
		tileSize = tSize;
		this.tM = tM;
		
		tL = tM.getTilesList();
		map = tM.getMap();
		
	}
	
	public void checkTileCleanup(Player player) {

		player.setR1(-1);
		player.setC1(-1);
		player.setR2(-1);
		player.setC2(-1);
		player.setR3(-1);
		player.setC3(-1);
		player.setR4(-1);
		player.setC4(-1);
	}
	/*
	 * returns an int designating what powerup/trap is collided with: -1 is a noninteractble tile
	*/
	public int checkTile(Player player) {
		player.setDataChanged(true);
		int index = -1;
		
		Rectangle r = player.getDimensions();
		int playerLeftWorldX = (int)(player.getWorldX() + r.x);
		int playerRightWorldX = (int)(player.getWorldX() + r.x + r.width);
		int playerTopWorldY = (int) (player.getWorldY() + r.y);
		int playerBottomWorldY = (int) (player.getWorldY() + r.y + r.height);
		
		int playerLeftCol = playerLeftWorldX/tileSize;
		int playerRightCol = playerRightWorldX/tileSize;

		int playerTopRow = playerTopWorldY/tileSize;

		int playerBottomRow = playerBottomWorldY/tileSize;

		int t1 = 0, t2 = 0, t3 = 0, t4 = 0 , t5= 0, t6= 0, t7= 0, t8 = 0;

			if (player.getN()) {
				playerTopRow =(int) ((playerTopWorldY - player.getSpeed())/tileSize);

				t1 = map[playerLeftCol][playerTopRow];
				t2 = map[playerRightCol][playerTopRow];
			}
			if (player.getS()) {
				playerBottomRow = (int)((playerBottomWorldY + player.getSpeed())/tileSize);

				t3 = map[playerLeftCol][playerBottomRow];
				t4 = map[playerRightCol][playerBottomRow];
			}
			if (player.getW()) {
				playerLeftCol = (int)((playerLeftWorldX - player.getSpeed())/tileSize);

				t5 = map[playerLeftCol][playerTopRow];
				t6 = map[playerLeftCol][playerBottomRow];
			}
			if (player.getE()) {
				playerRightCol = (int)((playerRightWorldX + player.getSpeed())/tileSize);

				t7 = map[playerRightCol][playerTopRow];
				t8 = map[playerRightCol][playerBottomRow];
			}
			ArrayList<Integer> t = new ArrayList<Integer>();
			t.add(t1);
			t.add(t2);
			t.add(t3);
			t.add(t4);
			t.add(t5);
			t.add(t6);
			t.add(t7);
			t.add(t8);
			

			boolean notMoving = true;
			for (int ti: t)
				if (ti != 0)
					notMoving = false;
			
			if (notMoving) {
				t.set(0, map[playerLeftCol][playerTopRow]);
				t.set(1, map[playerRightCol][playerTopRow]);
				t.set(2, map[playerLeftCol][playerBottomRow]);
				t.set(3, map[playerRightCol][playerBottomRow]);
				t.set(4, map[playerLeftCol][playerTopRow]);
				t.set(5, map[playerLeftCol][playerTopRow]);
				t.set(6, map[playerRightCol][playerTopRow]);
				t.set(7, map[playerRightCol][playerTopRow]);
			}
			
			for (int ti: t) {
				if (tL.get(ti).solidState())
					player.setCollisions(true);
				else if (tL.get(ti).isPowerUp() || tL.get(ti).isTrap()) {
					 index =  tM.tileInteract(ti, player);	

					}	
				}
			if (tL.get(map[playerLeftCol][playerTopRow]).isPowerUp()) {
				map[playerLeftCol][playerTopRow] = 22;
				player.setR1(playerTopRow);
				player.setC1(playerLeftCol);
			}
			
			if (tL.get(map[playerRightCol][playerTopRow]).isPowerUp()) {
				map[playerRightCol][playerTopRow] = 22;

				player.setR2(playerTopRow);
				player.setC2(playerRightCol);
			}
			
			if (tL.get(map[playerLeftCol][playerBottomRow]).isPowerUp()) {
				map[playerLeftCol][playerBottomRow] = 22;

				player.setR3(playerBottomRow);
				player.setC3(playerLeftCol);
			}
			
			if (tL.get(map[playerRightCol][playerBottomRow]).isPowerUp()) {
				map[playerRightCol][playerBottomRow] = 22;

				player.setR4(playerBottomRow);
				player.setC4(playerRightCol);
			}

			
			return index;
			
	}
	
	public boolean checkTiles(Bullet b) {
		ArrayList<Tile> tL = tM.getTilesList();
		int[][]map = tM.getMap();
		
		if(tL.get(map[(int) (b.getWorldX()/tM.getTileSize())][(int) (b.getWorldY()/tM.getTileSize())]).solidState())
				return true;
		
		return false;
	}
}
