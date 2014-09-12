
package com.iconmaster.iconuscalc.exception;

/**
 *
 * @author iconmaster
 */
public class IllegalFunctionIndexException extends IconusCalcException {
    
    public IllegalFunctionIndexException(String message) {
        super(message);
    }
    
    public IllegalFunctionIndexException() {
        super("Illegal arg count given");
    }
}
