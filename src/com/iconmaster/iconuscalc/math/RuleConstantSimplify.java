
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.function.FunctionNegate;

/**
 *
 * @author iconmaster
 */
public class RuleConstantSimplify implements IRule {

	@Override
	public Element simplify(FunctionCallElement e) {
		if (e.fn instanceof FunctionNegate) {
			if (e.content[0] instanceof NumberElement) {
				return new NumberElement(-((NumberElement)e.content[0]).content);
			} else {
				return null;
			}
		}
		Element e1 = e.content[0];
		Element e2 = e.content[1];
		boolean changed = false;
		
		if (e1 instanceof FunctionCallElement) {
			e1 = Simplifier.simplify(((FunctionCallElement)e1)).content[0];
			changed = true;
		}
		
		if (e2 instanceof FunctionCallElement) {
			e2 = Simplifier.simplify(((FunctionCallElement)e2)).content[0];
			changed = true;
		}
		
		if (e1 instanceof NumberElement && e2 instanceof NumberElement) {
			try {
				return e.fn.execute(new Element[] {e1,e2})[0];
			} catch (IconusCalcException ex) {
				return null;
			}
		} else {
			if (changed) {
				return new FunctionCallElement(e.fn,new Element[] {e1,e2});
			} else {
				return null;
			}
		}
	}
	
}
