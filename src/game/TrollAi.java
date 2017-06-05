package game;

public class TrollAi extends MobAi {

	public TrollAi(Mob mob, World world) {
		super(mob, world, true);
	}
	
	public void update(){
        hunt();
    }

	public void leaveCorpse() {
		Item corpse;
		
		corpse = new Item("pics/sprites/corpses/trollcorpse.png" , mob.name() + " corpse");
				
		corpse.modifyFoodValue(mob.maxHp() * 4);
		world.addAtEmptySpace(corpse, mob.x, mob.y, mob.z);
	}

}
