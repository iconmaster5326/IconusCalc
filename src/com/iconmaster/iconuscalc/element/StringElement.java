
package com.iconmaster.iconuscalc.element;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.function.IOperable;
import com.iconmaster.iconuscalc.function.OperationType;

/**
 *
 * @author iconmaster
 */
public class StringElement extends Element implements IOperable {
    public final String content;
    
    public StringElement(String n) {
        content = n;
    }
    
    @Override
    public String getDisplayString() {
        return "\""+content+"\"";
    }

    @Override
    public Element[] operate(OperationType type, Element operand, boolean reversed) throws IconusCalcException {
        return new Element[] {new StringElement(reversed?operand.getStringCastString()+this.content:this.content+operand.getStringCastString())};
    }

    @Override
    public boolean canOperate(OperationType type, Element operand, boolean reversed) {
        return type==OperationType.ADD;
    }
    
    @Override
    public String getStringCastString() {
        return content;
    }
    
    @Override
    public boolean equals(Object other) {
        return other instanceof StringElement && ((StringElement)other).content.equals(this.content);
    }
}
