
package com.iconmaster.iconuscalc.function;

import com.iconmaster.iconuscalc.IconusCalc;
import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.element.Entry;
import com.iconmaster.iconuscalc.element.ExpressionElement;
import com.iconmaster.iconuscalc.element.FunctionCallElement;
import com.iconmaster.iconuscalc.element.VarElement;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.IllegalArgumentTypeException;
import com.iconmaster.iconuscalc.file.Namespace;
import com.iconmaster.iconuscalc.file.Variable;
import com.iconmaster.iconuscalc.gui.KeyInput;
import com.iconmaster.iconuscalc.util.EntryStack;

/**
 *
 * @author iconmaster
 */
public class FunctionEquate extends Function implements IQuickCommand {
    
    @Override
    public Element[] execute(Element[] args) throws IconusCalcException {
        return this.execute(args, new EntryStack(), IconusCalc.getGlobalNamespace(), 2);
    }

    @Override
    public Element[] execute(Element[] args, EntryStack stack, Namespace ns, int need) throws IconusCalcException {
        String name;
        if (args[1] instanceof VarElement) {
            name = ((VarElement)args[1]).content;
        } else if (args[1] instanceof ExpressionElement) {
            Element[] ae = ((ExpressionElement)args[1]).content;
            if (ae.length==1 && ae[0] instanceof VarElement) {
                name = ((VarElement)ae[0]).content;
            } else {
                throw new IllegalArgumentTypeException();
            }
        } else {
            throw new IllegalArgumentTypeException();
        }
        
        Variable var = ns.getVar(name);
         if (var==null) {
             var = new Variable(name,args[0]);
             ns.addVar(var);
         } else {
             var.value = args[0];
         }
            
        return new Element[0];
    }
    
    @Override
    public int getDefaultArgs() {
        return 2;
    }
    
    @Override
    public String getEntryString(Entry[] args) {
        if (args.length==2) {
            String p1 = args[1].getAnswer().getDisplayString();
            if (args[1].getAnswer() instanceof FunctionCallElement) {
               Function fn = ((FunctionCallElement)args[1].getAnswer()).fn;
               if (fn instanceof IOperator && ((IOperator)fn).getOrder()>4) {
                    p1="("+p1+")";
               }
            }
            String p2 = args[0].getAnswer().getDisplayString();
            if (args[0].getAnswer() instanceof FunctionCallElement) {
               Function fn = ((FunctionCallElement)args[0].getAnswer()).fn;
               if (fn instanceof IOperator && ((IOperator)fn).getOrder()>4) {
                    p2="("+p2+")";
               }
            }
            return p1+getDisplayName()+p2;
        } else {
            return super.getEntryString(args);
        }
    }

    @Override
    public String getName() {
        return "=";
    }

    @Override
    public boolean isCommandKey(KeyInput e) {
        return e.key=='=';
    }
}
