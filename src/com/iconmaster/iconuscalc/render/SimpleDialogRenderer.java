
package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.manager.SimpleDialogManager;
import com.iconmaster.iconuscalc.util.StringUtils;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public class SimpleDialogRenderer extends GridRenderer {
    public SimpleDialogManager manager;

    @Override
    public void paint(Graphics g, int w, int h) {
        setWindowSize(w,h);
        
        int boxW = Math.max(2*rows/3,manager.buttons.length*rows/4);
        int boxX = rows/2-boxW/2+1;
        
        String[] desc = StringUtils.multilineSplit(manager.desc,boxW-2);
        
        int boxH = cols/3+desc.length-1;
        int boxY = cols/3-desc.length/2;

        drawBorderedRect(g,boxX,boxY,boxW,boxH);
        
        for (int i=0;i<desc.length;i++) {
            drawStringCentered(g,desc[i],boxX+boxW/2,boxY+i+1);
        }
        
        for (int i=0;i<manager.buttons.length;i++) {
            drawBorderedRect(g,(i+1)*boxW/manager.buttons.length+1-(manager.buttons[i].length()/2+1),boxY+boxH-2,5,1);
            drawStringCentered(g,manager.buttons[i],(i+1)*boxW/manager.buttons.length+1,boxY+boxH-2);
        }
        
        highlight(g,(manager.pos+1)*boxW/manager.buttons.length+1-(manager.buttons[manager.pos].length()/2+1),boxY+boxH-2,5,1);
    }
}
