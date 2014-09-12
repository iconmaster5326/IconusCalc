package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.Entry;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import com.iconmaster.iconuscalc.gui.KeyInput;

/**
 *
 * @author iconmaster
 */
public abstract class FunctionOperator extends Function implements IQuickCommand, IOperator {

    public KeyInput weNeverUseThisMemberButINeedTheAutoImport;

    @Override
    public Element[] execute(Element[] args) throws IconusCalcException {
        Element[] sum = new Element[]{args[args.length - 1]};

        for (int i = args.length - 2; i >= 0; i--) {
            if (sum[0] instanceof IOperable && ((IOperable) sum[0]).canOperate(getOp(), args[i], false)) {
                sum = ((IOperable) sum[0]).operate(getOp(), args[i], false);
            } else {
                throw new IllegalArgumentTypeException();
            }
        }

        return sum;
    }

    @Override
    public int getDefaultArgs() {
        return 2;
    }

    @Override
    public int getMaxArgs() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getEntryString(Entry[] args) {
        if (args.length == 2) {
            String p1 = args[1].getAnswer().getDisplayString();
            if (args[1].getAnswer() instanceof FunctionCallElement) {
                Function fn = ((FunctionCallElement) args[1].getAnswer()).fn;
                if (fn instanceof IOperator && ((IOperator) fn).getOrder() > getOrder()) {
                    p1 = "(" + p1 + ")";
                }
            }
            String p2 = args[0].getAnswer().getDisplayString();
            if (args[0].getAnswer() instanceof FunctionCallElement) {
                Function fn = ((FunctionCallElement) args[0].getAnswer()).fn;
                if (fn instanceof IOperator && ((IOperator) fn).getOrder() > getOrder()) {
                    p2 = "(" + p2 + ")";
                }
            }
            return p1 + getDisplayName() + p2;
        } else {
            return super.getEntryString(args);
        }
    }

    public abstract OperationType getOp();
}
