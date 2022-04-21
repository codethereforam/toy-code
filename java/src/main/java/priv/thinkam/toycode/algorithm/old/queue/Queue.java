package priv.thinkam.toycode.algorithm.old.queue;

import java.lang.reflect.Array;

//ÓÐcount
public class Queue<T> {
	private int size;
	private T[] arr;
	private int top, bot, count;
	
	@SuppressWarnings("unchecked")
	public Queue(Class<T> type, int size) {
		this.size = size;
		arr = (T[])Array.newInstance(type, size);
		top = -1;
		bot = 0;
		count = 0;
	}
	
	public boolean isEmpty() {
		return count == 0;
	} 
	
	public boolean isFull() {
		return count == size;
	}
	
	public void insert(T t) {
		if(isFull()) {
			System.out.println("fail, is full");
			return;
		}
		top = (++top) % size;
		arr[top] = t;
		count++;
	}
	
	public T remove() {
		if(isEmpty()) {
			System.out.println("fail, is empty");
			return null;
		}
		count--;
		int preBot = bot;
		bot = (++bot) % size;
		T res = arr[preBot];
		arr[preBot] = null;
		return res;
	}
	
	public T peek() {
		return arr[bot];
	}
	
	public int size() {
		return count;
	}
	
	public static void main(String...args) {
		Queue<String> q = new Queue<>(String.class, 5);
		q.insert("one");
		q.insert("two");
		q.insert("three");
		q.insert("four");
		
		for(int i=0; i<3; i++) {
			System.out.print(q.remove() + " ");
		}
		System.out.println();
		
		q.insert("five");
		q.insert("six");
		q.insert("seven");
		q.insert("eight");
		
		System.out.println(q.peek());
		System.out.println(q.size());
		
		while(!q.isEmpty()) {
			System.out.print(q.remove() + " ");
		}
	}
}
