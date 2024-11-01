import java.util.*;
import java.io.*;
public class Main {

	static int N;
	static int[] D, P;
	static int[] dp;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		D = new int[N + 1];
		P = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			D[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		dp = new int[21];
		for (int i = 1; i <= N; i++) {
			// i일에 상담을 하면
			// (i + D[i] - 1)일에 정산을 받는다.
			// (i + D[i] - 1)일에 정산받는 금액의 최댓값은
			// 기존에 (i + D[i] - 1)일에 정산받을 수 있었던 금액과
			// (i - 1)일까지 정산받은 금액 + i일에 상담을 해서 정산받는 금액 중 더 큰값이다.
			int day = D[i];
			int pay = P[i];
			
			dp[i + day - 1] = Math.max(dp[i + day - 1], dp[i - 1] + pay);
			max = Math.max(max, dp[i]);
			dp[i] = max;
			if (i + day - 1 <= N) {
				answer = Math.max(answer, dp[i + day - 1]);
			}
		}
		
		System.out.println(answer);
	}
}
