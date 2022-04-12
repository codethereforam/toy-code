package priv.thinkam.toycode.patternmatching.expr;

/**
 * @author yanganyu
 * @date 2022/3/30
 */
public class Let extends Expr {
    private final Var var;
    private final Expr expr;
    private final Expr body;

    public Let(Var var, Expr expr, Expr body) {
        this.var = var;
        this.expr = expr;
        this.body = body;
    }

    public Var getVar() {
        return var;
    }

    public Expr getExpr() {
        return expr;
    }

    public Expr getBody() {
        return body;
    }
}
