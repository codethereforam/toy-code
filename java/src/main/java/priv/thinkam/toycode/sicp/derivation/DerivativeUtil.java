package priv.thinkam.toycode.sicp.derivation;

import java.util.function.DoubleUnaryOperator;

/**
 * @author thinkam
 * @date 2019/12/12 22:57
 */
public class DerivativeUtil {
    private static final double DELTA_X = 0.000001;

    public static DoubleUnaryOperator d(DoubleUnaryOperator f) {
        return x -> (f.applyAsDouble(x + DELTA_X) - f.applyAsDouble(x)) / DELTA_X;
    }

    public static void main(String[] args) {
        DoubleUnaryOperator f = x -> x * x + x + 1;
        DoubleUnaryOperator f1 = d(f);
        System.out.println(f1.applyAsDouble(3));
        DoubleUnaryOperator f2 = d(f1);
        System.out.println(f2.applyAsDouble(1));
    }
}
