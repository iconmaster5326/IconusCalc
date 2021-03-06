package com.iconmaster.iconuscalc.function.math;

import com.iconmaster.iconuscalc.function.math.FunctionBasicMath;

/**
 *
 * @author iconmaster
 */
public class FunctionLn extends FunctionBasicMath {

	@Override
	public double doMath(double number) {
		return Math.log(number);
	}

	@Override
	public String getName() {
		return "LN";
	}
	
}
