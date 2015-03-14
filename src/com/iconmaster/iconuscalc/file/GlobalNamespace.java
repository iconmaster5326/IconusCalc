
package com.iconmaster.iconuscalc.file;

import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.operator.FunctionAdd;
import com.iconmaster.iconuscalc.function.files.FunctionChangeDir;
import com.iconmaster.iconuscalc.function.stack.FunctionClearStack;
import com.iconmaster.iconuscalc.function.math.FunctionCos;
import com.iconmaster.iconuscalc.function.operator.FunctionDivide;
import com.iconmaster.iconuscalc.function.files.FunctionEquate;
import com.iconmaster.iconuscalc.function.math.FunctionEulersNumber;
import com.iconmaster.iconuscalc.function.cas.FunctionEval;
import com.iconmaster.iconuscalc.function.math.FunctionLn;
import com.iconmaster.iconuscalc.function.math.FunctionLog;
import com.iconmaster.iconuscalc.function.files.FunctionMakeDir;
import com.iconmaster.iconuscalc.function.operator.FunctionMultiply;
import com.iconmaster.iconuscalc.function.math.FunctionNegate;
import com.iconmaster.iconuscalc.function.math.FunctionPi;
import com.iconmaster.iconuscalc.function.operator.FunctionPower;
import com.iconmaster.iconuscalc.function.cas.FunctionSimplify;
import com.iconmaster.iconuscalc.function.math.FunctionSin;
import com.iconmaster.iconuscalc.function.math.FunctionSqrt;
import com.iconmaster.iconuscalc.function.operator.FunctionSubtract;
import com.iconmaster.iconuscalc.function.math.FunctionTan;
import com.iconmaster.iconuscalc.function.cas.FunctionToFrac;
import com.iconmaster.iconuscalc.function.files.FunctionUpDir;
import java.util.HashMap;

/**
 *
 * @author iconmaster
 */
public class GlobalNamespace extends Namespace {
	public final HashMap<String,Function> functions = new HashMap<>();

	public GlobalNamespace() {
		super("HOME",null);
	}
		
	public void addFunction(Function fn) {
		functions.put(fn.getName().toUpperCase(), fn);
	}
	
	public Function getFunction(String name) {
		return functions.get(name.toUpperCase());
	}
	
	public static GlobalNamespace createGlobalNamespace() {
		GlobalNamespace ns = new GlobalNamespace();
		
		ns.addFunction(new FunctionAdd());
		ns.addFunction(new FunctionSubtract());
		ns.addFunction(new FunctionMultiply());
		ns.addFunction(new FunctionDivide());
		ns.addFunction(new FunctionPower());
		
		ns.addFunction(new FunctionEquate());
		
		ns.addFunction(new FunctionNegate());
		ns.addFunction(new FunctionSin());
		ns.addFunction(new FunctionCos());
		ns.addFunction(new FunctionTan());
		ns.addFunction(new FunctionLn());
		ns.addFunction(new FunctionLog());
		ns.addFunction(new FunctionSqrt());
		
		ns.addFunction(new FunctionPi());
		ns.addFunction(new FunctionEulersNumber());
		
		ns.addFunction(new FunctionEval());
		ns.addFunction(new FunctionClearStack());
		ns.addFunction(new FunctionSimplify());
		ns.addFunction(new FunctionToFrac());
		
		ns.addFunction(new FunctionMakeDir());
		ns.addFunction(new FunctionChangeDir());
		ns.addFunction(new FunctionUpDir());
		
		return ns;
	}
	
	@Override
	public String getPathName() {
		return "HOME";
	}
}
