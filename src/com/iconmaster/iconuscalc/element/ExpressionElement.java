
package com.iconmaster.iconuscalc.element;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionDivide;
import com.iconmaster.iconuscalc.function.FunctionMultiply;
import com.iconmaster.iconuscalc.function.FunctionSubtract;
import com.iconmaster.iconuscalc.function.IOperable;
import com.iconmaster.iconuscalc.function.OperationType;
import com.iconmaster.iconuscalc.parse.CodeExecutor;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author iconmaster
 */
public class ExpressionElement extends Element implements IOperable {
    public final Element[] content;
    
    public ExpressionElement(ArrayList<Element> n) {
        content = CodeExecutor.executeQuoting(n);
    }
    
    @Override
    public String getDisplayString() {
        return "'"+getStringCastString()+"'";
    }
    
    @Override
    public String getStringCastString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<content.length;i++) {
            Element e = content[i];
            sb.append(e.getDisplayString());
            if (i!=content.length-1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    @Override
    public Element[] operate(OperationType type, Element operand, boolean reversed) throws IconusCalcException {
        ArrayList<Element> a = new ArrayList<>();
        
        if (!reversed) {
            a.addAll(Arrays.asList(this.content));
        }
        if (operand instanceof ExpressionElement) {
            a.addAll(Arrays.asList(((ExpressionElement)operand).content));
        } else {
            a.add(operand);
        }
        if (reversed) {
            a.addAll(Arrays.asList(this.content));
        }
        
        a.add(new FunctionElement(Function.getOperationFunction(type)));
        
        return new Element[] {new ExpressionElement(a)};
    }

    @Override
    public boolean canOperate(OperationType type, Element operand, boolean reversed) {
        return true;
    }
}
