import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		String str = br.readLine();
		String keyword = br.readLine();
		int keyLen = keyword.length();

		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));

			// 폭발 문자열과 길이가 같아지면 탐색 시작
			if (stack.size() >= keyLen) {
				boolean flag = true;

				// 문자열과 폭발 문자열을 비교
				for (int j = 0; j < keyLen; j++) {
					if (stack.get(stack.size() - keyLen + j) != keyword.charAt(j)) {
						flag = false;
						break;
					}
				}

				// 문자열이 폭발 문자열과 일치한다면 일치하는만큼 pop
				if (flag) {
					for (int j = 0; j < keyLen; j++) stack.pop();
				}
			}
		}

		for (Character ch : stack) sb.append(ch);
		if (sb.length() == 0) System.out.println("FRULA");
		else System.out.println(sb);
	}
}