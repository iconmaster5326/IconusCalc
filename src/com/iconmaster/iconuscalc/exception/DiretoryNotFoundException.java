package com.iconmaster.iconuscalc.exception;

/**
 *
 * @author iconmaster
 */
public class DiretoryNotFoundException extends IconusCalcException {

    public DiretoryNotFoundException(String message) {
        super(message);

    }

    public DiretoryNotFoundException() {
        super("Directory not found");
    }
}
