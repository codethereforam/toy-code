package priv.thinkam.toycode.patternmatching;

import priv.thinkam.toycode.patternmatching.base.Env;
import priv.thinkam.toycode.patternmatching.expr.*;

import static priv.thinkam.toycode.patternmatching.base.OP.MULTI;
import static priv.thinkam.toycode.patternmatching.base.OP.PLUS;

/**
 * 模式匹配解释器
 * <p>
 * NOTE: 参考《怎样写一个解释器》http://www.yinwang.org/blog-cn/2012/08/01/interpreter
 *
 * @author yanganyu
 * @date 2022/3/29
 */
public class PatternMatchingInterpreter {

    static Expr interpret(Expr expr, Env env) {
        return switch (expr) {
            case Num num -> num;
            case Var var -> env.lookup(var);
            case Let let -> {
                Expr v1 = interpret(let.getExpr(), env);
                yield interpret(let.getBody(), env.ext(let.getVar(), v1));
            }
            case Func func -> new Closure(new Func(func.getParam(), func.getBody()), env);
            case FuncCall call -> {
                Closure v1 = (Closure) interpret(call.getFunc(), env);
                Expr v2 = interpret(call.getExpr(), env);
                yield interpret(v1.getFunc().getBody(), v1.getEnv().ext(v1.getFunc().getParam(), v2));
            }
            case BinOp binOp -> binOp.getOpName().apply(interpret(binOp.getLeft(), env), interpret(binOp.getRight(), env));
            default -> throw new AssertionError();
        };
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
