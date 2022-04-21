package priv.thinkam.toycode.algorithm.old.queue;

import java.lang.reflect.Array;

//without count
public class Queue1<T> {
	private int size;
	private T[] arr;
	private int top, bot;
	
	@SuppressWarnings("unchecked")
	public Queue1(Class<T> type, int size) {
		this.size = size + 1;
		arr = (T[])Array.newInstance(type, this.size);
		top = -1;
		bot = 0;
	}
	
	public boolean isEmpty() {
		return (top + 1) % size == bot;
	} 
	
	public boolean isFull() {
		return (top + 2) % size == bot;
	}
	
	public void insert(T t) {
		if(isFull()) {
			System.out.println("fail, is full");
			return;
		}
		top = (++top) % size;
		arr[top] = t;
	}
	
	public T remove() {
		if(isEmpty()) {
			System.out.println("fail, is empty");
			return null;
		}
		int preBot = bot;
		bot = (++bot) % size;
		return arr[preBot];
	}
	
	public T peek() {
		return arr[bot];
	}
	
	public int size() {
		return (top >= bot) ? top - bot + 1 : top + size + 1- bot;
	}
	
	public static void main(String...args) {
		Queue1<String> q = new Queue1<>(String.class, 5);
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
