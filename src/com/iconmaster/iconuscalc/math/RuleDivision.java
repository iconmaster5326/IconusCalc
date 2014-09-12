package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.function.FunctionDivide;
import com.iconmaster.iconuscalc.function.FunctionMultiply;

/**
 *
 * @author iconmaster
 */
public class RuleDivision implements IRule {

    @Override
    public Element simplify(FunctionCallElement e) {
        Element e1 = e.content[0];
        Element e2 = e.content[1];

        if (e2 instanceof FunctionCallElement && ((FunctionCallElement) e2).fn instanceof FunctionDivide) {
            Element a = ((FunctionCallElement) e2).content[0];
            Element b = ((FunctionCallElement) e2).content[1];
            Element c = e1;

            return new FunctionCallElement(new FunctionDivide(), new Element[]{new FunctionCallElement(new FunctionMultiply(), new Element[]{c, a}), b});
        }

        if (e1 instanceof FunctionCallElement && ((FunctionCallElement) e1).fn instanceof FunctionDivide) {
            Element a = ((FunctionCallElement) e1).content[0];
            Element b = ((FunctionCallElement) e1).content[1];
            Element c = e2;

            return new FunctionCallElement(new FunctionDivide(), new Element[]{b, new FunctionCallElement(new FunctionMultiply(), new Element[]{c, a})});
        }

        return null;
    }

}
