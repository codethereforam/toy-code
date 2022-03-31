package priv.thinkam.toycode.patternmatching.expr;

import priv.thinkam.toycode.patternmatching.base.Env;

/**
 * 闭包的数据结构定义，包含一个函数定义 f 和它定义时所在的环境
 *
 * @author yanganyu
 * @date 2022/3/31
 */
public class Closure extends Expr {
    private final Func func;
    private final Env env;

    public Closure(Func func, Env env) {
        this.func = func;
        this.env = env;
    }

    public Func getFunc() {
        return func;
    }

    public Env getEnv() {
        return env;
    }
}
