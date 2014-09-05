
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class RuleConstantSimplify implements IRule {

    @Override
    public Element simplify(FunctionCallElement e) {
        Element e1 = e.content[0];
        Element e2 = e.content[1];
        
        if (e1 instanceof FunctionCallElement) {
            e1 = Simplifier.simplify(((FunctionCallElement)e1)).content[0];
        }
        
        if (e2 instanceof FunctionCallElement) {
            e2 = Simplifier.simplify(((FunctionCallElement)e2)).content[0];
        }
        
        if (e1 instanceof NumberElement && e2 instanceof NumberElement) {
            try {
                return e.fn.execute(e.content)[0];
            } catch (IconusCalcException ex) {
                return null;
            }
        } else {
            ArrayList<Element> a = new ArrayList<>();
            return new FunctionCallElement(e.fn,new Element[] {e1,e2});
        }
    }
    
}
