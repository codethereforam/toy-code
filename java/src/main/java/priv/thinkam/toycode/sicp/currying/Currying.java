package priv.thinkam.toycode.sicp.currying;

import java.util.function.DoubleUnaryOperator;

/**
 * @author thinkam
 * @date 2019/12/18 20:53
 */
public class Currying {

    public static DoubleUnaryOperator curriedConverter(double a, double b) {
        return x -> a * x + b;
    }

    public static void main(String[] args) {
        // f1(x)=2x+3
        DoubleUnaryOperator f1 = curriedConverter(2, 3);
        System.out.println(f1.applyAsDouble(2));
        // f1(x)=3x+2
        DoubleUnaryOperator f2 = curriedConverter(3, 2);
        System.out.println(f2.applyAsDouble(2));
    }
}
