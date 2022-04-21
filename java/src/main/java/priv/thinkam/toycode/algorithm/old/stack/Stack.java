package priv.thinkam.toycode.algorithm.old.stack;

import java.lang.reflect.Array;

public class Stack<T> {
	private int size;
	private T[] arr;
	private int top;

	@SuppressWarnings("unchecked")
	public Stack(Class<T> type, int size) {
		this.size = size;
		arr = (T[]) Array.newInstance(type, size);
		top = -1;
	}

	public static void main(final String... args) {
		Stack<Integer> s = new Stack<>(Integer.class, 10);
		System.out.println(s.empty());

		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);

		while (!s.empty()) {
			System.out.print(s.pop() + " ");
		}
	}

	public boolean push(T n) {
		if (full()) {
			System.out.println("full, fail!");
			return false;
		}
		arr[++top] = n;
		return true;
	}

	public T pop() {
		if (empty()) {
			System.out.println("empty, fail!");
			return null;
		}
		T result = arr[top];
		arr[top] = null;
		top--;
		return result;
	}

	public T peek() {
		return arr[top];
	}

	public boolean empty() {
		return top == -1;
	}

	public boolean full() {
		return top == size - 1;
	}
}
