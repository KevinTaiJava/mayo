package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class SpriteAnimator extends Thread {

	private World world;
	private AppMain app; 
	private Mob player; 
	
	public SpriteAnimator(World world, AppMain app, Mob player){
		this.world = world;
		this.app = app;
		this.player = player;
	}

	public void run(){
		while(true) {
			for (Mob mob : world.getMobList()) {
				if (player.canSee(mob.x, mob.y, player.z)){
					int index = mob.spriteList.indexOf(mob.sprite());
					if (index == mob.spriteList.size() - 1 || index == -1) {
						index = 0;
					} else {
						index++;
					}
					
					mob.setSprite(mob.spriteList.get(index));
					
				}
			}
			
			app.repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
