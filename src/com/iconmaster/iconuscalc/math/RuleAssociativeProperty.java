
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionMultiply;

/**
 *
 * @author iconmaster
 */
public class RuleAssociativeProperty implements IRule {

    @Override
    public Element simplify(FunctionCallElement e) {
        Element e1 = e.content[0];
        Element e2 = e.content[1];
        
        if (e2 instanceof FunctionCallElement) {
            Element temp = e2;
            e2 = e1;
            e1 = temp;
        }
        
        if (e1 instanceof FunctionCallElement) {
            FunctionCallElement fne = (FunctionCallElement)e1;
            if (isValidFunction(fne.fn) && fne.fn.getClass() == e.fn.getClass()) {
                //(a+b)+c -> a+(b+c); a,b=fne.content[0,1]; c=e2
                FunctionCallElement ret2 = new FunctionCallElement(fne.fn,new Element[] {fne.content[0],e2});
                FunctionCallElement ret = new FunctionCallElement(e.fn,new Element[] {fne.content[1], Simplifier.simplify(ret2).content[0]});
                return ret;
            }
        } else {
            return null;
        }
        
        return null;
    }
    
    public boolean isValidFunction(Function fn) {
        return fn instanceof FunctionAdd || fn instanceof FunctionMultiply;
    }
    
}
