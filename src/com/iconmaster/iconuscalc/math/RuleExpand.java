
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionMultiply;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class RuleExpand implements IRule {

    @Override
    public Element simplify(FunctionCallElement e) {
        Element var = e.content[0];
        Element expr = e.content[1];
        
        if (expr instanceof FunctionCallElement) {
            
        } else if (var instanceof FunctionCallElement) {
            Element temp;
            temp = var;
            expr = var;
            var = temp;
        } else {
            return null;
        }
        
        ArrayList<Element> terms = RuleCollectTerms.getTerms(new FunctionAdd(), (FunctionCallElement) expr);
        
        FunctionCallElement ret = null;
        Element firste = null;
        
        for (Element term : terms) {
            FunctionCallElement op = new FunctionCallElement(new FunctionMultiply(), new Element[] {var,term});
            if (term!=null) {
                if (ret==null) {
                    if (firste==null) {
                        firste=term;
                    } else {
                        ret = new FunctionCallElement(new FunctionAdd(),new Element[] {firste,op});
                    }
                } else {
                    ret = new FunctionCallElement(new FunctionAdd(),new Element[] {ret,op});
                }
            }
        }

        return ret;
    }
    
}
