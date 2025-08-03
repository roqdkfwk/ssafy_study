import java.util.*;
import java.io.*;
public class Main {
	
	static class Edge implements Comparable<Edge> {
		int arrival, length;
		
		public Edge(int arrival, int length) {
			this.arrival = arrival;
			this.length = length;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.length, e.length);
		}
	}
	
	static int N, M;
	static List<Edge>[] edges;
	static final int INF = 987654321;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
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
			int C = Integer.parseInt(st.nextToken());
			
			edges[A].add(new Edge(B, C));
			edges[B].add(new Edge(A, C));
		}
		
		st = new StringTokenizer(br.readLine());
		dijkstra(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		System.out.println(answer);
	}
	
	private static void dijkstra(int departure, int arrival) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] lengthSum = new int[N + 1];
		
		pq.add(new Edge(departure, 0));
		Arrays.fill(lengthSum, INF);
		lengthSum[departure] = 0;
		
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			if (curr.arrival == arrival) {
				answer = curr.length;
				return;
			}
			
			for (Edge next : edges[curr.arrival]) {
				if (lengthSum[next.arrival] <= curr.length + next.length) continue;
				
				lengthSum[next.arrival] = curr.length + next.length;
				pq.add(new Edge(next.arrival, lengthSum[next.arrival]));
			}
		}
	}
}