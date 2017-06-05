package game;

public class SkeleAi extends MobAi {

	public SkeleAi(Mob mob, World world) {
		super(mob, world, true);
	}
	
	public void update(){
        hunt();
    }
	
}
