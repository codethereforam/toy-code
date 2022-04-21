package priv.thinkam.toycode.algorithm.old.recursion;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by thinkam on 12/11/17.
 */
public class TriangleNumberWithStack2 {
	private static Deque<Integer> stack = new ArrayDeque<>();

	public static void main(String[] args) {
		System.out.println(get(6));
	}

	public static int get(int n) {
		int result = 0;
		while(n > 0) {
			stack.push(n--);
		}
		while(!stack.isEmpty()) {
			result += stack.pop();
		}
		return result;
	}
}
