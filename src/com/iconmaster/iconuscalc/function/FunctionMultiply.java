
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.gui.KeyInput;

/**
 *
 * @author iconmaster
 */
public class FunctionMultiply extends FunctionOperator {
    @Override
    public OperationType getOp() {
        return OperationType.MUL;
    }

    @Override
    public String getName() {
        return "*";
    }

    @Override
    public boolean isCommandKey(KeyInput e) {
        return e.key=='*';
    }

    @Override
    public int getOrder() {
        return 2;
    }
    
    @Override
    public boolean argPosMatters() {
        return false;
    }
    
}