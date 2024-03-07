import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;	// 집의 개수
	static int[][] pay;	// 집 마다 필요한 비용을 저장할 배열
	static int[][] dp;
	static int minimumPay = Integer.MAX_VALUE;	// 최소비용
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		pay = new int[N][3];
		dp = new int[N][3];
		
		for (int r = 0; r < N; r++) {
			
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 3; c++) pay[r][c] = Integer.parseInt(st.nextToken());
		}
		
		for (int c = 0; c < 3; c++) dp[0][c] = pay[0][c];
		
		for (int c = 0; c < 3; c++) {
			pay[N - 1][c] = searchMinimumPay(N - 1, c);
			if (minimumPay > pay[N - 1][c]) minimumPay = pay[N - 1][c];
		}

		sb.append(minimumPay);
		System.out.println(sb);
	}
	
	private static int searchMinimumPay (int row, int col) {	// row번째 행, col번째 행에 색칠하는데 드는 비용의 최솟값을 구하는 메소드
		
		if (dp[row][col] != 0) return dp[row][col];	// 이미 계산했던 값이면 다시 계산하지 않고 그 값을 리턴, GPT
		
		if (row == 0) {	// 0번째 row인 경우
			
			return Math.min(pay[0][(col + 1) % 3], pay[0][(col + 2) % 3]);
		}
		
		// n번재 row의 c번째 col까지의 최솟값은 pay[n][c] 에 pay[n - 1][(c + 1) % 3]과 pay[n - 1][(c + 2) % 3]값 중 더 작은 값의 합이다.
		return dp[row][col] = pay[row][col] + Math.min(searchMinimumPay(row - 1, (col + 1) % 3), searchMinimumPay(row - 1, (col + 2) % 3));
	}
}