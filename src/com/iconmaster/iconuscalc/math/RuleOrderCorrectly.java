
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.element.VarElement;

/**
 *
 * @author iconmaster
 */
public class RuleOrderCorrectly implements IRule {
    boolean mulMode = false;

    RuleOrderCorrectly(boolean b) {
        mulMode = b;
    }

    @Override
    public Element simplify(FunctionCallElement e) {
        Element e1 = e.content[0];
        Element e2 = e.content[1];
        
        if (!mulMode && (e1 instanceof VarElement || e1 instanceof FunctionCallElement) && e2 instanceof NumberElement) {
            return new FunctionCallElement(e.fn,new Element[] {e2,e1});
        }
        
        if (mulMode && (e2 instanceof VarElement || e2 instanceof FunctionCallElement) && e1 instanceof NumberElement) {
            return new FunctionCallElement(e.fn,new Element[] {e2,e1});
        }
        
        return null;
    }
    
}
