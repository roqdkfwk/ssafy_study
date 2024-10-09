import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int To, Cost;
		
		public Edge (int To, int Cost) {
			this.To = To;
			this.Cost = Cost;
		}
		
		@Override
		public int compareTo(Edge E) {
			return this.Cost - E.Cost;
		}
	}
	
	static int N, M;
	static List<Edge>[] edges;
	static int[] path, distance;
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		edges = new List[N + 1];
		for (int i = 0; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[Integer.parseInt(st.nextToken())]
					.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		st = new StringTokenizer(br.readLine());
		int Start = Integer.parseInt(st.nextToken());
		int End = Integer.parseInt(st.nextToken());
		
		path = new int[N + 1];
		path[Start] = Start;
		
		distance = new int[N + 1];
		Arrays.fill(distance, INF);
		distance[Start] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(Start, 0));
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			if (curr.Cost > distance[curr.To]) continue;
			
			for (Edge edge : edges[curr.To]) {
				if (distance[edge.To] > distance[curr.To] + edge.Cost) {
					distance[edge.To] = distance[curr.To] + edge.Cost;
					pq.add(new Edge(edge.To, distance[curr.To] + edge.Cost));
					path[edge.To] = curr.To;
				}
			}
		}
		
		System.out.println(distance[End]);
		
		Stack<Integer> stack = new Stack<>();
		stack.add(End);
		while (path[End] != Start) {
			stack.add(path[End]);
			End = path[End];
		}
		stack.add(Start);
		
		System.out.println(stack.size());
		
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}
}