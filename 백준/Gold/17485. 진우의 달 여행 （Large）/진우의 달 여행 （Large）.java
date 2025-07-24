import java.util.*;
import java.io.*;
public class Main {
	
	static int R, C;
	static int[][] space;
	static int[][][] dp;
	static int[] dc = {-1, 0, 1};
	
	/**
	 * 같은 방향으로 움직여서 같은 위치에 도착했을 때 연료를 더 적게 사용해서 도착할 수 있으면 그 이후는 고려 X
	 * >> dp
	 */
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		space = new int[R][C];
		for (int r = 0; r < R; r++) {
			space[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		dp = new int[R][C][3];	//	[행][열][마지막 움직인 방향]
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				Arrays.fill(dp[r][c], 987654321);
			}
		}
		
		// 출발할 때 필요한 연료
		for (int d = 0; d < 3; d++) {
			for (int c = 0; c < C; c++) {
				dp[0][c][d] = space[0][c];
			}
		}
		
		for (int r = 1; r < R; r++) {
			dp[r][0][0] = Math.min(dp[r - 1][1][1], dp[r - 1][1][2]) + space[r][0];
			dp[r][0][1] = dp[r - 1][0][0] + space[r][0];
			dp[r][C - 1][1] = dp[r - 1][C - 1][2] + space[r][C - 1];
			dp[r][C - 1][2] = Math.min(dp[r - 1][C - 2][0], dp[r - 1][C - 2][1]) + space[r][C - 1];
			
			for (int c = 1; c < C - 1; c++) {
				dp[r][c][0] = Math.min(dp[r - 1][c + 1][1], dp[r - 1][c + 1][2]) + space[r][c];
				dp[r][c][1] = Math.min(dp[r - 1][c][0], dp[r - 1][c][2]) + space[r][c];
				dp[r][c][2] = Math.min(dp[r - 1][c - 1][0], dp[r - 1][c - 1][1]) + space[r][c];
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for (int c = 0; c < C; c++) {
			for (int d = 0; d < 3; d++) {
				answer = Math.min(answer, dp[R - 1][c][d]);
			}
		}
		
		System.out.println(answer);
	}
}