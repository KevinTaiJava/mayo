
//
//import java.awt.BorderLayout;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.TextField;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.*;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.*;
//
//import game.screens.*;
//
//public class AppMain extends JFrame implements KeyListener{
//	private static final long serialVersionUID = 1060623638149583738L;
//	
//	private JFrame frame; 
//	private Screen screen;
//	private JPanel panel;
//	
//	public AppMain() {
//		super();
//		frame = new JFrame("Cave Craft");
//		screen = new StartScreen();
//		
////		JLabel label = new JLabel ("Hello World!", JLabel.CENTER);
////		label.setAlignmentX(0);
////		label.setAlignmentY(0);
////		frame.add(label, BorderLayout.SOUTH);
//		
//		frame.setSize(500, 500);
//////		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 	
//		addKeyListener(this);
//		
//		repaint();
//	}
//	
//	public void paint(Graphics g) {
////		frame.getContentPane().removeAll();
//		screen.displayOutput(frame);
//
//	
//	}
//	
//	//KeyListener Methods
//	public void keyTyped(KeyEvent e) {
//
//	}
//
//	
//	public void keyPressed(KeyEvent e) {
//		System.out.println(e);
//		screen = screen.update(e);
//		repaint();
//	}
//
//	
//	public void keyReleased(KeyEvent e) {
//		
//		
//	}
//	
//	
//	public static void main(String[] args) {
//		AppMain app = new AppMain();
//		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		app.setVisible(true);
//	}
//
//}

package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.screens.Screen;
import game.screens.StartScreen;

import java.awt.Event.*;

public class AppMain extends JPanel implements KeyListener{

	private JFrame frame;
	private Screen screen; 
	private static final long serialVersionUID = 123; 
	
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		frame.getContentPane().removeAll();
		frame.add(this);
		
		this.screen.displayOutput(frame, g2d);
		this.requestFocusInWindow();
		frame.setVisible(true);
	}
	
	public void keyPressed(KeyEvent e) {
		System.out.println(e);
		this.screen = screen.update(e);
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }
	
	public AppMain(JFrame frame) {
		addKeyListener(this);
		setFocusable(true);
		
		this.screen = new StartScreen(this);
		this.frame = frame; 
	}
	
	public static void main (String[] args) throws InterruptedException {
		JFrame frame = new JFrame ("没有 Mayo");
		AppMain app = new AppMain(frame);
		frame.add(app);
		frame.setSize(1280,896);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	
	
}
