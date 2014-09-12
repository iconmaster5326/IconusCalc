
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.tokenize.TokenWhitespace;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class WhitespaceParser implements ParseHandler {

    @Override
    public boolean matchToken(Parser p) {
        return p.getItem() instanceof TokenWhitespace;
    }

    @Override
    public ArrayList parse(Parser p) throws IconusCalcException {
        return new ArrayList();
    }

    @Override
    public int getDelLength(Parser p) {
        return 1;
    }
    
}
