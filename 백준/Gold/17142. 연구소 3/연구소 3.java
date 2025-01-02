import java.util.*;
import java.io.*;
public class Main {

	static int N, M;
	static int emptySpace;
	static int[][] lab;
	static List<int[]> virusLocation;
	static int[] selectedVirus;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int answer = 987654321;
			
	public static void main(String[] args) throws IOException {
		InputHandler();
		
		Solution();
		
		printResult();
	}
	
	static void printResult() {
		System.out.println(answer == 987654321 ? -1 : answer);
	}
	
	static boolean isInside(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
	
	static void spreadVirus(int[] selected) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		for (int num : selected) {
			int[] virus = virusLocation.get(num);
			queue.add(virus);
			visited[virus[0]][virus[1]] = true;
		}
		
		int time = 0;
		int infectedCount = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] virus = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = virus[0] + dr[d];
					int nc = virus[1] + dc[d];
					
					if (!isInside(nr, nc)) continue;		// 연구소 범위를 벗어나는 경우
					if (lab[nr][nc] == 1) continue;			// 벽인 경우
					if (visited[nr][nc]) continue;			// 이미 방문한 위치인 경우
					if (lab[nr][nc] == 0) infectedCount++;	// 감염시킨 경우
					
					queue.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
			// 바이러스를 퍼뜨린 후 1초만큼 증가
			time++;
			// 빈 칸에 바이러스를 모두 퍼뜨린 경우
			if (emptySpace == infectedCount) {
				answer = Math.min(answer, time);
				return;
			}
		}
	}
	
	static void selectVirus(int[] selected, int start, int depth) {
		if (depth == M) {
			spreadVirus(selected);
			return;
		}
		
		for (int i = start; i < virusLocation.size(); i++) {
			selectedVirus[depth] = i;
			selectVirus(selectedVirus, i + 1, depth + 1);
		}
	}
	
	static void Solution() {
		if (emptySpace == 0) {
			answer = 0;
			return;
		}
		
		selectedVirus = new int[M];
		selectVirus(new int[M], 0, 0);
	}
	
	static void InputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lab = new int[N][N];
		virusLocation = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				lab[r][c] = Integer.parseInt(st.nextToken());
				
				if (lab[r][c] == 0)
					emptySpace++;	// 빈 칸의 개수
				if (lab[r][c] == 2)
					virusLocation.add(new int[] {r, c});
			}
		}
	}
}