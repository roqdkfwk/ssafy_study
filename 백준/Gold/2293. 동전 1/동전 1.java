import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] coins, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coins = new int[N];
		dp = new int[K + 1];
		dp[0] = 1;
		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		for (int coin : coins) {
			for (int k = coin; k <= K; k++) {
				dp[k] += dp[k - coin];
			}
		}
		
		System.out.println(dp[K]);
	}
}