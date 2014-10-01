
package com.iconmaster.iconuscalc.render;

import com.iconmaster.iconuscalc.gui.Window;
import java.awt.Graphics;

/**
 *
 * @author iconmaster
 */
public interface IScreenRenderer {
	public void paint(Graphics g, int w, int h);
	
	public void setParent(Window gui);
	public Window getParent();
}
