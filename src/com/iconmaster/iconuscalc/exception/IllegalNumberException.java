
package com.iconmaster.iconuscalc.exception;

/**
 *
 * @author iconmaster
 */
public class IllegalNumberException extends IconusCalcException {
    
    public IllegalNumberException(String message) {
        super(message);
    }
    
    public IllegalNumberException() {
        super("Illegal number format");
    }
}
