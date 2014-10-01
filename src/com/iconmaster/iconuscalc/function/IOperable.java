
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.exception.IconusCalcException;

/**
 *
 * @author iconmaster
 */
public interface IOperable {
	public Element[] operate(OperationType type, Element operand, boolean reversed) throws IconusCalcException;
	public boolean canOperate(OperationType type, Element operand, boolean reversed);
}
