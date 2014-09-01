
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.element.StringElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.tokenize.TokenChunk;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class ParenParser implements ParseHandler {

    @Override
    public boolean matchToken(Parser p) {
        return p.getItem() instanceof TokenChunk;
    }

    @Override
    public ArrayList parse(Parser p) throws IconusCalcException {
        ArrayList a = new ArrayList();
        TokenChunk t = ((TokenChunk)p.getItem());
        
        Parser p2 = new Parser(t.content);
        
        a.add(p2.parse());
                        
        return a;
    }

    @Override
    public int getDelLength(Parser p) {
        return 1;
    }
    
}
