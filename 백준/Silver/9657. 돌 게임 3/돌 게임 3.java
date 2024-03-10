import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static String[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new String[N + 1];
		
		for (int i = 1; i < N + 1; i++) {
			
			dp[i] = "SK";
			if (i % 7 == 0 || i % 7 == 2)
				dp[i] = "CY";
		}		
		
		System.out.println(dp[N]);
	}
}