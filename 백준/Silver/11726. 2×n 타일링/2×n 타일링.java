import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] DP;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		DP = new int[1001];
		DP[1] = 1;
		DP[2] = 2;
		
		if (N > 2) {
			for (int i = 3; i < N + 1; i++) 
				DP[i] = (DP[i - 1] + DP[i - 2]) % 10007;
		}
		
		System.out.println(DP[N]);
	}
}