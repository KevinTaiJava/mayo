package game;

import java.util.Random;

import game.Tile;
import game.World;
import game.WorldBuilder;

public class WorldBuilder {
    private int width;
    private int height;
    private int depth; 
    private Tile[][][] tiles;

    public WorldBuilder(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.tiles = new Tile[width][height][depth];
    }

    public World build() {
        return new World(tiles);
    }
    
    private WorldBuilder randomizeTiles() {
    	 for (int r = 0; r < width; r++) {
             for (int c = 0; c < height; c++) {
            	 for (int d = 0; d < depth; d++) {
            		 tiles[r][c][d] = Math.random() < 0.5 ? Tile.FLOOR : Tile.WALL;
            	 }
             }
         }
    	 for (Tile[][] b : tiles) {
    		 for (Tile[] t : b) { 
    				 for (Tile c : t) {
    			 System.out.print(c + " ");
    				 }
    		 System.out.println();
    		 }
    	 }
         return this;
    }
    
    public Tile[][][] updateWorld(Tile[][][] tiles) {
    	this.tiles = tiles; 
    	putVerticalWalls();
    	return this.tiles;
    }
    
    private WorldBuilder smooth(int times) { //checks area around each block and sets that block based on its surroundings
    	Tile[][][] tile2 = new Tile[width][height][depth]; 
    	
    	for (int i = 0; i <times; i++) {
	    	for (int r = 0; r < tile2.length; r++) {
	    		
	    		for (int c = 0; c < tile2[0].length; c++) {
	    			for (int d = 0; d < tile2[0][0].length; d++) {
		    			int floor = 0;
		        		int wall = 0;
		    			
		        		  for (int ox = -1; ox < 2; ox++) {
		                      for (int oy = -1; oy < 2; oy++) {
		                       if (r + ox < 0 || r + ox >= width || c + oy < 0
		                            || c + oy >= height)
		                           continue;
		
		                       if (tiles[r + ox][c + oy][d] == Tile.FLOOR)
		                           floor++;
		                       else
		                           wall++;
		                      }
		                  }
		        		  
		        		tile2[r][c][d] = floor >= wall ? Tile.FLOOR : Tile.WALL;   
	    			}
	    		}
	    	
	    	
	    	}
	    	tiles = tile2;
    	}
    	return this;
    }
    
//FINISH THIS LATER
    private WorldBuilder putVerticalWalls() {
    	for (int c = 1; c < tiles.length-1; c++) {
    		for (int r = 1; r < tiles[c].length-1; r++) {
    			for (int d = 0; d < tiles[c][r].length; d++) {
    				
	    			
	    			if(tiles[c][r][d] == Tile.WALL) {
	//    				int above = r-1;
	//        			int below = r+1;
	//        			int left = c-1;
	//        			int right = c+1;
	//        			
	//        			if (left < 0) {
	//        				if (above < 0) {
	//        					
	//        				} else if (above == tiles.length) {
	//        					
	//        				} else {
	//        					
	//        				}
	//        			}
	        		
	    				if (tiles[c][r-1][d] == Tile.FLOOR &&(tiles[c+1][r][d] == Tile.FLOOR || tiles[c+1][r][d] == Tile.VERTICALWALL)) {
	    					tiles[c][r][d] = Tile.WALLTOPRIGHT;
	    				} else if (tiles[c][r-1][d] == Tile.FLOOR && (tiles[c-1][r][d] == Tile.FLOOR || tiles[c-1][r][d] == Tile.VERTICALWALL)) {
	    					tiles[c][r][d] = Tile.WALLTOPLEFT;
	    				} else if (tiles[c][r+1][d] == Tile.FLOOR) { 
	        				tiles[c][r-1][d] = Tile.WALLTOP;
	        				tiles[c][r][d] = Tile.VERTICALWALL;
	        			} else if (tiles[c][r-1][d] == Tile.FLOOR) {
	        				tiles[c][r][d] = Tile.WALLBOTTOM;
	        			} else if ((tiles[c-1][r][d] == Tile.FLOOR || tiles[c-1][r][d] == Tile.VERTICALWALL)) {
	        				tiles[c][r][d] = Tile.WALLLEFT;
	        			} else if ((tiles[c+1][r][d] == Tile.FLOOR || tiles[c+1][r+1][d] == Tile.FLOOR)) {
	        				tiles[c][r][d] = Tile.WALLRIGHT;
	        			}
    			}
    			}
    		}
    	}
    	
    	return this;
    	
    }
    
    public WorldBuilder makeCaves() {
    	return randomizeTiles().smooth(8).putVerticalWalls();
    }
    
    
    //return to this to improve the spacing of staircases. 
    public WorldBuilder connectFloors(int numberOfStairs) {
 
    	int x;
    	int y;
    	for (int d = 0; d < depth-1; d++) {
    		for (int i = 1; i <= numberOfStairs; i++) {
    			do {
    				x = (int) (Math.random()*width);
    				y = (int) (Math.random()*height);
    			} while (tiles[x][y][d].isGround() == false ||  tiles[x][y][d+1].isGround() == false);
    			
    			tiles[x][y][d] = Tile.STAIRS_DOWN;
    			tiles[x][y][d+1] = Tile.STAIRS_UP;
    		}
    	}
    
    	return this;
    
    
    }
    
    public WorldBuilder addExit() {
    	Random random = new Random();
    	boolean hasAdded = false;
    	
    	while (!hasAdded) {
    		int depth = random.nextInt(2) + (this.depth - 2);
    		int x = (int) (Math.random() * this.width);
    		int y = (int) (Math.random() * this.height);
    		
    		if (tiles[x][y][depth] == Tile.VERTICALWALL) {
    			tiles[x][y][depth] = Tile.WINTILE;
    			hasAdded = true;
    			System.out.println("Win tile added");
    		}
    	}
    	
    	return this;
    }
}