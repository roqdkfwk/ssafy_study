import java.io.*;
import java.util.*;

public class Solution {

	static int T, N, M; // 테스트 케이스, 도시의 크기, 지불할 수 있는 비용
	static int[][] city; // 도시
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			city = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					city[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			answer = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					bfs(r, c);
				}
			}
			
			sb.append("#" + t + " " + answer + "\n");
		} // t에 대한 for

		System.out.print(sb);
	} // main

	private static void bfs(int r, int c) {
		int count;	// 서비스를 제공받는 집의 수
		int cost;	// 서비스 운영 비용
		
		for (int k = 1; k <= N + 1; k++) {
			count = 0;
			cost = k * k + (k - 1) * (k - 1);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((Math.abs(r - i) + Math.abs(c - j) < k) && city[i][j] == 1) {
						count++;
					}
				}
			}
			
			if (M * count >= cost) {
				answer = Math.max(answer, count);
			}
		}	// k에 대한 for문
	} // bfs
}