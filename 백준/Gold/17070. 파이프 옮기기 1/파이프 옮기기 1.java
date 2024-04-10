import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	static int ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int r = 1; r < N + 1; r++) {
			
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c < N + 1; c++)
				map[r][c] = Integer.parseInt(st.nextToken());
		}	// map
		
		ans = 0;
		DFS(1, 2, 0);
		System.out.println(ans);
	}

	private static void DFS(int row, int col, int D) {

		if (row == N && col == N) {
			ans++;
			return;
		}
		
		if (D == 0) {
			if (col + 1 <= N && map[row][col + 1] == 0)
				DFS(row, col + 1, 0);
			
		} else if (D == 1) {
			if (row + 1 <= N && map[row + 1][col] == 0) 
				DFS(row + 1, col, 1);
			
		} else if (D == 2) {
			if (col + 1 <= N && map[row][col + 1] == 0) 
				DFS(row, col + 1, 0);
			if (row + 1 <= N && map[row + 1][col] == 0)
				DFS(row + 1, col, 1);	
		}
		
		if (row + 1 <= N && col + 1 <= N && map[row][col + 1] == 0 && map[row + 1][col] == 0
				&& map[row + 1][col + 1] == 0)
			DFS(row + 1, col + 1, 2);
	}	// DFS

}