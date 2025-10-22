package priv.thinkam.toycode.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 简化版AQS - 帮助理解核心原理
 * 真实的AQS要复杂得多，但核心思想相同
 */
public class SimpleAQS {
    // 1. 同步状态（0表示未锁定，1表示已锁定）
    private volatile int state = 0;
    
    // 2. 等待队列
    private LinkedBlockingQueue<Thread> waitQueue = new LinkedBlockingQueue<>();
    
    // 3. 当前持有锁的线程
    private Thread owner;
    
    /**
     * 获取锁
     */
    public void acquire() {
        // 尝试获取锁
        if (tryAcquire()) {
            return;
        }
        
        // 获取失败，加入队列并阻塞
        Thread current = Thread.currentThread();
        waitQueue.offer(current);
        
        // 阻塞当前线程
        while (true) {
            LockSupport.park(); // 让线程休眠
            
            // 被唤醒后再次尝试获取
            if (tryAcquire()) {
                waitQueue.remove(current);
                return;
            }
        }
    }
    
    /**
     * 释放锁
     */
    public void release() {
        if (Thread.currentThread() != owner) {
            throw new IllegalStateException("不是锁的持有者");
        }
        
        // 释放锁
        state = 0;
        owner = null;
        
        // 唤醒队列中的第一个线程
        Thread first = waitQueue.peek();
        if (first != null) {
            LockSupport.unpark(first);
        }
    }
    
    /**
     * 尝试获取锁（原子操作）
     */
    private synchronized boolean tryAcquire() {
        if (state == 0) {
            state = 1;
            owner = Thread.currentThread();
            return true;
        }
        return false;
    }
}

class SimpleAQSDemo {
    public static void main(String[] args) {
        SimpleAQS lock = new SimpleAQS();

        // 创建3个线程竞争锁
        for (int i = 1; i <= 3; i++) {
            int threadNum = i;
            new Thread(() -> {
                System.out.println("线程" + threadNum + " 尝试获取锁");

                lock.acquire(); // 获取锁
                System.out.println("线程" + threadNum + " 获得锁，开始工作");

                try {
                    Thread.sleep(1000); // 模拟工作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程" + threadNum + " 释放锁");
                lock.release(); // 释放锁

            }).start();
        }
    }
}