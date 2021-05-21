package priv.thinkam.toycode.concurrent.primecount;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static priv.thinkam.toycode.concurrent.primecount.Common.*;
import static priv.thinkam.toycode.util.ListUtil.split;

/**
 * 多线程 并行 无锁 乱序（性能最佳）
 *
 * @author yanganyu
 * @date 2021/5/19
 */
class ParallelPrimeCount {

    public static final int SPLIT_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(SPLIT_COUNT);
        List<Integer> numberList = IntStream.rangeClosed(2, NUMBER_UPPER_LIMIT).boxed().collect(Collectors.toList());
        // 打乱数字顺序，使每个线程计算时间更均匀，在NUMBER_UPPER_LIMIT为300000时可看出差距
        Collections.shuffle(numberList);

        Future<Integer>[] futures = new Future[SPLIT_COUNT];
        List<List<Integer>> split = split(numberList, SPLIT_COUNT);
        for (int i = 0; i < split.size(); i++) {
            final List<Integer> numbers = split.get(i);
            futures[i] = executor.submit(() -> {
                int primeCount = 0;
                for (Integer number : numbers) {
                    primeCount += mapToPrimeCount(number);
                }
                return primeCount;
            });
        }


        int totalPrimeCount = 0;
        for (int i = 0; i < SPLIT_COUNT; i++) {
            totalPrimeCount += futures[i].get();
        }
        System.out.println(totalPrimeCount);

        System.out.println(System.currentTimeMillis() - startTime);
        executor.shutdown();
    }

}
