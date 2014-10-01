package com.iconmaster.iconuscalc.manager.dialog;

/**
 *
 * @author iconmaster
 */
public class DialogEntry {
	public EntryType type;
	public String label;
	public Object value;

	public DialogEntry(EntryType type, String label, Object value) {
		this.type = type;
		this.label = label;
		this.value = value;
	}
	
	public DialogEntry(EntryType type, String label) {
		this(type,label,null);
	}
	
	public int getSize() {
		return 1;
	}
}
