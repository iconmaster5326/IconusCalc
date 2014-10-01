
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.function.FunctionDivide;
import com.iconmaster.iconuscalc.function.FunctionSubtract;

/**
 *
 * @author iconmaster
 */
public class RuleCancelTerms implements IRule {

	@Override
	public Element simplify(FunctionCallElement e) {
		Element e1 = e.content[0];
		Element e2 = e.content[1];
		
		if (e.fn instanceof FunctionSubtract && e1.equals(e2)) {
			return new NumberElement(0);
		}
		
		if (e.fn instanceof FunctionDivide && e1.equals(e2)) {
			return new NumberElement(1);
		}

		return null;
	}
	
}
