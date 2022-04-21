package priv.thinkam.toycode.algorithm.old.recursion;

/**
 * Created by thinkam on 17-12-7.
 */
public class TriangleNumber {

	public static void main(String[] args) {
		final int n = 6;
		System.out.println(n + ":" + get2(n));
	}

	public static int get1(int n) {
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result += i;
		}
		return result;
	}

	public static int get2(int n) {
		return n == 1 ? 1 : get2(n - 1) + n;
	}
}