import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] T, P;
	static int max;
	static int[] DP;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		T = new int[N + 2];
		P = new int[N + 2];
		DP = new int[N + 2];
		
		for (int i = 1; i < N + 1; i++) {
			
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(T));
//		System.out.println(Arrays.toString(P));
		
		for (int i = 1; i < N + 2; i++) {
			
			if (max < DP[i]) max = DP[i];
//			System.out.println("i : " + i + " max : " + max);
			
			int day = i + T[i];
			if (day < N + 2) {
				DP[day] = Math.max(DP[day], max + P[i]);
//				System.out.println("day : " + day + " DP[day] : " + DP[day] + "\n");
			}
		}
		
		System.out.println(max);
	}
}