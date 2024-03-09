import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int T;	// 테스트 케이스의 수
	static int[] cost;	// 일일, 한달, 세달, 일년 이용권 가격
	static int[] countSwim;	// 월 별로 수영할 일수를 저장할 배열
	static int[] dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			cost = new int[4];
			countSwim = new int[13];
			dp = new int[13];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) cost[i] = Integer.parseInt(st.nextToken());	// 이용 가격을 저장
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < 13; i++) countSwim[i] = Integer.parseInt(st.nextToken());
			
			
			for (int i = 1; i < 13; i++) {
				
				// i월에 수영장을 이용하는데 필요한 최소 비용은 ((i - 1)월까지 수영장을 이용 + i월 일일권 비용)과 ((i - 1)월까지 수영장을 이용 + i월 한달권 비용) 중 작은 값
				dp[i] = Math.min(dp[i - 1] + countSwim[i] * cost[0], dp[i - 1] + cost[1]);
				
				// 3달 이용권을 이용할 경우를 고려
				if(i >= 3) dp[i] = Math.min(dp[i], dp[i - 3] + cost[2]);
			}			

			// 12월까지 이용권을 섞어서 지불할때의 총 비용과 일년권을 이용할때의 총 비용 중 작은 값을 출력
			System.out.println("#" + t + " " + Math.min(dp[12], cost[3]));
		}
	}
}