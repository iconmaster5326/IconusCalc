package com.iconmaster.iconuscalc.function;

/**
 *
 * @author iconmaster
 */
public class FunctionLog extends FunctionBasicMath {

	@Override
	public double doMath(double number) {
		return Math.log10(number);
	}

	@Override
	public String getName() {
		return "LOG";
	}
	
}
