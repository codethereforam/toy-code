package priv.thinkam.toycode.algorithm;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;

/**
 * 模仿ArrayBlockingQueue实现的Queue
 *
 * @author yanganyu
 * @date 2021/5/26
 */
public class ArrayQueue<E> extends AbstractQueue<E> {
    private final E[] items;
    private int count;
    private int putIndex;
    private int takeIndex;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.items = (E[]) new Object[capacity];
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
        return this.count;
    }

    @Override
    public boolean offer(E e) {
        if (this.count == items.length) {
            return false;
        } else {
            this.enqueue(e);
            return true;
        }
    }

    @Override
    public E poll() {
        if (this.count == 0) {
            return null;
        } else {
            return dequeue();
        }
    }

    @Override
    public E peek() {
        return items[takeIndex];
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

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueue<>(3);
        System.out.println(queue);

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println(queue);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
