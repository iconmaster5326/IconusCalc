
package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.gui.ColorScheme;
import com.iconmaster.iconuscalc.manager.ColorPickManager;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public class ColorPickRenderer extends GridRenderer {
    public ColorPickManager manager;

    @Override
    public void paint(Graphics g, int w, int h) {
        this.setWindowSize(w, h);
        
        this.drawBorderedRect(g, rows/2-7, 2, 14, cols-4);
        
        this.drawBorderedRect(g, rows/2-6, 3, 12, 1, manager.color, ColorScheme.bd);
        
        this.drawString(g,"Red:",rows/2-6, 5);
        if (!(manager.pos==0 && manager.editing)) {
            this.drawString(g,"Red:    "+manager.color.getRed(),rows/2-6, 5);
            if (manager.pos==0)
                this.highlight(g, rows/2+1, 5, 3, 1);
        }
        
        this.drawString(g,"Green:",rows/2-6, 6);
        if (!(manager.pos==1 && manager.editing)) {
            this.drawString(g,"Green:  "+manager.color.getGreen(),rows/2-6, 6);
            if (manager.pos==1)
                this.highlight(g, rows/2+1, 6, 3, 1);
        }
        
        this.drawString(g,"Blue:",rows/2-6, 7);
        if (!(manager.pos==2 && manager.editing)) {
            this.drawString(g,"Blue:   "+manager.color.getBlue(),rows/2-6, 7);
            if (manager.pos==2)
                this.highlight(g, rows/2+1, 7, 3, 1);
        }
        
        this.drawString(g,"Alpha:",rows/2-6, 8);
        if (!(manager.pos==3 && manager.editing)) {
            this.drawString(g,"Alpha:  "+manager.color.getAlpha(),rows/2-6, 8);
            if (manager.pos==3)
                this.highlight(g, rows/2+1, 8, 3, 1);
        }
        
        this.drawBorderedRect(g, rows/2-6, cols-4, 2, 1);
        this.drawString(g,"OK",rows/2-6, cols-4);
        if (manager.pos==4 && manager.bpos==0)
            this.highlight(g, rows/2-6, cols-4, 2, 1);
        this.drawBorderedRect(g, rows/2, cols-4, 6, 1);
        this.drawStringRightJustified(g,"Cancel",rows/2+6, cols-4);
        if (manager.pos==4 && manager.bpos==1)
            this.highlight(g, rows/2, cols-4, 6, 1);
    }
}
