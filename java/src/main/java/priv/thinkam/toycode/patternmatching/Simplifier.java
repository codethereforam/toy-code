package priv.thinkam.toycode.patternmatching;

import priv.thinkam.toycode.patternmatching.base.OP;
import priv.thinkam.toycode.patternmatching.base.TriFunction;
import priv.thinkam.toycode.patternmatching.expr.BinOp;
import priv.thinkam.toycode.patternmatching.expr.Expr;
import priv.thinkam.toycode.patternmatching.expr.Num;

import java.util.function.Function;
import java.util.function.Supplier;

import static priv.thinkam.toycode.patternmatching.base.OP.PLUS;
import static priv.thinkam.toycode.patternmatching.base.OP.MULTI;

/**
 * 伪模式匹配化简
 *
 * @author yanganyu
 * @date 2022/3/18
 */
public class Simplifier {

    static <T> T patternMatchExpr(Expr e, TriFunction<OP, Expr, Expr, T> binOpCase, Function<Integer, T> numCase,
                                  Supplier<T> defaultCase) {
        if (e instanceof Num) {
            return numCase.apply(((Num) e).getVal());
        } else if (e instanceof BinOp) {
            BinOp binOp = (BinOp) e;
            return binOpCase.apply(binOp.getOpName(), binOp.getLeft(), binOp.getRight());
        } else {
            return defaultCase.get();
        }
    }

    public static Expr simplify(Expr e) {
        TriFunction<OP, Expr, Expr, Expr> binOpCase = (opName, left, right) -> {
            if (PLUS.equals(opName)) {
                if (left instanceof Num && ((Num) left).getVal() == 0) {
                    return right;
                }
                if (right instanceof Num && ((Num) right).getVal() == 0) {
                    return left;
                }
            }
            if (MULTI.equals(opName)) {
                if (left instanceof Num && ((Num) left).getVal() == 1) {
                    return right;
                }
                if (right instanceof Num && ((Num) right).getVal() == 1) {
                    return left;
                }
            }
            return new BinOp(opName, left, right);
        };
        return patternMatchExpr(e, binOpCase, Num::new, () -> new Num(0));
    }

    public static void main(String[] args) {
        System.out.println(simplify(new BinOp(PLUS, new Num(2), new Num(0))));
        System.out.println(simplify(new BinOp(PLUS, new Num(1), new Num(2))));
    }
}
