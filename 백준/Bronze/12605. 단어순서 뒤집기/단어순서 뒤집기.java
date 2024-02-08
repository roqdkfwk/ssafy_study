import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		Stack<String> stack = new Stack<>();
		
		int N = Integer.parseInt(br.readLine());
		for (int T = 1; T < N + 1; T++) {
			sb = new StringBuilder();
			
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) stack.push(st.nextToken());
			while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");
			
			System.out.printf("Case #%d: %s\n", T, sb);
		}
	}
}