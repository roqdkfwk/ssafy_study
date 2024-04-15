import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] cave;
	static int[][] dist;
	static boolean[][] visit;
	static final int INF = 987654321;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int minDist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int stage = 1;
		while (true) {
			
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			
			cave = new int[N][N];
			for (int r = 0; r < N; r++) {
				
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) 
					cave[r][c] = Integer.parseInt(st.nextToken());
			}	// cave
			
			visit = new boolean[N][N];
			dist = new int[N][N];
			for (int r = 0; r < N; r++)
				Arrays.fill(dist[r], INF);
			dist[0][0] = cave[0][0];
			
			Dijkstra(0, 0);
			
			sb.append("Problem ").append(stage++).append(": ").append(minDist).append("\n");
		}	// while
		
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}	// main

	private static void Dijkstra(int row, int col) {

		int rNow = row;
		int cNow = col;
		
		for (int n = 0; n < N * N - 1; n++) {
			
			int min = INF;
			int Ridx = 0;
			int Cidx = 0;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (dist[r][c] < min && !visit[r][c]) {
						
						min = dist[r][c];
						Ridx = r;
						Cidx = c;
					}
				}
			}	// r에 대한 for문
			
			visit[Ridx][Cidx] = true;
			
			for (int i = 0; i < 4; i++) {
				
				int rNext = Ridx + dr[i];
				int cNext = Cidx + dc[i];
				if (isOK(rNext, cNext)) 
					dist[rNext][cNext] = Math.min(dist[rNext][cNext], dist[Ridx][Cidx] + cave[rNext][cNext]);
			}
		}	// n에 대한 for문
		
		minDist = dist[N - 1][N - 1];
	}	// Dijkstra

	private static boolean isOK(int row, int col) {
		
		if (row < 0 || row >= N || col < 0 || col >= N || visit[row][col])
			return false;
		
		return true;
	}	// isOK
}