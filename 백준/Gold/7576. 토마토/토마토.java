import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R , C;	// 행, 열
	static int[][] tom;	// 상자
	static Queue<int[]> queue;	// 토마토의 위치 저장
	static boolean[][] visit;	// 방문처리
	static int[] dr = {-1, 1, 0, 0};    // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};    // 상, 하, 좌, 우
    static int ans = -1;	// 정답
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		tom = new int[R][C];
		queue = new LinkedList<>();
		visit = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				
				tom[r][c] = Integer.parseInt(st.nextToken());
				if (tom[r][c] == 1)	{	// 토마토이면 queue에 추가 후 방문처리
					queue.add(new int[] {r, c});
					visit[r][c] = true;
				}
				else if (tom[r][c] == -1)	// 토마토가 없는 칸이면 방문처리
					visit[r][c] = true;
			}	// c에 대한 for문
		}	// r에 대한 for문
		
		BFS();
		
		for (int r = 0; r < R; r++) 
			for (int c = 0; c < C; c++)
				if (!visit[r][c])
					ans = -1;
		
		System.out.println(ans);
	}	// main
	
	static void BFS() {
		
		while (!queue.isEmpty()) {
			
			// queue를 다 비워야 하므로 queue의 사이즈를 저장할 변수 size
			int size = queue.size();			
			
			for (int i = 0; i < size; i++) {
				
				int[] now = queue.poll();
				int rNow = now[0];
				int cNow = now[1];
				
				for (int j = 0; j < 4; j++) {	// 4방 탐색
					
					int rNext = rNow + dr[j];
					int cNext = cNow + dc[j];
					
					if (isOk(rNext, cNext))	{	// 범위를 벗어나지 않고, 방문하지 않았다면
						queue.add(new int[] {rNext, cNext});
						visit[rNext][cNext] = true;
					}
				}
			}	// i에 대한 for문
			
			ans++;
		}	// while		
		
	}	// BFS

	static boolean isOk(int rNext, int cNext) {
		
		if (rNext < 0 || rNext >= R || cNext < 0 || cNext >= C || visit[rNext][cNext])
			return false;
		
		return true;
	}	// isOk
}