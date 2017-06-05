package game.screens;

import game.Item;
import game.Mob;
import game.Tile;

public class LookScreen extends TargetScreen {

    public LookScreen(Mob player, String caption, int sx, int sy) {
        super(player, caption, sx, sy);
    }

    public void enterWorldCoordinate(int x, int y, int screenX, int screenY) {
        Mob mob = player.mobs(x, y, player.z);
        if (mob != null){
            caption =  mob.name() + mob.details();
            return;
        }
    
        Item item = player.item(x, y, player.z);
        if (item != null){
            caption =item.name() + item.details();
            return;
        }
    
        Tile tile = player.tile(x/32, y/32, player.z);
        caption = tile.name() + " " + tile.details();
    }
}
