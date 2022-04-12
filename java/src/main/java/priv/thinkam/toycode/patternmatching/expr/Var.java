package priv.thinkam.toycode.patternmatching.expr;

import java.util.Objects;

/**
 * @author yanganyu
 * @date 2022/3/18
 */
public class Var extends Expr {
    private final char v;

    public Var(char v) {
        this.v = v;
    }

    public char getValue() {
        return v;
    }

    @Override
    public String toString() {
        return getValue() + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Var var = (Var) o;
        return v == var.v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v);
    }

    public static Var of(char v) {
        return new Var(v);
    }
}
