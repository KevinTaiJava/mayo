package game;

public class FungusAi extends MobAi {

	private MobFactory mobFactory; 
	private int spreadCount; 
	private World world;
	
	public FungusAi(Mob mob, MobFactory mobFactory, World world) {
		super(mob, world, false);
		this.mobFactory = mobFactory;
		this.world = world;
	}
	
	public void update() {
		if (spreadCount < 3 && Math.random() < .001)
			spread();
	}
	
	private void spread(){
        int x = mob.x + (int)((Math.random() * 11) - 5)*32;
        int y = mob.y + (int)((Math.random() * 11) - 5)*32;
  
        if (!mob.canEnter(x, y, mob.z))
            return;
  
        Mob child = mobFactory.newRedFungus(mob.z);
        child.x = x;
        child.y = y;
        child.z = mob.z;
        spreadCount++;
        mob.doAction("spawn a child");
    }
	
	public void die() {
		if (mob.name() == "Nuclear Shroom") {
		for (int i = mob.x-6*32; i <= mob.x+6*32; i=i+32) { // all mobs within radius get hurt, except other fellow nuclear shrooms
			for (int j = mob.y-6*32; j <= mob.y+6*32; j =j+32) { 
				if (world.mobs(i,j,mob.z) != null && !(i == mob.x && j == mob.y) && world.mobs(i, j,mob.z).name() != "Nuclear Shroom") {
					System.out.println("hello");
					world.mobs(i,j, mob.z).modifyHp(-10);
				} else {
					world.dig(i/32, j/32, mob.z);
				}
			}
		}
		mob.doAction("explode , dealing 10 damage to all mobs within the radius");
		}
	}
	
	public void leaveCorpse() {
		Item corpse;
		if (mob.name() == "Nuclear Shroom") {
			corpse = new Item("pics/sprites/corpses/shroomgreencorpse.png" , mob.name() + " corpse");
		} else {
			corpse = new Item("pics/sprites/corpses/shroomredcorpse.png", mob.name() + " corpse");
		}
		
		corpse.modifyFoodValue(mob.maxHp() * 4);
		world.addAtEmptySpace(corpse, mob.x, mob.y, mob.z);
	}
}
	
