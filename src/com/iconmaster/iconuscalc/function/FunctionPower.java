
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.gui.KeyInput;

/**
 *
 * @author iconmaster
 */
public class FunctionPower extends FunctionOperator {
    @Override
    public OperationType getOp() {
        return OperationType.POW;
    }

    @Override
    public String getName() {
        return "^";
    }

    @Override
    public boolean isCommandKey(KeyInput e) {
        return e.key=='^';
    }

    @Override
    public int getOrder() {
        return 1;
    }
    
}