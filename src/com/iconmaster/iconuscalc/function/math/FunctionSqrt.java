package com.iconmaster.iconuscalc.function.math;

import com.iconmaster.iconuscalc.function.math.FunctionBasicMath;

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
