import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        int row = sc.nextInt();
        int col = sc.nextInt();
        
        // 행렬 A, B, C를 선언
        int[][] A = new int[row][col];
        int[][] B = new int[row][col];
        int[][] C = new int[row][col];
        
        // 행렬 A의 각 성분에 값을 할당
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                A[i][j] = sc.nextInt();
            }
        }
        // 행렬 B의 각 성분에 값을 할당
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                B[i][j] = sc.nextInt();
            }
        }
        
        // C = A + B 연산 
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
	}
}