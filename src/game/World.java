package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class World {

	private Tile[][][] tiles;
	private int width;
	private int height; 
	private int depth; 
	private ArrayList<Mob> mobs; //keeps track of all mobs
	public boolean[][][] hasSeen;
	public ArrayList<Item> items;
	public Mob player;
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height; 
	}
	
	public void setPlayer(Mob player) {
		this.player = player;
	}
	
	public Mob mobs(int x, int y, int z) {
		for (Mob m : this.mobs) {
			if (m.x == x && m.y == y && m.z == z) {
				return m;
			}
		}
		
		return null;
	}
	
	public ArrayList<Mob> getMobList() {
		return mobs; 
	}
	
	public ArrayList<Item> getItemList() {
		return items; 
	}
	
	public World(Tile[][][] tiles){
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        this.depth = tiles[0][0].length;
        mobs = new ArrayList<Mob>();
        this.hasSeen = new boolean[width][height][depth];
        this.items = new ArrayList<Item>();
    }
	
	public Tile tile(int x, int y, int z){
        if (x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
            return Tile.BOUNDS;
        else
            return tiles[x][y][z];
    }
	
	public BufferedImage glyph(int x, int y, int z){
        return tile(x, y, z).glyph();
    }
	
	public void dig(int x, int y, int z) {
	    if (tile(x,y, z).isDiggable())
	        tiles[x][y][z] = Tile.FLOOR;
	    WorldBuilder updated = new WorldBuilder(tiles.length,tiles[0].length, tiles[0][0].length);
	    tiles = updated.updateWorld(tiles);
	}
	
	public void addAtEmptyLocation(Mob mob, int z) {
		int x;
		int y;
		Random random = new Random();
		do {
			
			x = random.nextInt(width*32);
			y = random.nextInt(height*32);
	
		} while(x%32 != 0  || y%32 != 0 || mobs(x,y, z) != null || !tile(x/32,y/32, z).isGround()); 
		//makes sure monster matches scale of game and is added to ground, and that there's not a monster already there
		
		mob.x=x;
		mob.y=y;
		mob.z = z; 
		mobs.add(mob);
	}
	
	public void remove (Mob other) {
		this.mobs.remove(other);
		
	}
	
	//We'd love to simulate all floors at the same time, but that would result in too much game lag,
	//We're not terribly experienced /w threading, so this is just a stopgap until we figure that out
	public void update(int currentFloor) {
		 ArrayList<Mob> toUpdate = new ArrayList<Mob>(mobs);
		    for (Mob mob : toUpdate){
		        if (mob.z == currentFloor) 
		        	mob.update();
		 }
	}
	
	//Item methods
	
	public Item item(int x, int y, int z){
		for (Item i : this.items) {
			if (i.x == x && i.y == y && i.z == z) {
				return i;
			}
		}
		
		return null;
	}
	
	public void addAtEmptyLocation(Item item, int depth) {
		int x;
		int y;
		Random random = new Random();
		do {
			
			x = random.nextInt(width*32);
			y = random.nextInt(height*32);
	
		} while(x%32 != 0  || y%32 != 0 || item(x,y, depth) != null || !tile(x/32,y/32, depth).isGround()); 
		
		
		item.x=x;
		item.y=y;
		item.z = depth; 
		items.add(item);
	}
	
	public void remove(int x, int y, int z) {
	    for (int i = 0; i < items.size(); i++) {
	    	Item temp = items.get(i);
	    	if (temp.x == x && temp.y == y && temp.z == z) {
	    		items.remove(i);
	    		return;
	    	}
	    }
	}

	public void addAtEmptySpace(Item item, int x, int y, int z) {
		item.x = x;
		item.y = y;
		item.z = z;
		items.add(item);
	}
	
	public boolean gameWon() {
		return tile(player.x/32,player.y/32,player.z) == Tile.WINTILE;
	}

	
	
	
}
