
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.gui.KeyInput;

/**
 *
 * @author iconmaster
 */
public class FunctionAdd extends FunctionOperator {
	@Override
	public OperationType getOp() {
		return OperationType.ADD;
	}

	@Override
	public String getName() {
		return "+";
	}

	@Override
	public boolean isCommandKey(KeyInput e) {
		return e.key=='+';
	}

	@Override
	public int getOrder() {
		return 3;
	}
	
	@Override
	public boolean argPosMatters() {
		return false;
	}
}
