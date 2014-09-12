package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.element.StringElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.tokenize.TokenString;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class StringParser implements ParseHandler {

    @Override
    public boolean matchToken(Parser p) {
        return p.getItem() instanceof TokenString;
    }

    @Override
    public ArrayList parse(Parser p) throws IconusCalcException {
        ArrayList a = new ArrayList();
        TokenString t = ((TokenString) p.getItem());

        a.add(new StringElement(t.content));

        return a;
    }

    @Override
    public int getDelLength(Parser p) {
        return 1;
    }

}
