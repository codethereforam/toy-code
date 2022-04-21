package priv.thinkam.toycode.algorithm.old.primenumber;

import java.util.ArrayList;
import java.util.List;

/**
 * 试除法
 * 对于给定的整型参数 n，该函数能够把自然数中，小于 n 的质数，从小到大打印出来
 * Created by thinkam on 17-11-26.
 */
public class PrimeNumberByDivide {
	private static List<Integer> numbers = new ArrayList<>();

	public static void main(String[] args) {
		final int n = 1000_000;
		long startTime = System.currentTimeMillis();
		List<Integer> numbers = PrimeNumberByDivide.get(n);
		long endTime = System.currentTimeMillis();
		System.out.println("spent " + (endTime - startTime) + "ms");
		System.out.println(numbers);
		System.out.println("total=" + numbers.size());
	}

	public static List<Integer> get(int n) {
		for (int i = 1; i <= n; i++) {
			if (isPrime5(i)) {
				numbers.add(i);
			}
		}
		return numbers;
	}

	/**
	 * 判断一个数是否为质数
	 *
	 * @param n
	 * @return
	 */
	private static boolean isPrime1(int n) {
		if (n == 2) {
			return true;
		}
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return n != 1;
	}

	private static boolean isPrime2(int n) {
		if (n == 2) {
			return true;
		}
		for (int i = 2; i <= n / 2; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return n != 1;
	}

	private static boolean isPrime3(int n) {
		if (n == 2) {
			return true;
		}

		if (n % 2 == 0) {
			return false;
		}

		for (int i = 3; i <= n / 2; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return n != 1;
	}

	private static boolean isPrime4(int n) {
		if (n == 2) {
			return true;
		}
		double max = Math.sqrt(n);
		for (int i = 2; i <= max; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return n != 1;
	}

	private static boolean isPrime5(int n) {
		if (n == 2) {
			return true;
		}
		double max =  Math.sqrt(n);
		for (int num : numbers) {
			if(num > max) {
				break;
			}
			if (n % num == 0) {
				return false;
			}
		}
		return n != 1;
	}

}
