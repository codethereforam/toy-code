package priv.thinkam.toycode.stream;

import java.util.Arrays;
import java.util.stream.LongStream;

/**
 * @author thinkam
 * @date 2019/12/29 20:43
 */
public class Sum {

    public static long sum(long[] intArray) {
        long result = 0;
        for (long i : intArray) {
            result = result + i;
        }
        return result;
    }

    public static long parallelSum(long[] intArray) {
        return Arrays.stream(intArray).parallel().sum();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {
        long[] intArray = LongStream.rangeClosed(0, 100_000_000).toArray();
        sum(intArray);
        parallelSum(intArray);
        sum(intArray);
        parallelSum(intArray);

        long t1 = System.currentTimeMillis();

        sum(intArray);
        sum(intArray);
        sum(intArray);
        sum(intArray);
        sum(intArray);
        System.out.println(System.currentTimeMillis() - t1);

        long t2 = System.currentTimeMillis();
        parallelSum(intArray);
        parallelSum(intArray);
        parallelSum(intArray);
        parallelSum(intArray);
        parallelSum(intArray);
        System.out.println(System.currentTimeMillis() - t2);
    }
}
