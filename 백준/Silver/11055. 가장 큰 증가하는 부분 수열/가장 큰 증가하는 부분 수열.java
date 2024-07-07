import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] A;	// 수열
	static int N;	// 수열의 크기
	static int[] DP;	// DP
	static int ans;	// 정답
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		DP = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		DP[0] = A[0];
		
		for (int i = 1; i < N; i++) 
			Dynamic(i);
		
		ans = 0;
		for (int i = 0; i < N; i++)
			if (ans < DP[i])
				ans = DP[i];
		
		System.out.println(ans);
	}	// main

	private static void Dynamic(int idx) {
		
		if (DP[idx] == 0)
			DP[idx] = A[idx];
		
		for (int i = idx - 1; i >= 0; i--) {
			if (A[i] < A[idx])
				DP[idx] = Math.max(DP[idx], DP[i] + A[idx]);
		}
	}	// Dynamic
}