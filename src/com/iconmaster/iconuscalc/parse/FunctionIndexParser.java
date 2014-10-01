
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.FunctionElement;
import com.iconmaster.iconuscalc.element.NumberElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalFunctionIndexException;
import com.iconmaster.iconuscalc.tokenize.TokenIndex;
import com.iconmaster.iconuscalc.tokenize.TokenWord;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class FunctionIndexParser implements ParseHandler {

	@Override
	public boolean matchToken(Parser p) {
		return !p.isEOF(1) && p.getItem() instanceof TokenWord && p.getItem(1) instanceof TokenIndex;
	}

	@Override
	public ArrayList parse(Parser p) throws IconusCalcException {
		ArrayList a = new ArrayList();
		TokenWord t = ((TokenWord)p.getItem());
		TokenIndex c = ((TokenIndex)p.getItem(1));
		
		ArrayList<Element> inc = (new Parser(c.content)).parse();
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
		return 2;
	}
	
}
