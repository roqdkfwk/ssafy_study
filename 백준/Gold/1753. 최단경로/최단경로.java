import java.util.*;
import java.io.*;
public class Main {
	
	/**
	 * V : 정점의 개수
	 * E : 간선의 개수
	 * K : 시작 정점의 번호
	 */
	
	private static class Edge implements Comparable<Edge> {
		int to, weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.weight, e.weight);
		}
	}
	
	static int V, E, K;
	static List<Edge>[] edges;
	static int[] distance;
	public static void main(String[] args) throws IOException {
		inputHandler();
		
		dijkstra(K);
		
		printResult();
	}
	
	private static void inputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine()) - 1;
		
		edges = new List[V];
		for (int i = 0; i < V; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			edges[Integer.parseInt(st.nextToken()) - 1]
					.add(new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
		}
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		
		distance = new int[V];
		Arrays.fill(distance, 987654321);
		distance[start] = 0;
		
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			for (Edge next : edges[curr.to]) {
				if (distance[next.to] > distance[curr.to] + next.weight) {
					distance[next.to] = distance[curr.to] + next.weight;
					pq.add(new Edge(next.to, distance[next.to]));
				}
			}
		}
	}
	
	private static void printResult() {
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < V; i++) {
			if (distance[i] == 987654321) {
				answer.append("INF").append("\n");
				continue;
			}
			
			answer.append(distance[i]).append("\n");
		}
		
		System.out.println(answer.toString());
	}
}
