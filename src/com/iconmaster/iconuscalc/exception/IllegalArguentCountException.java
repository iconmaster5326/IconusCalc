package com.iconmaster.iconuscalc.exception;

/**
 *
 * @author iconmaster
 */
public class IllegalArguentCountException extends IconusCalcException {

    public IllegalArguentCountException(String message) {
        super(message);
    }

    public IllegalArguentCountException() {
        super("Not enough arguments");
    }
}
