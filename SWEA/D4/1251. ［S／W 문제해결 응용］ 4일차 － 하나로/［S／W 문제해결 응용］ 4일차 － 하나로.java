import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

	static int T;	// 테스트 케이스의 개수
	static int N;	// 섬의 개수
	static long[][] map;	// 섬의 좌표
	static double E;	// 세율 실수
	static double[][] edges;	// [0] : 시작정점, [1] : 끝정점, [2] : 가중치
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
			
			edges = new double[(N * (N - 1)) / 2][3];
			int idx =0;
			for (int i = 0; i < N; i++) {	// 섬이 N개이므로 간선의 개수는 (N - 1)개
				for (int j = i + 1; j < N; j++) {
					edges[idx][0] = i;
					edges[idx][1] = j;
					edges[idx][2] = calCost(i, j, E);
					idx++;
				}
			}	// 간선들의 가중치 모두 계산
			
//			System.out.println((N * (N - 1)) / 2);
			
//			for (double[] edge : edges) {
//				System.out.println(Arrays.toString(edge));
//			}
//			System.out.println();
			
			Arrays.sort(edges, new Comparator<double[]>() {
				 
                @Override
                public int compare(double[] o1, double[] o2) {
//                    if (o1[2] >= o2[2]) return 1;
//                    else return -1;
                	return (o1[2] - o2[2]) < 0? -1:1; 
                }
            });	// 간선들을 가중치 기준으로 오름차순으로 정렬
			
//			for (double[] edge : edges) {
//				System.out.println(Arrays.toString(edge));
//			}
//			System.out.println();
			
			ans = 0;	// 비용의 합은 테스트 케이스마다 0으로 초기화
			pick = 0;	// 선택한 간선의 개수도 테스트 케이스마다 0으로 초기화
			
			// 대표자를 자기 자신으로 초기화
			p =  new int[N + 1];
			for (int i = 1; i < N + 1; i++) p[i] = i;
			for (int i = 0; i < (N * (N - 1)) / 2; i++) {
				
				int px = findset((int)edges[i][0]);
				int py = findset((int)edges[i][1]);
				
				if (px != py) {
					union(px, py);
					ans += edges[i][2];
					pick++;
				}
				
				if (pick == (N - 1)) break;
			}	// 가충지의 합을 계산하는 for문
			
			ans *= E;
			ans = Math.round(ans);
			sb.append((long)ans + "\n");
		}	// t에 대한 for문
		
		System.out.println(sb);
	}	// main

	static void union(int px, int py) {
		
		p[findset(py)] = findset(px);
	}	// union

	static int findset(int x) {
		
		if (x != p[x])	// x가 대표자가 아니라면
			p[x] = findset(p[x]);
		return p[x];
	}	// findset
	
	static double calCost(int idx1, int idx2, double e) {
		
		double x = map[0][idx1] - map[0][idx2];
		double y = map[1][idx1] - map[1][idx2];
		return Math.pow(x, 2) + Math.pow(y, 2);
	}	// calCost
}