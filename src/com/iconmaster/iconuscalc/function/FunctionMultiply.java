
package com.iconmaster.iconuscalc.function;

import java.awt.event.KeyEvent;

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
    public boolean isCommandKey(KeyEvent e) {
        return e.getKeyChar()=='*';
    }

    @Override
    public int getOrder() {
        return 2;
    }
    
}