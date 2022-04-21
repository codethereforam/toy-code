package priv.thinkam.toycode.algorithm.old.search;

public class BinarySearch {

	public static int search(int[] arr, int val) {
		int low = 0, high = arr.length - 1, index;
		while (low <= high) {
			index = (low + high) / 2;
			int n = arr[index];
			if (n == val) {
				return index;
			} else if (n > val) {
				high = index - 1;
			} else {
				low = index + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] foo = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int find = search(foo, 9);
		if (find == -1) {
			System.out.println("not found");
		} else {
			System.out.println("index = " + find);
		}
	}
}
