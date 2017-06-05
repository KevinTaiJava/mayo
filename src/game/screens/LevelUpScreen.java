package game.screens;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import game.*;

public class LevelUpScreen implements Screen {

	 private LevelUpController controller;
	 private Mob player;
	 private int picks;

	  public LevelUpScreen(Mob player, int picks){
	    this.controller = new LevelUpController();
	    this.player = player;
	    this.picks = picks;
	  }
	
	@Override
	public void displayOutput(JFrame frame, Graphics2D g2d) {
		ArrayList<String> options = controller.getLevelUpOptions();
		
		g2d.setFont(new Font("Courier", Font.ITALIC, 18));
		g2d.drawString("Advanced to level " + player.level() + ". Pick a bonus.", 50, 170);
		for (int i = 0; i < options.size(); i++) {
			g2d.setFont(new Font("Courier", Font.PLAIN, 16));
			g2d.drawString("[" + (i+1) + "]" + options.get(i), 50, 200+20*i);
		}
		
	}

	@Override
	public Screen update(KeyEvent key) {
		 ArrayList<String> options = controller.getLevelUpOptions();
		 String chars = "";

		 for (int i = 0; i < options.size(); i++){
			 chars = chars + Integer.toString(i+1);
		 }

		 int i = chars.indexOf(key.getKeyChar());

		 if (i < 0)
			 return this;

		 controller.getLevelUpOption(options.get(i)).invoke(player);

		 if (--picks < 1)
			 return null;
		 else
			 return this; 
		    	
	}
		
	
}
