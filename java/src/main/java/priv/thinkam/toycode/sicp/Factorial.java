package priv.thinkam.toycode.sicp;

import java.util.function.IntBinaryOperator;

/**
 * 实现不同风格的阶乘
 *
 * @author thinkam
 * @date 2020/1/19 21:43
 */
public class Factorial {

    /**
     * 函数式递归
     */
    public static int functionalRecursiveFactorial(int n) {
        if(n >= 1) {
            return n * functionalRecursiveFactorial(n - 1);
        } else {
            return 1;
        }
    }

    /**
     * 函数式迭代-方式1
     */
    public static int functionalIterativeFactorial1(int n) {
        Recursive<IntBinaryOperator> recursive = new Recursive<>();
        recursive.function = (i, r) -> {
            if(i <= n) {
                return recursive.function.applyAsInt(i + 1, r * i);
            } else {
                return r;
            }
        };
        return recursive.function.applyAsInt(1, 1);
    }

    public static class Recursive<Function> {
        public Function function;
    }

    /**
     * 函数式迭代-方式2
     */
    public static int functionalIterativeFactorial2(int n) {
        return foo(1, 1, n);
    }

    private static int foo(int i, int r, int n) {
        if(i <= n) {
            return foo(i + 1, r * i, n);
        } else {
            return r;
        }
    }

    /**
     * 函数式迭代-方式3
     */
    public static int functionalIterativeFactorial3(int n) {
        class Inner {
            int inner(int i, int r) {
                if (i <= n) {
                    return inner(i + 1, r * i);
                } else {
                    return r;
                }
            }
        }
        return new Inner().inner(1, 1);
    }

    /**
     * 命令式迭代（对外表现依然是函数）
     */
    public static int imperativeIterativeFactorial1(int n) {
        int i = 1;
        int r = 1;
        while (i <= n) {
            r = r * i;
            i++;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(functionalRecursiveFactorial(5) == 120);
        System.out.println(functionalIterativeFactorial1(5) == 120);
        System.out.println(functionalIterativeFactorial2(5) == 120);
        System.out.println(functionalIterativeFactorial3(5) == 120);
        System.out.println(imperativeIterativeFactorial1(5) == 120);
    }
}
