package priv.thinkam.toycode.algorithm.old.sort;

/**
 * 归并排序
 * Created by thinkam on 12/10/17.
 */
public class MergeSort {

	public static void main(String[] args) {
		/*int[] arr = {4, 3, 5, 6, 2, 7, 1};
		sort(arr);
		System.out.println(Arrays.toString(arr));*/
		System.out.println("中文");
	}

	public static void sort(int[] arr) {
		int arrLength = arr.length;
		int[] temp = new int[arrLength];
		sort(arr, 0, arrLength - 1, temp);
	}

	public static void sort(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			sort(arr, left, mid, temp);
			sort(arr, mid + 1, right, temp);
			merge(arr, left, mid, right, temp);
		}
	}

	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left;
		int j = mid + 1;
		int t = 0;
		while (i <= mid && j <= right) {
			if (arr[i] < arr[j]) {
				temp[t++] = arr[i++];
			} else {
				temp[t++] = arr[j++];
			}
		}
		while (i <= mid) {
			temp[t++] = arr[i++];
		}
		while (j <= right) {
			temp[t++] = arr[j++];
		}
		t = 0;
		while (left <= right) {
			arr[left++] = temp[t++];
		}
	}
}
