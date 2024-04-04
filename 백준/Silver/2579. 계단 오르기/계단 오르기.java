import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;	// 계단의 개수
	static int[] stair; // 계단의 층별 점수
	static int[] DP;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		stair = new int[301];
		DP = new int[301];
		for (int i = 1; i < N + 1; i++) 
			stair[i] = Integer.parseInt(br.readLine()); 
			
		stair[0] = 0;
		DP[0] = 0;
		DP[1] = stair[1];
		DP[2] = stair[1] + stair[2];
		
		for (int i = 3; i < N + 1; i++) 
			DP[i] = Math.max(DP[i - 2] + stair[i], DP[i - 3] + stair[i - 1] + stair[i]);
		
		System.out.println(DP[N]);
	}
}