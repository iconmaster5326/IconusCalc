
package com.iconmaster.iconuscalc.function;

import java.awt.event.KeyEvent;

/**
 *
 * @author iconmaster
 */
public class FunctionAdd extends FunctionOperator {
    @Override
    public OperationType getOp() {
        return OperationType.ADD;
    }

    @Override
    public String getName() {
        return "+";
    }

    @Override
    public boolean isCommandKey(KeyEvent e) {
        return e.getKeyChar()=='+';
    }

    @Override
    public int getOrder() {
        return 3;
    }
    
}
