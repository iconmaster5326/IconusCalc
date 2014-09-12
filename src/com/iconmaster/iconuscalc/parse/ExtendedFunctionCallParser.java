
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalFunctionIndexException;
import com.iconmaster.iconuscalc.tokenize.TokenChunk;
import com.iconmaster.iconuscalc.tokenize.TokenIndex;
import com.iconmaster.iconuscalc.tokenize.TokenWord;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class ExtendedFunctionCallParser implements ParseHandler {

    @Override
    public boolean matchToken(Parser p) {
        return !p.isEOF(2) && p.getItem() instanceof TokenWord && p.getItem(1) instanceof TokenIndex && p.getItem(2) instanceof TokenChunk;
    }

    @Override
    public ArrayList parse(Parser p) throws IconusCalcException {
        ArrayList a = new ArrayList();
        TokenWord t = ((TokenWord)p.getItem());
        TokenIndex ind = ((TokenIndex)p.getItem(1));
        TokenChunk c = ((TokenChunk)p.getItem(2));
        
        Parser p2 = new Parser(c.content);
        ArrayList<Element> args = p2.parse();
        a.add(args);
        
        ArrayList<Element> inc = (new Parser(ind.content)).parse();
        if (inc.isEmpty()) {
            a.add(new FunctionElement(t.content));
        } else if (inc.size()==1 && inc.get(0) instanceof NumberElement) {
            a.add(new FunctionElement(t.content,((NumberElement)inc.get(0)).getContent().intValue()));
        } else {
            throw new IllegalFunctionIndexException();
        }
            
        return a;
    }

    @Override
    public int getDelLength(Parser p) {
        return 3;
    }
    
}
