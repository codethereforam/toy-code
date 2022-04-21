package priv.thinkam.toycode.algorithm.old.sort;

import java.util.Arrays;

public class QuickSort {
	private static int marker = 0;

	private static void part(int[] a, int left, int right) {
		int temp = a[left];
		while (left < right) {
			while (temp < a[right] && left < right) {
				right--;
			}
			if (left < right) {
				a[left++] = a[right];
			}
			while (a[left] < temp && left < right) {
				left++;
			}
			if (left < right) {
				a[right--] = a[left];
			}
		}
		a[left] = temp;
		marker = left;
	}

	private static void quickSort(int[] a, int left, int right) {
		if (left < right) {
			part(a, left, right);
			quickSort(a, left, marker - 1);
			quickSort(a, marker + 1, right);
		}
	}

	private static void sort(int[] a) {
		quickSort(a, 0, a.length - 1);
	}

	public static void main(String[] args) {
		int[] array = {4, 3, 5, 6, 2, 7, 1};
//		Arrays.sort(array);
		QuickSort.sort(array);
		System.out.println(Arrays.toString(array));
	}
}
