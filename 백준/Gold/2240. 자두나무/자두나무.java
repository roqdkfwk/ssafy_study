import java.util.*;
import java.io.*;
public class Main {
	
	/**
	 * 받을 수 있는 자두의 최대 개수는?
	 * 자두는 매 초마다 떨어진다.
	 * 최대 W회 움직일 수 있다.
	 * 
	 * 완전 탐색 - 최대 1000combination30이니까 시간초과
	 * dp라면
	 * 현재 위치, 시각, 움직인 횟수를 고려해야 하므로 3차원 dp?
	 * 
	 */
	
	static int T, W;
	static int[] times;
	static int[][][] dp;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		times = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			times[i] = Integer.parseInt(br.readLine());
		}
		
		// dp[0][t][w] = Math.max(dp[0][t - 1][w], dp[1][t - 1][w - 1]) + 다음 위치
		// dp[1][t][w] = Math.max(dp[0][t - 1][w - 1], dp[1][t - 1][w]) + 다음 위치
		dp = new int[2][T + 1][W + 1];
		dp[0][1][0] = times[1] == 1 ? 1 : 0;
		dp[1][1][1] = 1 - dp[0][1][0];
		
		for (int time = 2; time <= T; time++) {
			// 다음 나무가 1이면 1, 2이면 0
			int tree = times[time] == 1 ? 1 : 0;
			
			// 한 번도 안움직이고 1번 나무 밑에 있는 경우
			dp[0][time][0] = dp[0][time - 1][0] + tree;
			
			// 한 번도 안움직이고 2번 나무 밑에 있을 수는 없다.
			
			for (int move = 1; move <= W; move++) {
				dp[0][time][move] = Math.max(dp[0][time - 1][move], dp[1][time - 1][move - 1]) + tree;
				dp[1][time][move] = Math.max(dp[0][time - 1][move - 1], dp[1][time - 1][move]) + (1 - tree);
			}
		}
		
		for (int move = 0; move <= W; move++) {
			answer = Math.max(answer, dp[0][T][move]);
			answer = Math.max(answer, dp[1][T][move]);
		}
		
		System.out.println(answer);
	}
}