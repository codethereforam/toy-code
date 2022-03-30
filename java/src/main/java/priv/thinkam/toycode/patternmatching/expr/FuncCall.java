package priv.thinkam.toycode.patternmatching.expr;

import priv.thinkam.toycode.patternmatching.expr.Expr;

/**
 * @author yanganyu
 * @date 2022/3/30
 */
public class FuncCall extends Expr {
    /**
     * Func或者变量Variable
     */
    private final Expr func;
    private final Expr expr;

    public FuncCall(Expr func, Expr expr) {
        this.func = func;
        this.expr = expr;
    }

    public Expr getFunc() {
        return func;
    }

    public Expr getExpr() {
        return expr;
    }
}
