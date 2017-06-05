package game.screens;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.AppMain;



public class StartScreen implements Screen{

	AppMain app;
	
	@Override
	public void displayOutput(JFrame frame, Graphics2D g2d) {
		
		JLabel label = new JLabel ("Cave Escape \n A 没有 Mayo Game", JLabel.CENTER);
		label.setAlignmentX(0);
		label.setAlignmentY(0);
		label.setFont(new Font("Courier New", Font.ITALIC, 30));
		label.setForeground(Color.BLACK);
		frame.add(label, BorderLayout.CENTER);
	
		
	}

	@Override
	public Screen update(KeyEvent key) {
//		switch (key.getKeyCode()) {
//		case KeyEvent.VK_ENTER: return new PlayScreen();
//		} 
//		return this;
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen(app) : this;
	}
	
	public StartScreen(AppMain app) {
		this.app = app;
	}

}
