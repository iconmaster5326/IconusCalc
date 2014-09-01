
package com.iconmaster.iconuscalc.element;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.IOperable;
import com.iconmaster.iconuscalc.function.OperationType;
import com.iconmaster.iconuscalc.parse.CodeExecutor;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public class FunctionCallElement extends Element implements IOperable {
    public final Function fn;
    public final Element[] content;
    
    public FunctionCallElement(Function fn,Element[] n) {
        this.fn = fn;
        this.content = n;
    }
    
    @Override
    public String getDisplayString() {
        Entry[] a = new Entry[content.length];
        for (int i=0;i<a.length;i++) {
            a[i] = new Entry(content[i].getDisplayString(),content[i]);
        }
        
        return fn.getEntryString(a);
    }
    
    @Override
    public void execute(EntryStack stack, Namespace ns) throws IconusCalcException {
        for (int i=content.length-1;i>=0;i--) {
            stack.push(content[i]);
        }
        CodeExecutor.executeFunction(fn, stack, ns, content.length);
    }

    @Override
    public Element[] operate(OperationType type, Element operand, boolean reversed) throws IconusCalcException {
        Element[] results = fn.execute(content);
        if (results[results.length-1] instanceof IOperable) {
            return ((IOperable)results[results.length-1]).operate(type, operand, reversed);
        } else {
            throw new IllegalArgumentTypeException();
        }
        //return expressionCreationOperation(type,operand,reversed);
    }

    @Override
    public boolean canOperate(OperationType type, Element operand, boolean reversed) {
        return true;
    }
}
