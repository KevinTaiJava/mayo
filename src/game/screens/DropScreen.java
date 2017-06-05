package game.screens;

import java.awt.event.KeyEvent;

import game.Item;
import game.Mob;

public class DropScreen extends InventoryScreens {

    public DropScreen(Mob player) {
        super(player);
    }

	@Override
	protected String getVerb() {
		return "drop";
	}

	@Override
	protected boolean isAcceptable(Item item) {
		return true;
	}

	@Override
	protected Screen use(Item item) {
		player.drop(item);
		return null;
	}
}
