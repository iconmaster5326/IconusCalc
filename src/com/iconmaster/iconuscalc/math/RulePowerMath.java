
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.function.operator.FunctionMultiply;
import com.iconmaster.iconuscalc.function.operator.FunctionPower;

/**
 *
 * @author iconmaster
 */
public class RulePowerMath implements IRule {

	@Override
	public Element simplify(FunctionCallElement e) {
		Element e1 = e.content[0];
		Double e1f = null;
		Element e1v = null;
		Element e2 = e.content[1];
		Double e2f = null;
		Element e2v = null;
		
		if (e1 instanceof FunctionCallElement && ((FunctionCallElement)e1).fn instanceof FunctionPower) {
			if (((FunctionCallElement)e1).content[0] instanceof NumberElement) {
				e1v = (((FunctionCallElement)e1).content[1]);
				e1f = ((NumberElement)(((FunctionCallElement)e1).content[0])).content;
			} else {
				return null;
			}
		} else {
			e1f = 1d;
			e1v = e1;
		}
		
		if (e2 instanceof FunctionCallElement && ((FunctionCallElement)e2).fn instanceof FunctionPower) {
			if (((FunctionCallElement)e2).content[0] instanceof NumberElement) {
				e2v = (((FunctionCallElement)e2).content[1]);
				e2f = ((NumberElement)(((FunctionCallElement)e2).content[0])).content;
			} else {
				return null;
			}
		} else {
			e2f = 1d;
			e2v = e2;
		}
		
		if (e1v.equals(e2v)) {
			return new FunctionCallElement(new FunctionPower(),new Element[] {new NumberElement(e.fn instanceof FunctionMultiply?e2f+e1f:e2f-e1f),e1v});
		}

		return null;
	}
	
}
