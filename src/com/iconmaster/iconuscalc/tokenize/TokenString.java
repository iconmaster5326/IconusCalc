
package com.iconmaster.iconuscalc.tokenize;

import com.iconmaster.iconuscalc.exception.IconusCalcException;

/**
 *
 * @author iconmaster
 */
public class TokenString implements IToken {
    public String content;
    
    public TokenString() {
        
    }
    
    public TokenString(String str) {
        this.content = str;
    }
    
    @Override
    public boolean doesMatch(Tokenizer tc, char c) {
        return c=='"';
    }

    @Override
    public IToken match(Tokenizer tc) throws IconusCalcException {
        StringBuilder word = new StringBuilder();
        tc.advance();
        while (true) {
            if (!tc.isEOF()) {
                char c = tc.getChar();
                if (!doesMatch(tc,c)) {
                    word.append(c);
                    tc.advance();
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        tc.advance(1);
        return new TokenString(word.toString());
    }
    
    @Override
    public String toString() {
        return "[TOKEN STR: "+content+"]";
    }
}
