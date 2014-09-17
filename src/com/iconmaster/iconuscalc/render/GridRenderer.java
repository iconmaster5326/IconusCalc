package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.RenderUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public abstract class GridRenderer implements IScreenRenderer {
    private Window parent;
    
    public int rows;
    public int cols;
    
    public static int ROWS = 35;
    public static int COLS = 16;
    private int winW = 0;
    private int winH = 0;

    public GridRenderer() {
        this(ROWS,COLS);
    }

    public GridRenderer(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public void setParent(Window gui) {
        parent = gui;
    }

    @Override
    public Window getParent() {
        return parent;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }
    
    public void setWindowSize(int w, int h) {
        this.winW = w;
        this.winH = h;
    }
    
    public int cellW() {
        return winW/rows;
    }
    
    public int cellH() {
        return winH/cols;
    }
    
    public void drawRect(Graphics g,int x,int y,int w,int h) {
        g.drawRect(x*cellW()+1, y*cellH()+1, w*cellW()-1, h*cellH()-1);
    }
    
    public void drawFillRect(Graphics g,int x,int y,int w,int h) {
        g.fillRect(x*cellW()+1, y*cellH()+1, w*cellW()-1, h*cellH()-1);
    }
    
    public void highlight(Graphics g,int x,int y,int w,int h) {
        Color oldc = g.getColor();
        g.setColor(new Color(0,0,0,128));
        g.fillRect(x*cellW()+1, y*cellH()+1, w*cellW()-1, h*cellH()-1);
        g.setColor(oldc);
    }
    
    public void drawBorderedRect(Graphics g,int x,int y,int w,int h, Color back, Color outline) {
        Color oldc = g.getColor();
        g.setColor(back);
        drawFillRect(g,x,y,w,h);
        g.setColor(outline);
        drawRect(g,x,y,w,h);
        g.setColor(oldc);
    }
    
    public void drawString(Graphics g, String str, int x, int y) {
        Font oldf = g.getFont();
        g.setFont(RenderUtils.getFont(cellW(), cellH()));
        g.drawString(str, x*cellW()+2, (y+1)*cellH()-3);
        g.setFont(oldf);
    }
    
    public void drawStringRightJustified(Graphics g, String str, int x, int y) {
        drawString(g,str,x-str.length(),y);
    }
    
    public void drawStringCentered(Graphics g, String str, int x, int y) {
        RenderUtils.drawCenteredString(g, str, x*cellW()+cellW()/2, (y+1)*cellH()-3);
    }
    
    public void drawCursor(Graphics g, int cx, int cy) {
        Color oldc = g.getColor();
        g.setColor(new Color(0,0,0,128));
        g.fillRect((cx+1)*cellW(), (cy)*cellH(), cellW()/4, cellH());
        g.setColor(oldc);
    }
    
    public void repaint() {
        if (getParent()!=null) {
            getParent().repaint();
        }
    }
}
