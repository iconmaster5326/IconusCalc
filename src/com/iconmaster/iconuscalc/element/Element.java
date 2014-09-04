
package com.iconmaster.iconuscalc.element;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.FunctionNegate;
import com.iconmaster.iconuscalc.function.OperationType;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.EntryStack;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public abstract class Element {
    public abstract String getDisplayString();
    
    public void execute(EntryStack stack, Namespace ns, Window window) throws IconusCalcException {
        stack.push(new Entry(this.getDisplayString(),this));
    }
    
    public String getStringCastString() {
        return getDisplayString();
    }

    public void executeQuoting(EntryStack stack) {
        stack.push(this);
    }
    
    public Element[] expressionCreationOperation(OperationType type, Element operand, boolean reversed) throws IconusCalcException {
        if (type==OperationType.NEGATE) {
            ArrayList<Element> a = new ArrayList<>();
            a.add(this);
            a.add(new FunctionElement(new FunctionNegate()));
            return new Element[] {new ExpressionElement(a)};
        }
        
        ArrayList<Element> a = new ArrayList<>();
        
        if (reversed) {
            if (operand instanceof ExpressionElement) {
                for (Element e : ((ExpressionElement)operand).content) {
                    a.add(e);
                }
            } else {
                a.add(operand);
            }
            a.add(this);
        } else {
            a.add(this);
            if (operand instanceof ExpressionElement) {
                for (Element e : ((ExpressionElement)operand).content) {
                    a.add(e);
                }
            } else {
                a.add(operand);
            }
        }
        a.add(new FunctionElement(Function.getOperationFunction(type)));
        
        return new Element[] {new ExpressionElement(a)};
    }
}
