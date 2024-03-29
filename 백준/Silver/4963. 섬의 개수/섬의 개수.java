import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int w;	// 너비
	static int h;	// 높이
	static int[][] map;	// 지도
	static boolean[][] visit;
	static int count;	// 떨어져있는 섬의 개수
	static int[] dr = {-1, 1, 0, 0, -1, 1, -1, 1};	// 상, 하, 좌, 우, 좌상, 좌하, 우상, 우하
	static int[] dc = {0, 0, -1, 1, -1, -1, 1, 1};	// 상, 하, 좌, 우, 좌상, 좌하, 우상, 우하
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 || h == 0) break;
			
			map = new int[h][w];
			visit = new boolean[h][w];
			count = 0;
			
			for (int r = 0; r < h; r++) {
				
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < w; c++) map[r][c] = Integer.parseInt(st.nextToken());
			}
			
			for (int r = 0; r < h; r++) {
				for (int c = 0; c < w; c++) {
					
					if (map[r][c] == 1 && !visit[r][c]) {	// 방문하지 않았던 섬이라면
						BFS(r, c);	// 이어져 있는 섬을 모두 방문처리 하고 돌아오면
						count++;
						
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	static void BFS(int r, int c) {
		
		visit[r][c] = true;	// 해당 섬을 방문처리
		
		for (int i = 0; i < 8; i++) {
			
			int rNow = r + dr[i];
			int cNow = c + dc[i];
			
			if (rNow >= 0 && rNow < h && cNow >= 0 && cNow < w) {
				if (map[rNow][cNow] == 1 && !visit[rNow][cNow]) {	// 방문하지 않았던 섬이라면
					BFS(rNow, cNow);
				}
			}
		}
	}
}