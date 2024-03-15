import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N; // 유저의 수
	static boolean[] visit;
	static int[][] relation;
	static int kevinNumber = Integer.MAX_VALUE;
	static int M; // 친구 관계의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		relation = new int[N + 1][N + 1];
		for (int r = 1; r < N + 1; r++) {
			for (int c = 1; c < N + 1; c++) {
				if (r == c)
					continue;
				relation[r][c] = 1000000;
			}
		}

		for (int i = 0; i < M; i++) {

			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());

			relation[num1][num2] = 1;
			relation[num2][num1] = 1;
		}

		floyd(relation);

		int num = -1;
		for (int i = 1; i < N + 1; i++) {

			int sum = 0;
			for (int n = 1; n < N + 1; n++) sum += relation[i][n];				
			
			if (kevinNumber > sum) {
				kevinNumber = sum;
				num = i;
			}
//			System.out.println("i : " + i + " sum : " + sum);
		}

		System.out.println(num);
	} // main

	static void floyd(int[][] numArray) {

		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {

				if (i == k)
					continue;
				for (int j = 1; j < N + 1; j++) {

					numArray[i][j] = Math.min(numArray[i][j], numArray[i][k] + numArray[k][j]);
					numArray[j][i] = numArray[i][j];
				}
			}
		}
	} // floyd 메소드
}