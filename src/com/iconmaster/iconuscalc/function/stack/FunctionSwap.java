
package com.iconmaster.iconuscalc.function.stack;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.Entry;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.function.Function;
import com.iconmaster.iconuscalc.function.IQuickCommand;
import com.iconmaster.iconuscalc.gui.KeyInput;

/**
 *
 * @author iconmaster
 */
public class FunctionSwap extends Function implements IQuickCommand {
	@Override
	public Element[] execute(Element[] args) throws IconusCalcException {
		return new Element[] {args[0], args[1]};
	}
	
	@Override
	public int getDefaultArgs() {
		return 2;
	}

	@Override
	public String getName() {
		return "SWAP";
	}

	@Override
	public String getEntryString(Entry[] args) {
		return "";
	}

	@Override
	public boolean isCommandKey(KeyInput e) {
		return e.key == KeyInput.RIGHT;
	}
}
