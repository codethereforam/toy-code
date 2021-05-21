package priv.thinkam.toycode.concurrent.primecount;

import static priv.thinkam.toycode.concurrent.primecount.Common.NUMBER_UPPER_LIMIT;
import static priv.thinkam.toycode.concurrent.primecount.Common.isPrime;

/**
 * 线程 + 锁并发模型 （效率低下，跟串行产不多）
 */
class ThreadLockPrimeCount implements Runnable {
    public static final int SPLIT_COUNT = Runtime.getRuntime().availableProcessors();
    private int currentNum = 2;
    private int primeCount = 0;

    private int getAndIncreaseNum() {
        synchronized (this) {
            if (currentNum > NUMBER_UPPER_LIMIT) {
                return -1;
            } else {
                return currentNum++;
            }
        }
    }

    private void increasePrimeCount() {
        synchronized (this) {
            primeCount++;
        }
    }

    @Override
    public void run() {
        int num;
        while ((num = getAndIncreaseNum()) != -1) {
            if (isPrime(num)) {
                increasePrimeCount();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        ThreadLockPrimeCount primeCount = new ThreadLockPrimeCount();
        for (int i = 0; i < SPLIT_COUNT; i++){
            Thread thread = new Thread(primeCount);
            thread.start();
            thread.join();
        }
        System.out.println(primeCount.primeCount);

        System.out.println(System.currentTimeMillis() - startTime);
    }
}