package priv.thinkam.toycode.sicp.rat.fp;

import priv.thinkam.toycode.sicp.rat.$;

import java.util.function.IntFunction;

import static priv.thinkam.toycode.sicp.rat.fp.Cons.*;

/**
 * @author thinkam
 * @date 2019/11/12 23:54
 */
public class RationalNumber {

    public static IntFunction<Integer> makeRat(int numer, int denom) {
        int gcd = $.gcd(numer, denom);
        return cons(numer / gcd, denom / gcd);
    }

    public static int numer(IntFunction<Integer> x) {
        return car(x);
    }

    public static int denom(IntFunction<Integer> x) {
        return cdr(x);
    }

    public static IntFunction<Integer> plusRat(IntFunction<Integer> a, IntFunction<Integer> b) {
        return makeRat(numer(a) * denom(b) + numer(b) * denom(a), denom(a) * denom(b));
    }

    public static IntFunction<Integer> multiplyRat(IntFunction<Integer> a, IntFunction<Integer> b) {
        return makeRat(numer(a) * numer(b), denom(a) * denom(b));
    }

    public static String printRat(IntFunction<Integer> x) {
        return numer(x) + "/" + denom(x);
    }

    public static void main(String[] args) {
        IntFunction<Integer> a = makeRat(2, 3);
        IntFunction<Integer> b = makeRat(1, 12);
        System.out.println("a = " + printRat(a));
        System.out.println("b = " + printRat(b));
        System.out.println("a + b = " + printRat(plusRat(a, b)));
        System.out.println("a * b = " + printRat(multiplyRat(a, b)));
    }
}
