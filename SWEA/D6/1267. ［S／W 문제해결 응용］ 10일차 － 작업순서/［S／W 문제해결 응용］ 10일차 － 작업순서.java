import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int V, E;	// 정점, 간선의 수
	static int[][] adj;	// 인접행렬
	static int[] degree;	// 진입 차수 저장할 배열
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int t = 1; t <= 10; t++) {
			
			sb.append("#" + t + " ");
			
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			adj = new int[V + 1][V + 1];
			degree = new int[V + 1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				adj[start][end] = 1;
				degree[end]++;
			}
			
			queue = new LinkedList<>();
			for (int i = 1; i < V + 1; i++) {
				if (degree[i] == 0) queue.offer(i);
			}
			
			while (!queue.isEmpty()) {
				
				int curr = queue.poll();
				sb.append(curr + " ");
				
				for (int i = 1; i < V + 1; i++) {
					
					if (adj[curr][i] == 1) {
						degree[i]--;
						
						if (degree[i] == 0) {
							queue.offer(i);						
						}
					}
				}
			}	// while
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}	// main
}