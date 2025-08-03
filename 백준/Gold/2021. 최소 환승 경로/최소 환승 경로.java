import java.util.*;
import java.io.*;
public class Main {
	
	static int N, L;
	static List<Integer>[] stationLines, lineStations;
	static int arrival, destination;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		stationLines = new ArrayList[N + 1];
		lineStations = new ArrayList[L];
		
		for (int i = 1; i <= N; i++) {
			stationLines[i] = new ArrayList<>();
		}
		for (int i = 0; i < L; i++) {
			lineStations[i] = new ArrayList<>();
		}
		
		// 1. 역 - 호선 매핑
		// 2. 출발 역의 호선에서 도착 역의 호선까지 bfs로 최소 환승 횟수 탐색
		// 출발 지점의 호선에서 이동할 때마다 도착 지점의 호선인지 확인해야하므로 HashSet
		for (int i = 0; i < L; i++) {
			int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < tmp.length - 1; j++) {
				lineStations[i].add(tmp[j]);
				stationLines[tmp[j]].add(i);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		arrival = Integer.parseInt(st.nextToken());
		destination = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(arrival, destination));
	}
	
	private static int bfs(int arr, int dest) {
		Deque<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[L];
		boolean[] visitedStation = new boolean[N + 1];
		
		for (int startLine : stationLines[arr]) {
			queue.add(startLine);
			visited[startLine] = true;
		}
		visitedStation[arr] = true;

		int count = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				int currLine = queue.poll();

				if (stationLines[dest].contains(currLine)) {
					return count;
				}
				
				for (int nextStation : lineStations[currLine]) {
					if (visitedStation[nextStation]) continue;
					visitedStation[nextStation] = true;
					
					for (int nextLine : stationLines[nextStation]) {
						if (visited[nextLine]) continue;
						
						visited[nextLine] = true;
						queue.add(nextLine);
					}
				}
			}
			count++;
		}
		
		return -1;
	}
}
