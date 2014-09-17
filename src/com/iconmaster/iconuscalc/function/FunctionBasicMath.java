
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.ExpressionElement;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public abstract class FunctionBasicMath extends Function {

    @Override
    public Element[] execute(Element[] args, EntryStack stack, Namespace ns, Window window, int need) throws IconusCalcException {
        if (args[0] instanceof NumberElement) {
            return new Element[] {new NumberElement(doMath(((NumberElement)args[0]).content))};
        } else {
            return new Element[] {new ExpressionElement(new FunctionCallElement(this,new Element[] {args[0]}))};
        }
    }
    
    @Override
    public int getDefaultArgs() {
        return 1;
    }
    
    public abstract double doMath(double number);
}
