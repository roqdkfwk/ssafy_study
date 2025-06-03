import java.util.*;
import java.io.*;
public class Main {

	static int N, K;
	static int[] dp;
	static int[] W, V;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[K + 1];
		W = new int[N + 1];	// 물건의 무게
		V = new int[N + 1];	// 물건의 가치
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int index = 1; index <= N; index++) {
			for (int wei = K; wei >= W[index]; wei--) {
				dp[wei] = Math.max(dp[wei], dp[wei - W[index]] + V[index]);
			}
		}
		
		System.out.println(dp[K]);
	}
}