import java.util.*;
import java.io.*;
public class Main {
	
	static class Star {
		int num, x, y, z;
		
		public Star(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int num1, num2, length;
		
		public Edge(int num1, int num2, int length) {
			this.num1 = num1;
			this.num2 = num2;
			this.length = length;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.length, e.length);
		}
	}
	
	static int N;
	static Star[] stars;
	static PriorityQueue<Edge> pq;
	static Edge[] sortedByX, sortedByY, sortedByZ;
	static int[] parent;
	static long answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		stars = new Star[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i] = new Star(
					i, Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		
		// x, y, z에 대해서 정렬하면 인접한 Star 사이의 x, y, z 거리가 가장 가까운 상태
		// 해당 Edge를 모두 pq에 넣은 후 가장 짧은 (N-1)개의 Edge를 사용해서 연결
		pq = new PriorityQueue<>();
		
		Arrays.sort(stars, (a, b) -> a.x - b.x);
		for (int i = 0; i < N - 1; i++) {
			Star s1 = stars[i];
			Star s2 = stars[i + 1];
			pq.add(new Edge(s1.num, s2.num, s2.x - s1.x));
		}
		
		Arrays.sort(stars, (a, b) -> a.y - b.y);
		for (int i = 0; i < N - 1; i++) {
			Star s1 = stars[i];
			Star s2 = stars[i + 1];
			pq.add(new Edge(s1.num, s2.num, s2.y - s1.y));
		}
		
		Arrays.sort(stars, (a, b) -> a.z - b.z);
		for (int i = 0; i < N - 1; i++) {
			Star s1 = stars[i];
			Star s2 = stars[i + 1];
			pq.add(new Edge(s1.num, s2.num, s2.z - s1.z));
		}
		
		combineStars();
		
		System.out.println(answer);
	}
	
	private static void combineStars() {
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		int edges = 0;
		while (edges < N - 1) {
			Edge curr = pq.poll();
			
			int num1 = findParent(curr.num1);
			int num2 = findParent(curr.num2);
			
			// 이미 연결된 경우
			if (num1 == num2) continue;
			
			// 연결되지 않은 경우 연결
			union(num1, num2);
			answer += curr.length;
			edges++;
		}
	}
	
	private static void union(int num1, int num2) {
		// 번호가 작은 Star를 부모로
		if (num1 < num2) {
			parent[num2] = num1;
		} else {
			parent[num1] = num2;
		}
	}
	
	private static int findParent(int num) {
		if (num != parent[num]) {
			parent[num] = findParent(parent[num]);
		}
		return parent[num];
	}
}