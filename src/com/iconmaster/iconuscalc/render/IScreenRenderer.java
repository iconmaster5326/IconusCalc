
package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.gui.MainGui;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public interface IScreenRenderer {
    public void paint(Graphics g, int w, int h);
    
    public void setParent(MainGui gui);
    public MainGui getParent();
}
