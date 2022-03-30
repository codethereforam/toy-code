package priv.thinkam.toycode.patternmatching.expr;

import java.util.Objects;

/**
 * @author yanganyu
 * @date 2022/3/18
 */
public class Variable extends Expr {
    private final char v;

    public Variable(char v) {
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
        Variable variable = (Variable) o;
        return v == variable.v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v);
    }

    public static Variable of(char v) {
        return new Variable(v);
    }
}
