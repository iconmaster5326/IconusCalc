package com.iconmaster.iconuscalc.function.math;

import com.iconmaster.iconuscalc.function.math.FunctionBasicMath;

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
