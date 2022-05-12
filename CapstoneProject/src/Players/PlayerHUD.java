package Players;

import processing.core.PApplet;

public class PlayerHUD {
	
	public void draw(PApplet p, int width, int height, Player player, Player avatar)
	{
		
		
		p.push();
		
//		avatar.setDirection(player);

		
		p.fill(255, 255, 255);
		
		p.rect(width-width/6,height-height/3, 200, 200);
		avatar.draw(p);
		if(avatar.avatar.spriteNum == 1)
		{
			avatar.avatar.spriteNum++;
		}
		else {
			avatar.avatar.spriteNum--;
		}
			
		
//		if(avatar.)
		
//		p.fill(0, 255, 0);
//		p.rect(p.width-100, p.height-100, 50, 50);
//
//		
//		p.fill(0, 0, 255);
//		p.rect(p.width-100, p.height-100, 50, 50);
//		
		p.pop();
	}
	
	
}
