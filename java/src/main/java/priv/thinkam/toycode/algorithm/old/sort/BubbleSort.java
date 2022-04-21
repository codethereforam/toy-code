package priv.thinkam.toycode.algorithm.old.sort;

public class BubbleSort {
	private int index;
	private int[] arr;

	public BubbleSort(int maxSize) {
		arr = new int[maxSize];
		index = 0;
	}

	public static void main(final String... args) {
		BubbleSort bs = new BubbleSort(100);
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

	public void insert(int a) {
		arr[index] = a;
		index++;
	}

	public void sort() {
		for (int i = index - 1; i >= 0; i--)
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(j, j + 1);
				}
			}
	}

	public void swap(int i, int j) {
		int temp;
		temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}

	public void display() {
		for (int i = 0; i < index; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
