package game;

import java.util.ArrayList;

public class MobFactory {

	private World world; 
	
	public MobFactory(World world) {
		this.world = world; 
	}
	
	public Mob newPlayer(ArrayList<String> messages) {
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/minerightframe1-3.png");
		fileLocations.add("pics/sprites/minerrightframe2.png");
		fileLocations.add("pics/sprites/minerightframe1-3.png");
		fileLocations.add("pics/sprites/minerightframe4.png");
		
		ArrayList<String> leftFileLocations = new ArrayList<String>();
		leftFileLocations.add("pics/sprites/minerleftframe1-3.png");
		leftFileLocations.add("pics/sprites/minerleftframe2.png");
		leftFileLocations.add("pics/sprites/minerleftframe1-3.png");
		leftFileLocations.add("pics/sprites/minerleftframe4.png");
		
		Mob player = new Mob(world, fileLocations, leftFileLocations, 100, 15, 15, 5,  "Hero");
		world.addAtEmptyLocation(player, 0);
		new PlayerAi(player, messages, world);
		return player;
	}
	
	public Mob newRedFungus(int z){
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/shroomred.png");
		fileLocations.add("pics/sprites/shroom2.png");
		
	    Mob fungus = new Mob(world, fileLocations, null, 10, 0 ,0, 1, "Red Shroom");
	    world.addAtEmptyLocation(fungus, z);
	    new FungusAi(fungus,this,world);
	    return fungus;
	}
	
	public Mob newGreenFungus(int z){
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/shroomgreen.png");
		fileLocations.add("pics/sprites/shroomgreenframe2.png");
		
	    Mob fungus = new Mob(world, fileLocations, null, 20, 0, 5, 5, "Nuclear Shroom");
	    world.addAtEmptyLocation(fungus, z);
	    new FungusAi(fungus,this,world);
	    return fungus;
	}
	
	public Mob newTroll (int z) {
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/trollframe1.png");
		fileLocations.add("pics/sprites/trollframe2.png");
		
		Mob troll = new Mob(world, fileLocations, null, 50, 17, 10, 10, "Troll");
		world.addAtEmptyLocation(troll, z);
		new TrollAi(troll, world);
		return troll; 
	}
	
	public Mob newBat (int z) {
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/Bat.png");
		fileLocations.add("pics/sprites/Bat2.png");
		
		Mob bat = new Mob(world, fileLocations, null, 15, 5, 0, 10, "Bat");
		world.addAtEmptyLocation(bat, z);
		new BatAi(bat, world);
		return bat; 	
	}
	
	public Mob newEye(int z) {
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/bloodshotframe1.png");
		fileLocations.add("pics/sprites/bloodshotframe2.png");
		
		ArrayList<String> leftFileLocations = new ArrayList<String>();
		leftFileLocations.add("pics/sprites/bloodshotleftframe1.png");
		leftFileLocations.add("pics/sprites/bloodshotleftframe2.png");
		
		Mob eye = new Mob(world, fileLocations, leftFileLocations, 45, 22, 15, 5,  "Bloodshot");
		world.addAtEmptyLocation(eye, z);
		new EyeAi(eye, world);
		return eye; 	
	}
	
	public Mob newBigShroom(int z) {
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/depressionshroomframe1.png");
		fileLocations.add("pics/sprites/depressionshroomframe1.png");
		fileLocations.add("pics/sprites/depressionshroomframe1.png");
		fileLocations.add("pics/sprites/depressionshroomframe2.png");
		fileLocations.add("pics/sprites/depressionshroomframe2.png");
		fileLocations.add("pics/sprites/depressionshroomframe2.png");
		
		Mob shroom = new Mob(world, fileLocations, null, 35, 15, 15, 5,  "Depressed Shroom");
		world.addAtEmptyLocation(shroom, z);
		new BigFungusAi(shroom, world);
		return shroom; 	
	}
	
	public Mob newSlimeWarrior(int z) {
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/slimewarriorframe1.png");
		fileLocations.add("pics/sprites/slimewarriorframe2.png");
		
		Mob slime = new Mob(world, fileLocations, null, 100, 28, 18, 6,  "Slime Warrior");
		world.addAtEmptyLocation(slime, z);
		new SlimeAi(slime, world);
		return slime; 	
	}
	
	public Mob newSkeleton(int z) {
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/skelesoldierframe1.png");
		fileLocations.add("pics/sprites/skelesoldierframe2.png");
		
		Mob skele = new Mob(world, fileLocations, null, 30, 22, 9, 5,  "Skeleton Soldier");
		world.addAtEmptyLocation(skele, z);
		new SkeleAi(skele, world);
		return skele; 	
	}
	
	public Mob newNecronomicon(int z) {
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/necronomiconframe1-4.png");
		fileLocations.add("pics/sprites/necronomiconframe2.png");
		fileLocations.add("pics/sprites/necronomiconframe3.png");
		fileLocations.add("pics/sprites/necronomiconframe1-4.png");
		fileLocations.add("pics/sprites/necronomiconframe5.png");
		
		Mob necro = new Mob(world, fileLocations, null, 75, 0, 22, 5,  "Necronomicon");
		world.addAtEmptyLocation(necro, z);
		new NecroAi(necro, world, this);
		return necro; 	
	}
	
	public Mob newSnake(int z) {
		ArrayList<String> fileLocations = new ArrayList<String>();
		fileLocations.add("pics/sprites/snakeframe1.png");
		fileLocations.add("pics/sprites/snakeframe2.png");
		
		Mob snake = new Mob(world, fileLocations, null, 35, 15, 5, 5,  "Snake");
		world.addAtEmptyLocation(snake, z);
		new SnakeAi(snake, world);
		return snake; 	
	}
	
}
