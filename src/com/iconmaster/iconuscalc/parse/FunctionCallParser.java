
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.tokenize.TokenChunk;
import com.iconmaster.iconuscalc.tokenize.TokenWord;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class FunctionCallParser implements ParseHandler {

	@Override
	public boolean matchToken(Parser p) {
		return !p.isEOF(1) && p.getItem() instanceof TokenWord && p.getItem(1) instanceof TokenChunk;
	}

	@Override
	public ArrayList parse(Parser p) throws IconusCalcException {
		ArrayList a = new ArrayList();
		TokenWord t = ((TokenWord)p.getItem());
		TokenChunk c = ((TokenChunk)p.getItem(1));
		
		Parser p2 = new Parser(c.content);
		ArrayList<Element> args = p2.parse();
		a.add(args);
		a.add(new FunctionElement(t.content));
						
		return a;
	}

	@Override
	public int getDelLength(Parser p) {
		return 2;
	}
	
}
