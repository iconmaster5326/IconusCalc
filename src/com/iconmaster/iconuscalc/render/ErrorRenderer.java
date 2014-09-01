
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
public class ErrorRenderer implements IScreenRenderer {
    Window gui;

    @Override
    public void paint(Graphics g, int w, int h) {
        g.setColor(Color.WHITE);
        g.fillRect(w/8, h/3, 6*w/8, h/3);
        g.setColor(Color.BLACK);
        g.drawRect(w/8, h/3, 6*w/8, h/3);

        g.setFont(RenderUtils.getFont(w/35, h/16));
        RenderUtils.drawCenteredString(g,"ERROR", w/8+(6*w/16), h/3+h/16+2);
        RenderUtils.drawCenteredString(g,gui.getError().getMessage(), w/8+(6*w/16), h/3+h/8+4);
    }

    @Override
    public void setParent(Window gui) {
        this.gui = gui;
    }

    @Override
    public Window getParent() {
        return gui;
    }
    
}
