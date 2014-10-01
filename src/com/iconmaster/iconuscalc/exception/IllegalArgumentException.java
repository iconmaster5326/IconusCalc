
package com.iconmaster.iconuscalc.exception;

/**
 *
 * @author iconmaster
 */
public class IllegalArgumentException extends IconusCalcException {
	
	public IllegalArgumentException(String message) {
		super(message);
	}
	
	public IllegalArgumentException() {
		super("Illegal argument");
	}
}
