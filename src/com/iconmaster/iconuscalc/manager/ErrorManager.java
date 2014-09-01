
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.gui.InputType;
import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.render.ErrorRenderer;
import com.iconmaster.iconuscalc.render.IScreenRenderer;

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
    public void onKey(KeyInput e) {
        if (e.type != InputType.UP)
            renderer.getParent().closeManager();
    }
    
}
