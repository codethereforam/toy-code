package priv.thinkam.toycode.concurrent;

import java.util.concurrent.*;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            System.out.println("start");
            for (int i = 1; i <= 5; i++) {
                System.out.println("future....." + i);
                TimeUnit.SECONDS.sleep(1);
            }
            return "end";
        });
        for (int i = 1; i <= 5; i++) {
            System.out.println("main thread....." + i);
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(future.get());
        System.out.println("hehe");
    }
}
