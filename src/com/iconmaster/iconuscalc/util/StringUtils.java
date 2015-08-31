
package com.iconmaster.iconuscalc.util;

import java.text.DecimalFormat;

/**
 *
 * @author iconmaster
 */
public class StringUtils {
	public static String truncateString(String str, int places) {
		return str.length()<=places ? str : str.substring(0, places-1)+"…";
	}
	
	public static String renderNumber(Double number) {
		if (number.isNaN()) {return "NaN";}
		DecimalFormat df;
		if (number > Math.pow(10.0,9.0) || (number < Math.pow(10.0,-9.0) && number > -Math.pow(10.0,-8.0) && number != 0) || number< -Math.pow(10.0,8.0)) {
			df = new DecimalFormat("0.######E0##");
		} else {
			df = new DecimalFormat("###,###.############");
		}
		
		return df.format(number);
	}

	private static final char[] SYMBOLS = new char[] {'+','-','*','/','=','<','>','^'};
	
	public static boolean isSymbol(char c) {
		for (char s : SYMBOLS) {
			if (s==c) {
				return true;
			}
		}
		return false;
	}
	
	public static String padLeft(String str, int places) {
		String pad = "";
		for (int i=0;i<places-str.length();i++) {
			pad+=" ";
		}
		return str+pad;
	}
	
	public static String[] multilineSplit(String input, int size) {
		int nSubs = input.length()/size+1;
		if (input.length()%size==0) {
			nSubs--;
		}
		String[] result = new String[nSubs];
		for (int i=0;i<nSubs;i++) {
			result[i] = input.substring(i*size, Math.min(i*size+size,input.length()));
		}
		return result;
	}
}
