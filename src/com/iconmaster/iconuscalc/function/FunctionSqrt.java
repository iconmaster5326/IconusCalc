package com.iconmaster.iconuscalc.function;

/**
 *
 * @author iconmaster
 */
public class FunctionSqrt extends FunctionBasicMath {

	@Override
	public double doMath(double number) {
		return Math.sqrt(number);
	}

	@Override
	public String getName() {
		return "SQRT";
	}
	
}
