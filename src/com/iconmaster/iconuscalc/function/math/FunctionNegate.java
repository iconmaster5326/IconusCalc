
package com.iconmaster.iconuscalc.function.math;

import com.iconmaster.iconuscalc.function.operator.IOperator;
import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.Entry;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.IOperable;
import com.iconmaster.iconuscalc.function.IQuickCommand;
import com.iconmaster.iconuscalc.function.OperationType;
import com.iconmaster.iconuscalc.gui.KeyInput;

/**
 *
 * @author iconmaster
 */
public class FunctionNegate extends Function implements IQuickCommand {
	public static final OperationType op = OperationType.NEGATE;

	@Override
	public Element[] execute(Element[] args) throws IconusCalcException {
		Element[] ret = new Element[0];
		
		if (args[0] instanceof IOperable && ((IOperable)args[0]).canOperate(op, null, false)) {
			ret = ((IOperable)args[0]).operate(op, null, false);
		} else {
			throw new IllegalArgumentTypeException();
		}
		
		return ret;
	}
	
	@Override
	public int getDefaultArgs() {
		return 1;
	}

	@Override
	public String getName() {
		return "NEG";
	}
	
	@Override
	public String getDisplayName() {
		return "-";
	}

	@Override
	public boolean isCommandKey(KeyInput e) {
		return e.key=='_';
	}
	
	@Override
	public String getEntryString(Entry[] args) {
		String str = args[0].getAnswer().getDisplayString();
		if (args[0].getAnswer() instanceof FunctionCallElement) {
			Function fn = ((FunctionCallElement)args[0].getAnswer()).fn;
			 if (fn instanceof IOperator) {
				  str="("+str+")";
			 }
		}
		return "−"+str;
	}
}
