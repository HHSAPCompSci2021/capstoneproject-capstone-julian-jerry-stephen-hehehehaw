package FireBaseStuff;

import java.awt.Rectangle;
/**
 * This class tracks the bullet data of the program
 * @author Jerry
 */
public class BulletData {
	/**
	 * Default Speed of the bullet
	 */
	public double defaultSpeed;
	/**
	 * Bullet Velocity x, Bullet velocity y, Bulet damage, Bullets location x on the world, Bullets location y on the world, bullet health
	 */
	public double vx, vy, damage, worldX, worldY, health;
	public int width, height;
	
	//dimensions = new Rectangle((int)worldX, (int)worldY, 5, 5);
	/**
	 * constructs a BulletData object
	 */
	public BulletData(){
		
	}

}
