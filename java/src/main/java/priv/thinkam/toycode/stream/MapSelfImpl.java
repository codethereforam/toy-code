package priv.thinkam.toycode.stream;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 用递归和迭代自己实现对集合的map操作
 *
 * @author thinkam
 * @date 2019/12/29 22:43
 */
public class MapSelfImpl {
    /**
     * 递归实现map
     */
    public static <T, R> List<R> recursiveMap(Function<T, R> func, List<T> list) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<R> l = new ArrayList<>();
            l.add(func.apply(list.get(0)));
            l.addAll(recursiveMap(func, list.subList(1, list.size())));
            return l;
        }
    }

    /**
     * 迭代实现map
     */
    public static <T, R> List<R> iterativeMap(Function<T, R> func, List<T> list) {
        List<R> l = new ArrayList<>();
        for (T t : list) {
            l.add(func.apply(t));
        }
        return l;
    }

    public static void main(String[] args) {
        final List<Integer> list = ImmutableList.of(1, 2, 3, 4, 5);
        System.out.println("recursiveMap square result: " + recursiveMap(square(), list));
        System.out.println("iterativeMap square result: " + iterativeMap(square(), list));
        System.out.println("recursiveMap encircledByQuote result: " + recursiveMap(encircledByQuote(), list));
        System.out.println("iterativeMap encircledByQuote result: " + iterativeMap(encircledByQuote(), list));

        // 比较性能
        final List<Integer> list1 = IntStream.range(1, 1000).boxed().collect(Collectors.toList());
        final int repeatTimes = 10;
        long t1 = System.currentTimeMillis();
        repeat(() -> recursiveMap(square(), list1), repeatTimes);
        System.out.println(System.currentTimeMillis() - t1);

        long t2 = System.currentTimeMillis();
        repeat(() -> iterativeMap(square(), list1), repeatTimes);
        System.out.println(System.currentTimeMillis() - t2);
    }

    /**
     * 用引号环绕
     */
    private static Function<Integer, String> encircledByQuote() {
        return x -> "\"" + x + "\"";
    }

    /**
     * 平方
     */
    private static UnaryOperator<Integer> square() {
        return x -> x * x;
    }

    private static void repeat(Operation operation, int repeatTimes) {
        for (int i = 0; i < repeatTimes; i++) {
            operation.operate();
        }
    }

    @FunctionalInterface
    private interface Operation {
        void operate();
    }
}
