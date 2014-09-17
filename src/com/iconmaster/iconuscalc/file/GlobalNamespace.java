
package com.iconmaster.iconuscalc.file;

import com.iconmaster.iconuscalc.function.*;

import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public class GlobalNamespace extends Namespace {
    public final HashMap<String,Function> functions = new HashMap<>();

    public GlobalNamespace() {
        super("HOME",null);
    }
        
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
        ns.addFunction(new FunctionPower());
        
        ns.addFunction(new FunctionEquate());
        
        ns.addFunction(new FunctionNegate());
        ns.addFunction(new FunctionSin());
        ns.addFunction(new FunctionCos());
        ns.addFunction(new FunctionTan());
        ns.addFunction(new FunctionLn());
        ns.addFunction(new FunctionLog());
        
        ns.addFunction(new FunctionPi());
        ns.addFunction(new FunctionEulersNumber());
        
        ns.addFunction(new FunctionEval());
        ns.addFunction(new FunctionClearStack());
        ns.addFunction(new FunctionSimplify());
        
        ns.addFunction(new FunctionMakeDir());
        ns.addFunction(new FunctionChangeDir());
        ns.addFunction(new FunctionUpDir());
        
        return ns;
    }
    
    @Override
    public String getPathName() {
        return "HOME";
    }
}
