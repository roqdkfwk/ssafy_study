import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R;
	static int C;
	static int[][] map;
	static int[][] copyMap;
	static int[] dr = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] dc = {0, 0, -1, 1};	// 상, 하, 좌, 우
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) map[r][c] = Integer.parseInt(st.nextToken());
		}	// map완성
		
		DFS(0);	// 벽을 세우고
		BFS();	// BFS로 빈 칸의 개수 중 제일 큰 값을 찾음
		System.out.println(max);
	}	// main
	
	// 벽을 세우는 메소드
	static void DFS(int wallCnt) {
		
		if (wallCnt == 3) {
			BFS();	// 벽 3개를 다 세웠으면 BFS 시작
			return;
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				
				if (map[r][c] == 0) {	// 빈 칸이면
					map[r][c] = 1;	// 벽을 세움
					DFS(wallCnt + 1);
					map[r][c] = 0;
				}
			}
		}
	}	// DFS

	// 바이러스를 퍼뜨리는 메소드
	private static void BFS() {
		
		Queue<int[]> queue = new LinkedList<int[]>();	// 바이러스의 좌표를 담음
		
		copyMap = new int[R][C];	// 벽의 위치가 바뀌어 BFS를 호출할 떄마다 copyMap을 새로 설정
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {	// copyMap에 map을 복사
				copyMap[r][c] = map[r][c];
				if (copyMap[r][c] == 2) queue.add(new int[] {r, c});	// 바이러스가 있다면 queue에 추가
			}			
		}
		
		while(!queue.isEmpty()) {
			
			int[] now = queue.poll();	// queue에서 바이러스의 좌표를 꺼내 now에 저장
			int rNow = now[0];
			int cNow = now[1];
			
			for (int i = 0; i < 4; i++) {
				
				int rNext = rNow + dr[i];
				int cNext = cNow + dc[i];
				if (rNext >= 0 && rNext < R && cNext >= 0 && cNext < C && copyMap[rNext][cNext] == 0) {	// (범위를 벗어나지 않고) && (빈 칸이라면)
					copyMap[rNext][cNext] = 2;	// 바이러스를 넣고
					queue.add(new int[] {rNext, cNext});	// 바이러스를 넣은 칸을 queue에 추가
				}
			}
		}
		
		int count = 0;	// 빈 칸의 개수를 저장할 변수
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (copyMap[r][c] == 0) {	// 감염되지 않았다면
					count++;
				}
			}
		}
		
		max = Math.max(max, count);
	}	// BFS, 근데 이것도 바이러스가 계속 퍼져나가는 거니까 DFS로 하면 안되나??
}