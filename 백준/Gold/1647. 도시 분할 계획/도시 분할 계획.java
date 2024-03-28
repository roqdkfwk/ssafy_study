import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	
	int start;
	int end;
	int weight;

	Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return weight - o.weight;
	}
}

public class Main {
	
	static int N;
	static int M;
	static int[] p;
	static ArrayList<Edge> edges;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges.add(new Edge(start, end, weight));
		}

		p = new int[N + 1];
		for (int i = 1; i <= N; i++) p[i] = i;

		Collections.sort(edges);
		
		int ans = 0;
		int cost = 0;
		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);

			if (findset(edge.start) != findset(edge.end)) {
				ans += edge.weight;
				union(edge.start, edge.end);
				
				cost = edge.weight;
			}
		}

		System.out.println(ans - cost);
	}

	public static int findset(int x) {
		
		if (x != p[x]) 
			p[x] = findset(p[x]);
		return p[x];
	}

	public static void union(int x, int y) {
		
		x = findset(x);
		y = findset(y);

		if (x != y) {
			p[y] = x;
		}
	}
}