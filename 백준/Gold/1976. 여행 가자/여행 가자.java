import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M;
	static int[] parent;
	static int[][] city;
	static int[] visit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		city = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				city[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 1; r <= N; r++) {
			for (int c = r; c <= N; c++) {
				if (city[r][c] == 1) {	// r에서 c로 가는 경로가 존재하는 경우
					union(r, c);		// 두 도시를 합친다.
				}
			}
		}
		
		visit = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			visit[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M - 1; i++) {
			if (find(visit[i]) != find(visit[i + 1])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		return;
	}
	
	static void union(int x, int y) {
		int parX = find(x);
		int parY = find(y);
		
		if (parX != parY) {
			parent[parY] = parX;
		}
	}
	
	static int find(int x) {
		if (x != parent[x]) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}		
}