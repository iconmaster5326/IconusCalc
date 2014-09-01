
package com.iconmaster.iconuscalc.file;

import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionDivide;
import com.iconmaster.iconuscalc.function.FunctionEquate;
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
    private Namespace parent;
    public HashMap<String,Variable> vars = new HashMap<>();
    
    public Namespace() {
        
    }
    
    public Namespace(Namespace parent) {
        this();
        this.parent = parent;
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
        return ns;
    }

    public Namespace getParent() {
        return parent;
    }

    public void setParent(Namespace parent) {
        this.parent = parent;
    }
    
    public void addVar(Variable fn) {
        vars.put(fn.name, fn);
    }
    
    public Variable getVar(String name) {
        return vars.get(name);
    }
}
