package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.gui.ColorScheme;
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
    
    public int boxOffset() {
        return (winH/600+winW/1200)/2;
    }
    
    public int stringOffsetX() {
        return (winH/600+winW/300)/2;
    }
    
    public int stringOffsetY() {
        return (winH/200+winW/400)/2;
    }
    
    public void drawRect(Graphics g,int x,int y,int w,int h, Color c) {
        g.setColor(c);
        g.drawRect(x*cellW()+boxOffset(), y*cellH()+boxOffset(), w*cellW()-boxOffset(), h*cellH()-boxOffset());
    }
    
    public void drawRect(Graphics g,int x,int y,int w,int h) {
        this.drawRect(g, x, y, w, h, ColorScheme.bd);
    }
    
    public void drawFillRect(Graphics g,int x,int y,int w,int h, Color c) {
        g.setColor(c);
        g.fillRect(x*cellW()+boxOffset(), y*cellH()+boxOffset(), w*cellW()-boxOffset(), h*cellH()-boxOffset());
    }
    
    public void drawFillRect(Graphics g,int x,int y,int w,int h) {
        this.drawFillRect(g, x, y, w, h, ColorScheme.bk);
    }
    
    public void highlight(Graphics g,int x,int y,int w,int h) {
        g.setColor(ColorScheme.hl);
        g.fillRect(x*cellW()+boxOffset(), y*cellH()+boxOffset(), w*cellW()-boxOffset(), h*cellH()-boxOffset());
    }
    
    public void drawBorderedRect(Graphics g,int x,int y,int w,int h, Color back, Color outline) {
        drawFillRect(g,x,y,w,h,back);
        drawRect(g,x,y,w,h,outline);
    }
    
    public void drawBorderedRect(Graphics g,int x,int y,int w,int h) {
        this.drawBorderedRect(g, x, y, w, h, ColorScheme.bk, ColorScheme.bd);
    }
    
    public void drawString(Graphics g, String str, int x, int y, Color c) {
        g.setColor(c);
        Font oldf = g.getFont();
        g.setFont(RenderUtils.getFont(cellW(), cellH()));
        g.drawString(str, x*cellW()+stringOffsetX(), (y+1)*cellH()-stringOffsetY());
        g.setFont(oldf);
    }
    
    public void drawString(Graphics g, String str, int x, int y) {
        this.drawString(g, str, x, y, ColorScheme.bd);
    }
    
    public void drawStringRightJustified(Graphics g, String str, int x, int y, Color c) {
        drawString(g,str,x-str.length(),y,c);
    }
    
    public void drawStringRightJustified(Graphics g, String str, int x, int y) {
        this.drawString(g,str,x-str.length(),y);
    }
    
    public void drawStringCentered(Graphics g, String str, int x, int y, Color c) {
        g.setColor(c);
        RenderUtils.drawCenteredString(g, str, x*cellW()+cellW()/2, (y+1)*cellH()-3);
    }
    
    public void drawStringCentered(Graphics g, String str, int x, int y) {
        this.drawStringCentered(g, str, x, y, ColorScheme.bd);
    }
    
    public void drawCursor(Graphics g, int cx, int cy) {
        g.setColor(ColorScheme.hl);
        g.fillRect((cx+1)*cellW(), (cy)*cellH(), cellW()/4, cellH());
    }
    
    public void repaint() {
        if (getParent()!=null) {
            getParent().repaint();
        }
    }
    
    public void onResize() {
        
    }
}
