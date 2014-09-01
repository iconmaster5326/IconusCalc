
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.render.IScreenRenderer;
import java.awt.event.KeyEvent;

/**
 *
 * @author iconmaster
 */
public interface IControlManager {
    public IScreenRenderer getRenderer();

    public void onKey(KeyEvent e, InputType type);
}
