package com.iconmaster.iconuscalc.file;

import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public interface IFileListener {
	public static enum ChangeType {
		CHANGE,DELETE,CREATE
	}
	
	static ArrayList<IFileListener> listeners = new ArrayList<>();
	
	public static void registerListener(IFileListener listener) {
		listeners.add(listener);
	}
	
	public static void fireEvent(ChangeType type, Namespace ns, Object actedOn) {
		for (IFileListener listener : listeners) {
			listener.onFileChange(type, ns, actedOn);
		}
	}
	
	public void onFileChange(ChangeType type,Namespace ns, Object actedOn);
}
