import java.util.*;
import java.io.*;
public class Solution {
	
	static int N;				// 고객 수
	static int[] locations;		// 좌표
	static int[][] customers;	// 고객 위치
	static int minDistance;		// 최단 거리
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			minDistance = (int)1e9;
			N = Integer.parseInt(br.readLine());
			locations = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			customers = new int[N][2];
			for (int i = 0; i < N; i++) {
				customers[i][0] = locations[2 * i + 4];
				customers[i][1] = locations[2 * i + 5];
			}
			
			for (int next = 0; next < N; next++) {
				dfs(next, (1 << next), Math.abs(locations[0] - customers[next][0]) + Math.abs(locations[1] - customers[next][1]), 1);
			}
			
			answer.append("#" + t + " ").append(minDistance).append("\n");
		}
		
		System.out.println(answer.toString().trim());
	}
	
	private static void dfs(int before, int visited, int distance, int count) {
		if (distance > minDistance) return;
		
		if (count == N) {
			minDistance = Math.min(minDistance, distance + Math.abs(customers[before][0] - locations[2]) + Math.abs(customers[before][1] - locations[3]));
			return;
		}
		
		for (int next = 0; next < N; next++) {
			// 이미 방문한 고객인 경우
			if (((1 << next) & visited) != 0) continue;
			
			// 다음 고객 탐색
			int nextDistance = distance
					+ Math.abs(customers[before][0] - customers[next][0])
					+ Math.abs(customers[before][1] - customers[next][1]);
			dfs(next, (visited | (1 << next)), nextDistance, count + 1);
		}
	}
}