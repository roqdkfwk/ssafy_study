import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;	// 재료의 개수
	static int[] S;	// 재료의 신맛
	static long mulS;	// 신맛의 곱
	static int[] B;	// 재료의 쓴맛o
	static long sumB;	// 쓴맛의 합
	static long minDiff;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		B = new int[N];
		
		for (int idx = 0; idx < N; idx++) {
			st = new StringTokenizer(br.readLine());
			S[idx] = Integer.parseInt(st.nextToken());
			B[idx] = Integer.parseInt(st.nextToken());
		}
		
		minDiff = Integer.MAX_VALUE;
		for (int i = 1; i < (1 << N); i++) {
			
			mulS = 1;
			sumB = 0;
			
			for (int j = 0; j < N; j++) {
				
				if ((i & (1 << j)) > 0) {
					mulS *= S[j];
					sumB += B[j];
				}
			}	
			
			if (minDiff >= Math.abs(mulS - sumB)) minDiff = Math.abs(mulS - sumB);
		}	// i에 대한 for문
		
		System.out.println(minDiff);
	}	// main
}