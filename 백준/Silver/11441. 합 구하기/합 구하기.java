import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		int[] stackSum = new int[N];
		st = new StringTokenizer(br.readLine());
		// 배열 완성
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		// 누적합 배열 완성
		stackSum[0] = num[0];
		for (int i = 1; i < N; i++) {
			stackSum[i] += stackSum[i - 1] + num[i];
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(stackSum[i]);
//		}
		
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (start == 1) {
				System.out.println(stackSum[end - 1]);
			} else {
				System.out.println(stackSum[end - 1] - stackSum[start - 2]); 				
			}
		}
	}
}