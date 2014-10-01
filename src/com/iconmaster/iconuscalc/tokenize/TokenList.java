
package com.iconmaster.iconuscalc.tokenize;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class TokenList implements IToken {
	public ArrayList<IToken> content;
	
	public TokenList() {
		
	}
	
	public TokenList(ArrayList<IToken> str) {
		this.content = str;
	}
	
	@Override
	public boolean doesMatch(Tokenizer tc, char c) {
		return c=='{';
	}

	@Override
	public IToken match(Tokenizer tc) throws IconusCalcException {
		ArrayList<IToken> a = new ArrayList<>();
		tc.advance();
		while (true) {
			if (!tc.isEOF()) {
				char c = tc.getChar();
				if (c!='}') {
					IToken token = tc.nextToken();
					if (token != null) {
						a.add(token);
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}
		tc.advance();
		return new TokenList(a);
	}
}
