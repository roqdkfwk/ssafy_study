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
		
		int flag;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			
			flag = 0;
			for (int j = 0; j < N - 1; j++) {
				
				// 앞의 숫자가 뒤의 숫자보다 크면 두 숫자의 자리를 바꿈
				if (A[j] > A[j + 1]) {
					int tmp = A[j];
					A[j] = A[j + 1];
					A[j + 1] = tmp;
					flag = 1;
					cnt++;
				}
				
				if (cnt == K) {
					int num1 = A[j];
					int num2 = A[j + 1];
					
					for (int k = 0; k < N; k++) sb.append(A[k] + " ");
					
					sb.deleteCharAt(sb.length() - 1);
					System.out.println(sb);
					break;
				}
				
			}	// j에 대한 for문
			
			if (cnt == K) break;
			// 정렬이 완료되면 더 이상 i에 대한 for문을 반복하지 않고 프로그램을 종료
			else if (flag == 0 && cnt < K) {				
				System.out.println(-1);
				break;
			}
		}	// i에 대한 for문
		
	}
}