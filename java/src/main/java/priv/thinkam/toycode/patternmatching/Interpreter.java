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
 * 伪模式匹配解释器
 * <p>
 * NOTE: 参考《怎样写一个解释器》http://www.yinwang.org/blog-cn/2012/08/01/interpreter
 *
 * @author yanganyu
 * @date 2022/3/29
 */
public class Interpreter {

    private static Expr patternMatch(Expr e,
                                     TriFunction<OP, Expr, Expr, Expr> binOpCase,
                                     Function<Num, Expr> numCase,
                                     Function<Var, Expr> variableCase,
                                     TriFunction<Var, Expr, Expr, Expr> letCase,
                                     BiFunction<Var, BinOp, Expr> funcCase,
                                     BiFunction<Expr, Expr, Expr> funcCallCase) {
        if (e instanceof Num) {
            return numCase.apply((Num) e);
        } else if (e instanceof Var) {
            return variableCase.apply((Var) e);
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
        return patternMatch(expr,
                (op, e1, e2) -> op.apply(interpret(e1, env), interpret(e2, env)),
                number -> number,
                env::lookup,
                (v, e, body) -> {
                    Expr v1 = interpret(e, env);
                    return interpret(body, env.ext(v, v1));
                },
                (var, body) -> new Closure(new Func(var, body), env),
                (func, e) -> {
                    Closure v1 = (Closure) interpret(func, env);
                    Expr v2 = interpret(e, env);
                    return interpret(v1.getFunc().getBody(), v1.getEnv().ext(v1.getFunc().getParam(), v2));
                });
    }

    static Expr interpret(Expr expr) {
        return interpret(expr, Env.emptyEnv());
    }

    public static void main(String[] args) {
        System.out.println(interpret(new BinOp(PLUS, Num.of(1), Num.of(2)))); // 3

        System.out.println(interpret(new BinOp(MULTI, Num.of(2), Num.of(3)))); // 6

        System.out.println(interpret(
                new BinOp(MULTI, Num.of(2),
                        new BinOp(PLUS, Num.of(3), Num.of(4))))); // 14

        System.out.println(interpret(
                new BinOp(MULTI,
                        new BinOp(PLUS, Num.of(1), Num.of(2)),
                        new BinOp(PLUS, Num.of(3), Num.of(4))))); // 21

        System.out.println(interpret(new FuncCall(new Func(Var.of('x'), new BinOp(MULTI, Num.of(2), Var.of('x'))),
                Num.of(3)))); // 6

        Func f2 = new Func(Var.of('y'), new BinOp(MULTI, Var.of('x'), Var.of('y')));

        System.out.println(interpret(
                new Let(Var.of('x'), Num.of(2),
                        new Let(Var.of('f'), f2,
                                new FuncCall(Var.of('f'), Num.of(3)))))); // 6

        System.out.println(interpret(
                new Let(Var.of('x'), Num.of(2),
                        new Let(Var.of('f'), f2,
                                new Let(Var.of('x'), Num.of(4),
                                        new FuncCall(Var.of('f'), Num.of(3))))))); // 6
    }
}
