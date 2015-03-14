package com.iconmaster.iconuscalc.function.math;

import com.iconmaster.iconuscalc.function.math.FunctionBasicMath;

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
