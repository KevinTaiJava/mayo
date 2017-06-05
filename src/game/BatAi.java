package game;

public class BatAi extends MobAi{

	public BatAi(Mob mob, World world) {
		super(mob, world, false);
	}
	
	public void update(){
		if (isAggressive) {
			hunt();
		} else {
			wander();
			wander();
		}
    }
	
	public void leaveCorpse() {
		Item corpse;
		
		corpse = new Item("pics/sprites/corpses/Bat Corpse.png" , mob.name() + " corpse");
				
		corpse.modifyFoodValue(mob.maxHp() * 4);
		world.addAtEmptySpace(corpse, mob.x, mob.y, mob.z);
	}
	
}
