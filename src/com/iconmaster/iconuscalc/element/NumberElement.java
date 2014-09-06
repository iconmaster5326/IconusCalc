
package com.iconmaster.iconuscalc.element;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import com.iconmaster.iconuscalc.function.IOperable;
import com.iconmaster.iconuscalc.function.OperationType;
import com.iconmaster.iconuscalc.util.StringUtils;
import java.util.Objects;

/**
 *
 * @author iconmaster
 */
public class NumberElement extends Element implements IOperable {
    public final Double content;
    
    public NumberElement(double n) {
        content = n;
    }
    
    public NumberElement(int n) {
        content = (double) n;
    }
    
    public Double getContent() {
        return content;
    }
    
    @Override
    public String getDisplayString() {
        return StringUtils.renderNumber(content);
    }

    @Override
    public Element[] operate(OperationType type, Element operand, boolean reversed)  throws IconusCalcException {
        if (type==OperationType.NEGATE) {
            return new NumberElement[] {new NumberElement(-this.content)};
        }
        
        if (operand instanceof NumberElement) {
            Double c1 = this.content;
            Double c2 = ((NumberElement)operand).content;
            switch (type) {
                case ADD:
                    return new NumberElement[] {new NumberElement((reversed?c2:c1)+(reversed?c1:c2))};
                case SUB:
                    return new NumberElement[] {new NumberElement((reversed?c2:c1)-(reversed?c1:c2))};
                case MUL:
                    return new NumberElement[] {new NumberElement((reversed?c2:c1)*(reversed?c1:c2))};
                case DIV:
                    return new NumberElement[] {new NumberElement((reversed?c2:c1)/(reversed?c1:c2))};
                case POW:
                    return new NumberElement[] {new NumberElement(Math.pow((reversed?c2:c1),(reversed?c1:c2)))};
            }
            return null;
        } else if (operand instanceof IOperable) {
            return ((IOperable)operand).operate(type, this, !reversed);
        } else {
            throw new IllegalArgumentTypeException();
        }
    }

    @Override
    public boolean canOperate(OperationType type, Element operand, boolean reversed) {
        if (type==OperationType.NEGATE) {
            return true;
        }
        if (reversed && (type==OperationType.SUB || type==OperationType.DIV)) {
            return false;
        }
        return operand instanceof NumberElement || operand instanceof IOperable && ((IOperable)operand).canOperate(type, this, !reversed);
    }
    
    @Override
    public String toString() {
        return content.toString();
    }
    
    @Override
    public boolean equals(Object other) {
        return other instanceof NumberElement && ((NumberElement)other).content.equals(this.content);
    }
}
