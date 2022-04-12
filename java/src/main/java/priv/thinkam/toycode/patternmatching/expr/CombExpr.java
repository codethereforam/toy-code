package priv.thinkam.toycode.patternmatching.expr;

import priv.thinkam.toycode.sicp.rat.pp.Cons;

/**
 *  复合表达式
 *
 * @author yanganyu
 * @date 2022/3/30
 */
public class CombExpr extends Expr {
    private final Cons<Expr, Expr> element;

    public CombExpr(Expr left, Expr right) {
        element = new Cons<>(left, right);
    }

    public Expr car() {
        return element.car();
    }

    public Expr cdr() {
        return cdr();
    }
}
