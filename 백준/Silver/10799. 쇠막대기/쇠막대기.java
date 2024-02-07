import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		Stack<Character> stack = new Stack<Character>();
		
		int cnt = 0;
		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			if (c == '(') {
				stack.push(c);
				cnt++;
			} else if (c == ')') {
				if (str.charAt(i - 1) == '(') {
					sum += --cnt;
				} else {
					cnt--;
					sum++;
				}
			}
		}
		
		System.out.println(sum);
	}
}