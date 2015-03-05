
package com.iconmaster.iconuscalc.element;

import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class EquationElement extends Element {
	public VarElement var;
	public ExpressionElement expr;
	

	public EquationElement( VarElement var, ExpressionElement expr) {
		this.expr = expr;
		this.var = var;
	}
	
	public EquationElement(VarElement var, ArrayList<Element> expr) {
		this.expr = new ExpressionElement(expr);
		this.var = var;
	}

	@Override
	public String getDisplayString() {
		return var.getStringCastString()+"->"+expr.getStringCastString();
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof EquationElement && ((EquationElement)other).var.equals(this.var) && ((EquationElement)other).expr.equals(this.expr);
	}
	
	@Override
	public String getDataTypeName() {
		return "EQN";
	}
}
