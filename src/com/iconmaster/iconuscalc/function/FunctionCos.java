package com.iconmaster.iconuscalc.function;

/**
 *
 * @author iconmaster
 */
public class FunctionCos extends FunctionBasicMath {

	@Override
	public double doMath(double number) {
		return Math.cos(number);
	}

	@Override
	public String getName() {
		return "COS";
	}
	
}
