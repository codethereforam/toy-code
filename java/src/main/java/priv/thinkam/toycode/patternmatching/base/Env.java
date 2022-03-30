package priv.thinkam.toycode.patternmatching.base;

import priv.thinkam.toycode.patternmatching.expr.Expr;
import priv.thinkam.toycode.patternmatching.expr.Variable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yanganyu
 * @date 2022/3/18
 */
public class Env {
    private Map<Variable, Expr> context;

    public Env(Map<Variable, Expr> context) {
        this.context = context;
    }

    private void init() {
        context = new HashMap<>();
    }

    public static Env emptyEnv() {
        return new Env();
    }

    public Env() {
        context = new HashMap<>();
    }

    public Env(Variable v, Expr e) {
        init();
        assign(v, e);
    }

    public Env(Variable v1, Expr e1,
               Variable v2, Expr e2) {
        init();
        assign(v1, e1);
        assign(v2, e2);
    }

    public void assign(Variable v, Expr n) {
        context.put(v, n);
    }

    public Env let(Variable v, Expr e) {
        context.put(v, e);
        return this;
    }

    @Override
    public String toString() {
        return "Env{" +
                "context=" + context +
                '}';
    }

    public Expr lookup(Variable v) {
        return context.get(v);
    }
}
