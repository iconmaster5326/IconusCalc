
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionNegate;

/**
 *
 * @author iconmaster
 */
public class RuleUndoSubtract implements IRule {

    @Override
    public Element simplify(FunctionCallElement e) {
        Element e1 = e.content[0];
        Element e2 = e.content[1];
        
//        if (e1 instanceof FunctionCallElement) {
//            e1 = Simplifier.simplify(((FunctionCallElement)e1)).content[0];
//        }
//        
//        if (e2 instanceof FunctionCallElement) {
//            e2 = Simplifier.simplify(((FunctionCallElement)e2)).content[0];
//        }
        

        return new FunctionCallElement(new FunctionAdd(),new Element[] {new FunctionCallElement(new FunctionNegate(),new Element[] {e1}),e2});
    }
    
}
