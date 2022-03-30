package priv.thinkam.toycode.patternmatching.expr;

import priv.thinkam.toycode.patternmatching.base.OP;

/**
 * @author yanganyu
 * @date 2022/3/18
 */
public class BinOp extends Expr {
    public BinOp(OP OPName, Expr left, Expr right) {
        this.OPName = OPName;
        this.left = left;
        this.right = right;
    }

    private final OP OPName;
    private final Expr left;
    private final Expr right;

    public OP getOpName() {
        return OPName;
    }

    public Expr getLeft() {
        return left;
    }

    public Expr getRight() {
        return right;
    }

    @Override
    public String toString() {
        return '(' + OPName.getValue() + " " + left + " " + right + ')';
    }
}
