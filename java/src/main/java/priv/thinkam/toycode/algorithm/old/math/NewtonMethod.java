package priv.thinkam.toycode.algorithm.old.math;

/**
 * 牛顿迭代法
 * Created by yay on 12/10/17.
 */
public class NewtonMethod {
	private int status;
	private int k;

	public static void main(String[] args) {
		new NewtonMethod().cal(2, 0.00001, 10);
	}

	public int cal(double x0, double scope, int n) {
		return k < n ? get(x0, scope) : status;
	}

	private int get(double x0, double scope) {
		k++;
		double y1 = f(x0);
		double y2 = calDerivative(x0);
		if (y2 == 0) {
			status = -1;
			return status;
		} else {
			double x1 = x0 - y1 / y2;
			if (Math.abs(x1 - x0) < scope) {
				status = 0;
				System.out.printf("迭代次数=%s\n", k);
				System.out.printf("近似值x=%s\n", x1);
				System.out.printf("近似值y=%s\n", f(x1));
				return status;
			} else {
				x0 = x1;
				return get(x0, scope);
			}
		}
	}

	private double f(double x) {
		return x * x * x - 0.2 * x * x - 0.2 * x - 1.2;
	}

	private double calDerivative(double x) {
		return 3 * x * x - 0.4 * x - 0.2;
	}
}
