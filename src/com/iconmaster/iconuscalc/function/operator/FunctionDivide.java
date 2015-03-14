
package com.iconmaster.iconuscalc.function.operator;

import com.iconmaster.iconuscalc.function.OperationType;
import com.iconmaster.iconuscalc.gui.KeyInput;
import java.awt.event.KeyEvent;

/**
 *
 * @author iconmaster
 */
public class FunctionDivide extends FunctionOperator {
	@Override
	public OperationType getOp() {
		return OperationType.DIV;
	}

	@Override
	public String getName() {
		return "/";
	}

	@Override
	public boolean isCommandKey(KeyInput e) {
		return e.key=='/';
	}

	@Override
	public int getOrder() {
		return 2;
	}
	
}