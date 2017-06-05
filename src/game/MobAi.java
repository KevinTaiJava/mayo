package game;

import java.awt.Point;
import java.util.Random;

public class MobAi {

	protected Mob mob; 
	public World world;
	protected boolean isAggressive;
	
	public MobAi(Mob mob, World world, boolean isAggressive) {
		this.mob = mob;
		this.mob.setMobAi(this);
		this.world = world;
		this.isAggressive = isAggressive;
	}
	
	public void onEnter(int x, int y, int z, Tile tile){
	    if (tile.isGround()){
	        mob.x = x;
	        mob.y = y;
	        mob.z = z;
	    } else {
	        mob.doAction("bump into a wall");
	    }
	}
	//aimlessly walk around
	public void wander() {
		int dx = 32 * ((int) (Math.random()*3) - 1);
		int dy = 32 * ((int) (Math.random()*3) - 1);
		
		//this is to prevent the monsters from killing ones from the same species 
		Mob other = mob.mobs(mob.x+dx, mob.y+dy, mob.z);
		
		if (other != null && other.sprite() == mob.sprite()) {
			return;
		} else {	
			mob.moveBy(dx, dy, 0);
		}
	}
	
	//hunt and chase player if spotted
	
	public void hunt() {
		int dx = 0; 
		int dy = 0;
		Random random = new Random();
		if (canSee(world.player.x, world.player.y, world.player.z)) {
			if (random.nextInt(2) == 1) {
				if (mob.x < world.player.x) {
					dx = 32;
				} else if (mob.x > world.player.x) {
					dx = -32;
				} 
			} else {
				if (mob.y < world.player.y) {
					dy = 32;
				} else if (mob.y > world.player.y) {
					dy = -32;
				} 
			}
			mob.moveBy(dx, dy, 0);
			world.player.notify("The " + mob.name() + " chases after you.");
		} else {
			wander();
		}
	}

	public void update() {
		
	}

	public void onNotify(String message) {
		
	}
	
	public void die() {
		
	}
	
	public boolean canSee (int wx, int wy, int wz) {
		if (mob.z != wz) {
			return false;
		} 
		
		if ((mob.x-wx)/32*(mob.x-wx)/32 + (mob.y-wy)/32*(mob.y-wy)/32 > mob.visionRadius()*mob.visionRadius())
            return false;
    
        for (Point p : new Line(mob.x/32, mob.y/32, wx/32, wy/32)){
            if (mob.tile(p.x, p.y, wz).isGround() || p.x == wx/32 && p.y == wy/32)
                continue;
        
            return false;
        }
    
        return true;
	}
	
	public void leaveCorpse() {
		
	}

	public void onGainLevel() {
	    new LevelUpController().autoLevelUp(mob);
	  }

	public void setAggressive() {
		if (isAggressive == false) {
			isAggressive = true; 
			
		}
	}
	
	
}
