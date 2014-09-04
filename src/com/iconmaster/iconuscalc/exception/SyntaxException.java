
package com.iconmaster.iconuscalc.exception;

/**
 *
 * @author iconmaster
 */
public class SyntaxException extends IconusCalcException {
    
    public SyntaxException(String message) {
        super(message);
    }
    
    public SyntaxException() {
        super("Syntax error");
        //Logger.getLogger(IllegalArgumentTypeException.class.getName()).log(Level.SEVERE, null, this);
    }
}
