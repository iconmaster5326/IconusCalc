
package com.iconmaster.iconuscalc.gui;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.manager.ErrorManager;
import com.iconmaster.iconuscalc.manager.IControlManager;
import java.util.Stack;

/**
 *
 * @author iconmaster
 */
public class Window {
    private final Stack<IControlManager> managers = new Stack<>();
    private IconusCalcException error;
    private final MainGui gui;
    
    public Window(IControlManager manager) {
        gui = new MainGui();
        gui.window = this;
        addManager(manager);
        gui.setVisible(true);
    }
    
    public void repaint() {
        gui.repaint();
    }
    
    public Stack<IControlManager> getManagers() {
        return (Stack<IControlManager>) managers.clone();
    }
    
    public IControlManager currentManager() {
        return managers.peek();
    }

    public void addManager(IControlManager manager) {
        managers.push(manager);
        manager.getRenderer().setParent(this);
    }
    
    public void closeManager() {
        managers.pop();
    }

    public void displayError(IconusCalcException ex) {
        this.error = ex;
        this.addManager(new ErrorManager());
    }

    public IconusCalcException getError() {
        return error;
    }

    public void clearError() {
        error = null;
    }
    
    public void onKey(KeyInput e) {
        if (getError()!= null && e.type == InputType.PRESS) {
                clearError();
                return;
            }
            if (currentManager()!=null) {
                currentManager().onKey(e);
            }
        }
}
