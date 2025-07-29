import java.util.*;
import java.io.*;
public class Main {
	
	static int N;
	static int[][] W;
	static int[][] dp;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		dp = new int[N][1 << N];
		
		for (int i = 0; i < N; i++) {
			W[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.fill(dp[i], -1);
		}
		
		int answer = tsp(0, 1 << 0);
		System.out.println(answer);
	}
	
	private static int tsp(int prev, int visited) {
		if ((visited == (1 << N) - 1)) {
			if (W[prev][0] == 0) {
				return INF;
			}
			return W[prev][0];
		}
		
		if (dp[prev][visited] != -1) {
			return dp[prev][visited];
		}
		
		int minCost = INF;
		for (int next = 0; next < N; next++) {
			if (W[prev][next] == 0 || (visited & (1 << next)) != 0) continue;
			
			int nextVisited = visited | (1 << next);
			int cost = W[prev][next] + tsp(next, nextVisited);
			
			minCost = Math.min(minCost, cost);
		}
		
		dp[prev][visited] = minCost;
		return minCost;
	}
}
