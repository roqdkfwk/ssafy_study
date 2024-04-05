import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static int N, M;	// N : 정점, M : 간선
	static int[][] adj;
	static boolean[] visit;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());
		
		adj = new int[N][N];
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			adj[A][B] = adj[B][A] = 1;
		}	// adj
		
		visit = new boolean[N];
		
		for (int i = 1; i < N; i++) {
			
			if (!visit[i]) {
				count++;
				DFS(i);
			}
		}
		
		System.out.println(count);
	}	// main
	
	static void DFS(int start) {
		
		visit[start] = true;
		
		for (int i = 1; i < N; i++) {
			if (adj[start][i] == 1 && !visit[i])
				DFS(i);
		}
	}	// DFS
}