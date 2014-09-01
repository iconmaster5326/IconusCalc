
package com.iconmaster.iconuscalc.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

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
        Logger.getLogger(IllegalArgumentTypeException.class.getName()).log(Level.SEVERE, null, this);
    }
}
