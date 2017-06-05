package game;

public class ItemFactory {
	
	private World world;
	
	public ItemFactory(World world) {
		this.world = world; 
	}
	
	public Item newRock(int depth){
        Item rock = new Item("pics/items/rock.png","rock");
        world.addAtEmptyLocation(rock, depth);
        return rock;
    }
	
	//food
	
	public Item newBread(int depth) {
		Item bread = new Item("pics/items/Bread.png", "bread");
		bread.modifyFoodValue(50);
		world.addAtEmptyLocation(bread, depth);
		return bread;
	}
	
	public Item newCarrot(int depth) {
		Item carrot = new Item("pics/items/Carrot.png", "carrot");
		carrot.modifyFoodValue(50);
		world.addAtEmptyLocation(carrot, depth);
		return carrot; 
	}
	
	//weapons and armor
	
	public Item newSword(int depth) {
		Item sword = new Item("pics/items/Sword.png", "sword");
		sword.modifyAttackValue(8);
		world.addAtEmptyLocation(sword, depth);
		return sword; 
	}
	
	public Item newDagger(int depth) {
		Item dagger = new Item("pics/items/dagger.png", "dagger");
		dagger.modifyAttackValue(5);
		world.addAtEmptyLocation(dagger, depth);
		return dagger;
	}
	
	public Item newAxe(int depth) {
		Item axe = new Item("pics/items/beardedaxe-.png", "bearded axe");
		axe.modifyAttackValue(11);
		world.addAtEmptyLocation(axe, depth);
		return axe;
	}
	
	public Item newLeatherArmor(int depth) {
		Item leatherArmor = new Item("pics/items/leatherarmor.png", "leather armor");
		leatherArmor.modifyDefenseValue(2);
		world.addAtEmptyLocation(leatherArmor, depth);
		return leatherArmor;
	}
	
	public Item newSamuraiArmor(int depth) {
		Item samuraiArmor = new Item("pics/items/samuraiarmor.png", "samurai armor");
		samuraiArmor.modifyDefenseValue(4);
		samuraiArmor.modifyAttackValue(2);
		world.addAtEmptyLocation(samuraiArmor, depth);
		return samuraiArmor; 
	}
	
	public Item newSteelArmor(int depth) { 
		Item steelArmor = new Item("pics/items/steelarmor.png", "steel armor");
		steelArmor.modifyDefenseValue(6);
		world.addAtEmptyLocation(steelArmor, depth);
		return steelArmor; 
	}
	
	//bow
	
	public Item newBow(int depth) {
		Item bow = new Item("pics/items/Bow.png", "bow");
		bow.modifyAttackValue(3);
		bow.modifyDefenseValue(2);
		world.addAtEmptyLocation(bow, depth);
		return bow;
	}
	
	
}
