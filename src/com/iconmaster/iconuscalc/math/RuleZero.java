
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionDivide;
import com.iconmaster.iconuscalc.function.FunctionMultiply;
import com.iconmaster.iconuscalc.function.FunctionPower;
import com.iconmaster.iconuscalc.function.FunctionSubtract;

/**
 *
 * @author iconmaster
 */
public class RuleZero implements IRule {

	@Override
	public Element simplify(FunctionCallElement e) {
		Element e1 = e.content[0];
		Element e2 = e.content[1];
		
		if (e.fn instanceof FunctionAdd || e.fn instanceof FunctionSubtract){
			if (e1 instanceof NumberElement && ((NumberElement)e1).content==0d) {
				return e2;
			} else if (e2 instanceof NumberElement && ((NumberElement)e2).content==0d) {
				return e1;
			}
		}
		
		else if (e.fn instanceof FunctionMultiply) {
			if ((e1 instanceof NumberElement && ((NumberElement)e1).content==0d) || (e2 instanceof NumberElement && ((NumberElement)e2).content==0d)) {
				return new NumberElement(0);
			}
		}
		
		else if (e.fn instanceof FunctionDivide) {
			if (e2 instanceof NumberElement && ((NumberElement)e2).content==0d) {
				return new NumberElement(0);
			}
		}
		
		else if (e.fn instanceof FunctionPower) {
			if (e1 instanceof NumberElement && ((NumberElement)e1).content==0d) {
				return new NumberElement(1);
			}
		}

		return null;
	}
	
}
