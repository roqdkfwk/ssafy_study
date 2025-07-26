import java.util.*;
import java.io.*;
public class Main {
	
	static Long[][] matrix;
	static Map<Integer, Long[][]> matrixMap;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		matrix = new Long[8][8];
		for (int r = 0; r < 8; r++) {
			Arrays.fill(matrix[r], 0L);
		}
		
		matrix[0][1] = matrix[1][0] = 1L;
		matrix[0][2] = matrix[2][0] = 1L;
		matrix[1][2] = matrix[2][1] = 1L;
		matrix[1][3] = matrix[3][1] = 1L;
		matrix[2][3] = matrix[3][2] = 1L;
		matrix[2][4] = matrix[4][2] = 1L;
		matrix[3][4] = matrix[4][3] = 1L;
		matrix[3][5] = matrix[5][3] = 1L;
		matrix[4][5] = matrix[5][4] = 1L;
		matrix[4][6] = matrix[6][4] = 1L;
		matrix[5][7] = matrix[7][5] = 1L;
		matrix[6][7] = matrix[7][6] = 1L;
	
		matrixMap = new HashMap<>();
		int D = sc.nextInt();
		System.out.println(exponent(D)[0][0]);
	}
	
	private static Long[][] exponent(int exp) {
		if (exp == 1) {
			return matrix;
		}

		Long[][] half = exponent(exp / 2);
		Long[][] result = calculate(half, half);
		
		if (exp % 2 == 0) {
			return result;
		}
		else {
			return calculate(result, matrix);
		}
	}
	
	private static Long[][] calculate(Long[][] m1, Long[][] m2) {
		Long[][] resultMatrix = new Long[8][8];
		for (int r = 0; r < 8; r++) {
			Arrays.fill(resultMatrix[r], 0L);
		}
		
		for (int r = 0; r < 8; r++) {
			
			for (int c = 0; c < 8; c++) {
				Long num = 0L;
				for (int i = 0; i < 8; i++) {
					num += ((m1[r][i] * m2[i][c]) % 1_000_000_007);
				}
				resultMatrix[r][c] = (num % 1_000_000_007);
			}
		}
		
		return resultMatrix;
	}
}
