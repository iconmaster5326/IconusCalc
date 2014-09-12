package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.ExpressionElement;
import com.iconmaster.iconuscalc.element.FunctionElement;
import com.iconmaster.iconuscalc.element.VarElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.FunctionEquate;
import com.iconmaster.iconuscalc.tokenize.TokenWord;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class EquateParser implements ParseHandler {

    @Override
    public boolean matchToken(Parser p) {
        if (p.isEOF(2) || !(p.getItem(1) instanceof TokenWord)) {
            return false;
        }
        if (!(p.getItem() instanceof ArrayList)) {
            return false;
        }
        ArrayList<Element> ea = (ArrayList<Element>) p.getItem();
        if (ea.size() > 0 && ea.get(0) instanceof VarElement) {
            Function fn = IconusCalc.getGlobalNamespace().getFunction(((TokenWord) p.getItem(1)).content);
            return fn != null && fn instanceof FunctionEquate;
        }
        return false;
    }

    @Override
    public ArrayList parse(Parser p) throws IconusCalcException {
        ArrayList a = new ArrayList();
        TokenWord t = ((TokenWord) p.getItem(1));
        VarElement o1 = (VarElement) ((ArrayList<Element>) p.getItem()).get(0);
        Object o2 = p.getItem(2);

        ArrayList<Element> ae = new ArrayList<>();
        ae.add(o1);

        a.add(new ExpressionElement(ae));
        a.add(o2);
        a.add(new FunctionElement(new FunctionEquate()));

        //System.out.println(a);
        return a;
    }

    @Override
    public int getDelLength(Parser p) {
        return 3;
    }

}
