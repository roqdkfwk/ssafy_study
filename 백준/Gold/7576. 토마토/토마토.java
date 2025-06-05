import java.io.*;
import java.util.*;

public class Main {

	static int R , C;
	static int[][] tom;
	static Queue<int[]> queue;
	static boolean[][] visit;
	static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int ans = -1;
	
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
				if (tom[r][c] == 1)	{
					queue.add(new int[] {r, c});
					visit[r][c] = true;
				}
				else if (tom[r][c] == -1)
					visit[r][c] = true;
			}
		}
		
		bfs();
		
		for (int r = 0; r < R; r++) 
			for (int c = 0; c < C; c++)
				if (!visit[r][c])
					ans = -1;
		
		System.out.println(ans);
	}	// main
	
	static void bfs() {
		
		while (!queue.isEmpty()) {
			
			int size = queue.size();			
			for (int i = 0; i < size; i++) {
				int[] now = queue.poll();
				int rNow = now[0];
				int cNow = now[1];
				
				for (int j = 0; j < 4; j++) {
					int rNext = rNow + dr[j];
					int cNext = cNow + dc[j];
					
					if (isOk(rNext, cNext) && !visit[rNext][cNext])	{
						queue.add(new int[] {rNext, cNext});
						visit[rNext][cNext] = true;
					}
				}
			}
			
			ans++;
		}
	}

	static boolean isOk(int r, int c) {
		return !(r < 0 || r >= R || c < 0 || c >= C);
	}
}