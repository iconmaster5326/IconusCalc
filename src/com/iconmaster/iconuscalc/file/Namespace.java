
package com.iconmaster.iconuscalc.file;

import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionDivide;
import com.iconmaster.iconuscalc.function.FunctionEval;
import com.iconmaster.iconuscalc.function.FunctionMultiply;
import com.iconmaster.iconuscalc.function.FunctionNegate;
import com.iconmaster.iconuscalc.function.FunctionSubtract;
import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public class Namespace {
    public final HashMap<String,Function> functions = new HashMap<>();
    private Namespace parent;
    
    public Namespace() {
        
    }
    
    public Namespace(Namespace parent) {
        this();
        this.parent = parent;
    }
    
    public static Namespace createGlobalNamespace() {
        Namespace ns = new Namespace();
        ns.addFunction(new FunctionAdd());
        ns.addFunction(new FunctionSubtract());
        ns.addFunction(new FunctionMultiply());
        ns.addFunction(new FunctionDivide());
        
        ns.addFunction(new FunctionNegate());
        ns.addFunction(new FunctionEval());
        return ns;
    }
    
    public void addFunction(Function fn) {
        functions.put(fn.getName(), fn);
    }
    
    public Function getFunction(String name) {
        return functions.get(name);
    }

    public Namespace getParent() {
        return parent;
    }

    public void setParent(Namespace parent) {
        this.parent = parent;
    }
}
