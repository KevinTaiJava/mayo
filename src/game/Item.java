package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Item {
	
	public int x;
	public int y;
	public int z;
	
	private int foodValue;
	
	public int foodValue() { 
		return foodValue; 
	}
	
	public void modifyFoodValue(int amount) { 
		foodValue += amount; 
	}
	
	private int defenseValue;
	
	public int defenseValue() {
		return defenseValue;
	}
	
	public void modifyDefenseValue(int amount) {
		defenseValue += amount;
	}
	
	private int attackValue;
	
	public int attackValue() {
		return attackValue;
	}
	
	public void modifyAttackValue(int amount) {
		attackValue += amount; 
	}
	
	private Image sprite; 
	
	public Image sprite() {
		return sprite;
	}
	
	private String name; 
	
	public String name() {
		return name; 
	}
	
	public Item(String fileLocation, String name) {
		try {
			sprite = ImageIO.read(new File(fileLocation));
			sprite = sprite.getScaledInstance(32, 32, BufferedImage.SCALE_FAST);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.name = name; 
	}

	public String details() {
		String result = "";
		
		if (attackValue != 0) {
			result += "		attack: " + attackValue;
		}
		
		if (defenseValue != 0) {
			result += "		defense: " + defenseValue;
		}
		
		if (foodValue != 0) {
			result += "		food: " + foodValue;
		}
		
		
		return result;
	}
	
	
	
	
	
}
