import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Stack<Character> stack = new Stack<>();
		Stack<Character> stack2 = new Stack<>();
		String str = br.readLine();
		boolean flag = true;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			// 알파벳인 경우 stack에 push
			if ('a' <= c && c <= 'z' || '0' <= c && c <= '9') {
				// 여는 꺾쇠가 있으면 그냥 붙여준고
				if (flag == false) sb.append(c);
				// 없으면 stack에 쌓아준다.
				else stack.push(c);
			// 공백인 경우 stack에 쌓여있는 것을 출력 >> 반대로 뒤집힘
			// 공백인데 stack 안에 여는 꺾쇠가 있는 경우는 그대로 출력해야하는데
			// 이 로직을 어떻게 구현하지?
			} else if (c == ' ') {
				// flag == false 이면 여는 꺾쇠가 있는 것이므로
				// 정방향으로 출력하기 위해서 sb에 그냥 붙여준다.
				if (flag == false) sb.append(c);
				else {
					while (!stack.isEmpty()) sb.append(stack.pop());
					sb.append(" ");
				}
			// 여는 꺾쇠인 경우 stack에 쌓여있는 것을 출력 후 여는 꺾쇠를 출력
			} else if (c == '<') {
				flag = false;
				while (!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append("<");
			// 닫는 꺾쇠인 경우 stack에 쌓여있는 것을 stack2에 옮긴 후 출력해서
			// 문자열에 정방향으로 출력되도록 하고 마지막으로 닫는 꺾쇠를 출력
			} else {
				flag = true;
				while (!stack.isEmpty()) stack2.push(stack.pop());
				while (!stack2.isEmpty()) sb.append(stack2.pop());
				sb.append(">");
			}
		}
		while (!stack.isEmpty()) sb.append(stack.pop());
		System.out.println(sb);
	}
}