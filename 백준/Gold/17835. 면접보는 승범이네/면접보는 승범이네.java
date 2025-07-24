import java.util.*;
import java.io.*;
public class Main {
	
	static class Edge implements Comparable<Edge> {
		int to;
		long dist;
		
		public Edge(int to, long dist) {
			this.to = to;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Long.compare(this.dist, e.dist);
		}
	}
	
	static int N, M, K;
	static List<Edge>[] edges;
	static int[] arrivals;
	static long[] distance;
	
	/**
	 * 면접장에서 가장 먼 도시를 구해야하므로
	 * 면접장에서 다익스트라를 시작해서 갱신
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 도로 입력
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long dist = Long.parseLong(st.nextToken());
			
			edges[to].add(new Edge(from, dist));
		}
		
		// 면접장 입력
		arrivals = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		// 면접장에서 다익스트라 시작
		distance = new long[N + 1];
		Arrays.fill(distance, Long.MAX_VALUE);
		for (int arrival : arrivals) {
			distance[arrival] = 0;
		}
		
		dijkstra();
		
		long maxDistance = 0;
		int index = 0;
		for (int i = 1; i <= N; i++) {
			if (maxDistance >= distance[i]) continue;
			
			maxDistance = distance[i];
			index = i;
		}
		
		System.out.println(index + "\n" + maxDistance);
	}
	
	private static void dijkstra() {
		// 특정 도시까지의 거리를 면접장마다 계산하여 가장 짧은 거리만 저장
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int arrival : arrivals) {
			pq.add(new Edge(arrival, 0));
		}
		
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			if (curr.dist > distance[curr.to]) continue;
			
			for (Edge next : edges[curr.to]) {
				if (distance[next.to] <= curr.dist + next.dist || distance[next.to] == 0) continue;
				
				distance[next.to] = curr.dist + next.dist;
				pq.add(new Edge(next.to, distance[next.to]));
			}
		}
	}
}