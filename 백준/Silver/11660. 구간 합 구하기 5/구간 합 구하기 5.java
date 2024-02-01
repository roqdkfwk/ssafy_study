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

		int[][] numArr = new int[N][N];
		int[][] stackSum = new int[N][N];
		for (int r= 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				numArr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int c = 0; c < N; c++) {
			stackSum[0][c] = numArr[0][c];
		}
		
		for (int c = 0; c < N; c++) {
			for (int r = 1; r < N; r++) {
				stackSum[r][c] += stackSum[r - 1][c] + numArr[r][c];
			}
		}
//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < N; c++) {
//				System.out.print(stackSum[r][c] + " ");
//			}
//			System.out.println();
//		}
		
		for (int T = 0; T < M; T++) {
			int sum = 0;
			
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for (int c = y1 - 1; c < y2; c++) {
				if (x1 == 1) {
					sum += stackSum[x2 - 1][c];
				} else {
					sum += stackSum[x2 - 1][c] - stackSum[x1 - 2][c];
				}
			}
			System.out.println(sum);
		}
	}
}