package game.screens;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class HelpScreen implements Screen {
	
	public void displayOutput(JFrame frame, Graphics2D g2d) {
	
		g2d.setFont(new Font("Courier", Font.PLAIN, 16));
		g2d.drawString("[,] to pick up items", 50 ,200 );
		g2d.drawString("[d] to drop", 50, 220);
		g2d.drawString("[e] to eat", 50, 240);
		g2d.drawString("[w] to wear or wield items", 50, 260);
		g2d.drawString("[l] to look around", 50, 280);
		g2d.drawString("[x] to examine your items", 50, 300);
		
	}

	
	public Screen update(KeyEvent key) {
		return null;
	}

}
