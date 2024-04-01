import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K;	// 원숭이가 나이트와 같이 움직일 수 있는 횟수
	static int R, C;	// 격자판의 세로, 가로 길이
	static int[][] map;	// 격자판
	static boolean[][][] visit;	// 방문 체크 배열
	static int[] dr = {-1, 1, 0, 0};	// 상, 하, 좌, 우
	static int[] dc = {0, 0, -1, 1};	// 상, 하, 좌, 우
	static int[] hr = {-2, -2, -1, 1, 2, 2, 1, -1};
	static int[] hc = {-1, 1, 2, 2, 1, -1, -2, -2};
	static Queue<int[]> queue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for (int r = 0; r < R; r++) {
			
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) map[r][c] = Integer.parseInt(st.nextToken());
		}	// map 완성
		
		System.out.println(BFS(0, 0));
	}	// main
	
	static int BFS(int row, int col) {	// row, col : 현재 위치, horseMove : 
		
		queue = new LinkedList<>();
		visit = new boolean[R][C][K + 1];
		
		queue.add(new int[] {row, col, 0, 0});	// 현재 위치, 움직인 수, 나이트처럼 움직인 횟수
		visit[row][col][0] = true;
		
		while (!queue.isEmpty()) {
			
			int[] now = queue.poll();
			
			int rNow = now[0];	// 현재 row
			int cNow = now[1];	// 현재 col
			int move = now[2];	// 현재 움직인 횟수
			int horseMove = now[3];	// 나이트처럼 움직인 횟수
			
			if (rNow == R - 1 && cNow == C - 1)	// 목표 지점에 도착했다면 여태 움직인 횟수를 리턴 
				return move;
			
			for (int i = 0; i < 4; i++) {
				
				int rNext = rNow + dr[i];
				int cNext = cNow + dc[i];
				if (rNext >= 0 && rNext < R && cNext >= 0 && cNext < C 
						&& map[rNext][cNext] == 0 && !visit[rNext][cNext][horseMove]) {
					visit[rNext][cNext][horseMove] = true;
					queue.offer(new int[] {rNext, cNext, move + 1, horseMove});
				}
			}	// 상, 하, 좌, 우 움직이는 경우
			
			if (horseMove < K) {
				
				for (int i = 0; i < 8; i++) {
					
					int rNext = rNow + hr[i];
					int cNext = cNow + hc[i];
					if (rNext >= 0 && rNext < R && cNext >= 0 && cNext < C 
							&& map[rNext][cNext] == 0 && !visit[rNext][cNext][horseMove + 1]) {
						visit[rNext][cNext][horseMove + 1] = true;
						queue.add(new int[] {rNext, cNext, move + 1, horseMove + 1});
					}
				}
			}	// 나이트처럼 움직이는 경우
		}	// while문
		
		return -1;	// while문 내에서 return이 되지 않는 경우 목표 지점에 도착하지 못하는 것이므로 -1을 리턴
	}	// BFS
}