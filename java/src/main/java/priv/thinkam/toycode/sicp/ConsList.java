package priv.thinkam.toycode.sicp;

import java.util.function.IntFunction;

/**
 * @author thinkam
 * @date 2019/12/17 20:47
 */
public class ConsList {

    public static IntFunction<Object> cons(Object a, Object b) {
        return pick -> {
            if (pick == 1) {
                return a;
            } else {
                return b;
            }
        };
    }

    public static Object car(Object original) {
        if (!(original instanceof IntFunction<?>)) {
            throw new IllegalArgumentException("argument must be IntFunction");
        }
        return ((IntFunction<?>) original).apply(1);
    }

    public static Object cdr(Object original) {
        if (!(original instanceof IntFunction<?>)) {
            throw new IllegalArgumentException("argument must be IntFunction");
        }
        return ((IntFunction<?>) original).apply(2);
    }

    public static IntFunction<Object> list(Object... args) {
        if (args.length == 1) {
            return cons(args[0], null);
        } else {
            Object[] newArray = new Object[args.length - 1];
            System.arraycopy(args, 1, newArray, 0, args.length - 1);
            return cons(args[0], list(newArray));
        }
    }

    public static boolean isList(IntFunction<?> list) {
        if (car(list) == null) {
            return false;
        } else {
            if (cdr(list) == null) {
                return true;
            } else {
                Object listCdr = cdr(list);
                if (listCdr instanceof IntFunction<?>) {
                    return isList((IntFunction<?>) listCdr);
                } else {
                    return false;
                }
            }
        }
    }

    public static void printList(IntFunction<?> list) {
        if (!isList(list)) {
            throw new IllegalArgumentException("argument is not list");
        }
        StringBuilder result = new StringBuilder("(");
        while (true){
            result.append(car(list)).append(" ");
            if(cdr(list) == null) {
                break;
            }
            Object listCdr = cdr(list);
            if (listCdr instanceof IntFunction<?>) {
                list = (IntFunction<?>) cdr(list);
            } else {
                throw new IllegalStateException("code has bug");
            }
        }
        System.out.println(result.toString().substring(0, result.length() - 1) + ")");
    }

    public static void main(String[] args) {
        IntFunction<Object> cons1 = cons(3, 5);
        System.out.println(car(cons1) + ", " + cdr(cons1));

        IntFunction<Object> cons2 = cons("left", "right");
        System.out.println(car(cons2) + ", " + cdr(cons2));

        IntFunction<Object> cons3 = cons(1, "two");
        System.out.println(car(cons3) + ", " + cdr(cons3));

        IntFunction<Object> cons4 = cons(1.1, null);
        System.out.println(car(cons4) + ", " + cdr(cons4));

        IntFunction<Object> cons5 = cons('a', cons1);
        System.out.println(car(cons5) + " | " + car(cdr(cons5)) + ", " + cdr(cdr(cons5)));

        IntFunction<Object> list1 = list(1, 2, 3);
        System.out.println(isList(list1));
        printList(list1);
    }
}
