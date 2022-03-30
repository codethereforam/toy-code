package priv.thinkam.toycode.patternmatching.expr;

/**
 * @author yanganyu
 * @date 2022/3/18
 */
public class Num extends Expr {
    public static final Num ZERO = new Num(0);
    public static final Num ONE = new Num(1);

    private final int val;

    public Num(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return val + "";
    }

    public static Num of(int val) {
        return new Num(val);
    }
}
