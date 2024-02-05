import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			
			int sum = 0;
			String str = br.readLine();
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '(') {
					stack.add(str.charAt(i));
				} else if (str.charAt(i) == ')' && str.charAt(i - 1) == '(') {
					stack.pop();					
					sum += stack.size();
				} else {
					stack.pop();
					sum++;
				}
			}
			
			System.out.println("#" + t + " " + sum);
		}
	}
}