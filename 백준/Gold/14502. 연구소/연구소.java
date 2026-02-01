import java.util.*;
import java.io.*;

public class Main {
	
	/**
	 * 연구소의 안전 영역의 크기의 최댓값
	 * = 안전 영역의 크기 or 지도 크기 - 바이러스
	 * 0 : 빈 칸, 1 : 벽, 2 : 바이러스
	 */
	
	static int N, M;
	static int[][] grid;
	static boolean[][] visited;
	static List<int[]> viruses;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int answer;
	
	public static void main(String[] args) throws IOException {
		inputHandler();
		
		solution();
		
		printResult();
	}
	
	private static void inputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		grid = new int[N][M];
		visited = new boolean[N][M];
		viruses = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
				
				if (grid[r][c] == 1) {
					visited[r][c] = true;
				}
				
				if (grid[r][c] == 2) {
					viruses.add(new int[] {r, c});
					visited[r][c] = true;
				}
			}
		}
	}
	
	/**
	 * 1. 벽 3개 세우는 방법 조합
	 * 2. 벽 3개를 모두 세우면 바이러스가 퍼질 수 있는 공간을 계산
	 */
	private static void solution() {
		combineWall(0, 0);
	}
	
	/**
	 * 
	 * @param index : 설치할 벽의 위치
	 * @param count : 설치한 벽의 개수 
	 */
	private static void combineWall(int index, int count) {
		if (count == 3) {
			countVirus();
			return;
		}

		if (index >= N * M) return;
		
		int r = index / M; 
		int c = index % M;
		
		if (grid[r][c] == 0) {
			visited[r][c] = true;
			combineWall(index + 1, count + 1);
			visited[r][c] = false;
		}
		
		combineWall(index + 1, count);
	}
	
	private static void countVirus() {
		boolean[][] tempVisited = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				tempVisited[r][c] = visited[r][c];
			}
		}
		
		for (int i = 0; i < viruses.size(); i++) {
			int r = viruses.get(i)[0];
			int c = viruses.get(i)[1];
			spreadVirus(r, c, tempVisited);
		}
		
		int safeZone = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (grid[r][c] == 0 && !tempVisited[r][c]) {
					safeZone++;
				}
			}
		}
		
		answer = Math.max(answer, safeZone);
	}
	
	private static int spreadVirus(int r, int c, boolean[][] tempVisited) {
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {r, c});
		
		int sumViruses = 0;
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];
				
				if (!isValid(nr, nc, tempVisited)) continue;
				
				tempVisited[nr][nc] = true;
				queue.add(new int[] {nr, nc});
				sumViruses++;
			}
		}
		
		return sumViruses;
	}
	
	private static boolean isValid(int r, int c, boolean[][] tempVisited) {
		return r >= 0 && r < N && c >= 0 && c < M && !tempVisited[r][c];
	}
	
	private static void printResult() {
		System.out.println(answer);
	}
}