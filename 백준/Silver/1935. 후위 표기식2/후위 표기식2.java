import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack<Double> stack = new Stack<>();

		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int[] alpha = new int[26];
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			// A부터 Z까지 알파벳을 입력받으면
			// stack에 숫자를 push
			if ('A' <= c && c <= 'Z') {
				if (alpha[c - 'A'] != 0)  {
					stack.push((double)alpha[c - 'A']);
					continue;
				}
				alpha[c - 'A'] = Integer.parseInt(br.readLine());
				stack.push((double)alpha[c - 'A']);
			} else {
				if (c == '+') {
					double num1 = stack.pop();
					double num2 = stack.pop();
					stack.push(num1 + num2);
				} else if (c == '-') {
					double num1 = stack.pop();
					double num2 = stack.pop();
					stack.push(num2 - num1);
				} else if (c == '*') {
					double num1 = stack.pop();
					double num2 = stack.pop();
					stack.push(num1 * num2);
				} else {
					double num1 = stack.pop();
					double num2 = stack.pop();
					stack.push(num2 / (double)num1);
				}
			}
		}
		
		System.out.printf("%.2f", stack.peek());
	}
}