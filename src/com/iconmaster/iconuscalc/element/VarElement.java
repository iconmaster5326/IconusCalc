
package com.iconmaster.iconuscalc.element;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.function.IOperable;
import com.iconmaster.iconuscalc.function.OperationType;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public class VarElement extends Element implements IOperable {
    public final String content;
    
    public VarElement(String n) {
        content = n;
    }
    
    @Override
    public String getDisplayString() {
        return content;
    }
    
    @Override
    public String getStringCastString() {
        return content;
    }

    @Override
    public Element[] operate(OperationType type, Element operand, boolean reversed) throws IconusCalcException {
        return expressionCreationOperation(type,operand,reversed);
    }

    @Override
    public boolean canOperate(OperationType type, Element operand, boolean reversed) {
        return true;
    }
    
    @Override
    public void execute(EntryStack stack, Namespace ns, Window window) throws IconusCalcException {
        if (ns.getVar(content)!=null) {
            stack.push(ns.getVar(content).value);
        } else {
            super.execute(stack, ns, window);
        }
    }
}
