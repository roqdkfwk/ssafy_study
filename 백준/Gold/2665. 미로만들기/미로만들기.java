import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;	// 한 변의 길이
	static int[][] miro, dist;	// 미로, 거리
	static Queue<int[]> queue;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static final int INF = 987654321;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		miro = new int[N][N];
		dist = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(dist[i], INF);
		
		for (int r = 0; r < N; r++) {
			
			String str = br.readLine();
			for (int c = 0; c < N; c++)
				miro[r][c] = str.charAt(c) - '0';
		}	// r에 대한 for문
		
		// 시작 방을 기준으로 탐색 시작
		BFS();
		
		System.out.println(dist[N - 1][N - 1]);
	}	// main

	private static void BFS() {

		queue = new LinkedList<int[]>();
		queue.add(new int[] {0, 0});
		dist[0][0] = 0;
		
		while (!queue.isEmpty()) {
			
			int[] now = queue.poll();
			int rNow = now[0];
			int cNow = now[1];
			
			for (int i = 0; i < 4; i++) {
				
				int rNext = rNow + dr[i];
				int cNext = cNow + dc[i];
				if (isOK(rNext, cNext, dist[rNow][cNow])) {
					
					// 다음 방이 흰 방이라면
					if (miro[rNext][cNext] == 1)
						dist[rNext][cNext] = dist[rNow][cNow];
					// 다음 방이 검은 방이라면
					else dist[rNext][cNext] = dist[rNow][cNow] + 1;
					
					queue.add(new int[] {rNext, cNext});
				}
			}
		}
	}	// BFS

	private static boolean isOK(int rNext, int cNext, int distance) {
		
		if (rNext < 0 || rNext >= N || cNext < 0 || cNext >= N || dist[rNext][cNext] <= distance)
			return false;
		return true;
	}	// isOK
}