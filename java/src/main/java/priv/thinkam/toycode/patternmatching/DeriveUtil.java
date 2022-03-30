package priv.thinkam.toycode.patternmatching;

import priv.thinkam.toycode.patternmatching.base.Env;
import priv.thinkam.toycode.patternmatching.base.OP;
import priv.thinkam.toycode.patternmatching.base.TriFunction;
import priv.thinkam.toycode.patternmatching.expr.BinOp;
import priv.thinkam.toycode.patternmatching.expr.Expr;
import priv.thinkam.toycode.patternmatching.expr.Num;
import priv.thinkam.toycode.patternmatching.expr.Variable;

import java.util.function.Function;

import static priv.thinkam.toycode.patternmatching.base.OP.*;

/**
 * 伪模式匹配求导
 * 
 * @author yanganyu
 * @date 2022/3/18
 */
public class DeriveUtil {

    private static Expr patternMatchExpr(Expr e, TriFunction<OP, Expr, Expr, Expr> binOpCase, Function<Num, Expr> numCase, Function<Variable, Expr> variableCase) {
        if (e instanceof Num) {
            return numCase.apply((Num) e);
        } else if (e instanceof Variable) {
            return variableCase.apply((Variable) e);
        } else if (e instanceof BinOp) {
            BinOp binOp = (BinOp) e;
            return binOpCase.apply(binOp.getOpName(), binOp.getLeft(), binOp.getRight());
        } else {
            throw new AssertionError();
        }
    }

    public static Expr derive(Expr expr, Variable v) {
        return patternMatchExpr(expr,
                (func, l, r) -> {
                    switch (func) {
                        // d(u+v)/dx = du/dx + dv/dx
                        case PLUS:
                            return PLUS.apply(derive(l, v), derive(r, v));
                        // d(u-v)/dx = du/dx - dv/dx
                        case MINUS:
                            return MINUS.apply(derive(l, v), derive(r, v));
                        // d(uv)/dx = u(dv/dx) + v(du/dx)
                        case MULTI:
                            return PLUS.apply(MULTI.apply(l, derive(r, v)), MULTI.apply(r, derive(l, v)));
                        // d(u/v)/dx = ((du/dx)*v - u*(dv/dx)) / v^2
                        case DIVIDE:
                            return DIVIDE.apply(MINUS.apply(MULTI.apply(derive(l, v), r), MULTI.apply(l, derive(r, v))), MULTI.apply(v, v));
                        default:
                            throw new AssertionError();
                    }
                },
                number -> Num.ZERO,
                var -> var.equals(v) ? Num.ONE : Num.ZERO);
    }

    public static void main(String[] args) {
        Variable x = new Variable('x');

        // x^2 + x + 1 -> (+ x^2 (+ x 1)) -> (+ (+ x x) 1)
        BinOp f = new BinOp(PLUS, new BinOp(MULTI, x, x), new BinOp(PLUS, x, new Num(1)));
        System.out.println("f(x)=" + f);

        Expr df = derive(f, x);
        System.out.println("df(x)=" + df);
        System.out.println(Calculator.calculate(df, new Env(x, new Num(3))));

        Expr ddf = derive(df, x);
        System.out.println("ddf(x)=" + ddf);
        System.out.println(Calculator.calculate(ddf, new Env(x, new Num(1))));
    }
}
