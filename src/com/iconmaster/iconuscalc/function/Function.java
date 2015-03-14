
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.function.math.FunctionNegate;
import com.iconmaster.iconuscalc.function.operator.FunctionSubtract;
import com.iconmaster.iconuscalc.function.operator.FunctionAdd;
import com.iconmaster.iconuscalc.function.operator.FunctionMultiply;
import com.iconmaster.iconuscalc.function.operator.FunctionPower;
import com.iconmaster.iconuscalc.function.operator.FunctionDivide;
import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.Entry;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public abstract class Function {
	public Element[] execute(Element[] args) throws IconusCalcException {
		return new Element[] {};
	}
	public abstract String getName();
	public abstract int getDefaultArgs();
	
	public Element[] execute(Element[] args, EntryStack stack, Namespace ns, Window window, int need) throws IconusCalcException {
		return this.execute(args);
	}
	
	public int getMinArgs() {
		return getDefaultArgs();
	}
	
	public int getMaxArgs() {
		return getDefaultArgs();
	}
	
	public String getDisplayName() {
		return getName();
	}
	
	public String getEntryString(Entry[] args) {
		StringBuilder sb = new StringBuilder(getDisplayName());
		sb.append("(");
		for (int i=0;i<args.length;i++) {
			Entry ent = args[i];
			sb.append(ent.getAnswer().getDisplayString());
			if (i!=args.length-1) {
				sb.append(",");
			}
		}

		sb.append(")");
		return sb.toString();
	}
	
	public static Function getOperationFunction(OperationType type) {
		switch (type) {
			case ADD:
				return new FunctionAdd();
			case SUB:
				return new FunctionSubtract();
			case MUL:
				return new FunctionMultiply();
			case DIV:
				return new FunctionDivide();
			case NEGATE:
				return new FunctionNegate();
			case POW:
				return new FunctionPower();
		}
		return null;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public boolean argPosMatters() {
		return true;
	}
}
