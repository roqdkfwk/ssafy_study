import java.util.*;
import java.io.*;
public class Main {
	
	static class Edge implements Comparable<Edge> {
		int to, length;
		
		public Edge (int to, int length) {
			this.to = to;
			this.length = length;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.length - e.length;
		}
	}

	static int N, M;
	static int X;
	static List<Edge>[] edges, reverseEdges;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		InputHandler();
		
		int answer = Solution();
		
		printResult(answer);
	}
	
	static void printResult(int answer) {
		System.out.println(answer);
	}
	
	static int[] findShortestLength(boolean direction) {
		int[] shortestLength = new int[N + 1];
		Arrays.fill(shortestLength, INF);
		shortestLength[X] = 0;
		
		List<Edge>[] edgeList;
		if (direction) edgeList = edges;	// 정방향인 경우
		else edgeList = reverseEdges;		// 역방향인 경우
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(X, 0));
		
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			if (shortestLength[edge.to] < edge.length) continue;
			
			for (Edge e : edgeList[edge.to]) {
				if (shortestLength[e.to] > edge.length + e.length) {
					shortestLength[e.to] = edge.length + e.length;
					pq.add(new Edge(e.to, edge.length + e.length));
				}
			}
		}
		return shortestLength;
	}
	
	static int Solution() {
		/*
		 * 유향 그래프
		 * 각각의 마을에서 X번 마을에 가는 최단 거리를 찾기 위해 매번 다익스트라를 할 수 업으니
		 * 도로를 반대 방향으로 이은 리스트를 하나 더 사용해야한다.
		*/
		
		int[] fowardDirection = findShortestLength(true);
		int[] reverseDirection= findShortestLength(false);
		int[] lengthSum = new int[N + 1];
		
		int maxLength = 0;
		for (int i = 1; i <= N; i++) {
			lengthSum[i] = fowardDirection[i] + reverseDirection[i];
			maxLength = Math.max(maxLength, lengthSum[i]);
		}
		
		return maxLength;
	}
	
	static void InputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList[N + 1];			// X에서 각각의 마을까지의 최단 경로를 찾기 위한 리스트
		reverseEdges = new ArrayList[N + 1];	// 각각의 마을에서 X까지의 최단 경로를 찾기 위한 리스트
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			reverseEdges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			edges[from].add(new Edge(to, length));
			reverseEdges[to].add(new Edge(from, length));
		}
	}
}