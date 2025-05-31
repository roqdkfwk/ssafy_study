import java.util.*;
import java.io.*;
public class Main {
	
	static class Study implements Comparable<Study> {
		int subject, cost;
		
		public Study(int subject, int cost) {
			this.subject = subject;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Study s) {
			return Integer.compare(this.cost, s.cost);
		}
	}
	
	static int N, M, R;
	static int count, answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 출제 범위 알고리즘 개수
		M = Integer.parseInt(st.nextToken());	// 배우고자 하는 알고리즘 개수
		
		PriorityQueue<Study> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		int[] subjects = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int cost = Integer.parseInt(st.nextToken());
			subjects[i] = cost;
			pq.add(new Study(i, cost));
		}
		
		Map<Integer, List<int[]>> map = new HashMap<>();
		R = Integer.parseInt(br.readLine());
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			map.putIfAbsent(A, new ArrayList<>());
			map.get(A).add(new int[] {B, C});
		}
		
		boolean[] visited = new boolean[N + 1];
		while(!pq.isEmpty()) {
			Study curr = pq.poll();
			int sub = curr.subject;
			int cost = curr.cost;
			
			if (visited[sub]) continue;
			
			visited[sub] = true;
			count++;
			answer = Math.max(answer, cost);
			
			if (count == M) {
				System.out.println(answer);
				return;
			}
			
			if (map.containsKey(sub)) {
				for (int[] s : map.get(sub)) {
					subjects[s[0]] = Math.max(subjects[s[0]] - s[1], 0);
					pq.add(new Study(s[0], subjects[s[0]]));
				}
			}
		}
	}
}