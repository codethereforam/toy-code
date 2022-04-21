package priv.thinkam.toycode.algorithm.old.queue;

import java.util.Arrays;

public class PriorityQueue {
	private int size;
	private int[] arr;
	private int count;
	
	public PriorityQueue(int size) {
		this.size = size;
		arr = new int[size];
		count = 0;
	}
	
	public boolean isEmpty() {
		return count == 0;
	} 
	
	public boolean isFull() {
		return count == size;
	}
	
	public void insert(int t) {
		if(isFull()) {
			System.out.println("fail, is full");
			return;
		}
		if(count == 0) {
			arr[count++] = t;
		} else {
			int n = count - 1;
			while(n >= 0 && arr[n] < t) {
				arr[n+1] = arr[n];
				n--;
			}
			arr[n+1] = t;
			count++;
		}
	}
	
	public int remove() {
		if(isEmpty()) {
			System.out.println("fail, is empty");
			System.exit(-1);
		}
		return arr[--count];
	}
	
	public int peek() {
		return arr[count - 1];
	}
	
	public int size() {
		return count;
	}
	
	public static void main(String...args) {
		PriorityQueue pq = new PriorityQueue(10);
		
		pq.insert(5);
		pq.insert(3);
		pq.insert(2);
		pq.insert(9);
		pq.insert(1);
		pq.insert(8);
		
		System.out.println(pq.peek());
		System.out.println(pq.size());
		
		while(!pq.isEmpty()) {
			System.out.print(pq.remove() + " ");
		}
		
		System.out.println();
		java.util.PriorityQueue<Integer> pq1 = new java.util.PriorityQueue<>(Arrays.asList(5, 3, 2, 9, 1, 8));
		System.out.println("pq1:");
		while(!pq1.isEmpty()) {
			System.out.print(pq1.poll() + " ");
		}
	}
}
