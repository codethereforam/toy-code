package priv.thinkam.toycode.sicp.rat.pp;

import priv.thinkam.toycode.sicp.rat.$;

/**
 * @author thinkam
 * @date 2019/11/12 23:54
 */
public class RationalNumber {


    public static Cons<Integer, Integer> makeRat(int numer, int denom) {
        int gcd = $.gcd(numer, denom);
        return new Cons<>(numer / gcd, denom / gcd);
    }

    public static int numer(Cons<Integer, Integer> x) {
        return car(x);
    }

    public static int denom(Cons<Integer, Integer> x) {
        return cdr(x);
    }

    public static Cons<Integer, Integer> plusRat(Cons<Integer, Integer> a, Cons<Integer, Integer> b) {
        return makeRat(numer(a) * denom(b) + numer(b) * denom(a), denom(a) * denom(b));
    }

    public static Cons<Integer, Integer> multiplyRat(Cons<Integer, Integer> a, Cons<Integer, Integer> b) {
        return makeRat(numer(a) * numer(b), denom(a) * denom(b));
    }

    public static String printRat(Cons<Integer, Integer> x) {
        return numer(x) + "/" + denom(x);
    }

    public static void main(String[] args) {
        Cons<Integer, Integer> a = makeRat(2, 3);
        Cons<Integer, Integer> b = makeRat(1, 12);
        System.out.println("a = " + printRat(a));
        System.out.println("b = " + printRat(b));
        System.out.println("a + b = " + printRat(plusRat(a, b)));
        System.out.println("a * b = " + printRat(multiplyRat(a, b)));
    }

    private static <L, R> L car(Cons<L, R> cons) {
        return cons.car();
    }

    private static <L, R> R cdr(Cons<L, R> cons) {
        return cons.cdr();
    }

}
