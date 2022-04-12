package priv.thinkam.toycode.patternmatching.base;

import priv.thinkam.toycode.patternmatching.expr.BinOp;
import priv.thinkam.toycode.patternmatching.expr.Expr;
import priv.thinkam.toycode.patternmatching.expr.Num;

public enum OP {
    PLUS("+") {
        @Override
        public Expr apply(Expr e1, Expr e2) {
            if (e1 instanceof Num && ((Num) e1).getVal() == 0) {
                return e2;
            } else if (e2 instanceof Num && ((Num) e2).getVal() == 0) {
                return e1;
            } else if (e1 instanceof Num && e2 instanceof Num) {
                return new Num(((Num) e1).getVal() + ((Num) e2).getVal());
            } else {
                return new BinOp(PLUS, e1, e2);
            }
        }
    },
    MINUS("-") {
        @Override
        public Expr apply(Expr e1, Expr e2) {
            if (e2 instanceof Num && ((Num) e2).getVal() == 0) {
                return e1;
            } else if (e1 instanceof Num && e2 instanceof Num) {
                return new Num(((Num) e1).getVal() - ((Num) e2).getVal());
            } else {
                return new BinOp(MINUS, e1, e2);
            }
        }
    },
    DIVIDE("/") {
        @Override
        public Expr apply(Expr e1, Expr e2) {
            if (e1 instanceof Num && ((Num) e1).getVal() == 0) {
                return Num.ZERO;
            } else if (e2 instanceof Num && ((Num) e2).getVal() == 0) {
                throw new UnsupportedOperationException(e1 + " / 0");
            } else if (e2 instanceof Num && ((Num) e2).getVal() == 1) {
                return e1;
            } else if (e1 instanceof Num && e2 instanceof Num) {
                return new Num(((Num) e1).getVal() / ((Num) e2).getVal());
            } else {
                return new BinOp(DIVIDE, e1, e2);
            }
        }
    },
    MULTI("*") {
        @Override
        public Expr apply(Expr e1, Expr e2) {
            if (e1 instanceof Num && ((Num) e1).getVal() == 0) {
                return new Num(0);
            } else if (e2 instanceof Num && ((Num) e2).getVal() == 0) {
                return new Num(0);
            } else if (e1 instanceof Num && ((Num) e1).getVal() == 1) {
                return e2;
            } else if (e2 instanceof Num && ((Num) e2).getVal() == 1) {
                return e1;
            } else if (e1 instanceof Num && e2 instanceof Num) {
                return new Num(((Num) e1).getVal() * ((Num) e2).getVal());
            } else {
                return new BinOp(MULTI, e1, e2);
            }
        }
    };

    public String getValue() {
        return value;
    }

    private final String value;

    OP(String value) {
        this.value = value;
    }

    public abstract Expr apply(Expr e1, Expr e2);
}