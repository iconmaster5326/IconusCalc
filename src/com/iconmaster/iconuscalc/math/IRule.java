
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;

/**
 *
 * @author iconmaster
 */
public interface IRule {
	public Element simplify(FunctionCallElement e);
}
