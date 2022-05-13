package Players;

import processing.core.PApplet;

public class PlayerHUD {
	

	public void draw(PApplet p, int width, int height, Player player, Player avatar)
	{
		
		p.push();		
		p.fill(255, 255, 255);
		
		p.rect((float) (width-width/5.5),(float) (height-height/2.5), width/5, 300);
		avatar.draw(p);
//		if(avatar.avatar.spriteNum == 1)
//		{
//			avatar.avatar.spriteNum++;
//		}
//		else {
//			avatar.avatar.spriteNum--;
//		}
			
				
		
		p.fill(0, 255, 0);
		
		double ratio1 = (width/5.5) / player.getInitHealth();

		p.rect((float) (p.width-p.width/5.5), p.height-p.height/5, (float) (width/5.5 - ratio1 * (player.getInitHealth() - player.getHealth())), height/10);

		if(player.getWeapon().getAmmo() >= 1)
		{
			double ratio = (width/5.5) / player.getWeapon().getMagSize();
			
			p.fill(0, 0, 255);
			p.rect((float) (p.width-p.width/5.5), (float) (p.height-p.height/10.5), (float) (width/5.5 - ratio * (player.getWeapon().getMagSize()-player.getWeapon().getAmmo())), height/10);
		}
		else {
			double ratio = (width/5.5) / player.getWeapon().getReloadTime();
			
			p.fill(255, 0, 0);
			p.rect((float) (p.width-p.width/5.5), (float) (p.height-p.height/10.5), (float) (ratio * (player.getWeapon().getReloadTime()-player.getWeapon().getReloadCounter())), height/10);
		}
		
		
		p.pop();
	}
	
	
}
