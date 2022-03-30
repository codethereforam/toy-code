package priv.thinkam.toycode.patternmatching.expr;

/**
 * @author yanganyu
 * @date 2022/3/29
 */
public class Func extends Expr {
    private final Variable param;
    private final BinOp binOp;

    public Func(Variable param, BinOp binOp) {
        this.param = param;
        this.binOp = binOp;
    }

    public Variable getParam() {
        return param;
    }

    public BinOp getBinOp() {
        return binOp;
    }
}
