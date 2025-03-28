import java.util.*;
import java.io.*;
public class Main {
	
	static int T;
	static int R, C;
	static char[][] grid;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] visited;
	static Queue<int[]> Jihun, Fire;
	static int answer;
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			InputHandler();
			
			Solution();
			
			printResult();
		}
	}
	
	static void printResult() {
		if (answer == 0) System.out.println("IMPOSSIBLE");
		else System.out.println(answer);
	}
	
	static boolean onEdge(int r, int c) {
		return r == 0 || r == R - 1 || c == 0 || c == C - 1;
	}
	
	static boolean jihunCanMove(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C && grid[r][c] == '.';
	}
	
	static boolean fireCanMove(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C && (grid[r][c] == '.' || grid[r][c] == '@');
	}
	
	static void Solution() {
		// 테스트 케이스마다 초기화
		answer = 0;
		// 지훈이가 가장자리에서 시작하는 경우
		if (onEdge(Jihun.peek()[0], Jihun.peek()[1])) {
			answer = 1;
			return;
		}
		
		int move = 0;
		while (!Jihun.isEmpty()) {
			// 불길이 먼저 번지고
			int fireCount = Fire.size();
			for (int i = 0; i < fireCount; i++) {
				int[] curr = Fire.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];
					
					if (!fireCanMove(nr, nc)) continue;
					
					grid[nr][nc] = '*';
					visited[nr][nc] = true;
					Fire.add(new int[] {nr, nc});
				}
			}
			
			// 지훈이가 움직인다.
			move++;
			int jihunCount = Jihun.size();
			for (int i = 0; i < jihunCount; i++) {
				int[] curr = Jihun.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];
					
					if (!jihunCanMove(nr, nc)) continue;
					if (onEdge(nr, nc)) {
						answer = move + 1;
						return;
					}
					
					grid[nr][nc] = '@';
					visited[nr][nc] = true;
					Jihun.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	static void InputHandler() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Jihun = new LinkedList<>();
		Fire = new LinkedList<>();
		
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		grid = new char[R][C];
		visited = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				grid[r][c] = str.charAt(c);
				
				if (grid[r][c] == '@') Jihun.add(new int[] {r, c});
				if (grid[r][c] == '*') {
					Fire.add(new int[] {r, c});
					visited[r][c] = true;
				}
			}
		}
	}
}