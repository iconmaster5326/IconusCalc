
package com.iconmaster.iconuscalc.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iconmaster
 */
public class IllegalArgumentTypeException extends IconusCalcException {
    
    public IllegalArgumentTypeException(String message) {
        super(message);

    }
    
    public IllegalArgumentTypeException() {
        super("Illegal argument type");
    }
}
