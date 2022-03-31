package priv.thinkam.toycode.patternmatching.base;

import priv.thinkam.toycode.patternmatching.expr.Expr;
import priv.thinkam.toycode.patternmatching.expr.Var;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanganyu
 * @date 2022/3/18
 */
public class Env {
    private Map<Var, Expr> context;

    public Env(Map<Var, Expr> context) {
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

    public Env(Var v, Expr e) {
        init();
        assign(v, e);
    }

    public Env(Var v1, Expr e1,
               Var v2, Expr e2) {
        init();
        assign(v1, e1);
        assign(v2, e2);
    }

    public void assign(Var v, Expr n) {
        context.put(v, n);
    }

    public Env let(Var v, Expr e) {
        context.put(v, e);
        return this;
    }

    @Override
    public String toString() {
        return "Env{" +
                "context=" + context +
                '}';
    }

    public Expr lookup(Var v) {
        return context.get(v);
    }
}
