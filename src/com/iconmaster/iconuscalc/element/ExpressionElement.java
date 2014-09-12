package com.iconmaster.iconuscalc.element;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.function.Function;
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

    public ExpressionElement(Element n) {
        ArrayList<Element> a = new ArrayList<>();
        a.add(n);
        content = CodeExecutor.executeQuoting(a);
    }

    @Override
    public String getDisplayString() {
        return "'" + getStringCastString() + "'";
    }

    @Override
    public String getStringCastString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < content.length; i++) {
            Element e = content[i];
            sb.append(e.getDisplayString());
            if (i != content.length - 1) {
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
            a.addAll(Arrays.asList(((ExpressionElement) operand).content));
        } else if (operand != null) {
            a.add(operand);
        }
        if (reversed) {
            a.addAll(Arrays.asList(this.content));
        }

        a.add(new FunctionElement(Function.getOperationFunction(type)));

        return new Element[]{new ExpressionElement(a)};
    }

    @Override
    public boolean canOperate(OperationType type, Element operand, boolean reversed) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FunctionCallElement && this.content.length == ((ExpressionElement) other).content.length)) {
            return false;
        }
        for (int i = 0; i < this.content.length; i++) {
            if (!((ExpressionElement) other).content[i].equals(this.content[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getDataTypeName() {
        return "EXPR";
    }
}
