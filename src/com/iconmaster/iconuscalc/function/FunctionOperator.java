
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.Entry;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import com.iconmaster.iconuscalc.gui.KeyInput;

/**
 *
 * @author iconmaster
 */
public abstract class FunctionOperator extends Function implements IQuickCommand,IOperator {
    public KeyInput weNeverUseThisMemberButINeedTheAutoImport;

    @Override
    public Element[] execute(Element[] args) throws IconusCalcException {
        Element[] ret = new Element[0];
        
        if (args[1] instanceof IOperable && ((IOperable)args[1]).canOperate(getOp(), args[1], false)) {
            ret = ((IOperable)args[1]).operate(getOp(), args[0], false);
        } else {
            throw new IllegalArgumentTypeException();
        }
        
        return ret;
    }
    
    @Override
    public int getDefaultArgs() {
        return 2;
    }
    
    @Override
    public String getEntryString(Entry[] args) {
        String p1 = args[1].getAnswer().getDisplayString();
        if (args[1].getAnswer() instanceof FunctionCallElement) {
           Function fn = ((FunctionCallElement)args[1].getAnswer()).fn;
           if (fn instanceof IOperator && ((IOperator)fn).getOrder()>getOrder()) {
                p1="("+p1+")";
           }
        }
        String p2 = args[0].getAnswer().getDisplayString();
        if (args[0].getAnswer() instanceof FunctionCallElement) {
           Function fn = ((FunctionCallElement)args[0].getAnswer()).fn;
           if (fn instanceof IOperator && ((IOperator)fn).getOrder()>getOrder()) {
                p2="("+p2+")";
           }
        }
        return p1+getDisplayName()+p2;
    }
    
    public abstract OperationType getOp();
}
