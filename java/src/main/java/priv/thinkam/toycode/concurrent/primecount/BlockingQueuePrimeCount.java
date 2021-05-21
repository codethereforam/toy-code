package priv.thinkam.toycode.concurrent.primecount;

import java.util.concurrent.*;

import static priv.thinkam.toycode.concurrent.primecount.Common.NUMBER_UPPER_LIMIT;
import static priv.thinkam.toycode.concurrent.primecount.Common.mapToPrimeCount;

/**
 * 多线程 blockingQueue 并行
 *
 * @author yanganyu
 * @date 2021/5/19
 */
class BlockingQueuePrimeCount {
    public static final int SPLIT_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(SPLIT_COUNT);

        BlockingQueue<Integer> numbersQueue = new ArrayBlockingQueue<>(NUMBER_UPPER_LIMIT - 1);
        // 使用ArrayDeque结果是不对的，奇怪的是效率变得很低！！！
        // Queue<Integer> numbersQueue = new ArrayDeque<>(NUMBER_UPPER_LIMIT - 1);
        for (int i = 2; i <= NUMBER_UPPER_LIMIT; i++) {
            numbersQueue.add(i);
        }

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
