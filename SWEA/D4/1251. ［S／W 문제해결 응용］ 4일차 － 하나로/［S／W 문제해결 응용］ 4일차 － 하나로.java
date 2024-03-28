import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int T;	// 테스트 케이스의 개수
	static int N;	// 섬의 개수
	static final double INF = Double.MAX_VALUE;
	static long[][] map;	// 섬의 좌표
	static double E;	// 세율 실수
	static double[][] adjArr;	// [0] : 시작정점, [1] : 끝정점, [2] : 가중치
	static double ans;	// 최소 환경부담금
	static int pick;	
	static int[] p;	// 대표자를 저장할 배열
	
	// 환경부담금 = E * L * L	(L은 해저터널 길이)
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			sb.append("#" + t + " ");
			
			N = Integer.parseInt(br.readLine());	// 섬의 개수
			map = new long[2][N];
			
			for (int r = 0; r < 2; r++) {
				
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) map[r][c] = Long.parseLong(st.nextToken());
			}	// 섬의 x, y좌표
			
			E = Double.parseDouble(br.readLine());	// 세율실수
			
			adjArr = new double[N][N];
			for (int i = 0; i < N; i++) {	// 섬이 N개이므로 간선의 개수는 (N - 1)개
				for (int j = 0; j < N; j++) {
					
					adjArr[i][j] = adjArr[j][i] = calCost(i, j, E);
				}
			}	// 간선들의 가중치 모두 계산
						
			boolean[] visit = new boolean[N];
			int[] p = new int[N];
			double[] dist = new double[N];
			
			for (int i = 0; i < N; i++) {
				dist[i] = INF;
				p[i] = -1;
			}

			dist[0] = 0;			
			ans = 0;	// 비용의 합은 테스트 케이스마다 0으로 초기화
			
			for (int i = 0; i < N - 1; i++) {
				
				double min = INF;
				int index = -1;
				
				for (int j = 0; j < N; j++) {
					
					if (!visit[j] && dist[j] < min) {
						min = dist[j];
						index = j;
					}
				}
				
				visit[index] = true;
				
				for (int j = 0; j < N; j++) {
					
					if (!visit[j] && adjArr[index][j] != 0 && dist[j] > adjArr[index][j]) {
						dist[j] = adjArr[index][j];
						p[j] = index;
					}
				}
			}
			
			for (int i = 0; i < N; i++) ans+= dist[i];
			
			ans *= E;
			ans = Math.round(ans);
			sb.append((long)ans + "\n");
		}	// t에 대한 for문
		
		System.out.println(sb);
	}	// main
	
	static double calCost(int idx1, int idx2, double e) {
		
		if (idx1 == idx2) return 0;
		
		double x = map[0][idx1] - map[0][idx2];
		double y = map[1][idx1] - map[1][idx2];
		return Math.pow(x, 2) + Math.pow(y, 2);
	}	// calCost
}