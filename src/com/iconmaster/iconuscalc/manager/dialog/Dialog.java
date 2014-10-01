package com.iconmaster.iconuscalc.manager.dialog;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author iconmaster
 */
public class Dialog {
	public ArrayList<DialogEntry> entries = new ArrayList<>();
	
	public Dialog(DialogEntry... forms) {
		entries.addAll(Arrays.asList(forms));
	}
	
	public Dialog(ArrayList<DialogEntry> entries) {
		this.entries = entries;
	}
}
