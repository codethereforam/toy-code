package priv.thinkam.toycode.sicp.rat.fp;

import java.util.function.IntFunction;

/**
 * @author thinkam
 * @date 2019/12/17 20:47
 */
public class Cons {

    public static <R> IntFunction<R> cons(R a, R b) {
        return pick -> {
            if (pick == 1) {
                return a;
            } else {
                return b;
            }
        };
    }

    public static <R> R car(IntFunction<R> x) {
        return x.apply(1);
    }

    public static <R> R cdr(IntFunction<R> x) {
        return x.apply(2);
    }

    public static void main(String[] args) {
        System.out.println(car(cons(3, 5)));
        System.out.println(cdr(cons(3, 5)));

        System.out.println(car(cons("left", "right")));
        System.out.println(cdr(cons("left", "right")));
    }
}
