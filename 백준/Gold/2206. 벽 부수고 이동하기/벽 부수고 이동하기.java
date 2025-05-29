import java.util.*;
import java.io.*;
public class Main {
	
	static class State {
		int r, c, wall, moveCount;
		
		public State(int r, int c, int wall, int moveCount) {
			this.r = r;
			this.c = c;
			this.wall = wall;
			this.moveCount = moveCount;
		}
	}
	
	static int R, C;
	static int[][] maze;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		maze = new int[R][C];
		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				maze[r][c] = str.charAt(c) - '0';
			}
		}
		
		visited = new boolean[R][C][2];
		visited[0][0][0] = true;
		
		Deque<State> queue = new ArrayDeque<>();
		queue.add(new State(0, 0, 0, 1));
		
		while (!queue.isEmpty()) {
			State curr = queue.poll();
			int wall = curr.wall;
			int moveCount = curr.moveCount;
			
			// 목표 위치에 도달하는 경우 종료
			if (curr.r == R - 1 && curr.c == C - 1) {
				System.out.println(curr.moveCount);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc][wall]) continue;
				if (curr.wall == 1 && maze[nr][nc] == 1) continue;	// 벽을 두 개 부숴야 하는 경우는 고려하지 않는다.
				
				
				if (maze[nr][nc] == 0) {
					visited[nr][nc][wall] = true;
					queue.add(new State(nr, nc, wall, moveCount + 1));
				} else {
					visited[nr][nc][wall + 1] = true;
					queue.add(new State(nr, nc, wall + 1, moveCount + 1));
				}
			}
		}
		
		// 목표 위치에 도달하지 못하는 경우 -1을 출력하고 종료
		System.out.println(-1);
		return;
	}
}