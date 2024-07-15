import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C, H;	// 상자의 크기, 상자의 개수
	static int[][][] box;	// 상자
	static int minimumDay;
	static Queue<int[]> tomato;	// 토마토의 위치를 저장할 Queue
	static boolean[][][] visit;	// 방문체크 배열
	static int[] dr = {-1, 1, 0, 0, 0, 0};	// 상, 하, 좌, 우, 위, 아래  
	static int[] dc = {0, 0, -1, 1, 0, 0};	// 상, 하, 좌, 우, 위, 아래
	static int[] dh = {0, 0, 0, 0, -1, 1};	// 상, 하, 좌, 우, 위, 아래
	static int ans = -1;	// 정답
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		box = new int[H][R][C];
		visit = new boolean[H][R][C];
		tomato = new LinkedList<int[]>();
		
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < R; r++) {
				
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					
					box[h][r][c] = Integer.parseInt(st.nextToken());
					if (box[h][r][c] == 1) {
						tomato.add(new int[] {h, r, c});
						visit[h][r][c] = true;
					}
					else if (box[h][r][c] == -1)
						visit[h][r][c] = true;
				}
			}
		}	// i에 대한 for문
		
		BFS();
		
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (!visit[h][r][c])
						ans = -1;
				}
			}
		}
		
		System.out.println(ans);
	}	// main

	private static void BFS() {

		while (!tomato.isEmpty()) {
			
			int size = tomato.size();
			
			for (int i = 0; i < size; i++) {
				
				int[] now = tomato.poll();
				int hNow = now[0];
				int rNow = now[1];
				int cNow = now[2];
				
				for (int j = 0; j < 6; j++) {
					
					int hNext = hNow + dh[j];
					int rNext = rNow + dr[j];
					int cNext = cNow + dc[j];
					
					if (isOk(hNext, rNext, cNext)) {
						tomato.add(new int[] {hNext, rNext, cNext});
						visit[hNext][rNext][cNext] = true;
					}
				}
			}
			
			ans++;
		}	// while
	}	// BFS

	private static boolean isOk(int hNext, int rNext, int cNext) {
		
		if (hNext < 0 || hNext >= H || rNext < 0 || rNext >= R || cNext < 0 || cNext >= C
				|| visit[hNext][rNext][cNext])
			return false;
		
		return true;
	}	// isOk
}