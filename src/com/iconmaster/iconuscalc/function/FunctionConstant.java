
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.ExpressionElement;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public abstract class FunctionConstant extends Function {

	@Override
	public Element[] execute(Element[] args, EntryStack stack, Namespace ns, Window window, int need) throws IconusCalcException {
		return new Element[] {new NumberElement(value())};
	}
	
	@Override
	public int getDefaultArgs() {
		return 0;
	}
	
	public abstract double value();
}
