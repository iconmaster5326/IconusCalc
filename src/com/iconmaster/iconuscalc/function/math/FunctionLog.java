package com.iconmaster.iconuscalc.function.math;

import com.iconmaster.iconuscalc.function.math.FunctionBasicMath;

/**
 *
 * @author iconmaster
 */
public class FunctionLog extends FunctionBasicMath {

	@Override
	public double doMath(double number) {
		return Math.log10(number);
	}

	@Override
	public String getName() {
		return "LOG";
	}
	
}
