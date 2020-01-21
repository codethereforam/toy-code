package priv.thinkam.toycode.concurrent;

import java.util.concurrent.*;

/**
 * 线程饥饿死锁
 *
 * @author yanganyu
 * @date 2020/1/20 15:22
 */
public class ThreadHungryDeadlock {
    /**
     * max线程数：
     * if >=3，firstTask和secondTask并行，执行时间大概600ms
     * if = 2，firstTask和secondTask串行，执行时间大概1000ms
     * if = 1，线程饥饿死锁
     */
    private static ExecutorService threadPool = Executors.newFixedThreadPool(1);

    private static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<Integer> firstTask = threadPool.submit(() -> foo(400));
            Future<Integer> secondTask = threadPool.submit(() -> foo(600));
            return System.currentTimeMillis() + " | " +
                    firstTask.get() + " | " +
                    System.currentTimeMillis() + " | " +
                    secondTask.get() + " | " +
                    System.currentTimeMillis();
        }

        private Integer foo(int sleepMilliSeconds) throws InterruptedException {
            TimeUnit.MILLISECONDS.sleep(sleepMilliSeconds);
            return sleepMilliSeconds;
        }
    }

    public static void main(String[] args) throws Exception {
        Future<String> future = threadPool.submit(new Task());
        long startTime = System.currentTimeMillis();
        System.out.println(future.get());
        System.out.println(System.currentTimeMillis() - startTime);
        threadPool.shutdown();
    }
}