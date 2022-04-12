package priv.thinkam.toycode.patternmatching.base;

public interface TriFunction<S, T, U, R> {
    R apply(S s, T t, U u);
}