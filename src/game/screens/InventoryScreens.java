package game.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game.Item;
import game.Mob;

public abstract class InventoryScreens implements Screen {

	protected Mob player;
    private String letters;

    protected abstract String getVerb();
    protected abstract boolean isAcceptable(Item item);
    protected abstract Screen use(Item item);

    public InventoryScreens(Mob player){
        this.player = player;
        this.letters = "abcdefghijklmnopqrstuvwxyz";
    }
    
    public void displayOutput(JFrame frame, Graphics2D g2d) {
    	Item[] inventory = player.inventory().getItems();
    	
    	g2d.setColor(Color.WHITE);
        
    	g2d.drawString("What would you like to " + getVerb() + "?", 50, frame.getHeight()-100);
    	
    	int j = 1;
        for (int i = inventory.length-1; i >= 0; i--){
            Item item = inventory[i];
        
            if (item == null || !isAcceptable(item)) {
            	
            } else {
            	String toPrint = letters.charAt(i) + " - " + item.name();
            	if (item == player.armor() || item == player.weapon()) {
            		toPrint += " (equipped)";
            	}
            	g2d.drawString(toPrint, 50, frame.getHeight() - 100 - 20*j);
            	g2d.drawImage(item.sprite().getScaledInstance(25, 25, BufferedImage.SCALE_FAST), g2d.getFontMetrics().stringWidth(toPrint) + 55, frame.getHeight() - 100 - 20*j - 15, null);
            	j++;
            }
	
        }    
    }
    
    private ArrayList<Item> getList() {
        ArrayList<Item> result = new ArrayList<Item>();
        Item[] inventory = player.inventory().getItems();

        
        for (int i = 0; i < inventory.length; i++){
            Item item = inventory[i];
        
            if (item == null || !isAcceptable(item))
                continue;
           
            result.add(item);
        }
        return result;
    }
    
    public Screen update(KeyEvent key) {
        char c = key.getKeyChar();

        Item[] items = player.inventory().getItems();
    
        if (letters.indexOf(c) > -1
             && items.length > letters.indexOf(c)
             && items[letters.indexOf(c)] != null
             && isAcceptable(items[letters.indexOf(c)]))
            return use(items[letters.indexOf(c)]);
        else if (key.getKeyCode() == KeyEvent.VK_ESCAPE)
            return null;
        else
            return this;
    }
    
}


