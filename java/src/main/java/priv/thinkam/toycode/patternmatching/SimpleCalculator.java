package priv.thinkam.toycode.patternmatching;

import priv.thinkam.toycode.patternmatching.base.OP;
import priv.thinkam.toycode.patternmatching.expr.BinOp;
import priv.thinkam.toycode.patternmatching.expr.Expr;
import priv.thinkam.toycode.patternmatching.expr.Num;

/**
 * @author yanganyu
 * @date 2022/3/18
 */
public class SimpleCalculator {

    public static Expr calculate(Expr expr) {
        if(expr instanceof Num) {
            return expr;
        } else if(expr instanceof BinOp) {
            BinOp binOp = (BinOp) expr;
            return binOp.getOpName().apply(calculate(binOp.getLeft()), calculate(binOp.getRight()));
        } else {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) {
        Expr expr = new BinOp(OP.MULTI,
                new BinOp(OP.PLUS, new Num(1), new Num(2)),
                new BinOp(OP.MINUS, new Num(4), new Num(2)));
        System.out.println(SimpleCalculator.calculate(expr));
    }
}
