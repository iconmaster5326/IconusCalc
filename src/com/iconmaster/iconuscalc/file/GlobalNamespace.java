
package com.iconmaster.iconuscalc.file;

import com.iconmaster.iconuscalc.function.*;

import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public class GlobalNamespace extends Namespace {
    public final HashMap<String,Function> functions = new HashMap<>();
        
    public void addFunction(Function fn) {
        functions.put(fn.getName().toUpperCase(), fn);
    }
    
    public Function getFunction(String name) {
        return functions.get(name.toUpperCase());
    }
    
    public static GlobalNamespace createGlobalNamespace() {
        GlobalNamespace ns = new GlobalNamespace();
        
        ns.addFunction(new FunctionAdd());
        ns.addFunction(new FunctionSubtract());
        ns.addFunction(new FunctionMultiply());
        ns.addFunction(new FunctionDivide());
        
        ns.addFunction(new FunctionEquate());
        
        ns.addFunction(new FunctionNegate());
        
        ns.addFunction(new FunctionEval());
        ns.addFunction(new FunctionClearStack());
        return ns;
    }
}
