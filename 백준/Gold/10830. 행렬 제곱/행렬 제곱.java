import java.util.*;
import java.io.*;
public class Main {

	static int N;
	static long B;
	static int[][] A;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		
		A = pow(A, B);
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sb.append(A[r][c]).append(" ");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int[][] pow(int[][] matrix, long exp) {
		if (exp == 1L) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++)
					matrix[r][c] %= 1000;
			}

			return matrix;
		}
		
		int[][] res = pow(matrix, exp / 2);
		
		res = multiply(res, res);
		
		if (exp % 2 == 1L)
			return multiply(res, A);
		
		return res;
	}
	
	static int[][] multiply(int[][] matrix1, int[][] matrix2) {
		int[][] res = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int i = 0; i < N; i++) {
					res[r][c] += matrix1[r][i] * matrix2[i][c];
					res[r][c] %= 1000;
				}
			}
		}
		
		return res;
	}
}