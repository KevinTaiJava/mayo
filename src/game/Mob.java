package game;

import java.awt.AlphaComposite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage; 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;



public class Mob {
	
	private World world;
	
	public int x; 
	public int y; 
	public int z; 
	public boolean isFacingRight;
	
	private Image sprite;
	
	public Image sprite() {
		return sprite; 
	}
	
	public ArrayList<Image> spriteList;
	
	private ArrayList<Image> leftSpriteList;
	public ArrayList<Image> leftSpriteList() {
		return leftSpriteList; 
	}
	
	private ArrayList<Image> rightSpriteList;
	public ArrayList<Image> rightSpriteList() {
		return rightSpriteList; 
	}
	
	private String name; 
	public String name() {
		return name; 
	}
	
    private int maxHp;
    public int maxHp() { 
    	return maxHp; 
    }

    private int hp;
    public int hp() { 
    	return hp; 
    }

    private int attackValue;
    public int attackValue() { 
    	return attackValue 
    			+ (weapon == null ? 0 : weapon.attackValue())
    		    + (armor == null ? 0 : armor.attackValue());
    }

    private int defenseValue;
    public int defenseValue() { 
    	return defenseValue 
    			+ (weapon == null ? 0 : weapon.defenseValue())
    			+ (armor == null ? 0 : armor.defenseValue());
    }
	
	private MobAi ai;
	public void setMobAi(MobAi ai) { 
		this.ai = ai; 
	}
	
	private int visionRadius; 
	public int visionRadius() {
		return visionRadius; 
	}
	
	private Inventory inventory;
	public Inventory inventory() {
		return inventory;
	}
	
	private int maxFood;
	public int maxFood() { 
		return maxFood; 
	}

	public int food;
	public int food() {
		return food;
	}
	
	
	private Item weapon;
	public Item weapon() { 
		return weapon; 
	}

	private Item armor;
	public Item armor() { 
		return armor; 
	}
	
	private int xp;
	public int xp() {
		return xp;
	}
	
	private int level;
	public int level() { 
		return level; 
	}
	
	public Mob (World world, ArrayList<String> rightFileLocations, ArrayList<String> leftFileLocations, int maxHp, int attack, int defense, int visionRadius, String name) {
		this.world = world; 
		rightSpriteList = new ArrayList<Image>();
		leftSpriteList = new ArrayList<Image>();
		for (String file : rightFileLocations) {
			Image tmp; 
			try {
				tmp = ImageIO.read(new File(file));
				tmp = tmp.getScaledInstance(32, 32, BufferedImage.SCALE_FAST);
			} catch (IOException e) {
				tmp = null;
			}
			
			rightSpriteList.add(tmp);
		}
		
		
		if (leftFileLocations == null) {
			leftSpriteList = rightSpriteList;
		} else {
			for (String file : leftFileLocations) {
				Image tmp; 
				try {
					tmp = ImageIO.read(new File(file));
					tmp = tmp.getScaledInstance(32, 32, BufferedImage.SCALE_FAST);
				} catch (IOException e) {
					tmp = null;
				}
				
				leftSpriteList.add(tmp);
			}
		}
		
		this.isFacingRight = true; 
		spriteList = rightSpriteList;
		this.sprite = rightSpriteList.get(0);
		this.name = name;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attackValue = attack;
		this.defenseValue = defense; 
		this.visionRadius = visionRadius;
		this.inventory = new Inventory(25);
		this.maxFood = 1000;
		this.food = 1000 / 4 * 3;
	}
	
	public void setSprite (Image sprite) {
		this.sprite = sprite; 
	}
	
	public void moveBy(int mx, int my, int mz){
		Tile tile = world.tile((x+mx)/32, (y+my)/32, z+mz);
		
		 if (mz == -1){
	            if (tile == Tile.STAIRS_DOWN) {
	                doAction("climbed up the ladder to level %d", z+mz+1);
	            } else {
	                doAction("try to go up but are stopped by the cave ceiling");
	                return;
	            }
	        } else if (mz == 1){
	            if (tile == Tile.STAIRS_UP) {
	                doAction("climbed down the ladder to level %d", z+mz+1);
	            } else {
	                doAction("try to go down but are stopped by the cave floor");
	                return;
	            }
	        }
	    
		
		Mob other = world.mobs(x+mx, y+my, z+mz);
		
		if (mx < 0) {
			isFacingRight = false; 
			spriteList = leftSpriteList;
		} else if (mx > 0) {
			isFacingRight = true; 
			spriteList = rightSpriteList;
		}
	  
	    if (other == null) {
	        ai.onEnter(x+mx, y+my, z+mz, tile);
	    }
	    else {
	        attack(other);
	    }
	}

	public void attack(Mob other){
		  int amount = Math.max(0, this.attackValue() - other.defenseValue());
		    
	      amount = (int)(Math.random() * amount) + 1;
	    
	      other.modifyHp(-amount);
	      if (isPlayer()) {
	    	  other.ai.setAggressive();
	      }
	      
	      if (other.hp < 1)
	          gainXp(other);
	      
	      doAction("attack the %s for %d damage", other.name, amount);
	      other.notify("The '%s' attacks you for %d damage.", other.name, amount);
	    
	}
	
	public void modifyHp(int amount) {
		this.hp += amount;
		
		if(this.hp > this.maxHp) {
			hp = maxHp;
		}
		
		if (this.hp < 1) {
			doAction("die");
			ai.leaveCorpse();
			ai.die();
			world.remove(this);
		}
	}

	
	public void dig(int wx, int wy, int wz) {
	    world.dig(wx, wy, wz);
	    doAction("dig");
	}
	
	public void update() {
		ai.update();
	}
	
	public boolean canEnter(int wx, int wy, int wz) {
		return world.tile(wx/32, wy/32, wz).isGround() && world.mobs(wx/32, wy/32, wz) == null;
	}
	
	public void notify (String message, Object ... params) {
		ai.onNotify(String.format(message, params));
	}
	
	public void doAction(String message, Object ... params){
		int r = 9*32;
		for (int ox = -r; ox < r+1; ox=ox+32){
			for (int oy = -r; oy < r+1; oy=oy+32){
				if (ox*ox + oy*oy > r*r)
					continue;
				
				Mob other = world.mobs(x+ox, y+oy, z);
				
				if (other == null)
					continue;
				
				if (other == this)
					other.notify("You " + message + ".", params);
				else if (other.canSee(x, y, z)) 
					other.notify(String.format("The %s %s.", name, makeSecondPerson(message)), params);
			}
		}
	}
	
	
	private String makeSecondPerson(String text){
	    String[] words = text.split(" ");
	    words[0] = words[0] + "s";
	    
	    StringBuilder builder = new StringBuilder();
	    for (String word : words){
	        builder.append(" ");
	        builder.append(word);
	    }
	    
	    return builder.toString().trim();
	}
	
	public boolean canSee (int wx, int wy, int wz) {
		return ai.canSee(wx, wy, wz);
	}
	
	public Tile tile(int wx, int wy, int wz) {
	        return world.tile(wx, wy, wz);
	}
	
	public Mob mobs(int wx, int wy, int wz) {
	    return world.mobs(wx, wy, wz);
	}
	
	public void pickup(){
        Item item = world.item(x, y, z);
    
        if (item == null){
            doAction("grab at the ground");
        } else if (inventory.isFull()) {
        	doAction ("can't hold that many items! Drop some of them [d].");
        } else {
            doAction("pickup a %s", item.name());
            world.remove(x, y, z);
            inventory.add(item);
        }
    }
	
	public void drop(Item item){
	    if (world.tile(x/32, y/32, z/32).isGround() && world.item(x,y,z) == null){
	         doAction("drop a " + item.name());
	         world.addAtEmptySpace(item, x, y, z);
	         inventory.remove(item);
	         unequip(item);
	         
	         
	    } else {
	         notify("There's nowhere to drop the %s.", item.name());
	    }
	}

	public void modifyFood(int amount) {
	    food += amount;
	    if (food > maxFood) {
	        food = maxFood;
	    } else if (food < 1 && isPlayer()) {
	        modifyHp(-1000);
	    }
	}
	
	public boolean isPlayer() {
		return name.equals("Hero");
	}
	
	public void eat(Item item){
		if (item.foodValue() <= 0) {
			notify("Yuck. That's not food.");
		} else {
			modifyFood(item.foodValue());
			modifyHp(item.foodValue()/8);
			inventory.remove(item);
			unequip(item);
		}
	}

	public void unequip(Item item){
	      if (item == null)
	         return;
	  
	      if (item == armor){
	          doAction("remove a " + item.name());
	          armor = null;
	      } else if (item == weapon) {
	          doAction("put away a " + item.name());
	          weapon = null;
	      }
	  }
	public void equip(Item item){
	      if (item.attackValue() == 0 && item.defenseValue() == 0)
	          return;
	  
	      if (item.attackValue() >= item.defenseValue()){
	          unequip(weapon);
	          doAction("wield a " + item.name());
	          weapon = item;
	      } else {
	          unequip(armor);
	          doAction("put on a " + item.name());
	          armor = item;
	      }
	  }
	
	 public void modifyXp(int amount) {
	      xp += amount;

	      notify("You %s %d xp.", amount < 0 ? "lose" : "gain", amount);

	      while (xp > (int)(Math.pow(level, 1.5) * 30)) {
	          level++;
	          doAction("advance to level %d", level);
	          ai.onGainLevel();
	          modifyHp(level * 2);
	      }
	  }
	 
	 public void gainXp(Mob other){
		    int amount = (other.maxHp
		      + other.attackValue()
		      + other.defenseValue())/2
		      - level * 4;

		    if (amount > 0)
		      modifyXp(amount);
	}
	 
	public void gainMaxHp() {
		maxHp += 10;
		hp += 10;
		doAction("look healthier");
	}

	public void gainAttackValue() {
		attackValue += 2;
		doAction("look stronger");
	}

	public void gainDefenseValue() {
		defenseValue += 2;
		doAction("look tougher");
	}

	public void gainVision() {
		visionRadius += 1;
		doAction("look more aware");
	}	 
	
	public Item item(int x, int y, int z) {
		return world.item(x, y, z);
	}	
	
	public String details() {
        return String.format("     level:%d     attack:%d     defense:%d     hp:%d", level, attackValue(), defenseValue(), hp);
    }
	
}
