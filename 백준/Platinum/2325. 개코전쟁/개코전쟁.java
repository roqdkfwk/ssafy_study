import java.util.*;
import java.io.*;
public class Main {
	
	static class Edge implements Comparable<Edge> {
		int to, distance;
		
		public Edge(int to, int distance) {
			this.to = to;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.distance, e.distance);
		}
	}
	
	/**
	 * 무조건 파괴해야 하는 도로 - 최단 경로에 포함되어 있는 도로
	 * 
	 */
	static int N, M;
	static List<Edge>[] edges;
	static int[] distanceForward;
	static int[] parent;
	static int[] distance;
	static int answer;
	public static void main(String[] args) throws IOException {
		inputHandler();
		
		solution();
		
		printResult();
	}
	
	private static void inputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new List[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int C = Integer.parseInt(st.nextToken());
			
			edges[A].add(new Edge(B, C));
			edges[B].add(new Edge(A, C));
		}
		
		parent = new int[N];
	}
	
	private static void solution() {
		// 정방향 다익스트라
		distanceForward = dijkstra(0);
		
		int prev = N - 1;
		List<Integer> indexes = new ArrayList<>();
		while (true) {
			indexes.add(prev);
			if (prev == 0) {
				break;
			}
			
			prev = parent[prev];
		}
		
		Collections.reverse(indexes);
		for (int i = 0; i < indexes.size() - 1; i++) {
			int vertex_1 = indexes.get(i);
			int vertex_2 = indexes.get(i + 1);
			
			int distance = dijkstra(vertex_1, vertex_2);
			answer = Math.max(answer, distance);
		}
	}
	
	private static void printResult() {
		System.out.println(answer);
	}
	
	private static int[] dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		
		distance = new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			if (curr.distance > distance[curr.to]) continue;
			
			for (Edge next : edges[curr.to]) {
				if (distance[next.to] <= distance[curr.to] + next.distance) {
					continue;
				}
				
				distance[next.to] = distance[curr.to] + next.distance;
				pq.add(new Edge(next.to, distance[next.to]));
				
				parent[next.to] = curr.to;
			}
		}
		
		return distance;
	}
	
	private static int dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0));
		
		distance = new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0] = 0;
		
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			if (curr.distance > distance[curr.to]) continue;
			
			for (Edge next : edges[curr.to]) {
				if (distance[next.to] <= distance[curr.to] + next.distance) {
					continue;
				}
				
				if ((curr.to == start && next.to == end) || (curr.to == end && next.to == start)) {
					continue;
				}
				
				distance[next.to] = distance[curr.to] + next.distance;
				pq.add(new Edge(next.to, distance[next.to]));
			}
		}
		
		return distance[N - 1];
	}
}