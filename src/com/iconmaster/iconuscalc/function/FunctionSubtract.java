
package com.iconmaster.iconuscalc.function;

import java.awt.event.KeyEvent;

/**
 *
 * @author iconmaster
 */
public class FunctionSubtract extends FunctionOperator {
    @Override
    public OperationType getOp() {
        return OperationType.SUB;
    }

    @Override
    public String getName() {
        return "-";
    }

    @Override
    public boolean isCommandKey(KeyEvent e) {
        return e.getKeyChar()=='-';
    }

    @Override
    public int getOrder() {
        return 3;
    }
    
}
