
package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.RenderUtils;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public class TextGridRenderer extends GridRenderer {
    public char[][] grid;
    
    public int cx = -100;
    public int cy = -100;
    
    public int cx2 = -100;
    public int cy2 = -100;
    private boolean exmode = false;
    
    public TextGridRenderer() {
        this(ROWS,COLS);
    }
    
    public TextGridRenderer(int rows,int cols) {
        super(rows,cols);
        
        grid = new char[rows][cols];
        
        for (int x=0;x<rows;x++) {
            for (int y=0;y<cols;y++) {
                grid[x][y] = ' ';
                //grid[x][y] = (char) new Random().nextInt();
            }
        }
    }

    @Override
    public void paint(Graphics g, int w, int h) {
        this.setWindowSize(w, h);
        
        for (int x=0;x<rows;x++) {
            for (int y=0;y<cols;y++) {
                drawString(g,new String(new char[] {grid[x][y]}),x,y);
            }
        }
        
        if (exmode) {
            highlight(g,cx,cy,cx2,cy2);
        } else {
            drawCursor(g,cx,cy);
        }
    }
    
    public void putString(String str, int x, int y) {
        if (str==null) {return;}
        char[] chars = str.toCharArray();
        for (int i=0;i<chars.length;i++) {
            if (x+i<rows && y<cols && x+i>=0 && y>=0) {
                grid[x+i][y] = chars[i];
            }
        }
        
        repaint();
    }
    
    public void putStringRightJustified(String str, int x, int y) {
        putString(str, x-str.length(), y);
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

    public void moveCursor(int x, int y) {
        cx = x;
        cy = y;
        exmode = false;
    }
    
    public void removeCursor() {
        cx = -100;
        cy = -100;
        cx2 = -100;
        cy2 = -100;
        exmode = false;
    }
    
    public void moveCursorEx(int x1, int y1, int x2, int y2) {
        cx = x1;
        cy = y1;
        cx2 = x2;
        cy2 = y2;
        exmode = true;
    }
}
