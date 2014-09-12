
package com.iconmaster.iconuscalc.tokenize;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public interface IToken {
    public boolean doesMatch(Tokenizer tc, char c);
    public IToken match(Tokenizer tc) throws IconusCalcException;
    
    //public ArrayList<Element> parse(Parser p) throws IconusCalcException;
}
