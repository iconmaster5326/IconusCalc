package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.manager.SimpleDialogManager;
import com.iconmaster.iconuscalc.util.RenderUtils;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public class SimpleDialogRenderer implements IScreenRenderer {

    Window gui;
    public SimpleDialogManager manager;

    @Override
    public void paint(Graphics g, int w, int h) {
        g.setColor(Color.WHITE);
        g.fillRect(w / 8, h / 3, 6 * w / 8, h / 2);
        g.setColor(Color.BLACK);
        g.drawRect(w / 8, h / 3, 6 * w / 8, h / 2);

        g.setFont(RenderUtils.getFont(w / 35, h / 16));

        for (int i = 0; i < manager.desc.length; i++) {
            RenderUtils.drawCenteredString(g, manager.desc[i], w / 8 + (6 * w / 16), h / 3 + (h / 16) * (i + 1) + 2);
        }

        if (manager.buttons.length == 1) {
            g.drawRect(2 * w / 8 + w / 16, h / 3 + h / 2 - 2 * h / 16, 3 * w / 8, h / 16 + 4);
            RenderUtils.drawCenteredString(g, manager.buttons[0], 2 * w / 8 + w / 16 + 2 + 3 * w / 16 - 6, h / 3 + h / 2 - h / 16 + 1);
        } else if (manager.buttons.length == 2) {
            g.drawRect(w / 8 + w / 16, h / 3 + h / 2 - 2 * h / 16, 2 * w / 8, h / 16 + 4);
            RenderUtils.drawCenteredString(g, manager.buttons[0], w / 8 + w / 16 + 2 + w / 8 - 6, h / 3 + h / 2 - h / 16 + 1);
            g.drawRect(4 * w / 8 + w / 16, h / 3 + h / 2 - 2 * h / 16, 2 * w / 8, h / 16 + 4);
            RenderUtils.drawCenteredString(g, manager.buttons[1], 4 * w / 8 + w / 16 + 2 + w / 8 - 6, h / 3 + h / 2 - h / 16 + 1);

            g.setColor(new Color(0, 0, 0, 128));
            if (manager.pos == 0) {
                g.fillRect(w / 8 + w / 16, h / 3 + h / 2 - 2 * h / 16, 2 * w / 8, h / 16 + 4);
            } else {
                g.fillRect(4 * w / 8 + w / 16, h / 3 + h / 2 - 2 * h / 16, 2 * w / 8, h / 16 + 4);
            }
            g.setColor(Color.BLACK);
        }
        //RenderUtils.drawCenteredString(g,gui.getError().getMessage(), w/8+(6*w/16), h/3+h/8+4);
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
