
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.EquationElement;
import com.iconmaster.iconuscalc.element.VarElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.tokenize.TokenWord;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class UserFunctionParser implements ParseHandler {

	@Override
	public boolean matchToken(Parser p) {
		if (p.isEOF(2) || !(p.getItem(1) instanceof TokenWord)) {return false;}
		if (!(p.getItem() instanceof ArrayList && p.getItem(2) instanceof ArrayList)) {return false;}
		ArrayList<Element> ea = (ArrayList<Element>)p.getItem();
		ArrayList<Element> ea2 = (ArrayList<Element>)p.getItem(2);
		if (ea.size()>0 && ea.get(0) instanceof VarElement) {
			return ((TokenWord)p.getItem(1)).content.equals("->");
		}
		return false;
	}

	@Override
	public ArrayList parse(Parser p) throws IconusCalcException {
		ArrayList a = new ArrayList();
		TokenWord t = ((TokenWord)p.getItem(1));
		VarElement o1 = (VarElement) ((ArrayList<Element>)p.getItem()).get(0);
		Object o2 = p.getItem(2);
		
		a.add(new EquationElement(o1, Parser.flatten((ArrayList<Element>) o2)));
		
		return a;
	}

	@Override
	public int getDelLength(Parser p) {
		return 3;
	}
	
}
