package com.iconmaster.iconuscalc.function;

/**
 *
 * @author iconmaster
 */
public class FunctionTan extends FunctionBasicMath {

	@Override
	public double doMath(double number) {
		return Math.tan(number);
	}

	@Override
	public String getName() {
		return "TAN";
	}
	
}
