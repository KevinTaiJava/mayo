package game.screens;

import game.Item;
import game.Mob;

public class EatScreen extends InventoryScreens {

	public EatScreen(Mob player) {
		super(player);
	}

	@Override
	protected String getVerb() {
		return "eat";
	}
	
	@Override
	protected boolean isAcceptable(Item item) {
		return (item.foodValue() != 0);
	}

	@Override
	protected Screen use(Item item) {
		player.eat(item);
		return null;
	}

	
	
}
