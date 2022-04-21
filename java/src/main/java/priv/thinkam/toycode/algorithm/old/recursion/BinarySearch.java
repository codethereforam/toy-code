package priv.thinkam.toycode.algorithm.old.recursion;


/**
 * Created by thinkam on 17-12-8.
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = {1, 2, 5, 6, 7, 9, 10, 11};
		final int searchKey = 9;
		System.out.printf("arr[%d]=%d", BinarySearch.get1(arr, searchKey), searchKey);
	}

	public static int get1(int[] arr, int key) {
		return find(arr, 0, arr.length - 1, key);
	}

	public static int find(int[] arr, int start, int end, int key) {
		int half = (start + end) / 2;
		int halfValue = arr[half];
		if (halfValue > key) {
			return find(arr, start, half - 1, key);
		} else if (halfValue < key) {
			return find(arr, half + 1, end, key);
		} else {
			return half;
		}
	}

	public static int get2(int[] arr, int val) {
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

}
