package priv.thinkam.toycode.sicp.rat.pp;

/**
 * @author thinkam
 * @date 2019/11/12 23:57
 */
public class Cons<L, R> {
    private final L left;
    private final R right;

    public Cons(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L car() {
        return this.left;
    }

    public R cdr() {
        return this.right;
    }
}
