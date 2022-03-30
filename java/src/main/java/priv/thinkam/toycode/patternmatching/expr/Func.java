package priv.thinkam.toycode.patternmatching.expr;

/**
 * @author yanganyu
 * @date 2022/3/29
 */
public class Func extends Expr {
    private final Variable param;
    private final BinOp body;

    public Func(Variable param, BinOp body) {
        this.param = param;
        this.body = body;
    }

    public Variable getParam() {
        return param;
    }

    public BinOp getBody() {
        return body;
    }
}
