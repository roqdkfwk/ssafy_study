import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack;
		
		int cnt = 0;
		int N = Integer.parseInt(br.readLine());
		for (int T = 0; T < N; T++) {
			stack = new Stack<Character>();
						
			String str = br.readLine();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				
				// stack이 비어있으면 알파벳을 push
				if (stack.isEmpty()) {
					stack.push(c);
//					System.out.println(1);
				// 비어있지 않을 때
				} else {
					// stack에 있는 알파벳과 같은 알파벳이면 pop
					if (stack.peek() == c) {
						stack.pop();
//						System.out.println(2);
					}
					// stack에 있는 알파벳과 다른 알파벳이면 push
					else {
						stack.push(c);
//						System.out.println(3);
					}
				}
			}
			// stack에 알파벳이 3개 쌓였거나 stack에 남은 알파벳이 있으면 나쁜 단어
			if (!stack.isEmpty()) {}
			else cnt++;
		}
		System.out.println(cnt);
	}
}