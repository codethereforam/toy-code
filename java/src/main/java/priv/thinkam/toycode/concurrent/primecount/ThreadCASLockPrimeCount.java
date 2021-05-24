package priv.thinkam.toycode.concurrent.primecount;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

import static priv.thinkam.toycode.concurrent.primecount.Common.NUMBER_UPPER_LIMIT;
import static priv.thinkam.toycode.concurrent.primecount.Common.isPrime;

/**
 * 线程 + CAS锁并发模型
 */
class ThreadCASLockPrimeCount {
    public static final int SPLIT_COUNT = Runtime.getRuntime().availableProcessors();
    private final AtomicInteger currentNum = new AtomicInteger(2);
    private final LongAdder primeCount = new LongAdder();

    private int getAndIncreaseNum() {
        return currentNum.getAndUpdate(value -> value <= NUMBER_UPPER_LIMIT ? value + 1 : value);
    }

    private void increasePrimeCount() {
        primeCount.increment();
    }

    public void doCountPrime(CountDownLatch latch) {
        int num;
        while ((num = getAndIncreaseNum()) <= NUMBER_UPPER_LIMIT) {
            if (isPrime(num)) {
                increasePrimeCount();
            }
        }
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        ThreadCASLockPrimeCount primeCount = new ThreadCASLockPrimeCount();
        CountDownLatch latch = new CountDownLatch(SPLIT_COUNT);
        for (int i = 0; i < SPLIT_COUNT; i++) {
            new Thread(() -> primeCount.doCountPrime(latch)).start();
        }
        latch.await();
        System.out.println(primeCount.primeCount);

        System.out.println(System.currentTimeMillis() - startTime);
    }
}