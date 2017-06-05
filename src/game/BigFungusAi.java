package game;

public class BigFungusAi extends MobAi{
	
	public BigFungusAi(Mob mob, World world) {
		super(mob, world, false);
	}
	
	public void leaveCorpse() {
		Item corpse;
		
		corpse = new Item("pics/sprites/corpses/depressionshroomcorpse.png" , mob.name() + " corpse");
				
		corpse.modifyFoodValue(mob.maxHp() * 4);
		world.addAtEmptySpace(corpse, mob.x, mob.y, mob.z);
	}
	
}
