
package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.RenderUtils;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public class TextGridRenderer implements IScreenRenderer {
    private Window parent;
    
    public final int rows;
    public final int cols;
    
    public static int ROWS = 35;
    public static int COLS = 16;
    
    
    public char[][] grid;
    
    public int cx = -100;
    public int cy = -100;
    
    public TextGridRenderer() {
        //this(25,16);
        this(ROWS,COLS);
    }
    
    public TextGridRenderer(int rows,int cols) {
        grid = new char[rows][cols];
        
        for (int x=0;x<rows;x++) {
            for (int y=0;y<cols;y++) {
                grid[x][y] = ' ';
                //grid[x][y] = (char) new Random().nextInt();
            }
        }
        
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public void paint(Graphics g, int w, int h) {
        g.setFont(RenderUtils.getFont(w/rows,h/cols));
        
        for (int x=0;x<rows;x++) {
            for (int y=0;y<cols;y++) {
                char c = grid[x][y];
                
                if (!Character.isWhitespace(c)) {
                    g.drawString(Character.toString(c), (x)*(w/rows), (y+1)*(h/cols));
                }
            }
        }
        
        g.setColor(new Color(0,0,0,128));
        g.fillRect((cx+1)*(w/rows), (cy)*(h/cols), w/rows/4, h/cols);
        g.setColor(Color.BLACK);
    }
    
    public void drawString(String str, int x, int y) {
        if (str==null) {return;}
        char[] chars = str.toCharArray();
        for (int i=0;i<chars.length;i++) {
            if (x+i<rows && y<cols && x+i>=0 && y>=0) {
                grid[x+i][y] = chars[i];
            }
        }
        
        repaint();
    }
    
    public void drawStringRightJustified(String str, int x, int y) {
        drawString(str, x-str.length(), y);
    }

    public void clearScreen() {
        grid = new char[rows][cols];
        for (int x=0;x<grid.length;x++) {
            for (int y=0;y<grid[0].length;y++) {
                grid[x][y] = ' ';
            }
        }
        repaint();
    }

    @Override
    public void setParent(Window gui) {
        parent = gui;
    }

    @Override
    public Window getParent() {
        return parent;
    }

    private void repaint() {
        if (parent!=null) {
            parent.repaint();
        }
    }

    public void moveCursor(int x, int y) {
        cx = x;
        cy = y;
    }
    
    public void removeCursor() {
        cx = -100;
        cy = -100;
    }
}
