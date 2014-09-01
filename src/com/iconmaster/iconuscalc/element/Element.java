
package com.iconmaster.iconuscalc.element;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionDivide;
import com.iconmaster.iconuscalc.function.FunctionMultiply;
import com.iconmaster.iconuscalc.function.FunctionSubtract;
import com.iconmaster.iconuscalc.function.OperationType;
import com.iconmaster.iconuscalc.util.EntryStack;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public abstract class Element {
    public abstract String getDisplayString();
    
    public void execute(EntryStack stack, Namespace ns) throws IconusCalcException {
        stack.push(new Entry(this.getDisplayString(),this));
    }
    
    public String getStringCastString() {
        return getDisplayString();
    }

    public void executeQuoting(EntryStack stack) {
        stack.push(this);
    }
    
    public Element[] expressionCreationOperation(OperationType type, Element operand, boolean reversed) throws IconusCalcException {
        ArrayList<Element> a = new ArrayList<>();
        
        if (reversed) {
            a.add(operand);
            a.add(this);
        } else {
            a.add(this);
            a.add(operand);
        }
        a.add(new FunctionElement(Function.getOperationFunction(type)));
        
        return new Element[] {new ExpressionElement(a)};
    }
}
