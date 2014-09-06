
package com.iconmaster.iconuscalc.util;

/**
 *
 * @author iconmaster
 */
public class MathUtils {
    public static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }
    
    public static int[] reduce(int a, int b) {
        int g = gcd(a,b);
        if (g==0) {
            return new int[] {a,b};
        }
        return new int[] {a/g, b/g};
    }
    
    public static final double EPSILON = 1e-15;
    
    public static int[] toFraction(double a) {
        for (int i=1;i<1000000000;i*=10) {
            if (a*i==Math.floor(a*i) || a*i+EPSILON==Math.floor(a*i) || a*i-EPSILON==Math.floor(a*i)) {
                return reduce((int) Math.round(a*i),i);
            }
        }
        for (int i=1;i<=Math.ceil(a);i++) {
            if (i/a==Math.floor(i/a)) {
                return new int[] {i,Math.round((float) (i/a))};
            }
        }
        return new int[] {Math.round((float) (a*1000000000)),1000000000};
    }
}
