import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static int A;
	static int[] seq;
	static int[] dp;
	static int answer;
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		A = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		seq = new int[A];
		dp = new int[A];
		Arrays.fill(dp, 1);
		for (int i = 0; i < A; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < A - 1; i++) {
			for (int j = i + 1; j < A; j++) {
				if (seq[i] < seq[j]) {
					dp[j] = Math.max(dp[j], dp[i] + 1);
				}
			}
		}
		
		for (int i = 0; i < A; i++) {
			answer = Math.max(answer, dp[i]);
		}
		
		int len = answer;
		stack = new Stack<Integer>();
		for (int i = A - 1; i >= 0; i--) {
			if (dp[i] == len) {
				stack.add(seq[i]);
				len--;
			}
		}
		
		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		
//		System.out.println(Arrays.toString(dp));
		System.out.println(answer);
		System.out.println(sb);
	}	// main
}