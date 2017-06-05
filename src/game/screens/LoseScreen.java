package game.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game.AppMain;

public class LoseScreen implements Screen {

	AppMain app;
	
	@Override
	public void displayOutput(JFrame frame, Graphics2D g2d) {
		
		JLabel label = new JLabel ("You lose \t \t Press enter to play again", JLabel.CENTER);
		label.setAlignmentX(0);
		label.setAlignmentY(0);
		label.setFont(new Font("Courier New", Font.ITALIC, 40));
		label.setForeground(Color.WHITE);
		frame.add(label, BorderLayout.CENTER);
		
		
		

	}

	@Override
	public Screen update(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_ENTER) {
			return new PlayScreen(app);
		}
		
		
		return this;
	}

	public LoseScreen(AppMain app) {
		System.out.println("hi");
		this.app = app;
		
	}
	
}
