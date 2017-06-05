package game;

public class SlimeAi extends MobAi{

	public SlimeAi(Mob mob, World world) {
		super(mob, world, true);
	}
	
	public void update(){
       	hunt();
    }

	public void leaveCorpse() {
		Item corpse;
		
		corpse = new Item("pics/sprites/corpses/slimewarriorcorpse.png" , mob.name() + " corpse");
				
		corpse.modifyFoodValue(mob.maxHp() * 4);
		world.addAtEmptySpace(corpse, mob.x, mob.y, mob.z);
	}
	
	
}
