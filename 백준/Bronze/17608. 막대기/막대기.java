import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			stack.push(Integer.parseInt(br.readLine()));
		}
		
		int cnt = 0;
		int maxHeight = 0;
		while (!stack.isEmpty()) {
			if (maxHeight < stack.peek()) {
				maxHeight = stack.peek();
				stack.pop();
				cnt++;
			} else stack.pop();
		}
		
		System.out.println(cnt);
	}
}