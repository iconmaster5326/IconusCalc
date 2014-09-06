
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionNegate;
import com.iconmaster.iconuscalc.function.FunctionSubtract;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class RuleCollectTerms implements IRule {

    @Override
    public Element simplify(FunctionCallElement e) {
        Function fn = e.fn instanceof FunctionSubtract ? new FunctionAdd() : e.fn;
        ArrayList<Element> a = new ArrayList<>();
        
        iterate(a,e,fn);
        
        FunctionCallElement ret = null;
        Element firste = null;
        
        NumberElement constant = null;
        ArrayList<Element> terms = new ArrayList<>();
        
        for (Element ae : a) {
  
            if (ae instanceof NumberElement) {
                if (constant==null) {
                    constant = (NumberElement) ae;
                } else {
                    try {
                        constant = (NumberElement) fn.execute(new Element[] {constant,ae})[0];
                    } catch (IconusCalcException ex) {
                        return null;
                    }
                }
            } else {
                terms.add(ae);
            }
        }
        
        for (Element ae : terms) {
            if (ret==null) {
                if (firste==null) {
                    firste=ae;
                } else {
                    ret = new FunctionCallElement(fn,new Element[] {firste,ae});
                }
            } else {
                ret = new FunctionCallElement(fn,new Element[] {ret,ae});
            }
        }
        
        if (constant!=null) {
            if (ret==null) {
                ret = new FunctionCallElement(fn,new Element[] {firste,constant});
            } else {
                ret = new FunctionCallElement(fn,new Element[] {ret,constant});
            }
        }

        return ret;
    }
    
    public void iterate(ArrayList<Element> a, Element e, Function fn) {
        if (e==null) {
            return;
        }
        if (e instanceof FunctionCallElement && ((FunctionCallElement)e).fn instanceof FunctionSubtract && fn instanceof FunctionAdd) {
            iterate(a,Simplifier.simplify(new FunctionCallElement(new FunctionNegate(), new Element[] {((FunctionCallElement)e).content[0]})).content[0],fn);
            iterate(a,((FunctionCallElement)e).content[1],fn);
        } else if (e instanceof FunctionCallElement && ((FunctionCallElement)e).fn.getClass() == fn.getClass()) {
            iterate(a,((FunctionCallElement)e).content[0],fn);
            iterate(a,((FunctionCallElement)e).content[1],fn);
        } else {
            a.add(e);
        }
    }
    
}
