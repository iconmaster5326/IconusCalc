
package com.iconmaster.iconuscalc.tokenize;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.util.StringUtils;

/**
 *
 * @author iconmaster
 */
public class TokenWord implements IToken {
    public String content;
    
    public TokenWord() {
        
    }
    
    public TokenWord(String str) {
        this.content = str;
    }
    
    @Override
    public boolean doesMatch(Tokenizer tc, char c) {
        return Character.isLetter(c);
    }

    @Override
    public IToken match(Tokenizer tc) throws IconusCalcException {
        StringBuilder word = new StringBuilder();
        while (true) {
            if (!tc.isEOF()) {
                char c = tc.getChar();
                if (Character.isLetterOrDigit(c)) {
                    word.append(c);
                    tc.advance();
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return new TokenWord(word.toString());
    }
    @Override
    public String toString() {
        return "[TOKEN WORD: "+content+"]";
    }
}
