package priv.thinkam.toycode.algorithm;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanganyu
 * @date 2021/5/26
 */
public class MyArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E> {
    private final E[] items;
    private int count;
    private int putIndex;
    private int takeIndex;

    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;

    @SuppressWarnings("unchecked")
    public MyArrayBlockingQueue(int capacity) {
        this.items = (E[]) new Object[capacity];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    private void enqueue(E x) {
        items[putIndex] = x;
        if (++putIndex == items.length) {
            putIndex = 0;
        }
        count++;
    }

    private E dequeue() {
        E e = items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length) {
            takeIndex = 0;
        }
        count--;
        return e;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return this.count;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E e) {
        lock.lock();
        try {
            if (this.count == items.length) {
                return false;
            } else {
                this.enqueue(e);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            // 防止虚假唤醒
            while (this.count == items.length) {
                notFull.await();
            }
            this.enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public E take() throws InterruptedException {
        lock.lock();
        try {
            // 防止虚假唤醒
            while (this.count == 0) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int remainingCapacity() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E poll() {
        lock.lock();
        try {
            if (this.count == 0) {
                return null;
            } else {
                return dequeue();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E peek() {
        lock.lock();
        try {
            return items[takeIndex];
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        int c = 0;
        StringBuilder sb = new StringBuilder("[");
        for (int i = takeIndex; c < count; ) {
            c++;
            sb.append(items[i]);
            if (c != count) {
                sb.append(", ");
            }
            if (++i == items.length) {
                i = 0;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new MyArrayBlockingQueue<>(3);
        System.out.println(queue);

        queue.put(1);
        queue.put(2);
        queue.put(3);
        System.out.println(queue);

        queue.put(4);
        System.out.println(queue);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
