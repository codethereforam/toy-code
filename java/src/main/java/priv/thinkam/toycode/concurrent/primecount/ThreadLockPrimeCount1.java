package priv.thinkam.toycode.concurrent.primecount;

import java.util.concurrent.CountDownLatch;

import static priv.thinkam.toycode.concurrent.primecount.Common.NUMBER_UPPER_LIMIT;
import static priv.thinkam.toycode.concurrent.primecount.Common.isPrime;

/**
 * 线程 + 锁并发模型 (细化锁粒度)
 */
class ThreadLockPrimeCount1 {
    public static final int SPLIT_COUNT = Runtime.getRuntime().availableProcessors();
    private int currentNum = 2;
    private int primeCount = 0;

    private final Object increaseNumLock = new Object();
    private final Object primeCountLock = new Object();

    private int getAndIncreaseNum() {
        synchronized (increaseNumLock) {
            if (currentNum > NUMBER_UPPER_LIMIT) {
                return -1;
            } else {
                return currentNum++;
            }
        }
    }

    private void increasePrimeCount() {
        synchronized (primeCountLock) {
            primeCount++;
        }
    }

    public void doCountPrime(CountDownLatch latch) {
        int num;
        while ((num = getAndIncreaseNum()) != -1) {
            if (isPrime(num)) {
                increasePrimeCount();
            }
        }
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        ThreadLockPrimeCount1 primeCount = new ThreadLockPrimeCount1();
        CountDownLatch latch = new CountDownLatch(SPLIT_COUNT);
        for (int i = 0; i < SPLIT_COUNT; i++){
            new Thread(() -> primeCount.doCountPrime(latch)).start();
        }
        latch.await();
        System.out.println(primeCount.primeCount);

        System.out.println(System.currentTimeMillis() - startTime);
    }
}