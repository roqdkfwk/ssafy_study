import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int T;	// 테스트 케이스의 개수
	static int N;	// 마을 사람 수
	static boolean[] visit;
	static int[][] adj;	// 인접행렬
	static int M;	// 간선의 개수
	static int count;	// 무리의 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			sb.append("#" + t + " ");
			count = 0;	// 테스트 케이스마다 무리의 수 초기화
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			visit = new boolean[N + 1];
			adj = new int[N + 1][N + 1];
			for (int i = 1; i < M + 1; i++) {
				
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				adj[A][B] = adj[B][A] = 1;
			}
			
			for (int i = 1; i < N + 1; i++) {
				if (!visit[i]) {
					DFS(i);
					count++;
				}
			}
			
			sb.append(count + "\n");
		}	// t에 대한 for문
		
		System.out.println(sb);
	}
	
	static void DFS(int num) {
		
		visit[num] = true;
		
		for (int i = 1; i < N + 1; i++) {
			if (adj[num][i] == 1 && !visit[i]) {	// num번째 사람과 i번째 사람이 아는 사이 && i번째 사람은 방문하지 않았다면
				DFS(i);
			}
		}
	}
}