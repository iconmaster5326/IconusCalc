
package com.iconmaster.iconuscalc.tokenize;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.util.StringUtils;

/**
 *
 * @author iconmaster
 */
public class TokenOperator extends TokenWord {
    
    public TokenOperator() {
        
    }

    public TokenOperator(String content) {
        this.content = content;
    }
    
    @Override
    public boolean doesMatch(Tokenizer tc, char c) {
        return StringUtils.isSymbol(c);
    }

    @Override
    public IToken match(Tokenizer tc) throws IconusCalcException {
        StringBuilder word = new StringBuilder();
        while (true) {
            if (!tc.isEOF()) {
                char c = tc.getChar();
                if (StringUtils.isSymbol(c)) {
                    word.append(c);
                    tc.advance();
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return new TokenOperator(word.toString());
    }
}
