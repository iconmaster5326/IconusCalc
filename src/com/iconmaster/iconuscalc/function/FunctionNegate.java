
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.Entry;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import java.awt.event.KeyEvent;

/**
 *
 * @author iconmaster
 */
public class FunctionNegate extends Function implements IQuickCommand {
    public static final OperationType op = OperationType.NEGATE;

    @Override
    public Element[] execute(Element[] args) throws IconusCalcException {
        Element[] ret = new Element[0];
        
        if (args[0] instanceof IOperable && ((IOperable)args[0]).canOperate(op, null, false)) {
            ret = ((IOperable)args[0]).operate(op, null, false);
        } else {
            throw new IllegalArgumentTypeException();
        }
        
        return ret;
    }
    
    @Override
    public int getDefaultArgs() {
        return 1;
    }

    @Override
    public String getName() {
        return "NEG";
    }
    
    @Override
    public String getDisplayName() {
        return "-";
    }

    @Override
    public boolean isCommandKey(KeyEvent e) {
        return e.getKeyChar()=='_';
    }
    
    @Override
    public String getEntryString(Entry[] args) {
        return "-"+args[0].getAnswer().getDisplayString();
    }
}
