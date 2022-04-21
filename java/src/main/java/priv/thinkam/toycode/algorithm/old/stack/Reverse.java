package priv.thinkam.toycode.algorithm.old.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//µ¥´ÊÄæÐò
public class Reverse {
	private Stack<Character> stack;
	
	public Reverse(int size) {
		stack = new Stack<>(Character.class, size);
	}
	
	public static String input() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		try {
			s = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public String reverse(String str) {
		StringBuilder sb = new StringBuilder("");
		for(int i=0; i<str.length(); i++) {
			stack.push(str.charAt(i));
		}
		while(!stack.empty()) {
			sb.append(stack.pop());
		}
		return sb.toString();
	}
	
	public static void main(final String...args) {
		System.out.print("please input: ");
		String input = input();
		Reverse rvs = new Reverse(input.length());
		String output = rvs.reverse(input);
		
		System.out.println("input : "  + input);
		System.out.println("output : " + output);
	}
}
