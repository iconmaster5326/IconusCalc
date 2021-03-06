
package com.iconmaster.iconuscalc.gui;

import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.manager.ErrorManager;
import com.iconmaster.iconuscalc.manager.IApplication;
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
	private Namespace dir = IconusCalc.getGlobalNamespace();
	
	public Window(IControlManager manager) {
		IconusCalc.windows.add(this);
		gui = new MainGui();
		gui.window = this;
		addManager(manager);
		nameWindow(manager);
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
		manager.setParent(this);
		manager.getRenderer().setParent(this);
	}
	
	public void closeManager() {
		managers.pop();
		repaint();
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
	
	public Namespace getNamspace() {
		return dir;
	}
	
	public void setNamespace(Namespace ns) {
		dir = ns;
		repaint();
	}

	public void close() {
		IconusCalc.windows.remove(this);
		IconusCalc.renameAll();
		gui.dispose();
	}

	public void nameWindow(IControlManager manager) {
		if (manager instanceof IApplication) {
			gui.setTitle("IconusCalc - "+((IApplication)manager).getAppName()+" ["+(IconusCalc.windows.indexOf(this)+1)+"]");
		} else {
			gui.setTitle("IconusCalc ["+(IconusCalc.windows.indexOf(this)+1)+"]");
		}
	}
}
