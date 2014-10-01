
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.render.IScreenRenderer;

/**
 *
 * @author iconmaster
 */
public interface IControlManager {
	public IScreenRenderer getRenderer();

	public void onKey(KeyInput e);

	public boolean showStatusBar();
	
	public void setParent(Window gui);
	public Window getParent();
}
