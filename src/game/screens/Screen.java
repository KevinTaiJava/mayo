package game.screens;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public interface Screen {
	
	public void displayOutput(JFrame frame, Graphics2D g2d);
	
	public Screen update(KeyEvent key);

}
