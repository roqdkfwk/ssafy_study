import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] pascal = new int[N + 1][N + 1];
		
		for(int i = 0; i < pascal.length; i++) {
			for(int j = 0; j <= i; j++) {
				if(i == j || j == 0)
					pascal[i][j] = 1;
				else
					pascal[i][j] = (pascal[i - 1][j - 1] + pascal[i - 1][j]) % 10007;
			}
		}
		System.out.println(pascal[N][K]);
	}
}