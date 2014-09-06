
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;

/**
 *
 * @author iconmaster
 */
public class RuleExpand implements IRule {

    @Override
    public Element simplify(FunctionCallElement e) {
        Element var = e.content[0];
        Element expr = e.content[1];
        
        if (var instanceof FunctionCallElement) {
            
        } else if (expr instanceof FunctionCallElement) {
            Element temp;
            temp = var;
            expr = var;
            var = temp;
        } else {
            return null;
        }
        
        

        return null;
    }
    
}
