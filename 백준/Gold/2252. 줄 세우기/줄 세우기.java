import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		int[] income = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			adj[A].add(B);
			income[B]++;
		}
		
		StringBuilder answer = new StringBuilder();
		Deque<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (income[i] == 0) {
				queue.add(i);
				answer.append(i).append(" ");
			}
		}
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			
			for (int i = 1; i <= N; i++) {
				if (adj[num].size() == 0) continue;
				
				for (int stu : adj[num]) {
					income[stu]--;					
					if (income[stu] == 0) {
						queue.add(stu);
						answer.append(stu).append(" ");
					}
				}
				
				adj[num] = new ArrayList<>();
			}
		}
		
		System.out.println(answer.toString().trim());
	}
}