package game;

public class NecroAi extends MobAi{

	MobFactory mobFactory;
	
	public NecroAi(Mob mob, World world, MobFactory mobFactory) {
		super(mob, world, false);
		this.mobFactory = mobFactory;
	}
	
	public void update(){
       if(mob.canSee(world.player.x, world.player.y, world.player.z)) {
    	   if (Math.random() <= 2.0/5.0) {
    		   spread();
    	   }
       }
    }
	
	private void spread(){
        int x = mob.x + (int)((Math.random() * 11) - 5)*32;
        int y = mob.y + (int)((Math.random() * 11) - 5)*32;
  
        if (!mob.canEnter(x, y, mob.z))
            return;
  
        Mob skele = mobFactory.newSkeleton(mob.z);
        skele.x = x;
        skele.y = y;
        skele.z = mob.z;
        mob.doAction("spawn a skeleton");
    }
	
	
}
