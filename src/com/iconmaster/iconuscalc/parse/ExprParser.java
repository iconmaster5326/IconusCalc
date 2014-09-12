package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.element.ExpressionElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.tokenize.TokenQuote;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class ExprParser implements ParseHandler {

    @Override
    public boolean matchToken(Parser p) {
        return p.getItem() instanceof TokenQuote;
    }

    @Override
    public ArrayList parse(Parser p) throws IconusCalcException {
        ArrayList a = new ArrayList();
        TokenQuote t = ((TokenQuote) p.getItem());

        Parser p2 = new Parser(t.content);

        a.add(new ExpressionElement(p2.parse()));

        return a;
    }

    @Override
    public int getDelLength(Parser p) {
        return 1;
    }

}
