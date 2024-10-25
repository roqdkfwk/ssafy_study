import java.util.*;
import java.io.*;

public class Main {
	
	static class Road implements Comparable<Road> {
		int Num;
		long Time;
		
		public Road (int Num, long Time) {
			this.Num = Num;
			this.Time = Time;
		}
		
		@Override
		public int compareTo(Road R) {
			return Long.compare(this.Time, R.Time);
		}
	}

	static int N, M;	// 도시, 도로
	static int A, B;
	static List<Road>[] roads;
	static long[] distance;
	static List<Integer>[] map;
	static Set<Integer> answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		roads = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			roads[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			roads[X].add(new Road(Y, C));
		    roads[Y].add(new Road(X, C));
		}
		
		distance = new long[N + 1];
		Arrays.fill(distance, Long.MAX_VALUE);
		map = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
		
		answer = new HashSet<>();
		dijkstra(A);
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(B);
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			answer.add(curr);
			
			for (int num : map[curr]) {
				queue.add(num);
			}
		}
		
		System.out.println(answer.size());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int num : answer) {
			pq.add(num);
		}
		
		while (!pq.isEmpty()) {
			System.out.print(pq.poll() + " ");
		}
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Road> pq = new PriorityQueue<>();
		pq.add(new Road(A, 0));
		distance[A] = 0;
		
		while (!pq.isEmpty()) {
			Road curr = pq.poll();
			
			if (distance[curr.Num] < curr.Time)
				continue;
			
			for (Road road : roads[curr.Num]) {
				if (distance[road.Num] >= distance[curr.Num] + road.Time) {
					// 경로의 길이가 같은 경우엔 추기
					if (distance[road.Num] == distance[curr.Num] + road.Time) {
						map[road.Num].add(curr.Num);
					// 경로의 길이가 짧아진 경우엔 초기화 후 추가
					} else {
						distance[road.Num] = distance[curr.Num] + road.Time;
						map[road.Num] = new ArrayList<>();
						map[road.Num].add(curr.Num);
						pq.add(new Road(road.Num, distance[curr.Num] + road.Time));
					}
				}
			}
		}
	}
}