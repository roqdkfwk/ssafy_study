import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long[] stackSum = new long[N];
		st = new StringTokenizer(br.readLine());
		stackSum[0] = Long.parseLong(st.nextToken());
		for (int i = 1; i < N; i++) {
			stackSum[i] = stackSum[i - 1] + Long.parseLong(st.nextToken());
		}
		
//		이중 for문 쓰니까 시간초과인듯
//		for (int len = 1; len < stackSum.length + 1; len++) {
//			for (int start = 0; start < stackSum.length - len; start++) {
//				if ((stackSum[start + len] - stackSum[start]) % M == 0) {
//					cnt++;
//				}
//			}
//		}
		
		long cnt = 0;
		long[] remainder = new long[M];
		for (int i = 0; i < stackSum.length; i++) {
			if (stackSum[i] % M == 0) {
				cnt++;
			}
			stackSum[i] %= M;
			remainder[(int)stackSum[i]]++;
		}
		for (int i = 0; i < M; i++) {
			if (remainder[i] > 1) {
				// remainder[i]개 중 2개를 고르는 조합의 수
				cnt += remainder[i] * (remainder[i] - 1) / 2;
			}
		}
		System.out.println(cnt);
	}
}