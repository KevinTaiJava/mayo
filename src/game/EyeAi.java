package game;

public class EyeAi extends MobAi {

	public EyeAi(Mob mob, World world) {
		super(mob, world, false);
	}
	
	public void update(){
		if (isAggressive) {
			hunt();
		} else {
			wander();
		}
    }

	public void leaveCorpse() {
		Item corpse;
		
		corpse = new Item("pics/sprites/corpses/bloodshotcorpse.png" , mob.name() + " corpse");
				
		corpse.modifyFoodValue(mob.maxHp() * 4);
		world.addAtEmptySpace(corpse, mob.x, mob.y, mob.z);
	}
	
}
