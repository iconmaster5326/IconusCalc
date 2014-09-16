
package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.gui.MainGui;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.RenderUtils;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public class ErrorRenderer extends GridRenderer {
    
    @Override
    public void paint(Graphics g, int w, int h) {
        setWindowSize(w,h);
        drawBorderedRect(g,rows/4-1,cols/3,2*rows/3,cols/3,Color.WHITE,Color.BLACK);

        drawStringCentered(g,"ERROR", rows/2+1,cols/3);
        drawStringCentered(g,getParent().getError().getMessage(), rows/2+1,cols/3+1);
    }
}
