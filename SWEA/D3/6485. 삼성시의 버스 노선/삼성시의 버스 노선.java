import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int T;	// 테스트 케이스의 개수
	static int N;	// 버스 노선의 개수
	static int[][] AB;	
	static int P;	// 버스 정류장의 개수
	static int[] C;
	static int[] countC;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			sb.append("#" + t + " ");
			
			N = Integer.parseInt(br.readLine());
			AB = new int[N][2];
			for (int i = 0; i < N; i++) {
				
				st = new StringTokenizer(br.readLine());
				AB[i][0] = Integer.parseInt(st.nextToken());
				AB[i][1] = Integer.parseInt(st.nextToken());
			}
			
			P = Integer.parseInt(br.readLine());
			C = new int[P];
			countC = new int[P];
			for (int i = 0; i < P; i++) C[i] = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				for (int j = AB[i][0]; j <= AB[i][1]; j++) {
					for (int idx = 0; idx < P; idx++) {
						if (C[idx] == j) countC[idx]++;
					}
				}
			}
			
			for (int i = 0; i < P; i++) sb.append(countC[i] + " ");
			sb.append("\n");
		}	// t에 대한 for문
		
		System.out.println(sb);
	}
}