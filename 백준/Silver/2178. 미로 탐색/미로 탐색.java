import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R;	// 가로 줄 수 
	static int C;	// 세로 줄 수
	static int[][] miro;	// 미로
	static boolean[][] visit;
	static int[][] dist;	// (1, 1)에서 떨어진 거리를 저장할 배열
	static int[] dr = {-1, 1, 0, 0};    // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};    // 상, 하, 좌, 우
    
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		miro = new int[R][C];
		visit = new boolean[R][C];
		dist = new int[R][C];
		dist[0][0] = 1;
		visit[0][0] = true;
		for (int r = 0; r < R; r++) {
			
			String str = br.readLine();
			for (int c = 0; c < C; c++) miro[r][c] = str.charAt(c) - '0';
		}
		
		search(0, 0);
//		for (int r = 0; r < R; r++) {
//			for (int c = 0; c < C; c++) System.out.print(dist[r][c] + " ");
//			System.out.println();
//		}
		
		System.out.println(dist[R - 1][C - 1]);
	}
	
	static void search(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		
		while(!queue.isEmpty()) {
			
			int now[] = queue.poll();
			int rNow = now[0];
			int cNow = now[1];
			for (int i = 0; i < 4; i++) {
				
				int rNext = rNow + dr[i];
				int cNext = cNow + dc[i];
				
				if ((rNext >= 0 && rNext < R && cNext >= 0 && cNext < C) && miro[rNext][cNext] != 0 && !visit[rNext][cNext]) {	// (배열을 벗어나지 않고) && (벽이 아니면서) && (이전에 방문하지 않았던 곳)
					queue.add(new int[] {rNext, cNext});
					dist[rNext][cNext] = dist[rNow][cNow] + 1;
					visit[rNext][cNext] = true;				
				}
			}
		}
	}
}