package priv.thinkam.toycode.concurrent.primecount;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static priv.thinkam.toycode.concurrent.primecount.Common.NUMBER_UPPER_LIMIT;
import static priv.thinkam.toycode.concurrent.primecount.Common.mapToPrimeCount;

/**
 * 多线程 concurrentQueue 并行
 *
 * @author yanganyu
 * @date 2021/5/19
 */
public class ConcurrentQueuePrimeCount {
    public static final int SPLIT_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(SPLIT_COUNT);

        // 1. 使用ConcurrentLinkedQueue
        Queue<Integer> numbersQueue = IntStream.rangeClosed(2, NUMBER_UPPER_LIMIT)
                .boxed().collect(Collectors.toCollection(ConcurrentLinkedQueue::new));

        // 2. 使用BlockingQueue
//        Queue<Integer> numbersQueue = new ArrayBlockingQueue<>(NUMBER_UPPER_LIMIT - 1, false,
//                IntStream.rangeClosed(2, NUMBER_UPPER_LIMIT)
//                        .boxed()
//                        .collect(Collectors.toList()));

        // 使用ArrayDeque结果是不对的，奇怪的是效率变得很低！！！
//        Queue<Integer> numbersQueue = IntStream.rangeClosed(2, NUMBER_UPPER_LIMIT)
//                .boxed().collect(Collectors.toCollection(ArrayDeque::new));

        Future<Integer>[] futures = new Future[SPLIT_COUNT];
        for (int i = 0; i < SPLIT_COUNT; i++) {
            futures[i] = executor.submit(() -> {
                int primeCount = 0;
                while (true) {
                    Integer number = numbersQueue.poll();
                    if (number == null) {
                        break;
                    } else {
                        primeCount += mapToPrimeCount(number);
                    }
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
