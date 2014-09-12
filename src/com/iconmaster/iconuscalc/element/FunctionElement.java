
package com.iconmaster.iconuscalc.element;

import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.parse.CodeExecutor;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public class FunctionElement extends Element {
    private final Function content;
    private final int args;
    
    public FunctionElement(String n) {
        this(IconusCalc.getGlobalNamespace().getFunction(n));
    }
    
    public FunctionElement(Function n) {
        this(n,n.getDefaultArgs());
    }
    
    public FunctionElement(String n, int args) {
        this(IconusCalc.getGlobalNamespace().getFunction(n),args);
    }
    
    public FunctionElement(Function n, int args) {
        this.content = n;
        this.args = args;
    }
    
    public Function getContent() {
        return content;
    }
    
    @Override
    public String getDisplayString() {
        return content.getDisplayName();
    }
    
    @Override
    public void execute(EntryStack stack, Namespace ns, Window window) throws IconusCalcException {
        CodeExecutor.executeFunction(content, stack, ns, window, args);
    }
    
    @Override
    public void executeQuoting(EntryStack stack) {
        if (args>stack.size()) {
            super.executeQuoting(stack);
            return;
        }
        
        Element[] es = new Element[args];
        for (int i=0;i<args;i++) {
            es[i] = stack.pop();
        }
        stack.push(new FunctionCallElement(content,es));
    }
    
    @Override
    public boolean equals(Object other) {
        return other instanceof FunctionElement && ((FunctionElement)other).content.equals(this.content);
    }
    
    @Override
    public String getDataTypeName() {
        return "FUNC";
    }
}
