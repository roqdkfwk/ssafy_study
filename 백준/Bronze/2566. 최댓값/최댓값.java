import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        // 가장 큰 숫자가 포함된 행과 열의 index를 저장할 변수
        int row_idx = 0;
        int col_idx = 0;
        // 가장 큰 숫자의 값을 저장할 변수 
        int max = Integer.MIN_VALUE;
        int[][] mat = new int[9][9];
        
        // 9 X 9 행렬에 col순으로 숫자를 할당
        for (int row=0; row<9; row++) {
            for (int col=0; col<9; col++) {
                mat[row][col] = sc.nextInt();
            }
        }
        // 행렬 내부의 모든 숫자를 순회하며
        // max변수에 가장 큰 수를 저ㅏㅇ하고
        // 가장 큰 수가 위치한 행과 열을 row_idx, col_idx에 저장
        for (int row=0; row<9; row++) {
            for (int col=0; col<9; col++) {
                if (mat[row][col] > max) {
                    max = mat[row][col];
                    row_idx = row + 1;
                    col_idx = col + 1;
                }
            }
        }
        // 가장 큰 값과 그 값이 위치한 행과 열의 위치를 출력
        System.out.println(max);
        System.out.println(row_idx + " " + col_idx);   
	}
}