import java.util.*;

class Solution {
	
	class Node implements Comparable<Node> {
		int to, weight;

		Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Solution.Node o) {
			return this.weight - o.weight;
		}
	}	// Node
	
	int N;	// 지점의 개수
	int[][] paths;	// 경로
	int[] gates;	// 출입구
	int[] summits;	// 산봉우리
	List<Node>[] nodes;	// 노드 리스트
	PriorityQueue<Node> pq;	// 가중치에 따른 우선순위 큐
	Set<Integer> summitSet;
	int[] intensity;
	final int INF = 987654321;

	public int[] solution(int N, int[][] paths, int[] gates, int[] summits) {
		this.N = N;
		this.paths = paths;
		this.gates = gates;
		this.summits = summits;
		
		nodes = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int[] path : paths) {
			int i = path[0], j = path[1], w = path[2];
			nodes[i].add(new Node(j, w));
			nodes[j].add(new Node(i, w));
		}

		summitSet = new HashSet<>();
		for (int summit : summits) {
			summitSet.add(summit);
		}

		intensity = new int[N + 1];
		Arrays.fill(intensity, INF);

		dijkstra();

		int minIntensity = INF;	// 최소 intensity
		int minSummit = 0;	// 번호가 가장 작은 산봉우리

		Arrays.sort(summits);

		for (int summit : summits) {
			if (intensity[summit] < minIntensity) {
				minIntensity = intensity[summit];
				minSummit = summit;
			}
		}

		return new int[] { minSummit, minIntensity };
	}

	private void dijkstra() {
		pq = new PriorityQueue<>();
 
		// 출발점을 모두 우선순위 큐에 넣고
		// 출발점의 intensity 값은 0으로 초기화
		for (int gate : gates) {
			pq.offer(new Node(gate, 0));
			intensity[gate] = 0;
		}

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			// 
			if (curr.weight > intensity[curr.to])
				continue;
			if (summitSet.contains(curr.to))
				continue;

			for (Node node : nodes[curr.to]) {
				int newIntensity = Math.max(intensity[curr.to], node.weight);
				if (newIntensity < intensity[node.to]) {
					intensity[node.to] = newIntensity;
					pq.offer(new Node(node.to, newIntensity));
				}
			}
		}
	}	// dijkstra
}
