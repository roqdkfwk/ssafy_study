import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	static int T;	// 테스트 케이스의 수
	static int N, K;	// N : 한 변의 길이, K : 최대 공사 가능 깊이
	static int[][] area;	// 부지
	static boolean[][] visit;	// 방문 체크
	static int Height;	// 가장 높은 높이를 저장할 변수
	static Queue<int[]> maxH;	// 높이가 가장 높은 위치를 저장할 Queue
	static Stack<int[]> stack;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int maxAns;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			sb.append("#" + tc+ " ");
			
			ans = Integer.MIN_VALUE;
			maxAns = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			Height = 0;
			maxH = new LinkedList<>();
			area = new int[N][N];
			for (int r = 0; r < N; r++) {
				
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					
					area[r][c] = Integer.parseInt(st.nextToken());
					Height = Math.max(Height, area[r][c]);
				}
			}	// area
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					
					if (Height == area[r][c])
						maxH.add(new int[] {r, c});
				}
			}	// 가장 높은 위치를 Queue에 저장
			
			while (!maxH.isEmpty()) {
				
				visit = new boolean[N][N];
				int[] now = maxH.poll();
				DFS(now[0], now[1], 1, 0);
			}
			
			sb.append(ans + "\n");
		}	// tc에 대한 for문
		
		System.out.println(sb);
	}	// main
	
	// 현재 위치, 등산로의 길이, 공사 횟수
	static void DFS(int row, int col, int len, int use) {

		stack = new Stack<>();
		visit[row][col] = true;
		stack.add(new int[] {row, col});
		
		while (!stack.isEmpty()) {
			
			int[] now = stack.pop();
			int rNow = now[0];
			int cNow = now[1];
			
			for (int i = 0; i < 4; i++) {
				
				int rNext = rNow + dr[i];
				int cNext = cNow + dc[i];
				if (rNext < 0 || rNext >= N || cNext < 0 || cNext >= N)	// 범위를 벗어나는 경우
					continue;
				if (visit[rNext][cNext])	// 이미 방문한 지점인 경우
					continue;
				
				// 1번 IF
				if (area[rNext][cNext] < area[rNow][cNow]) {	// 다음 지형이 현재 지형보다 낮다면
//					System.out.println("1번 IF : " + rNext + " " + cNext);
					DFS(rNext, cNext, len + 1, use);			// 길이를 1만큼 증가시키고 다음 지형에서 DFS
					visit[rNext][cNext] = false;				// 다음 지형에서 DFS를 마치면 원상복구
				}
				// 2번 IF
				if (area[rNext][cNext] >= area[rNow][cNow] && use == 0) {	// 다음 지형이 현재 지형보다 높지만 공사를 안했다면
					if (area[rNext][cNext] - area[rNow][cNow] < K) {		// 공사를 해서 현재 지형보다 낮게 만들 수 있는 경우
//						System.out.println("2번 IF : " + rNext + " " + cNext);
						int tmp = area[rNext][cNext];
						area[rNext][cNext] = area[rNow][cNow] - 1;	
						DFS(rNext, cNext, len + 1, use + 1);
						area[rNext][cNext] = tmp;
						visit[rNext][cNext] = false;
					}
				}
				
			}
		}	// while

//		System.out.println("len : " + len);
		ans = Math.max(ans, len);
	}	// DFS
}