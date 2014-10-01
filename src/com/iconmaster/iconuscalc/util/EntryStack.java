
package com.iconmaster.iconuscalc.util;


import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.Entry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iconmaster
 */
public class EntryStack {
	private final ArrayList<Element> answers = new ArrayList<>();
	private final ArrayList<String> entries = new ArrayList<>();
	
	public void push(Element item) {
		push("",item);
	}
	
	public void push(Entry item) {
		push(item.getEntry(),item.getAnswer());
	}
	
	public void push(String entry, Element answer) {
		answers.add(0, answer);
		entries.add(0, entry);
	}
	
	public Element pop() {
		if (answers.isEmpty()) {return null;}
		return popEntry().getAnswer();
	}
	
	public Entry popEntry() {
		if (answers.isEmpty()) {return null;}
		return new Entry(entries.remove(0),answers.remove(0));
	}
	
	public List<Element> toList() {
		return (List<Element>)answers.clone();
	}
	
	public List<Entry> toEntryList() {
		ArrayList<Entry> out = new ArrayList<>();
		for (int i=size()-1;i>=0;i--) {
			out.add(0,new Entry(entries.get(i),answers.get(i)));
		}
		return out;
	}
	
	public int size() {
		return answers.size();
	}

	public void dup() {
		if (answers.isEmpty()) {return;}
		push(popEntry());
	}

	public Element peek() {
		if (answers.isEmpty()) {return null;}
		return answers.get(0);
	}
	
	public Entry peekEntry() {
		if (entries.isEmpty()) {return null;}
		return new Entry(entries.get(0),answers.get(0));
	}
	
	public void setEntry(int index, String entry) {
		entries.set(index, entry);
	}

	public void clear() {
		answers.clear();
		entries.clear();
	}
}
