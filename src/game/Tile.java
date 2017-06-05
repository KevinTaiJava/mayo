package game;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public enum Tile {
	
	FLOOR("pics/tiles/floor.png", "A dirt and rock cave floor."),
	WALL("pics/tiles/rooftile1.png", "A dirt and rock cave wall"),
	VERTICALWALL("pics/tiles/walltile1.png","A dirt and rock cave wall"),
	WALLBOTTOM("pics/tiles/rooftilebottom.png","A dirt and rock cave wall"),
	WALLTOP("pics/tiles/rooftiletop.png", "A dirt and rock cave wall"),
	WALLLEFT("pics/tiles/rooftileleft.png", "A dirt and rock cave wall"),
	WALLRIGHT("pics/tiles/rooftileright.png", "A dirt and rock cave wall"),
	WALLTOPRIGHT("pics/tiles/rooftoprightcorner.png","A dirt and rock cave wall"),
	WALLTOPLEFT("pics/tiles/rooftopleftcorner.png", "A dirt and rock cave wall"),
	
	GREYVERTICALWALL("pics/tiles/walltile1grey.png", "A dirt and rock cave wall"),
	GREYWALL("pics/tiles/rooftile1grey.png", "A dirt and rock cave wall"),
	GREYFLOOR("pics/tiles/floorgrey.png", "A dirt and rock cave floor"),
	BOUNDS("pics/tiles/Bat.png", "Beyond the edge of the world."),
	STAIRS_DOWN("pics/tiles/ladderdown clone.png", "A stone staircase that goes down."),
	STAIRS_UP("pics/tiles/ladderup.png", "A stone staircase that goes up."),
	UNKNOWN("pics/tiles/rooftile1.png", "(unknown)"), 
	WINTILE("pics/tiles/finalexit.png", "The exit!");
	
	
	
	
	private BufferedImage glyph;
	public BufferedImage glyph() { 
		return glyph; 
	}
	

	private String description;
	public String details(){ 
		return description; 
	}
	
	Tile(String fileLocation, String description){
		try {
			this.glyph = ImageIO.read(new File(fileLocation));
			BufferedImage resizedImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(this.glyph, 0, 0, 32, 32, null);
			g.dispose();
			g.setComposite(AlphaComposite.Src);
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			this.glyph = resizedImage; 
		} catch (IOException e) {
			this.glyph = null;
			e.printStackTrace();
		}
		this.description = description;
	}
	
	public boolean isDiggable() {
	    return this.details() == "A dirt and rock cave wall"; //true if wall, gotta consider all the types of walls we'll be using
	}
	
	public boolean isGround() {
	    return (this == FLOOR || this == STAIRS_DOWN || this == STAIRS_UP || this == WINTILE);
	}

	
}
