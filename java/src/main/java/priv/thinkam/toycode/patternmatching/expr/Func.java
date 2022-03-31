package priv.thinkam.toycode.patternmatching.expr;

/**
 * @author yanganyu
 * @date 2022/3/29
 */
public class Func extends Expr {
    private final Var param;
    private final BinOp body;

    public Func(Var param, BinOp body) {
        this.param = param;
        this.body = body;
    }

    public Var getParam() {
        return param;
    }

    public BinOp getBody() {
        return body;
    }
}
