import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
		
		int key = -1;
		int cnt = 0;
		for (int i = 1; i < N; i++) {
			
			key = A[i];
			int j;
			// key값(i번 index에 해당하는 값 보다 앞의 값(j번, 즉 i - 1번 index에 해당하는 값)이 더 크면
			for (j = i - 1; j >= 0 && A[j] > key; j--) {
				// 배열 내의 값들을 오른쪽으로 한 칸씩 옮긴다.
				A[j + 1] = A[j];
				cnt++;
				
				if (cnt == K) System.out.println(A[j + 1]);
			}
			
			if (A[j + 1] != key) {
				A[j + 1] = key;
				cnt++;
			}
			if (cnt == K) System.out.println(A[j + 1]);
			else if (i == N - 1 && cnt < K) System.out.println(-1);
		}
		
		
	}
}