package priv.thinkam.toycode.algorithm.old.sort;

public class InsertSort {
	private int index;
	private int[] arr;

	public InsertSort(int maxSize) {
		arr = new int[maxSize];
		index = 0;
	}

	public static void main(final String... args) {
		InsertSort bs = new InsertSort(100);
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
		for (int i = 1, j; i < index; i++) {
			int t = arr[i];
			for (j = i - 1; j >= 0 && arr[j] > t; j--) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = t;
		}
	}

	public void display() {
		for (int i = 0; i < index; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
