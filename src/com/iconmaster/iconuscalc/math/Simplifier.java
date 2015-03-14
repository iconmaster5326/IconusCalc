
package com.iconmaster.iconuscalc.math;

import com.iconmaster.iconuscalc.function.math.FunctionNegate;
import com.iconmaster.iconuscalc.function.operator.FunctionSubtract;
import com.iconmaster.iconuscalc.function.operator.FunctionAdd;
import com.iconmaster.iconuscalc.function.operator.FunctionMultiply;
import com.iconmaster.iconuscalc.function.operator.FunctionDivide;
import com.iconmaster.iconuscalc.function.operator.FunctionPower;
import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.ExpressionElement;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.function.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public class Simplifier {
	public static HashMap<Class<? extends Function>,ArrayList<IRule>> rules = new HashMap<>();
			
	public static ExpressionElement simplify(ExpressionElement e) {
		if (e.content[0] instanceof FunctionCallElement) {
			return simplify((FunctionCallElement)e.content[0]);
		} else {
			return e;
		}
		
	}
	
	public static ExpressionElement simplify(FunctionCallElement e) {
		//System.out.println("Begin simplify "+e);
		Element ret = e;
		ArrayList<IRule> ruleset = rules.get(e.fn.getClass());
		boolean restarted = false;
		if (ruleset!=null) {
			for (int i=0;i<ruleset.size();i++) {
				IRule rule = ruleset.get(i);
				////System.out.println("simplify rule "+rule.getClass().getName());
				Element got = rule.simplify((FunctionCallElement)ret);

				if (got!=null) {
					//System.out.println("ret is now "+got);
					ret = got;
				}

				if (!(ret instanceof FunctionCallElement)) {
					break;
				} else if (((FunctionCallElement)ret).fn.getClass()!=e.fn.getClass() && !(rule instanceof RuleUndoSubtract)) {
					return simplify(((FunctionCallElement)ret));
				}
			}
		}
		
		//System.out.println("end simplify "+ret);
		ArrayList<Element> a = new ArrayList<>();
		a.add(ret);
		return new ExpressionElement(a);
	}
	
	public static void addRule(Class<? extends Function> clas,IRule rule) {
		if (rules.get(clas)==null) {
			rules.put(clas,new ArrayList<IRule>());
		}
		rules.get(clas).add(rule);
	}
	
	public static void registerRules() {
		addRule(FunctionAdd.class,new RuleConstantSimplify());
		addRule(FunctionSubtract.class,new RuleConstantSimplify());
		addRule(FunctionMultiply.class,new RuleConstantSimplify());
		//addRule(FunctionDivide.class,new RuleConstantSimplify());
		addRule(FunctionPower.class,new RuleConstantSimplify());
		addRule(FunctionNegate.class,new RuleConstantSimplify());
		
		addRule(FunctionDivide.class,new RuleDivision());
		addRule(FunctionDivide.class,new RuleDivisionCancel());
		
		addRule(FunctionSubtract.class,new RuleCancelTerms());
		addRule(FunctionDivide.class,new RuleCancelTerms());
		
		addRule(FunctionAdd.class,new RuleCollectFactors(true));
		addRule(FunctionSubtract.class,new RuleCollectFactors(false));
		
		addRule(FunctionAdd.class,new RuleCollectTerms());
		addRule(FunctionSubtract.class,new RuleCollectTerms());
		addRule(FunctionMultiply.class,new RuleCollectTerms());
		
		addRule(FunctionMultiply.class,new RuleOrderCorrectly(true));
		addRule(FunctionAdd.class,new RuleOrderCorrectly(false));
		
		addRule(FunctionAdd.class,new RuleZero());
		addRule(FunctionSubtract.class,new RuleZero());
		addRule(FunctionMultiply.class,new RuleZero());
		addRule(FunctionDivide.class,new RuleZero());
		addRule(FunctionPower.class,new RuleZero());
		
		addRule(FunctionMultiply.class,new RuleOne());
		addRule(FunctionDivide.class,new RuleOne());
		addRule(FunctionPower.class,new RuleOne());
		
		addRule(FunctionMultiply.class,new RulePowerMath());
		addRule(FunctionDivide.class,new RulePowerMath());
		
		addRule(FunctionAdd.class,new RuleUndoSubtract(true));
		addRule(FunctionAdd.class,new RuleUndoSubtract(false));
		
		//addRule(FunctionMultiply.class,new RuleExpand()); //expansion should be its own function
	}
}
