import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[][] map;
	static boolean[][] visit;
	static Queue<int[]> queue;
	static int[] dr = {-1, 1, 0, 0};    // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};    // 상, 하, 좌, 우
    static int maxDist = Integer.MIN_VALUE;
    static int dist;
    
    // 보물이 묻혀 있는 두 곳 간의 최단 거리를 구해야 하므로 DFS를 쓸 수 없고 BFS를 이용해야 한다.
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			
			String str = br.readLine();
			for (int c = 0; c < C; c++) 
				map[r][c] = str.charAt(c);
		}	// map
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				
				if (map[r][c] == 'L') 
					BFS(r, c);
			}
		}
		
		System.out.println(maxDist);
	}	// main
	
	static void BFS(int r, int c) {
		
//		System.out.println();
		visit = new boolean[R][C];
		visit[r][c] = true;
		
		dist = 0;	// BFS를 할 때마다 dist는 0으로 초기화
		
		queue = new LinkedList<int[]>();
		queue.add(new int[] {r, c});
//		System.out.println("row : " + r + " col : " + c);
		
		while (!queue.isEmpty()) {
		
			int size = queue.size();
			
			for (int n = 0; n < size; n++) {
				
				int[] now = queue.poll();
				int rNow = now[0];
				int cNow = now[1];
				
				for (int i = 0; i < 4; i++) {
					
					int rNext = rNow + dr[i];
					int cNext = cNow + dc[i];
					if (isOK(rNext, cNext)) {
						
						visit[rNext][cNext] = true;	// 방문처리 후
//						System.out.println("r : " + rNext + " c : " + cNext);
						queue.add(new int[] {rNext, cNext});	// queue에 집어넣는다.
					}
				}	// i에 대한 for문
			}	// n에 대한 for문

			// i에 대한 for문이 끝난 후 queue가 비어있지 않다면 한 칸 더 갈 수 있다는 뜻이므로 dist를 1만큼 증가시킨다.
			if (!queue.isEmpty()) {

//				System.out.println("dist : " + dist);
				dist++;
			}
		}	// while
		
		maxDist = Math.max(maxDist, dist);
	}	// BFS

	private static boolean isOK(int row, int col) {

		if (row < 0 || row >= R || col < 0 || col >= C || visit[row][col] || map[row][col] == 'W')
			return false;
		
		return true;
	}	// isOK
	
}