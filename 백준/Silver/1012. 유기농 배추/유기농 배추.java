import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T;
	static int R;
	static int C;
	static int[][] map;
	static boolean[][] visit;
	static int K;
	static int count;
	static Queue<int[]> queue;
	static int[] dr = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] dc = {0, 0, -1, 1};	// 상, 하, 좌, 우
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			visit = new boolean[R][C];
			K = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < K; i++) {
				
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
			}
			
			count = 0;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					
					if (map[r][c] == 1 && !visit[r][c]) {
						BFS(r, c);
						count++;
					}
				}
			}	// r에 대한 for문
			
			System.out.println(count);
		}
	}	// main 메소드
	
	static void BFS(int r, int c) {
		
		queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		
		while (!queue.isEmpty()) {
			
			int[] now = queue.poll();
			int rNow = now[0];
			int cNow = now[1];
			
			for (int i = 0; i < 4; i++) {
				
				int rNext = rNow + dr[i];
				int cNext = cNow + dc[i];
				if (rNext >= 0 && rNext < R && cNext >= 0 && cNext < C && map[rNext][cNext] == 1 && !visit[rNext][cNext]) {
					
					visit[rNext][cNext] = true;
					queue.add(new int[] {rNext, cNext});
				}
			}
		}
	}	// BFS 메소드
}