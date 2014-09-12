
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.element.FunctionElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.tokenize.TokenWord;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class FunctionParser implements ParseHandler {

    @Override
    public boolean matchToken(Parser p) {
        return p.getItem() instanceof TokenWord && IconusCalc.getGlobalNamespace().getFunction(((TokenWord)p.getItem()).content)!=null;
    }

    @Override
    public ArrayList parse(Parser p) throws IconusCalcException {
        ArrayList a = new ArrayList();
        TokenWord t = ((TokenWord)p.getItem());

        a.add(new FunctionElement(t.content));
                        
        return a;
    }

    @Override
    public int getDelLength(Parser p) {
        return 1;
    }
    
}
