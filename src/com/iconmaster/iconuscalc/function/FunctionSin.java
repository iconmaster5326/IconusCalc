package com.iconmaster.iconuscalc.function;

/**
 *
 * @author iconmaster
 */
public class FunctionSin extends FunctionBasicMath {

	@Override
	public double doMath(double number) {
		return Math.sin(number);
	}

	@Override
	public String getName() {
		return "SIN";
	}
	
}
