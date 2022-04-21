package priv.thinkam.toycode.algorithm.old.primenumber;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * 筛法
 * 对于给定的整型参数 n，该函数能够把自然数中，小于 n 的质数，从小到大打印出来
 * Created by thinkam on 17-11-26.
 */
public class PrimeNumberBySieve {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		boolean[] numbers = get2(1000000000);
		long endTime = System.currentTimeMillis();
		System.out.println("spent " + (endTime - startTime) + "ms");
		int count = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (!numbers[i]) {
//				System.out.print((i + 1) + ", ");
				count++;
			}
		}
		System.out.println();
		System.out.println("total=" + count);
	}

	public static BitSet get3(int n) {
		BitSet numbers = new BitSet(n);
		int position = 1;
		numbers.set(position++ - 1);
		while (position <= n) {
			int num;
			for (int i = 2; (num = position * i) <= n; i++) {
				numbers.set(num - 1);
			}
			do {
				position++;
			} while (position <= n && numbers.get(position - 1));
		}
		return numbers;
	}

	public static boolean[] get2(int n) {
		boolean[] numbers = new boolean[n];
		int position = 1;
		numbers[position++ - 1] = true;
		while (position <= n) {
			int num;
			for (int i = 2; (num = position * i) <= n; i++) {
				numbers[num - 1] = true;
			}
			do {
				position++;
			} while (position <= n && numbers[position - 1]);
		}
		return numbers;
	}

	public static List<Integer> get1(int n) {
		List<Integer> numbers = new ArrayList<>();
		List<Integer> result = new ArrayList<>();
		//添加
		for (int i = 2; i <= n; i++) {
			numbers.add(i);
		}
		//筛选
		while (numbers.size() != 0) {
			int start = numbers.get(0);
			result.add(start);
			int num = start;
			for (int i = 2; num <= n; i++) {
				numbers.remove(new Integer(num));
				num = start * i;
			}
		}
		return result;
	}

}
