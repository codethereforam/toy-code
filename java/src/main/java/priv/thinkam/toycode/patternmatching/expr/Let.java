package priv.thinkam.toycode.patternmatching.expr;

import priv.thinkam.toycode.patternmatching.expr.Expr;
import priv.thinkam.toycode.patternmatching.expr.Variable;

/**
 * @author yanganyu
 * @date 2022/3/30
 */
public class Let extends Expr {
    private final Variable var;
    private final Expr expr;
    private final Expr body;

    public Let(Variable var, Expr expr, Expr body) {
        this.var = var;
        this.expr = expr;
        this.body = body;
    }

    public Variable getVar() {
        return var;
    }

    public Expr getExpr() {
        return expr;
    }

    public Expr getBody() {
        return body;
    }
}
