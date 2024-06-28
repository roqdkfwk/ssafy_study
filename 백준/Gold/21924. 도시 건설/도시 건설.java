import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}
	}	// Edge
	
	static int N, M;	// 건물, 도로의 개수
	static long maxCost;	// 도로를 다 설치할 때 드는 비용
	static long minCost;	// 도로를 설치할 수 있는 최소 비용
	static ArrayList<Edge> edges;	// 도로의 정보를 담을 배열
	static int[] p;	// 대표를 저장할 배열
	static int numOfEdges;	// 도로의 개수
	static long ans;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maxCost = 0;
		minCost = 0;
		
		edges = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 도로 설치 비용을 모두 더해줌
			maxCost += weight;
			
			edges.add(new Edge(from, to, weight));
		}	// edges 완성
		
		// edges를 weight에 대해 오름차순으로 정렬
		Collections.sort(edges);
		
		p = new int[N + 1];
		for (int i = 0; i < N; i++) p[i] = i;
		
		numOfEdges = 0;
		ans = -1;
		for (int i = 0; i < M; i++) {
			
			// i번째 도로
			Edge edge = edges.get(i);
			
			int px = findset(edge.from);
			int py = findset(edge.to);
			
			if (px != py) {	// 두 집합의 대표가 다르면 서로소인 집합
				
				union(px, py);	// 두 지합을 합치고				
				minCost += edge.weight;	// 도로 건설 비용을 더하고
				numOfEdges++;	// 건설한 도로의 개수를 1만큼 증가
			}
			
			
			// (N - 1)개의 도로를 건설했으면 반복문을 종료
			if (numOfEdges == N - 1) {
				ans = maxCost - minCost;
				break;
			}
		}	// for문
		
		System.out.println(ans);
		
	}	// main

	static void union(int x, int y) {
		
		p[findset(y)] = findset(x);
	}

	static int findset(int x) {
	
		if (x != p[x])
			p[x] = findset(p[x]);
		return p[x];
	}	// findset
	
}