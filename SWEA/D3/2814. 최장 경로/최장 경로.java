import java.util.*;
import java.io.*;
public class Solution {
	
	static int N, M;
	static List<Integer>[] edges;
	static int maxDistance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			maxDistance = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			edges = new List[N + 1];
			for (int i = 1; i <= N; i++) {
				edges[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				edges[A].add(B);
				edges[B].add(A);
			}
			
			for (int i = 1; i <= N; i++) {
				int visited = (1 << i);
				bfs(i, 1, visited);
			}
			
			answer.append("#" + t + " ").append(maxDistance).append("\n");
		}
		
		System.out.println(answer.toString().trim());
	}
	
	private static void bfs(int curr, int distance, int visited) {
		maxDistance = Math.max(maxDistance, distance);
		
		for (int next : edges[curr]) {
			if (((1 << next) & visited) != 0) continue;
			bfs(next, distance + 1, visited | (1 << next));
		}
	}
}