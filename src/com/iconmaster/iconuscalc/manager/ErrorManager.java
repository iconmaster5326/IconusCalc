
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.render.ErrorRenderer;
import com.iconmaster.iconuscalc.render.IScreenRenderer;
import java.awt.event.KeyEvent;

/**
 *
 * @author iconmaster
 */
public class ErrorManager implements IControlManager {
    public ErrorRenderer renderer = new ErrorRenderer();

    @Override
    public IScreenRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void onKey(KeyEvent e, InputType type) {
        if (type != InputType.UP)
            renderer.getParent().closeManager();
    }
    
}
