import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] A, DP;
	static int maxLength = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		DP = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) 
			A[i] = Integer.parseInt(st.nextToken());
		
		DP[0] = 1;
		for (int i = 1; i < N; i++)
			Dynamic(i);
		
		for (int i = 0; i < N; i++) 
			maxLength = Math.max(maxLength, DP[i]);
		
		System.out.println(maxLength);
	}	// main
	
	static void Dynamic(int idx) {
		
		if (DP[idx] == 0) 
			DP[idx] = 1;
		
		for (int i = N - 1; i >= 0; i--) {
			
			if (A[i] > A[idx])
				DP[idx] = Math.max(DP[idx], DP[i] + 1);
		}
	}	// Dynamic
}