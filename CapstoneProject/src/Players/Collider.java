package Players;

import java.awt.Rectangle;
import java.util.ArrayList;

import Instructions.World;
import Tiles.Tile;
import Tiles.TileManager;

public class Collider {
	int tileSize;
	TileManager tM;
	
//	World world;
	public Collider(int tSize, TileManager tM){//World worldd) {
		tileSize = tSize;
		this.tM = tM;
		
	}
	
	public void checkTile(Player player) {
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
		
		ArrayList<Tile> tL = tM.getTilesList();
		int[][]map = tM.getMap();
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
	
			if (tL.get(t1).solidState() || tL.get(t2).solidState() 
					|| tL.get(t3).solidState() || tL.get(t4).solidState() 
					|| tL.get(t5).solidState() || tL.get(t6).solidState() 
					|| tL.get(t7).solidState() || tL.get(t8).solidState()) {
				player.setCollisions(true);
			}
	}
	
	public boolean checkTiles(Bullet b) {
		//if 
		return false;
	}
}
