import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N;	// 정점의 개수
	public static int M;	// 간선의 개수
	public static int V;	// 탐색을 시작할 정점의 번호
	public static boolean[] visit;
	public static int[][] arr;
	public static Queue<Integer> queue = new LinkedList<>();;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][N + 1];
		visit = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			arr[r][c] = 1;
			arr[c][r] = 1;
		}

		for (int i = 1; i < N + 1; i++) visit[i] = false;
		DFS(V);
		for (int i = 1; i < N + 1; i++) visit[i] = false;
		BFS(V);
		System.out.println(sb);
	}	// main 메소드

	public static void DFS(int num) {
		
		visit[num] = true;
		sb.append(num).append(" ");
		
		for (int i = 1; i <= N; i++) {
			
			if (arr[num][i] == 1 && !visit[i]) DFS(i);
		}
	}	// DFS 메소드

	public static void BFS(int num) {
		
		queue.add(num);
		visit[num] = true;
		sb.append('\n').append(num).append(" ");
		
		while (!queue.isEmpty()) {
			
			int idx = queue.poll();
			for (int i = 1; i < N + 1; i++) {
				
				if (arr[idx][i] == 1 && !visit[i]) {
					queue.add(i);
					visit[i] = true;
					sb.append(i).append(" ");
				}

			}
		}

	}	// BFS 메소드
}