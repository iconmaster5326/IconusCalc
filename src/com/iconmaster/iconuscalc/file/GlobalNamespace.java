
package com.iconmaster.iconuscalc.file;

import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.FunctionAdd;
import com.iconmaster.iconuscalc.function.FunctionChangeDir;
import com.iconmaster.iconuscalc.function.FunctionClearStack;
import com.iconmaster.iconuscalc.function.FunctionCos;
import com.iconmaster.iconuscalc.function.FunctionDivide;
import com.iconmaster.iconuscalc.function.FunctionEquate;
import com.iconmaster.iconuscalc.function.FunctionEulersNumber;
import com.iconmaster.iconuscalc.function.FunctionEval;
import com.iconmaster.iconuscalc.function.FunctionLn;
import com.iconmaster.iconuscalc.function.FunctionLog;
import com.iconmaster.iconuscalc.function.FunctionMakeDir;
import com.iconmaster.iconuscalc.function.FunctionMultiply;
import com.iconmaster.iconuscalc.function.FunctionNegate;
import com.iconmaster.iconuscalc.function.FunctionPi;
import com.iconmaster.iconuscalc.function.FunctionPower;
import com.iconmaster.iconuscalc.function.FunctionSimplify;
import com.iconmaster.iconuscalc.function.FunctionSin;
import com.iconmaster.iconuscalc.function.FunctionSqrt;
import com.iconmaster.iconuscalc.function.FunctionSubtract;
import com.iconmaster.iconuscalc.function.FunctionTan;
import com.iconmaster.iconuscalc.function.FunctionUpDir;
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
