package com.iconmaster.iconuscalc.function;

/**
 *
 * @author iconmaster
 */
public class FunctionPi extends FunctionConstant {

	@Override
	public double value() {
		return Math.PI;
	}

	@Override
	public String getName() {
		return "PI";
	}
	
}
