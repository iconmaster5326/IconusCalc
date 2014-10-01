
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.tokenize.IToken;
import com.iconmaster.iconuscalc.tokenize.Tokenizer;
import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.Entry;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArguentCountException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.gui.Window;
import com.iconmaster.iconuscalc.util.EntryStack;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class CodeExecutor {
	public static void execute(String line, EntryStack stack, Namespace ns, Window window) throws IconusCalcException {
		int oldSize = stack.size();
		Tokenizer tc = new Tokenizer(line);
		ArrayList<IToken> tokens = tc.tokenize();
		ArrayList<Element> es = (new Parser(tokens)).parse();
		for (Element e : es) {
			e.execute(stack, ns, window);
		}
		int newSize = stack.size();
		int stackDelta = newSize-oldSize;
		if (stackDelta==1) {
			stack.setEntry(stackDelta-1, line);
		}
	}
	
	public static void execute(Element[] line, EntryStack stack, Namespace ns, Window window) throws IconusCalcException {
		for (Element e : line) {
			e.execute(stack, ns, window);
		}
	}
	
	public static Element[] executeQuoting(ArrayList<Element> es) {
		EntryStack stack = new EntryStack();
		for (Element e : es) {
			e.executeQuoting(stack);
		}
		return stack.toList().toArray(new Element[0]);
	}
	
	public static void executeFunction(Function fn, EntryStack stack, Namespace ns, Window window, int need) throws IconusCalcException {
		if (need>fn.getMaxArgs()) {
			throw new IllegalArguentCountException("Too many aguments");
		} else if (need<fn.getMinArgs()) {
			throw new IllegalArguentCountException("Too few aguments");
		}
		
		Element[] args = null;
		Entry[] ents = null;
		try {
			//int need = fn.getNumberStackArgs();
			args = new Element[need];
			ents = new Entry[need];
			for (int i=0;i<args.length;i++) {
				Entry ent = stack.popEntry();
				if (ent == null) {
					throw new IllegalArguentCountException();
				} else {
					args[i] = ent.getAnswer();
					ents[i] = ent;
				}
			}
			Element[] ret = fn.execute(args, stack, ns, window, need);
			for (Element item : ret) {
				stack.push(new Entry(fn.getEntryString(ents),item));
			}
		} catch (IconusCalcException ex) {
			if (args != null) {
				for (int i=args.length-1;i>=0;i--) {
					Element ele = args[i];
					if (ele!=null) {
						stack.push(ents[i]);
					}
				}
			}
			throw ex;
		}
	}
	
	public static void executeFunction(Function fn, EntryStack stack, Namespace ns, Window window) throws IconusCalcException {
		executeFunction(fn,stack,ns,window,fn.getDefaultArgs());
	}
}
