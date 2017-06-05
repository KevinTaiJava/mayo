package game.screens;

import game.Item;
import game.Mob;

public class ExamineScreen extends InventoryScreens {

    public ExamineScreen(Mob player) {
        super(player);
    }

    protected String getVerb() {
        return "examine";
    }

    protected boolean isAcceptable(Item item) {
        return true;
    }

    protected Screen use(Item item) {
        String article;
        if (item.name().substring(0,1).equals("a")) {
        	article = "an ";
        } else {
        	article = "a ";
        }
        player.notify("It's " + article + item.name() + "." + item.details());
        return null;
    }
}
