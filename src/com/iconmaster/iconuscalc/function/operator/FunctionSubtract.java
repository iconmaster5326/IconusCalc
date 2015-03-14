
package com.iconmaster.iconuscalc.function.operator;

import com.iconmaster.iconuscalc.function.OperationType;
import com.iconmaster.iconuscalc.gui.KeyInput;
import java.awt.event.KeyEvent;

/**
 *
 * @author iconmaster
 */
public class FunctionSubtract extends FunctionOperator {
	@Override
	public OperationType getOp() {
		return OperationType.SUB;
	}

	@Override
	public String getName() {
		return "-";
	}

	@Override
	public boolean isCommandKey(KeyInput e) {
		return e.key=='-';
	}

	@Override
	public int getOrder() {
		return 3;
	}
	
}
