
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.StringElement;
import com.iconmaster.iconuscalc.exception.DiretoryNotFoundException;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public class FunctionUpDir extends Function {

    @Override
    public Element[] execute(Element[] args, EntryStack stack, Namespace ns, Window window, int need) throws IconusCalcException {
        if (ns.getParent() != null)
            window.setNamespace(ns.getParent());
        
        return new Element[0];
    }
    
    @Override
    public int getDefaultArgs() {
        return 0;
    }

    @Override
    public String getName() {
        return "UPDIR";
    }

    @Override
    public Element[] execute(Element[] args) throws IconusCalcException {
        return new Element[] {};
    }
}
