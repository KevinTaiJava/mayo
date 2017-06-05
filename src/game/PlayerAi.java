package game;

import java.util.ArrayList;

public class PlayerAi extends MobAi {

	private ArrayList<String> messages;
	
	public PlayerAi(Mob mob, ArrayList<String> messages, World world) {
		super(mob, world, false);
		this.messages = messages;
	}
	
	@Override
	public void onEnter(int x, int y, int z, Tile tile){
	    if (tile.isGround()){
	        mob.x = x;
	        mob.y = y;
	        mob.z = z; 
	        mob.modifyFood(-1);
	    } else if (tile.isDiggable()) {
	        mob.dig(x/32, y/32, z);
	        mob.modifyFood(-8);
	    }
	}
	
	@Override
	public void onNotify(String message) {
		messages.add(message);
	}
	
	@Override
	public void onGainLevel(){
		
	}
	
}
