
package com.iconmaster.iconuscalc.function.stack;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public class FunctionClearStack extends Function {
	@Override
	public Element[] execute(Element[] args) throws IconusCalcException {
		return new Element[0];
	}

	@Override
	public Element[] execute(Element[] args, EntryStack stack, Namespace ns, Window window, int need) throws IconusCalcException {
		stack.clear();
		return new Element[0];
	}
	
	@Override
	public int getDefaultArgs() {
		return 0;
	}

	@Override
	public String getName() {
		return "CLEAR";
	}
}
