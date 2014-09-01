
package com.iconmaster.iconuscalc.file;

import com.iconmaster.iconuscalc.function.Function;
import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public class GlobalNamespace extends Namespace {
    public final HashMap<String,Function> functions = new HashMap<>();
        
    public void addFunction(Function fn) {
        functions.put(fn.getName(), fn);
    }
    
    public Function getFunction(String name) {
        return functions.get(name);
    }
}
