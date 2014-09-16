
package com.iconmaster.iconuscalc.manager;

import com.iconmaster.iconuscalc.gui.InputType;
import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.render.IScreenRenderer;
import com.iconmaster.iconuscalc.render.SimpleDialogRenderer;

/**
 *
 * @author iconmaster
 */
public class SimpleDialogManager implements IControlManager {
    public interface DialogResult {
        public void onButton(int id, String str);
    }
    
    public final DialogResult result;
    public SimpleDialogRenderer renderer;
    public String[] buttons;
    public String desc;
    public int pos = 0;
    
    private Window gui;
    
    public SimpleDialogManager(String desc, String[] buttons, DialogResult result) {
        renderer = new SimpleDialogRenderer();
        renderer.manager = this;
        this.desc = desc;
        this.buttons = buttons;
        this.result = result;
    }

    @Override
    public IScreenRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void onKey(KeyInput e) {
        if (e.type == InputType.PRESS && e.key == KeyInput.ENTER) {
            gui.closeManager();
            result.onButton(pos, buttons[pos]);
        }
        
        if (e.type == InputType.DOWN && e.key == KeyInput.LEFT) {
            if (pos<=0) {
                pos=buttons.length-1;
            } else {
                pos--;
            }
            gui.repaint();
        }
        
        if (e.type == InputType.DOWN && e.key == KeyInput.RIGHT) {
            if (pos>=buttons.length-1) {
                pos=0;
            } else {
                pos++;
            }
            gui.repaint();
        }
    }

    @Override
    public boolean showStatusBar() {
        return false;
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
