
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.function.operator.FunctionDivide;
import com.iconmaster.iconuscalc.function.operator.FunctionMultiply;
import com.iconmaster.iconuscalc.util.MathUtils;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class RuleDivisionCancel implements IRule {

	@Override
	public Element simplify(FunctionCallElement e) {
		Element e1 = e.content[0];
		Element e2 = e.content[1];
		
		
		ArrayList<Element> f1;
		if (e1 instanceof FunctionCallElement) {
			f1 = RuleCollectTerms.getTerms(new FunctionMultiply(), (FunctionCallElement) e1);
		} else {
			f1 = new ArrayList<>();
			f1.add(e1);
		}
		
		ArrayList<Element> f2;
		if (e2 instanceof FunctionCallElement) {
			f2 = RuleCollectTerms.getTerms(new FunctionMultiply(), (FunctionCallElement) e2);
		} else {
			f2 = new ArrayList<>();
			f2.add(e2);
		}
		
		for (int i=0;i<f1.size();i++) {
			for (int j=0;j<f2.size();j++) {
				if (f1.get(i)!=null && f1.get(i).equals(f2.get(j))) {
					f1.set(i,null);
					f2.set(j,null);
				} else if (f1.get(i) instanceof NumberElement && f2.get(j) instanceof NumberElement && ((NumberElement)f2.get(j)).content.intValue()==((NumberElement)f2.get(j)).content && ((NumberElement)f1.get(i)).content.intValue()==((NumberElement)f1.get(i)).content) {
					int[] facts = MathUtils.reduce(((NumberElement)f2.get(j)).content.intValue(),((NumberElement)f1.get(i)).content.intValue());
					if (facts!=null) {
						f1.set(i,new NumberElement(facts[1]));
						f2.set(j,new NumberElement(facts[0]));
					}
				}
			}
		}
		Element ret1 = null;
		Element firste = null;
		
		for (Element ae : f1) {
			if (ae!=null) {
				if (ret1==null) {
					if (firste==null) {
						firste=ae;
					} else {
						ret1 = new FunctionCallElement(new FunctionMultiply(),new Element[] {firste,ae});
					}
				} else {
					ret1 = new FunctionCallElement(new FunctionMultiply(),new Element[] {ret1,ae});
				}
			}
		}
		
		Element ret1f;
		if (ret1==null) {
			ret1f = firste;
		} else {
			ret1f = ret1;
		}
		
		Element ret2 = null;
		firste = null;
		
		for (Element ae : f2) {
			if (ae!=null) {
				if (ret2==null) {
					if (firste==null) {
						firste=ae;
					} else {
						ret2 = new FunctionCallElement(new FunctionMultiply(),new Element[] {firste,ae});
					}
				} else {
					ret2 = new FunctionCallElement(new FunctionMultiply(),new Element[] {ret2,ae});
				}
			}
		}
		
		Element ret2f;
		if (ret2==null) {
			ret2f = firste;
		} else {
			ret2f = ret2;
		}
		
		if (ret1f==null) {
			ret1f = new NumberElement(1);
		}
		
		if (ret2f==null) {
			ret2f = new NumberElement(1);
		}

		return new FunctionCallElement(new FunctionDivide(), new Element[] {ret1f,ret2f});
	}
	
}
