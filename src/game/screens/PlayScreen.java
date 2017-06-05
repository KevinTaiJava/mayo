package game.screens;

import java.awt.AlphaComposite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.*;

public class PlayScreen implements Screen {
	private AppMain app; 
	private World world;
	private int screenWidth;
	private int screenHeight;
	public final int SCALE = 32;
	private boolean updateMessage = false; 
	
	public MobFactory mobFactory; 
	public ItemFactory itemFactory;
	private Mob player; 
	private Screen subscreen;
	
	private ArrayList<String> messages;
	private ArrayList<String> messageLog;
	
	private Thread SpriteAnimator;
	
	public PlayScreen(AppMain app){
		screenWidth = 1280;	//height and width should be a multiple of your scale, otherwise a small issue will occur when scrolling
		screenHeight = 704; //should be JFrame.height - 2*SCALE
		messages = new ArrayList<String>();
		messageLog = new ArrayList<String>();
		createWorld();
		mobFactory = new MobFactory(world);
		itemFactory = new ItemFactory(world);
		
		makeItems(itemFactory);
		makeMobs(mobFactory);
		this.app = app;
		SpriteAnimator = new SpriteAnimator(world, app, player);
		SpriteAnimator.start();
		
	}
	
	private void createWorld(){
		world = new WorldBuilder(2560/SCALE, 1408/SCALE, 7) //build is 2x JFrame.width and Jframe.height
					.makeCaves()
					.connectFloors(6)
					.addExit()
					.build();
	}
	
	private void makeMobs(MobFactory mobFactory) {
		player = mobFactory.newPlayer(messages);
		world.setPlayer(player);
		
		for (int d = 0; d <= 6; d++) {
			
			//small fungus
			for (int i = 1; i <= 25; i++) {
				if (d != 4) {
					mobFactory.newRedFungus(d);
				}
			}
			for (int i = 1; i <= 6; i++) {
				if (d != 4) {
					mobFactory.newGreenFungus(0);
				}
			}
			
			//troll
			if (d >= 1) {
				if (d == 2 || d == 3) {
					for (int i = 1; i <= 15; i++) {
						mobFactory.newTroll(d);
					}
				} else if (d == 1) {
					for (int i = 1; i <= 20; i++) {
						mobFactory.newTroll(d);
					}
				}
			}
			
			
			//bat
			if (d <= 3) {
				if (d == 0) {
					for (int i = 1; i <= 28; i++) {
						mobFactory.newBat(d);
					}
				} else {
					for (int i = 1; i <= 18; i++) {
						mobFactory.newBat(0);
					}
				}
			} 
			
			//bloodshot
			for (int i = 1; i <= 30; i++) {
				if (d >= 3 && d <= 6) {
					mobFactory.newEye(d);
				}
			}
			
			//big shroom
			for (int i = 1; i <= 15; i++) {
				if (d >= 2 && d <= 4) {
					mobFactory.newBigShroom(d);
				}
			}		
			
			//necro
			for (int i = 1; i <= 8; i++) {
				if (d >= 5) {
					mobFactory.newNecronomicon(d);
				}
			}
			
			//slime warrior
			for (int i = 1; i <= 15; i++) {
				if (d >= 4) {
					mobFactory.newSlimeWarrior(d);
				}
			}
			System.out.println("Floor" + d + " loaded");
		}
		
	}
	
	private void makeItems(ItemFactory itemFactory) {
		for (int d = 0; d <= 6; d++) {	
			for (int i = 1; i <= 30; i++) {
				itemFactory.newRock(d);
			}
			for (int i = 1; i <= 8; i++) {
				if (d > 2) {
					itemFactory.newSword(d);
					itemFactory.newSamuraiArmor(d);
				}
			}
			for (int i = 1; i <= 12; i++) {
				itemFactory.newBread(d);
			}
			for (int i = 1; i <= 8; i++) {
				itemFactory.newCarrot(d);
			} 
			
			for (int i = 1; i <= 4; i++) {
				if (d > 3) {
					itemFactory.newAxe(d);
					itemFactory.newSteelArmor(d);
				}
			}
			for (int i = 1; i<= 10; i++) {
				if (d < 3) {
					itemFactory.newDagger(d);
					itemFactory.newBow(d);
					itemFactory.newLeatherArmor(d);
				}
			}
		
		}
			
	}

	public int getScrollX() { 
		return Math.max(0, Math.min(player.x - screenWidth / 2, world.width()*SCALE - screenWidth)); 
	}
	
	public int getScrollY() { 
		return Math.max(0, Math.min(player.y - screenHeight / 2, world.height()*SCALE - screenHeight)); 
	}
	
	@Override
	public void displayOutput(JFrame frame, Graphics2D g2d) {
		
		int left = getScrollX();
		int top = getScrollY(); 
//		world.update(player.z);
		displayTiles(frame, left, top, g2d);
		//test code
//		BufferedImage character = null;
//		Image tmp = null;
//		try {
//			character = ImageIO.read(new File ("pics/tiles/Bat.png"));
//			tmp = character.getScaledInstance(SCALE, SCALE, BufferedImage.SCALE_FAST);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("player.y: " + player.y + " Top: " + top );
//		g2d.drawImage(player.sprite(), player.x-left, player.y-top, null);
//		System.out.println("" + player.x + " : " + player.y);
		String stats = " HP: " + player.hp() + "/" + player.maxHp();
		stats += "\t Hunger: " + player.food() + "/" + player.maxFood();
		
		JLabel label = new JLabel (stats, JLabel.LEFT);
		label.setAlignmentX(0);
		label.setAlignmentY(0);
		label.setFont(new Font("Courier New", Font.ITALIC, 16));
		label.setForeground(Color.WHITE);
		frame.add(label, BorderLayout.SOUTH);
		frame.getContentPane().setBackground(Color.BLACK);
		displayMessages(g2d); 
		
		if (subscreen != null)
			subscreen.displayOutput(frame, g2d);
	}

	private void displayTiles(JFrame frame, int left, int top, Graphics2D g2d) {
		for (int x = 0; x < screenWidth; x=x+SCALE){
			for (int y = 0; y < screenHeight; y=y+SCALE){
				int wx = x + left;
				int wy = y + top;
				
				if (player.canSee(wx, wy, player.z)) {
					world.hasSeen[wx/32][wy/32][player.z] = true; 
					BufferedImage tile = world.glyph(wx/SCALE, wy/SCALE,player.z);
					BufferedImage tile2 = tile; 
					g2d.drawImage(tile2, x,y,null);
				} else if (world.hasSeen[wx/32][wy/32][player.z] == true) {
					Tile tile = world.tile(wx/32, wy/32, player.z);
					BufferedImage grey; 
					
					if (tile.details() == Tile.WALL.details()) {
						if (tile == Tile.VERTICALWALL) {
							grey = Tile.GREYVERTICALWALL.glyph();
						} else {
							grey = Tile.GREYWALL.glyph();
						}
					} else {
						grey = Tile.GREYFLOOR.glyph();
					}
					
					g2d.drawImage(grey, x, y, null);
					
				} else {
					BufferedImage unknown = Tile.UNKNOWN.glyph();
					g2d.drawImage(unknown,x,y,null);
				}
			}
		}
		
		for (Item item: world.getItemList()) {
			if ((item.x >= left && item.x <= left+screenWidth) && (item.y >= top && item.y <= top+screenHeight) && item.z == player.z && player.canSee(item.x, item.y, player.z)) {
				g2d.drawImage(item.sprite(), item.x-left, item.y-top, null);
			}
		}
		
		for (Mob mob : world.getMobList()) {
			if ((mob.x >= left && mob.x <= left+screenWidth) && (mob.y >= top && mob.y <= top+screenHeight) && mob.z == player.z && player.canSee(mob.x, mob.y, player.z)) {
				g2d.drawImage(mob.sprite(), mob.x-left, mob.y-top, null);
			}
		}
		
	}
	
	private void displayMessages(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		for (int i = 0; i < messages.size(); i++) {
			g2d.drawString(messages.get(i), 50, 20 + (10*i));
			messageLog.add(messages.get(i));
		}
//		if (updateMessage == true) {
//			messages.clear();
//			updateMessage = false; 
//		}
	}
	
	@Override
	public Screen update(KeyEvent key) {
		
		int level = player.level();
		messages.clear();
		
		if (subscreen != null){
		    subscreen = subscreen.update(key);
		}
		else {
			switch (key.getKeyCode()){
			case KeyEvent.VK_ESCAPE: return new StartScreen(app);
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_H: player.moveBy(-SCALE, 0, 0); break;
			case KeyEvent.VK_RIGHT: player.moveBy(SCALE, 0, 0); break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_K: player.moveBy( 0,-SCALE, 0); break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_J: player.moveBy( 0, SCALE, 0); break;
			case KeyEvent.VK_Y: player.moveBy(-SCALE,-SCALE, 0); break;
			case KeyEvent.VK_U: player.moveBy( SCALE,-SCALE, 0); break;
			case KeyEvent.VK_B: player.moveBy(-SCALE, SCALE, 0); break;
			case KeyEvent.VK_N: player.moveBy( SCALE, SCALE, 0); break;
			}
		
			switch (key.getKeyChar()){
			case 'd': subscreen = new DropScreen(player); break; 
			case 'e': subscreen = new EatScreen(player); break;
			case 'x': subscreen = new ExamineScreen(player); break;
			case 'l': subscreen = new LookScreen(player, "Looking", player.x - getScrollX(), player.y - getScrollY()); break;
			case 'g':
	        case ',': player.pickup(); break;
	        case 'w': subscreen = new EquipScreen(player); break;
			case '<': player.moveBy( 0, 0, -1); break;
			case '>': player.moveBy( 0, 0, 1); break;
			}
			
			if (subscreen == null)
				world.update(player.z);
			
		}
		
		if (player.level() > level)
		      subscreen = new LevelUpScreen(player, player.level() - level);
		

		
		if (world.gameWon()) {
			SpriteAnimator.interrupt();
			return new WinScreen(app);
		}
		
		if (player.hp() < 1) {
			SpriteAnimator.interrupt();
		    return new LoseScreen(app);
		}
		
		return this;
		
		
		
	}
	


}
