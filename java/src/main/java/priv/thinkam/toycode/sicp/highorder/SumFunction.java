package priv.thinkam.toycode.sicp.highorder;

import java.util.function.DoubleUnaryOperator;

/**
 * 两函数求和
 *
 * @author thinkam
 * @date 2019/12/13 21:00
 */
public class SumFunction {

    /**
     * 两函数结果和的函数
     *
     * @param f1 一个函数
     * @param f2 另一个函数
     * @return 两函数结果和的函数
     */
    public static DoubleUnaryOperator sum(DoubleUnaryOperator f1, DoubleUnaryOperator f2) {
        return x -> f1.applyAsDouble(x) + f2.applyAsDouble(x);
    }

    /**
     * 平方函数
     *
     * @return 平方函数
     */
    public static DoubleUnaryOperator square() {
        return x -> x * x;
    }

    /**
     * 立方函数
     *
     * @return 立方函数
     */
    public static DoubleUnaryOperator cube() {
        return x -> x * x * x;
    }

    public static void main(String[] args) {
        // x^2 + x^3
        DoubleUnaryOperator f3 = sum(square(), cube());
        System.out.println(f3.applyAsDouble(3) == 36);
        DoubleUnaryOperator f4 = sum(x -> x * x, x -> x * x * x);
        System.out.println(f4.applyAsDouble(3) == 36);
    }
}
