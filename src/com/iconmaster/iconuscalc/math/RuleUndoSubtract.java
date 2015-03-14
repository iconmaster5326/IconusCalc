
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.function.math.FunctionNegate;
import com.iconmaster.iconuscalc.function.operator.FunctionSubtract;

/**
 *
 * @author iconmaster
 */
public class RuleUndoSubtract implements IRule {
	private boolean mode;
	
	public RuleUndoSubtract(boolean mode) {
		this.mode = mode;
	}

	@Override
	public Element simplify(FunctionCallElement e) {
		Element e1 = e.content[0];
		Element e2 = e.content[1];
		boolean changed = false;
		
//		if (e1 instanceof FunctionCallElement) {
//			e1 = Simplifier.simplify(((FunctionCallElement)e1)).content[0];
//			changed  = true;
//		}
//		
//		if (e2 instanceof FunctionCallElement) {
//			e2 = Simplifier.simplify(((FunctionCallElement)e2)).content[0];
//			changed  = true;
//		}
		if (mode) {
			if (e1 instanceof NumberElement && ((NumberElement)e1).content<0) {
				e1 = new NumberElement(-((NumberElement)e1).content);
				changed  = true;
			}

			if (e1 instanceof FunctionCallElement && ((FunctionCallElement)e1).fn instanceof FunctionNegate) {
				e1 = ((FunctionCallElement)e1).content[0];
				changed  = true;
			}

			if (changed) {
				return new FunctionCallElement(new FunctionSubtract(),new Element[] {e1,e2});
			} else {
				return null;
			}
		} else {
			if (e1 instanceof NumberElement) {
				return null;
			}

			if (e1 instanceof FunctionCallElement && ((FunctionCallElement)e1).fn instanceof FunctionNegate) {
				e1 = ((FunctionCallElement)e1).content[0];
				changed  = true;
			}

			if (changed) {
				return new FunctionCallElement(new FunctionSubtract(),new Element[] {e2,e1});
			} else {
				return null;
			}
		}
	}
	
}
