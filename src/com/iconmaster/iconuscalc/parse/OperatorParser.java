
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.element.FunctionElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.IOperator;
import com.iconmaster.iconuscalc.tokenize.IToken;
import com.iconmaster.iconuscalc.tokenize.TokenWord;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class OperatorParser implements ParseHandler {
	int order;
	
	public OperatorParser(int order) {
		this.order = order;
	}

	@Override
	public boolean matchToken(Parser p) {
		if (p.isEOF(2) || !(p.getItem(1) instanceof TokenWord)) {return false;}
		Function fn = IconusCalc.getGlobalNamespace().getFunction(((TokenWord)p.getItem(1)).content);
		return fn!=null && fn instanceof IOperator && ((IOperator)fn).getOrder()==order;
	}

	@Override
	public ArrayList parse(Parser p) throws IconusCalcException {
		ArrayList a = new ArrayList();
		TokenWord t = ((TokenWord)p.getItem(1));
		Object o1 = p.getItem();
		Object o2 = p.getItem(2);

		if (o1 instanceof IToken) {
			a.add(Parser.parseToken((IToken) o1));
		} else {
			a.add(o1);
		}
		if (o2 instanceof IToken) {
			a.add(Parser.parseToken((IToken) o2));
		} else {
			a.add(o2);
		}
		a.add(new FunctionElement(t.content));
						
		return a;
	}

	@Override
	public int getDelLength(Parser p) {
		return 3;
	}
	
}
