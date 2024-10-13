import java.util.*;
import java.io.*;
public class Main {

	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		Stack<Character> stack = new Stack<Character>();
		
		for (int i = 0; i < N; i++) {
			while (K > 0 && !stack.isEmpty() && stack.peek() < str.charAt(i)) {
				stack.pop();
				K--;
			}
			stack.add(str.charAt(i));
		}
		while (K > 0) {
			stack.pop();
			K--;
		}
		
		for (char ch : stack) {
			sb.append(ch);
		}
		
		System.out.println(sb.toString());
	}
}