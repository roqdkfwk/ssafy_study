import java.util.*;
import java.io.*;
public class Main {
	
	static int[][] cave = new int[125][125];
	static int[][] dp = new int[125][125];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = 0;
		int t = 1;
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		while((N = Integer.parseInt(br.readLine())) != 0) {
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				Arrays.fill(dp[r], -1);
				for (int c = 0; c < N; c++) {
					cave[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			dp[0][0] = cave[0][0];
			
			Deque<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] {0, 0});
			
			while(!queue.isEmpty()) {
				int[] curr = queue.poll();
				int r = curr[0];
				int c = curr[1];
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					// 이전에 방문한 위치 && 최소로 소지금을 잃는 루트가 아닌 경우 패스
					if (dp[nr][nc] != -1 && dp[nr][nc] <= dp[r][c] + cave[nr][nc]) continue;
					
					dp[nr][nc] = dp[r][c] + cave[nr][nc];
					queue.add(new int[] {nr, nc});
				}
			}
			
			System.out.println("Problem " + t + ": " + dp[N - 1][N - 1]);
			t++;
		}
	}
}