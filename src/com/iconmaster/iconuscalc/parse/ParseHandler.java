
package com.iconmaster.iconuscalc.parse;

import com.iconmaster.iconuscalc.exception.IconusCalcException;
import java.util.ArrayList;

/**
 *
 * @author iconmaster
 */
public interface ParseHandler {
	public boolean matchToken(Parser p);
	public ArrayList parse(Parser p) throws IconusCalcException;
	public int getDelLength(Parser p);
}
