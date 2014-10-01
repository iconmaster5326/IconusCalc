
package com.iconmaster.iconuscalc.element;

/**
 *
 * @author iconmaster
 */
public class Entry {
	private String entry;
	private Element answer;
	
	public Entry(String ent, Element ans) {
		this.entry = ent;
		this.answer = ans;
	}

	public String getEntry() {
		return entry;
	}
	
	public Element getAnswer() {
		return answer;
	}
}
