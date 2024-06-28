import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static class Vertex {
		int num, x, y, z;
		
		Vertex(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}	// Vertex
	
	static class Edge implements Comparable<Edge> {
		
		int start, end, weight;
		
		Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}
	}	// Edge
	
	static int N;	// 행성의 개수
	static Vertex[] vertex;	// 정점 배열
	static ArrayList<Edge> edgeList;	// 간선 정보
	static int[] p;
	static int ans;	// 최소비용
	static int count;	// 간선의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		vertex = new Vertex[N];
				
		for (int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			vertex[i] = new Vertex(i, x, y, z);
		}
		
		edgeList = new ArrayList<>();
		
		// x에 대해 정렬
		Arrays.sort(vertex, (v1, v2) -> v1.x - v2.x);
		for (int i = 0; i < N - 1; i++) {
			
			// 비용을 계산
			int weight = Math.abs(vertex[i].x - vertex[i + 1].x);
			
			// 두 정점의 정보와 비용을 edgeList에 저장
			edgeList.add(new Edge(vertex[i].num, vertex[i + 1].num, weight));
		}
		
		// y에 대해 정렬
		Arrays.sort(vertex, (v1, v2) -> v1.y - v2.y);
		for (int i = 0; i < N - 1; i++) {
			
			int weight = Math.abs(vertex[i].y - vertex[i + 1].y);
			edgeList.add(new Edge(vertex[i].num, vertex[i + 1].num, weight));
		}
		
		// z에 대해 정렬
		Arrays.sort(vertex, (v1, v2) -> v1.z - v2.z);
		for (int i = 0; i < N - 1; i++) {
			
			int weight = Math.abs(vertex[i].z - vertex[i + 1].z);
			edgeList.add(new Edge(vertex[i].num, vertex[i + 1].num, weight));
		}
		
		// 각 정점의 부모를 자기 자신으로 설정
		p = new int[N];
		for (int i = 0; i < N; i++) p[i] = i;
		
		// edgeList를 weight 기준 오름차순으로 정렬
		Collections.sort(edgeList);
		
		ans = 0;
		count = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			
			Edge edge = edgeList.get(i);
			
			int px = findset(edge.start);
			int py = findset(edge.end);
			
			// px != py이면 서로소 집합이므로 두 집합을 union
			if (px != py) {
				
				union(px, py);	// 두 집합을 union하고
				ans += edge.weight;	// ans에 필요한 비용을 더한 후
				count++;	// 간선의 개수를 1만큼 증가
			}
			
			// 정점의 개수가 N개이므로 간선의 개수가 (N - 1)개이면 MST완성이므로 반복문 종료
			if (count == N - 1) break;
		}
		
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