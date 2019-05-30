package priv.thinkam.toycode.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        semaphore.acquire();
        boolean success = false;
        try {
            success = set.add(t);
            return success;
        } finally {
            if(!success) {
                semaphore.release();
            }
        }
    }

    public boolean remove(T t) {
        boolean success = set.remove(t);
        if(success) {
            semaphore.release();
        }
        return success;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet<String> boundedHashSet = new BoundedHashSet<>(3);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                for (int i = 1; i <= 3; i++) {
                   if(boundedHashSet.remove(String.valueOf(i))) {
                       System.out.println("remove..." + i);
                   }
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        for (int i = 1; i <= 8; i++) {
            boundedHashSet.add(String.valueOf(i));
            System.out.println("add..." + i);
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}

