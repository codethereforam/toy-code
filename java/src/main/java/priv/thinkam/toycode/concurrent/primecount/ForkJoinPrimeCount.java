package priv.thinkam.toycode.concurrent.primecount;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

import static priv.thinkam.toycode.concurrent.primecount.Common.NUMBER_UPPER_LIMIT;
import static priv.thinkam.toycode.concurrent.primecount.Common.mapToPrimeCount;

/**
 * fork-join并发
 *
 * @author yanganyu
 */
class ForkJoinPrimeCount extends RecursiveTask<Integer> {
    public static final int SMALLEST_RANGE = 1000;
    private final int from;
    private final int to;
    private final int[] numbers;

    public ForkJoinPrimeCount(int[] numbers, int from, int to) {
        this.numbers = numbers;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from < SMALLEST_RANGE) {
            int primeCount = 0;
            for (int i = from; i <= to; i++) {
                primeCount += mapToPrimeCount(i);
            }
            return primeCount;
        } else {
            int middle = (from + to) / 2;
            ForkJoinPrimeCount task1 = new ForkJoinPrimeCount(numbers, from, middle);
            ForkJoinPrimeCount task2 = new ForkJoinPrimeCount(numbers, middle + 1, to);
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        System.out.println(new ForkJoinPool().invoke(new ForkJoinPrimeCount(
                IntStream.rangeClosed(2, NUMBER_UPPER_LIMIT).toArray(), 2, NUMBER_UPPER_LIMIT)));

        System.out.println(System.currentTimeMillis() - startTime);
    }
}