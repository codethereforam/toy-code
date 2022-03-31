package priv.thinkam.toycode.patternmatching;

import priv.thinkam.toycode.patternmatching.base.Env;
import priv.thinkam.toycode.patternmatching.base.OP;
import priv.thinkam.toycode.patternmatching.base.TriFunction;
import priv.thinkam.toycode.patternmatching.expr.BinOp;
import priv.thinkam.toycode.patternmatching.expr.Expr;
import priv.thinkam.toycode.patternmatching.expr.Num;
import priv.thinkam.toycode.patternmatching.expr.Var;

import java.util.function.Function;

/**
 * @author yanganyu
 * @date 2022/3/18
 */
public class Calculator {

    private static Expr patternMatchExpr(Expr e, TriFunction<OP, Expr, Expr, Expr> binOpCase, Function<Num, Expr> numberCase,
                                         Function<Var, Expr> variableCase) {
        if (e instanceof Num) {
            return numberCase.apply(((Num) e));
        }  else if (e instanceof Var) {
            return variableCase.apply((Var) e);
        } else if (e instanceof BinOp) {
            BinOp binOp = (BinOp) e;
            return binOpCase.apply(binOp.getOpName(), binOp.getLeft(), binOp.getRight());
        } else {
            throw new AssertionError();
        }
    }

    public static Expr calculate(Expr expr, Env env) {
        return patternMatchExpr(expr,
                (func, left, right) -> func.apply(calculate(left, env) , calculate(right, env)),
                e -> e,
                v -> {
                    Num num = (Num) env.lookup(v);
                    if (num == null) {
                        throw new UnsupportedOperationException(v + " can't be lookup from env, env: " + env);
                    } else {
                        return num;
                    }
                });
    }

    public static Expr calculate(Expr expr) {
        return calculate(expr, Env.emptyEnv());
    }

    public static void main(String[] args) {
        Expr expr1 = new BinOp(OP.MULTI,
                new BinOp(OP.PLUS, new Num(1), new Num(2)),
                new BinOp(OP.MINUS, new Num(4), new Num(2)));
        System.out.println(expr1 + " = " + Calculator.calculate(expr1));

        Var x = new Var('x');
        Expr expr2 = new BinOp(OP.PLUS, x, new Num(2));
        System.out.println(expr2 + " = " + Calculator.calculate(expr2, new Env().let(x, new Num(3))));
    }
}
