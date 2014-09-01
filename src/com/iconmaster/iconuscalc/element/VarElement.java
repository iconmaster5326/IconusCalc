
package com.iconmaster.iconuscalc.element;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionDivide;
import com.iconmaster.iconuscalc.function.FunctionMultiply;
import com.iconmaster.iconuscalc.function.FunctionSubtract;
import com.iconmaster.iconuscalc.function.IOperable;
import com.iconmaster.iconuscalc.function.OperationType;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class VarElement extends Element implements IOperable {
    private final String content;
    
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
}
