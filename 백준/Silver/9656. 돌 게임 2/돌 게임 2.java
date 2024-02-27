import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[][] dp = new boolean[2][1001];
		
		dp[0][1] = true;
		dp[0][3] = true;
		dp[1][2] = true;
		
		for (int i = 4; i < 1001; i++) {
			
			if (dp[0][i - 2] == true) dp[0][i] = true;
			else dp[1][i] = true;
		}
		
		int N = Integer.parseInt(br.readLine());
		if (dp[0][N] == true) System.out.println("CY");
		else System.out.println("SK");
	}	
}