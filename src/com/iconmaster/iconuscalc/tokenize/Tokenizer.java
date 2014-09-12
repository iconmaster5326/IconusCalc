package com.iconmaster.iconuscalc.tokenize;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.SyntaxException;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class Tokenizer {

    private int ptr;
    private final String str;

    public Tokenizer(String str) {
        this.str = str;
        this.ptr = 0;
    }

    private static ArrayList<IToken> handlers = new ArrayList<>();

    public static void addHandler(IToken handler) {
        handlers.add(handler);
    }

    public static void addDefaultHandlers() {
        addHandler(new TokenNumber());
        addHandler(new TokenString());
        addHandler(new TokenWhitespace());
        addHandler(new TokenChunk());
        addHandler(new TokenList());
        addHandler(new TokenIndex());
        addHandler(new TokenQuote());
        addHandler(new TokenOperator());
        addHandler(new TokenWord());
    }

    public IToken getHandler(char c) {
        for (IToken handler : handlers) {
            if (handler.doesMatch(this, c)) {
                return handler;
            }
        }
        return null;
    }

    public ArrayList<IToken> tokenize() throws IconusCalcException {
        ArrayList<IToken> a = new ArrayList<>();
        while (!isEOF()) {
            IToken token = nextToken();
            if (token != null) {
                a.add(token);
            }
        }
        return a;
    }

    public IToken nextToken() throws IconusCalcException {
        IToken handler = getHandler(getChar());
        if (handler == null) {
            throw new SyntaxException();
        } else {
            return handler.match(this);
        }
    }

    public void advance() {
        advance(1);
    }

    public void advance(int amt) {
        ptr += amt;
    }

    public char getChar() {
        return str.charAt(ptr);
    }

    public boolean isEOF() {
        return ptr >= str.length();
    }
}
