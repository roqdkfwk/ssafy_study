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
		int K = Integer.parseInt(st.nextToken());

		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

		int cnt = 0;	// 교환이 발생한 횟수
		int flag = 0;
		for (int i = 0; i < N; i++) {
			
			int max = Integer.MIN_VALUE;
			int maxIdx = -1;
			for (int j = 0; j < N - i; j++) {
				
				if (max < A[j]) {
					max = A[j];
					maxIdx = j;
				}
			}	// j에 대한 for문
			
			// 정렬이 안된 배열의 가장 큰 숫자가 가장 뒤에 있는 경우가 아니라면 정렬을 실행
			if (maxIdx != N - 1 - i) {
				int tmp = A[maxIdx];
				A[maxIdx] = A[N - 1 - i];
				A[N - 1 - i] = max;
				cnt++;
				
				// 정렬를 실행한 횟수가 K와 같다면
				if (cnt == K) {
					int num1 = A[maxIdx];
					int num2 = A[N - 1 - i];
					System.out.println(num1 + " " + num2);
					break;
				}
			}
			
			// 정렬이 완료 되었는데도 cnt < K라면
			if (i == N - 1 && cnt < K) System.out.println(-1);
		} // i에 대한 for문
	}
}