
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.function.FunctionDivide;
import com.iconmaster.iconuscalc.function.FunctionMultiply;
import com.iconmaster.iconuscalc.function.FunctionPower;

/**
 *
 * @author iconmaster
 */
public class RuleOne implements IRule {

	@Override
	public Element simplify(FunctionCallElement e) {
		Element e1 = e.content[0];
		Element e2 = e.content[1];
		
		if (e.fn instanceof FunctionMultiply){
			if (e1 instanceof NumberElement && ((NumberElement)e1).content==1d) {
				return e2;
			} else if (e2 instanceof NumberElement && ((NumberElement)e2).content==1d) {
				return e1;
			}
		}
		
		else if (e.fn instanceof FunctionDivide) {
			if (e1 instanceof NumberElement && ((NumberElement)e1).content==1d) {
				return e2;
			}
		}
		
		else if (e.fn instanceof FunctionPower) {
			if (e1 instanceof NumberElement && ((NumberElement)e1).content==1d) {
				return e2;
			}
		}

		return null;
	}
	
}
