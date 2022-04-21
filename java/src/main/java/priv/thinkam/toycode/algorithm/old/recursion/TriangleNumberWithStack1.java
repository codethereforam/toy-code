package priv.thinkam.toycode.algorithm.old.recursion;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by thinkam on 12/11/17.
 */
public class TriangleNumberWithStack1 {
	private int number;
	private int result;
	private Deque<Param> stack = new ArrayDeque<>();
	private int code = 1;
	private Param param;

	public static void main(String[] args) {
		TriangleNumberWithStack1 t = new TriangleNumberWithStack1();
		t.calculate(6);
		System.out.println(t.result);
	}

	public void calculate(int n) {
		this.number = n;
		while (!step()) {

		}
	}

	public boolean step() {
		switch (code) {
			//method entry
			case 1:
				param = new Param(number, 6);
				stack.push(param);
				code = 2;
				break;
			//test
			case 2:
				param = stack.peek();
				if(param.getN() == 1) {
					result = 1;
					code = 5;
				} else {
					code = 3;
				}
				break;
			//method call
			case 3:
				stack.push(new Param(param.getN() - 1, 4));
				code = 2;
				break;
			//calculate
			case 4:
				param = stack.peek();
				result += param.getN();
				code = 5;
				break;
			//method exit
			case 5:
				param = stack.peek();
				code = param.getReturnAddress();
				stack.pop();
				break;
			//return point
			case 6:
				return true;
			default:
		}
		return false;
	}

}

class Param {
	private int n;
	private int returnAddress;

	public Param(int n, int returnAddress) {
		this.n = n;
		this.returnAddress = returnAddress;
	}

	public int getN() {
		return n;
	}


	public int getReturnAddress() {
		return returnAddress;
	}

}
