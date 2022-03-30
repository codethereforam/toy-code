package priv.thinkam.toycode.patternmatching;

import priv.thinkam.toycode.patternmatching.base.Env;
import priv.thinkam.toycode.patternmatching.base.OP;
import priv.thinkam.toycode.patternmatching.base.TriFunction;
import priv.thinkam.toycode.patternmatching.expr.*;

import java.util.function.BiFunction;
import java.util.function.Function;
import static priv.thinkam.toycode.patternmatching.base.OP.MULTI;
import static priv.thinkam.toycode.patternmatching.base.OP.PLUS;

/**
 * @author yanganyu
 * @date 2022/3/29
 */
public class Interpreter {

    private static Expr patternMatchExpr(Expr e,
                                         TriFunction<OP, Expr, Expr, Expr> binOpCase,
                                         Function<Num, Expr> numCase,
                                         Function<Variable, Expr> variableCase,
                                         TriFunction<Variable, Expr, Expr, Expr> letCase,
                                         BiFunction<Variable, BinOp, Expr> funcCase,
                                         BiFunction<Expr, Expr, Expr> funcCallCase) {
        if (e instanceof Num) {
            return numCase.apply((Num) e);
        } else if (e instanceof Variable) {
            return variableCase.apply((Variable) e);
        } else if (e instanceof BinOp) {
            BinOp binOp = (BinOp) e;
            return binOpCase.apply(binOp.getOpName(), binOp.getLeft(), binOp.getRight());
        } else if (e instanceof Let) {
            Let let = (Let) e;
            return letCase.apply(let.getVar(), let.getExpr(), let.getBody());
        } else if (e instanceof Func) {
            Func func = (Func) e;
            return funcCase.apply(func.getParam(), func.getBody());
        } else if (e instanceof FuncCall) {
            FuncCall funcCall = (FuncCall) e;
            return funcCallCase.apply(funcCall.getFunc(), funcCall.getExpr());
        } else {
            throw new AssertionError();
        }
    }

    static Expr interpret(Expr expr, Env env) {
        return patternMatchExpr(expr,
                (op, e1, e2) -> op.apply(interpret(e1, env), interpret(e2, env)),
                number -> number,
                env::lookup,
                (v, e, body) -> interpret(body, env.let(v, e)),
                Func::new,
                (func, e) -> {
                    Func v1 = (Func) interpret(func, env);
                    Expr v2 = interpret(e, env);
                    return interpret(v1.getBody(), env.let(v1.getParam(), v2));
                });
    }

    static Expr interpret(Expr expr) {
        return interpret(expr, Env.emptyEnv());
    }

    public static void main(String[] args) {
        Variable x = new Variable('x');
        Func f1 = new Func(x, new BinOp(PLUS, new BinOp(MULTI, x, x), new BinOp(PLUS, x, new Num(1))));
        Expr e1 = new FuncCall(f1, Num.of(2));
        System.out.println(interpret(e1));

        Func f2 = new Func(Variable.of('y'), new BinOp(MULTI, Variable.of('x'), Variable.of('y')));
        Expr e2 =
                new Let(x, Num.of(2),
                        new Let(Variable.of('f'), f2,
                                new Let(Variable.of('x'), Num.of(4),
                                        new FuncCall(Variable.of('f'), Num.of(3)))));
        System.out.println(interpret(e2));
    }
}
