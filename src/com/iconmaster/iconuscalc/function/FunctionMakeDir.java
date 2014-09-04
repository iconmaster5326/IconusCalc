
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.StringElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public class FunctionMakeDir extends Function {

    @Override
    public Element[] execute(Element[] args, EntryStack stack, Namespace ns, Window window, int need) throws IconusCalcException {
        if (!(args[0] instanceof StringElement)) {
            throw new IllegalArgumentTypeException();
        }
        ns.addFolder(new Namespace(((StringElement)args[0]).content,ns));
        return new Element[] {};
    }
    
    @Override
    public int getDefaultArgs() {
        return 1;
    }

    @Override
    public String getName() {
        return "MKDIR";
    }

    @Override
    public Element[] execute(Element[] args) throws IconusCalcException {
        return new Element[] {};
    }
}
