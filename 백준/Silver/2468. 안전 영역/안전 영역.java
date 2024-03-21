import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] dc = {0, 0, -1, 1};	// 상, 하, 좌, 우
	static int[] safeZone;	// 안전 영역의 개수를 저장할 배열
	static int count;	// 높이별 안전 영역의 개수를 저장할 변수
	static int maxCount = Integer.MIN_VALUE;	// 안전 영역의 개수의 최댓값을 저장한 변수	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		int maxHeight = 0;	// 
		for (int r = 0; r < N; r++) {
			
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				
				map[r][c] = Integer.parseInt(st.nextToken());
				if (maxHeight < map[r][c]) maxHeight = map[r][c];
			}
		}	// r에 대한 for문
		
		safeZone = new int[maxHeight];
		for (int h = 0; h <= maxHeight; h++) {
		
			visit = new boolean[N][N];
			count = 0;
			water(h);
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					
					if (map[r][c] > 0 && !visit[r][c]) {	// 침수되지 않았고 && 방문하지 않은 위치라면
						BFS(r, c);	// BFS
						count++;
					}
				}
			}
			
			if (maxCount < count) maxCount = count;
		}	// h에 대한 for문
		
		System.out.println(maxCount);		
	}	// main 메소드
	
	static void water(int Height) {
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
				if (map[r][c] < Height) {
					map[r][c] = -1;	// 침수처리
				}
			}
		}
	}
	
	static void BFS(int R, int C) {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {R, C});
		visit[R][C] = true;
		
		while (!queue.isEmpty()) {
			
			int[] now = queue.poll();
			int rNow = now[0];
			int cNow = now[1];
			
			for (int i = 0; i < 4; i++) {
				
				int rNext = rNow + dr[i];
				int cNext = cNow + dc[i];
				if(rNext >= 0 && rNext < N && cNext >= 0 && cNext < N && map[rNext][cNext] > 0 && !visit[rNext][cNext]) { 
					
					visit[rNext][cNext] = true;
					queue.add(new int[] {rNext, cNext});
				}			
			}
		}
	}
}