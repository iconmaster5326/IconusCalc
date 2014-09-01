
package com.iconmaster.iconuscalc.util;

import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public class RenderUtils {
    public static Font getFont(int xSize, int ySize) {
        return new Font(Font.MONOSPACED, Font.PLAIN, (int) (Math.min(xSize, ySize) * 1.5));
    }
    
    public static void drawCenteredString(Graphics g, String s, int x, int y) {
        g.drawString(s, (int) (x-g.getFontMetrics().getStringBounds(s, g).getMaxX()/2), y);
    }
}
