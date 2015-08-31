
package com.iconmaster.iconuscalc.tokenize;

import com.iconmaster.iconuscalc.exception.IconusCalcException;

/**
 *
 * @author iconmaster
 */
public class TokenNumber implements IToken {
	public String content;
	
	public TokenNumber() {
		
	}
	
	public TokenNumber(String str) {
		this.content = str;
	}
	
	@Override
	public boolean doesMatch(Tokenizer tc, char c) {
		return Character.isDigit(c) || c=='.';
	}

	@Override
	public IToken match(Tokenizer tc) throws IconusCalcException {
		StringBuilder word = new StringBuilder();
		boolean exp = false;
		while (true) {
			if (!tc.isEOF()) {
				char c = tc.getChar();
				if (doesMatch(tc,c)) {
					word.append(c);
					tc.advance();
				} else if (c=='e' || c=='E') {
					word.append(c);
					tc.advance();
					exp = true;
				} else if (c=='-' && exp) {
					word.append(c);
					tc.advance();
					exp = false;
				} else if (c=='+' && exp) {
					word.append(c);
					tc.advance();
					exp = false;
				} else {
					break;
				}
			} else {
				break;
			}
		}
		return new TokenNumber(word.toString());
	}
	
	@Override
	public String toString() {
		return "[TOKEN NUM: "+content+"]";
	}
}
