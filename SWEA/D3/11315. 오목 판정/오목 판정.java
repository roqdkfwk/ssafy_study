import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static char[][] grid;
	static int[] dr = {0, 1, 1, 1};
	static int[] dc = {1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		outer:
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			grid = new char[N][N];
			
			for (int r = 0; r < N; r++) {
				grid[r] = br.readLine().toCharArray();
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (grid[r][c] == '.') continue;
					
					if (findLine(grid, r, c)) {
						answer.append("#" + t + " ").append("YES").append("\n");
						continue outer;
					}
				}
			}
			
			answer.append("#" + t + " ").append("NO").append("\n");
		}
		
		System.out.println(answer.toString().trim());
	}
	
	private static boolean findLine(char[][] grid, int r, int c) {
		outer:
		for (int d = 0; d < 4; d++) {
			int nr = r;
			int nc = c;
			
			for (int i = 0; i < 4; i++) {
				nr += dr[d];
				nc += dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || grid[nr][nc] == '.') continue outer;
			}
			
			return true;
		}
		
		return false;
	}
}