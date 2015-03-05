
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.ExpressionElement;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.EntryStack;
import com.iconmaster.iconuscalc.util.MathUtils;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class FunctionToFrac extends Function {

	@Override
	public Element[] execute(Element[] args, EntryStack stack, Namespace ns, Window window, int need) throws IconusCalcException {

		if (args[0] instanceof NumberElement) {
			
			ExpressionElement e = new ExpressionElement(new ArrayList<>());
			int[] frac = MathUtils.toFraction(((NumberElement)args[0]).content);
			e.content = new Element[] {new FunctionCallElement(new FunctionDivide(), new Element[] {new NumberElement(frac[1]), new NumberElement(frac[0])})};

			return new Element[] {e};
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
		return "FRAC";
	}
}
