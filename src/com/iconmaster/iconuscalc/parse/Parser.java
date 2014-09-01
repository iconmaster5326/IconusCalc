
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.tokenize.IToken;
import com.iconmaster.iconuscalc.element.Element;
import com.iconmaster.iconuscalc.exception.IconusCalcException;
import com.iconmaster.iconuscalc.exception.SyntaxException;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public class Parser {
    private final ArrayList<IToken> tokens;
    
    private int ptr = 0;
    //this is a list of both tokens and elements.
    private ArrayList list = new ArrayList();
    
    public Parser(ArrayList<IToken> tokens) {
        this.tokens = tokens;
    }
    
    private static ArrayList<ParseHandler> handlers = new ArrayList<>();
    
    public static void addHandler(ParseHandler handler) {
        handlers.add(handler);
    }
    
    public static void addDefaultHandlers() {
        addHandler(new FunctionIndexParser());
        addHandler(new FunctionCallParser());
        addHandler(new ExprParser());
        addHandler(new ParenParser());
        addHandler(new WhitespaceParser());
        addHandler(new NumberParser());
        addHandler(new StringParser());
        addHandler(new VarParser());
        addHandler(new OperatorParser(1));
        addHandler(new OperatorParser(2));
        addHandler(new OperatorParser(3));
        addHandler(new FunctionParser());
    }
    
    public ArrayList<Element> parse() throws IconusCalcException {
        //copy all tokens to the miexed list.
        list.addAll(tokens);
        //for each handler, find all handelable tokens. If one's found, insert the array and start over.
        for (ParseHandler handler : handlers) {
            while (!isEOF()) {
                if (handler.matchToken(this)) {
                    ArrayList<Element> a = handler.parse(this);
                    if (a!=null) {
                        int amt = handler.getDelLength(this);
                        for (int i=0;i<amt;i++) {
                            list.remove(ptr);
                        }
                        list.add(ptr, a);
                        ptr = -1;
                    }
                }
                ptr++;
            }
            ptr = 0;
        }
            
        //Flatten the thing, and return the list ONLY if there's no tokens left in it.
        return flatten(list);
    }
    
    public ArrayList<Element> flatten(ArrayList input) throws IconusCalcException {
        ArrayList<Element> out = new ArrayList<>();
        for (Object item : input) {
            if (item instanceof Element) {
                out.add((Element)item);
            } else if (item instanceof ArrayList) {
                out.addAll(flatten((ArrayList) item));
            } else {
                System.out.println(item);
                throw new SyntaxException();
            }
        }
        return out;
    }
    
    public Object getItem(int forward) {
        return list.get(ptr+forward);
    }
    
    public Object getItem() {
        return getItem(0);
    }
    
    public boolean isEOF(int forward) {
        return ptr+forward>=list.size();
    }
    
    public boolean isEOF() {
        return isEOF(0);
    }
    
    public int getPtr() {
        return ptr;
    }
}
