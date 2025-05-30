import java.util.*;
import java.io.*;
public class Main {
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.weight, e.weight);
		}
	}
	
	static int V, E;
	static PriorityQueue<Edge> edges;;
	static int[] parent;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edges = new PriorityQueue<>();
		for (int i = 0; i < E; i++) {
 			st = new StringTokenizer(br.readLine());
 			edges.add(
 					new Edge(Integer.parseInt(st.nextToken()),
 							Integer.parseInt(st.nextToken()),
 							Integer.parseInt(st.nextToken()))
 					);
		}
		
		parent = new int[V + 1];
		for (int i = 1; i<= V; i++) {
			parent[i] = i;
		}
		
		int count = 0;
		while(!edges.isEmpty()) {
			Edge curr = edges.poll();
			
			int A = findParent(curr.from);
			int B = findParent(curr.to);
			
			if (A == B) continue;
			
			union(A, B);
			answer += curr.weight;
			count++;
			
			if (count == V - 1) {
				System.out.println(answer);
				return;
			}
		}
	}

	private static int findParent(int x) {
		if (parent[x] != x) {
			parent[x] = findParent(parent[x]);
		}
		return parent[x];
	}
	
	private static void union(int x, int y) {
		if (x < y) {
			parent[y] = parent[x];
		} else {
			parent[x] = parent[y];
		}
	}
}