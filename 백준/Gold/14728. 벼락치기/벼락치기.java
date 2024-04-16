import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, T;	// 단원 개수, 총 시간
	static int[] K;	// 단원 별 예상 공부 시간
	static int[] S;	// 문제 배점
	static int[][] DP;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		K = new int[N + 1];
		S = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			
			st = new StringTokenizer(br.readLine());
			K[i] = Integer.parseInt(st.nextToken());
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		DP = new int[N + 1][T + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int k = 0; k <= T; k++) {
				
				if (k >= K[i]) {
					DP[i][k] = Math.max(DP[i - 1][k], DP[i - 1][k - K[i]] + S[i]);
				} else {
					DP[i][k] = DP[i - 1][k];
				}
			}
		}	// i에 대한 for문
		
		System.out.println(DP[N][T]);
	}	// main
}