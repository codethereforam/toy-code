package priv.thinkam.toycode.algorithm.old.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Brackets {
	private Stack<Character> brackets;
	
	public Brackets(int size) {
		brackets = new Stack<>(Character.class, size);
	}
	
	public static String input() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public boolean check(String str) {
		char[] s = str.toCharArray();
		for(char c : s) {
			switch(c) {
			case '}' :
				if(brackets.pop() != '{') {
					return false;
				}
				break;
			case ']' :
				if(brackets.pop() != '[') {
					return false;
				}
				break;
			case ')' :
				if(brackets.pop() != '(') {
					return false;
				}
				break;
			case '{' :
			case '[' :
			case '(' :
				brackets.push(c);
				break;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.print("please input: ");
		String input = input();
		Brackets bk = new Brackets(input.length());
		boolean b = bk.check(input);
		if(b) {
			System.out.println("match");
		} else {
			System.out.println("not match");
		}
	}
}
