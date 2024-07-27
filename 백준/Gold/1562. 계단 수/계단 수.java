import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;	// 길이
	static long[][][] dp;
	static final int mod = 1_000_000_000;
	static long ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1][10][(1 << 10)];
		
		// 첫번째 자리 숫자가 k인 경우의 수는 1
		// (1 << k) 연산으로 사용한 숫자 체크
		for (int k = 1; k <= 9; k++)
			dp[1][k][1 << k] = 1;
		
		for (int n = 2; n <= N; n++) {
			for (int k = 0; k <= 9; k++) {
				for (int visit = 0; visit < (1 << 10); visit++) {
					
					if (k == 0)
						dp[n][k][visit | (1 << k)] += dp[n - 1][k + 1][visit] % mod;
					else if (k == 9)
						dp[n][k][visit | (1 << k)] += dp[n - 1][k - 1][visit] % mod;
					else
						dp[n][k][visit | (1 << k)] += dp[n - 1][k - 1][visit] % mod + dp[n - 1][k + 1][visit] % mod;
					
					dp[n][k][visit | (1 << k)] %= mod;
				}
			}
		}
		
		for (int i = 0; i <= 9; i++) {
			ans += (dp[N][i][(1 << 10) - 1] % mod);
			ans %= mod;
		}
		
		System.out.println(ans);
	}	// main
}