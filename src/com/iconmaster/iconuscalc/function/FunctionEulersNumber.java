package com.iconmaster.iconuscalc.function;

/**
 *
 * @author iconmaster
 */
public class FunctionEulersNumber extends FunctionConstant {

	@Override
	public double value() {
		return Math.E;
	}

	@Override
	public String getName() {
		return "e";
	}
	
}
