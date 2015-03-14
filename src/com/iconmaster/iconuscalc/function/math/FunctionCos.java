package com.iconmaster.iconuscalc.function.math;

import com.iconmaster.iconuscalc.function.math.FunctionBasicMath;

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
