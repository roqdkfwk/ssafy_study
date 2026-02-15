import java.util.*;
import java.io.*;
public class Main {

	/**
	 * 최소한의 비용으로 모든 논을 연결
	 * 유니온 파인드 + 우선순위 큐 > 이게 크루스칼인가 프림인가
	 */
	
	static class Paddy implements Comparable<Paddy> {
		int from, to, cost;
		
		public Paddy(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Paddy paddy) {
			return Integer.compare(this.cost, paddy.cost);
		}
	}
	
	static int N, W;	// N : 논의 수, W : 비용
	static int minimumCost;
	static PriorityQueue<Paddy> pq;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		inputHandler();
		
		solution();
		
		printResult();
	}
	
	private static void inputHandler() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.add(new Paddy(0, i + 1, Integer.parseInt(br.readLine())));
		}
		
		parents = new int[N + 1];		
		for (int from = 1; from <= N; from++) {
			st = new StringTokenizer(br.readLine());
			parents[from] = from;
			for (int to = 1; to <= N; to++) {
				pq.add(new Paddy(from, to, Integer.parseInt(st.nextToken())));
			}
		}
	}
	
	/**
	 * (부모 = 0)이면 물이 들어오는 논?
	 * 최소 비용으로만 처리했더니 논끼리만 연결되고 우물은 하나도 없으면?
	 */
	private static void solution() {
		if (N == 1) {
			minimumCost = pq.poll().cost;
			return;
		}
		
		int linkedPaddy = 0;
		while (!pq.isEmpty()) {
			// 모든 논이 연결된 경우
			if (linkedPaddy == N) {
				return;
			}
			
			Paddy paddy = pq.poll();
			int from = paddy.from;
			int to = paddy.to;
			
			int parentX = findParent(from);
			int parentY = findParent(to);			
			if (parentX == parentY) {
				continue;
			}
			
			union(parentX, parentY);
			minimumCost += paddy.cost;
			linkedPaddy++;
		}
	}
	
	private static int findParent(int x) {
		if (x != parents[x]) {
			parents[x] = findParent(parents[x]);
		}
		
		return parents[x];
	}
	
	private static void union(int x, int y) {
		if (x <= y) {
			parents[y] = parents[x];
			return;
		}
		
		parents[x] = parents[y];
	}
	
	private static void printResult() {
		System.out.println(minimumCost);
	}
}