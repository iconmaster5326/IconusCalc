package com.iconmaster.iconuscalc.tokenize;

import com.iconmaster.iconuscalc.exception.IconusCalcException;

/**
 *
 * @author iconmaster
 */
public class TokenWhitespace implements IToken {

    @Override
    public boolean doesMatch(Tokenizer tc, char c) {
        return Character.isWhitespace(c) || c == ',' || c == ';';
    }

    @Override
    public IToken match(Tokenizer tc) throws IconusCalcException {
        while (true) {
            if (!tc.isEOF()) {
                char c = tc.getChar();
                if (doesMatch(tc, c)) {
                    tc.advance();
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return new TokenWhitespace();
    }

}
