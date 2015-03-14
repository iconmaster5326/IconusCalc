
package com.iconmaster.iconuscalc.function.cas;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.ExpressionElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.math.Simplifier;
import com.iconmaster.iconuscalc.parse.CodeExecutor;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public class FunctionSimplify extends Function {

	@Override
	public Element[] execute(Element[] args, EntryStack stack, Namespace ns, Window window, int need) throws IconusCalcException {

		if (args[0] instanceof ExpressionElement) {
			return new Element[] {Simplifier.simplify((ExpressionElement) args[0])};
		} else {
			throw new IllegalArgumentTypeException();
		}
	}
	
	@Override
	public int getDefaultArgs() {
		return 1;
	}

	@Override
	public String getName() {
		return "SIMP";
	}
}
