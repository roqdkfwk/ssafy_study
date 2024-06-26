import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N;
	static int[] DP;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		DP = new int[N + 1];
		Arrays.fill(DP, 987654321);
		DP[0] = DP[1] = 0;
		
		for (int i = 1; i < N + 1; i++) {
			
			DP[i] = Math.min(DP[i], DP[i - 1] + 1);
			
			if (i % 3 == 0) DP[i] = Math.min(DP[i / 3] + 1, DP[i - 1] + 1);
			
			if (i % 2 == 0) DP[i] = Math.min(DP[i], DP[i / 2] + 1);
		}
		
		System.out.println(DP[N]);
	}
}