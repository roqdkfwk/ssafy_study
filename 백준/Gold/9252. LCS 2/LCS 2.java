import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	static int[][] DP;
	static char[] str1;
	static char[] str2;
	static Stack<Character> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();
		
		DP = new int[str1.length + 1][str2.length + 1];
		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				
				if (str1[i - 1] == str2[j - 1]) 
					DP[i][j] = DP[i - 1][j - 1] + 1;
				else {
					DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
				}
			}
		}	// for
		
		stack = new Stack<Character>();
		int r = str1.length;
		int c = str2.length;
		
		while (r > 0 && c > 0) {
			
			if (DP[r][c] == DP[r - 1][c])
				r--;
			else if (DP[r][c] == DP[r][c - 1])
				c--;
			else {
				stack.add(str1[r - 1]);
				r--;
				c--;
			}
		}	// while
		
		while (!stack.isEmpty())
			sb.append(stack.pop());
		
		System.out.println(DP[str1.length][str2.length]);
		System.out.println(sb);		
	}	// main
}