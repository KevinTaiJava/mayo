package game.screens;

import game.Item;
import game.Mob;

public class EquipScreen extends InventoryScreens {

	public EquipScreen(Mob player) {
		super(player);
	}

	@Override
	protected String getVerb() {
		return "wear or brandish";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return (item.attackValue() > 0 || item.defenseValue() > 0);
	}

	@Override
	protected Screen use(Item item) {
		player.equip(item);
		return null;
	}

}
