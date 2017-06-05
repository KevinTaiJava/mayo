package game.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import game.Line;
import game.Mob;

public abstract class TargetScreen implements Screen {

    protected Mob player;
    protected String caption;
    private int sx;
    private int sy;
    private int x;
    private int y;

    public TargetScreen(Mob player, String caption, int sx, int sy) {
        this.player = player;
        this.caption = caption;
        this.sx = sx;
        this.sy = sy;
    }
    
    public void displayOutput(JFrame frame, Graphics2D g2d) {
        for (Point p : new Line(sx/32, sy/32, (sx + x)/32, (sy + y)/32)){
            if (p.x < 0 || p.x >= 80 || p.y < 0 || p.y >= 24)
                continue;
            System.out.println("Hello");
            g2d.setColor(Color.MAGENTA);
            g2d.setFont(new Font("Courier",Font.PLAIN,16));
            g2d.drawString("*", p.x*32+12, p.y*32+18);
        }
        
        g2d.setFont(new Font("Courier", Font.PLAIN, 16));
        g2d.drawString(caption, 50, 50);
    }

    public Screen update(KeyEvent key) {
        int px = x;
        int py = y;
        
      
        switch (key.getKeyCode()){
        case KeyEvent.VK_LEFT:
        case KeyEvent.VK_H: x=x-32; break;
        case KeyEvent.VK_RIGHT:
        case KeyEvent.VK_L: x=x+32; break;
        case KeyEvent.VK_UP:
        case KeyEvent.VK_J: y=y-32; break;
        case KeyEvent.VK_DOWN:
        case KeyEvent.VK_K: y=y+32; break;
        case KeyEvent.VK_Y: x--; y--; break;
        case KeyEvent.VK_U: x++; y--; break;
        case KeyEvent.VK_B: x--; y++; break;
        case KeyEvent.VK_N: x++; y++; break;
        case KeyEvent.VK_ENTER: selectWorldCoordinate(player.x + x, player.y + y, sx + x, sy + y); return null;
        case KeyEvent.VK_ESCAPE: return null;
        }
    
        if (!isAcceptable(player.x + x, player.y + y)){
            x = px;
            y = py;
        }
    
        enterWorldCoordinate(player.x + x, player.y + y, sx + x, sy + y);
    
        return this;
    }
    
    public boolean isAcceptable(int x, int y) {
        return player.canSee(x, y, player.z);
    }
    
    public void enterWorldCoordinate(int x, int y, int screenX, int screenY) {
    }
    
    public void selectWorldCoordinate(int x, int y, int screenX, int screenY){
    }

    
}

