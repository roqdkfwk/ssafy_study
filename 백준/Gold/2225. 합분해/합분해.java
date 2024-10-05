import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static long[][] dp;
	static long answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new long[N + 1][K + 1];
		for (int k = 1; k <= K; k++) {
			dp[1][k] = k;
		}
		for (int i = 1; i <= N; i++) {
			dp[i][1] = 1;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= K; j++) {
				for (int k = 1; k <= j; k++) {
					dp[i][j] += dp[i - 1][k];
					dp[i][j] %= 1_000_000_000;
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}		
}