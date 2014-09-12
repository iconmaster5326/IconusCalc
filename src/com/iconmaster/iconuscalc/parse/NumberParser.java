package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalNumberException;
import com.iconmaster.iconuscalc.tokenize.TokenNumber;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class NumberParser implements ParseHandler {

    @Override
    public boolean matchToken(Parser p) {
        return p.getItem() instanceof TokenNumber;
    }

    @Override
    public ArrayList parse(Parser p) throws IconusCalcException {
        ArrayList a = new ArrayList();
        TokenNumber t = ((TokenNumber) p.getItem());

        try {
            a.add(new NumberElement(Double.parseDouble(t.content)));
        } catch (NumberFormatException ex) {
            throw new IllegalNumberException();
        }

        return a;
    }

    @Override
    public int getDelLength(Parser p) {
        return 1;
    }

}
