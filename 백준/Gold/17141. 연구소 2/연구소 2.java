import java.util.*;
import java.io.*;
public class Main {

	static int N, M;
	static int[][] lab;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<int[]> virusList = new ArrayList<>();
	static int emptyPlace, infectedPlace;
	static final int INF = 987654321;
	static int answer = INF;
	public static void main(String[] args) throws IOException {
		InputHandler();
		
		Solution();
		
		printResult();
	}
	
	static void printResult() {
		if (answer == INF) System.out.println(-1);
		else System.out.println(answer);
	}
	
	static boolean virusCanMove(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N && lab[r][c] != 1;
	}
	
	static int spreadVirus(int[] selectedVirus) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		for (int i : selectedVirus) { 
			int[] virus = virusList.get(i);		// 조합된 바이러스를 가져와서
			queue.add(virus);					// 큐에 집어넣고
			visited[virus[0]][virus[1]] = true;	// 방문처리
		}

		int time = -1;
		infectedPlace = M;	// 이미 M개의 지역에 바이러스를 놓고 시작하므로 M으로 초기화
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] curr = queue.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];
					
					if (!virusCanMove(nr, nc)) continue;	// 바이러스가 퍼질 수 없는 위치이거나
					if (visited[nr][nc]) continue;			// 이전에 이미 방문했던 경우
					
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
					infectedPlace++;
				}
			}
			
			// 바이러스가 한 칸씩 퍼지고나서는 시간이 1만큼 증가한다.
			time++;
		}
		
		if (emptyPlace == infectedPlace) return time;
		
		return INF;
	}
	
	static void combination(int[] selectedVirus, int start, int depth) {
		// 2. M개의 바이러스 조합을 마친 경우 바이러스가 퍼진다.
		if (depth == M) {
			answer = Math.min(answer, spreadVirus(selectedVirus));
			return;
		}
		
		for (int i = start; i < virusList.size(); i++) {
			selectedVirus[depth] = i;						// depth번째로 선택한 바이러스의 번호가 i번
			combination(selectedVirus, i + 1, depth + 1);	// i번 바이러스를 선택했다면 i+1번 바이러스부터 고려
		}
	}
	
	static void Solution() {
		// 1. 바이러스 조합
		combination(new int[M], 0, 0);
	}
	
	static void InputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lab = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				lab[r][c] = Integer.parseInt(st.nextToken());
				
				if (lab[r][c] == 2) virusList.add(new int[] {r, c});
				if (lab[r][c] != 1) emptyPlace++;
			}
		}
	}
}