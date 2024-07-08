import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;	// 수열의 길이
	static int[] A;	// 수열
	static int[] increaseDP;
	static int[] decreaseDP;
	static int maxLength;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		A = new int[N];
		increaseDP = new int[N];
		decreaseDP = new int[N];
		Arrays.fill(increaseDP, 1);
		Arrays.fill(decreaseDP, 1);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		ans = 0;
		for (int i = 1; i < N; i++) 
			increaseDynamic(i);
		
		for (int i = N - 2; i >= 0; i--)
			decreaseDynamic(i);

		ans= 0;
		for (int i = 0; i < N; i++) 
			ans = Math.max(ans, increaseDP[i] + decreaseDP[i] - 1);
		
		System.out.println(ans);
	}	// main

	private static void increaseDynamic(int idx) {
		
		for (int i = idx - 1; i >= 0; i--)
			if (A[i] < A[idx])
				increaseDP[idx] = Math.max(increaseDP[i] + 1, increaseDP[idx]);
	}	// increaseDynamic

	private static void decreaseDynamic(int idx) {
		
		for (int i = idx + 1; i <= N - 1; i++) 
			if (A[i] < A[idx])
				decreaseDP[idx] = Math.max(decreaseDP[i] + 1, decreaseDP[idx]);
	}	// decreaseDynamic

}