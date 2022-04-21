package priv.thinkam.toycode.algorithm.old.math;

/**
 * 二分法
 * Created by yay on 12/10/17.
 */
public class Dichotomy {
	private double count;
	private double[] result;

	public static void main(String[] args) {
		double[] result = new Dichotomy().cal(1, 3, 0.00001, 0.00001);
		if (result == null) {
			System.out.println("此区间无根");
			return;
		}
		System.out.printf("迭代次数=%s\n", result[0]);
		System.out.printf("近似值x=%s\n", result[1]);
		System.out.printf("近似值y=%s\n", result[2]);

	}

	public double[] cal(double x1, double x2, double left, double right) {
		double y1 = f(x1);
		double y2 = f(x2);
		if (y1 * y2 > 0) {
			return result;
		} else {
			count++;
			double[] result = iterator(x1, x2, y1, left, right);
			this.result = result;
		}
		return result;
	}

	private double[] iterator(double x1, double x2, double y1, double left, double right) {
		double x = (x1 + x2) / 2;
		double y = f(x);
		result = new double[3];
		result[0] = count;
		result[1] = x;
		result[2] = y;
		if (Math.abs(y) < left) {
			return result;
		} else if (y1 * y < 0) {
			x2 = x;
		} else {
			x1 = x;
			y1 = y;
		}
		if (x2 - x1 < right) {
			return result;
		} else {
			count++;
			double[] result = iterator(x1, x2, y1, left, right);
			return result;
		}
	}

	private double f(double x) {
		return x * x * x - 0.2 * x * x - 0.2 * x - 1.2;
	}

}
