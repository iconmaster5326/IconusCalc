
package com.iconmaster.iconuscalc.file;

import com.iconmaster.iconuscalc.element.Element;

/**
 *
 * @author iconmaster
 */
public class Variable {
	public final String name;
	public Element value;
	
	public Variable(String name) {
		this(name,null);
	}
	
	public Variable(String name, Element value) {
		this.name = name;
		this.value = value;
	}
}
