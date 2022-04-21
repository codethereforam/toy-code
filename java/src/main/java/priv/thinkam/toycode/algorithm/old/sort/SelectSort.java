package priv.thinkam.toycode.algorithm.old.sort;

public class SelectSort {
	private int index;
	private int[] arr;
	
	public SelectSort(int maxSize) {
		arr = new int[maxSize];
		index = 0;
	}
	
	public void insert(int a) {
		arr[index] = a;
		index++;
	}
	
	public void sort() {
		for(int i=0; i<index-1; i++) {	
			int min = i;
			for(int j=i+1; j<index; j++) {
				if(arr[j] < arr[min]) {
					min = j;
				}
			}
			if(min != i) {				
				swap(i, min);
			}
		}
	}

	public void swap(int i, int j) {
		int temp;
		temp = arr[j];
		arr[j] = arr[i];
		arr[i] =temp;
	}
	
	public void display() {
		for(int i=0; i<index; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(final String...args) {
		SelectSort bs = new SelectSort(100);
		bs.insert(2);
		bs.insert(1);
		bs.insert(8);
		bs.insert(5);
		bs.insert(4);
		bs.insert(0);
		bs.insert(7);
		bs.insert(6);
		bs.display();
		bs.sort();
		bs.display();
	}
}
