import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static long ans;
	static long[] DP;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		DP = new long[1000001];
		
		DP[0] = 0;
		DP[1] = 0;
		DP[2] = 1;
		
		for (int i = 3; i <= N; i++)
			DP[i] = (i - 1) * (DP[i - 2] + DP[i - 1]) % 1000000000;
			
			System.out.println(DP[N]);
	}
}