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
		int[][] arr = new int[N][M];
		int[][] stackSum = new int[N][M];
		
		// 배열 완성
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int c = 0; c < M; c++) {
			stackSum[0][c] = arr[0][c];
		}
		for (int c = 0; c < M; c++) {
			for (int r = 1; r < N; r++) {
				stackSum[r][c] = stackSum[r - 1][c] + arr[r][c];
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			
			if (r1 == 1) {
				for (int c = c1 - 1; c < c2; c++) {
					sum += stackSum[r2 - 1][c];
				}
			} else {
				for (int c = c1 - 1; c < c2; c++) {
					sum += stackSum[r2 - 1][c] - stackSum[r1 - 2][c];
				}
			}
			System.out.println(sum);
		}
	}
}