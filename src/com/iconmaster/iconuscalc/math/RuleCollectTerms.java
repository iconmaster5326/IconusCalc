
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
    public class IntWrap {

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 89 * hash + this.v;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final IntWrap other = (IntWrap) obj;
            return this.v == other.v;
        }
        public int v;

        private IntWrap(int i) {
            this.v = i;
        }
    }

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
        
        ArrayList<IntWrap> todel = new ArrayList<>();
        for (int i=0;i<terms.size();i++) {
            for (int j=0;j<terms.size();j++) {
                if (terms.get(j) instanceof FunctionCallElement && ((FunctionCallElement)terms.get(j)).fn instanceof FunctionNegate) {
                    Element ne = ((FunctionCallElement)terms.get(j)).content[0];
                    if (terms.get(i).equals(ne)) {
                        if (!todel.contains(new IntWrap(i))) {
                            todel.add(new IntWrap(i));
                        }
                        if (!todel.contains(new IntWrap(j))) {
                            todel.add(new IntWrap(j));
                        }
                    }
                }
            }
        }
        
        for (IntWrap i : todel) {
            terms.set(i.v,null);
        }
        
        for (Element ae : terms) {
            if (ae!=null) {
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
        }
        
        Element ret2;
        if (ret==null) {
            ret2 = firste;
        } else {
            ret2 = ret;
        }
        
        if (constant!=null) {
            if (ret2==null) {
                ret2 = constant;
            } else {
                ret2 = new FunctionCallElement(fn,new Element[] {ret2,constant});
            }
        }

        return ret2;
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
