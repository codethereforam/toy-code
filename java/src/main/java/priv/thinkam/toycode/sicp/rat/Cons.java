package priv.thinkam.toycode.sicp.rat;

/**
 * @author thinkam
 * @date 2019/11/12 23:57
 */
public class Cons<L,R> {
    private L left;
    private R right;

    public Cons(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L car() {
        return left;
    }

    public R cdr() {
        return right;
    }
}
