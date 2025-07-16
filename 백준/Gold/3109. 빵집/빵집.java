import java.util.*;
import java.io.*;
public class Main {
	
	static int R, C;
	static char[][] grid;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1};	// 우상, 우 우하
	static boolean installed;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		grid = new char[R][C];
		visited = new boolean[R][C];
		
		for (int r = 0; r < R; r++) {
			grid[r] = br.readLine().toCharArray();
		}
		
		// (0, 0)에서 시작해서 행을 한 칸씩 내려가면서 파이프 설치
		// 파이프를 설치한 위치에는 다른 파이프 설치X
		// 탐색 방향은 우상, 우, 우하 세 가지 방향
		// 파이프를 설치한 경우에만 해당 위치 방문처리, 파이프를 설치하지 못한 경우에는 방문처리 X
		for (int i = 0; i < R; i++) {
			installed = false;
			
			dfs(i, 1);
		}
		
		System.out.println(answer);
	}
	
	private static void dfs(int row, int col) {
		if (installed) return;
		
		// 끝까지 도착하면 파이프 설치
		if (col == C) {
			// 설치한 파이프의 개수를 하나 더해주고
			answer++;
			
			// 파이프를 이미 설치했으므로 installed를 true
			installed = true;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int nr = row + dr[i];
			
			if (!isOk(nr, col)) continue;
			if (installed) return;
			
			visited[nr][col] = true;
			dfs(nr, col + 1);
		}
	}
	
	private static boolean isOk(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C && grid[r][c] == '.' && !visited[r][c];
	}
}
