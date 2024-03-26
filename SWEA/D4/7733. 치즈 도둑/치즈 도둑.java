import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int T;
	static int N;
	static int[][] map;
	static boolean[][] visit;	// 방문 체크할 배열
	static int count;	// 치즈덩어리의 개수
	static int maxCount = 0;	// 치즈덩어리의 개수의 최댓값
	static Queue<int[]> queue;
	static int[] dr = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] dc = {0, 0, -1, 1};	// 상, 하, 좌, 우
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			maxCount = 0;	// 테스트 케이스마다 치즈덩어리의 최대 개수 초기화
			sb.append("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) map[r][c] = Integer.parseInt(st.nextToken());
			}	// r에 대한 for문
			
			for (int i = 0; i <= 100; i++) {
				
				count = 0;	// 날마다 치즈덩어리의 개수를 다시 세기 위해 값을 0으로 초기화
				visit = new boolean[N][N];	// 날마다 치즈덩어리의 개수를 다시 세기 위해 방문처리 했던 것을 초기화 
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						
						if (map[r][c] == i) map[r][c] = 0;
					}
				}
				
				// 치즈덩어리 개수의 최댓값은 기존의 최댓값과 현재 덩어리의 개수 중 더 큰 값으로 갱신
				maxCount = Math.max(maxCount, countCheese(map));
			}
			
			sb.append(maxCount + "\n");
		}	// t에 대한 for문
		
		System.out.println(sb);		
	}
	
	static int countCheese(int[][] arr) {
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
				if (arr[r][c] != 0 && !visit[r][c]) {	// 이전에 방문하지 않았던 치즈덩어리를 만나면 
					bfs(r, c);	// 그 위치를 기준으로 bfs
					count++;	// bfs가 실행되는 횟수 = 떨어진 치즈덩어리의 개수
				}
			}
		}
		
		return count;
	}	// countCheese 메소드

	static void bfs(int row, int col) {
		
		queue = new LinkedList<>();
		queue.add(new int[] {row, col});
		visit[row][col] = true;
		
		while (!queue.isEmpty()) {
			
			int[] now = queue.poll();
			int rNow = now[0];
			int cNow = now[1];
			
			for (int i = 0; i < 4; i++) {
				
				int rNext = rNow + dr[i];
				int cNext = cNow + dc[i];
				if (rNext >= 0 && rNext < N && cNext >= 0 && cNext < N && map[rNext][cNext] != 0 && !visit[rNext][cNext]) {
					queue.add(new int[] {rNext, cNext});
					visit[rNext][cNext] = true;
				}
			}
		}
	}	// bfs 메소드
}