import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] area = new int[100][100];
		
		for (int i = 0; i < 4; i++) {
			int xL = sc.nextInt();
			int yL = sc.nextInt();
			int xR = sc.nextInt();
			int yR = sc.nextInt();
			
			for (int r = xL; r < xR; r++) {
				for (int c = yL; c < yR; c++) {
					area[r][c] = 1;
				}
			}
		}
		int sur = 0;
		
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if (area[r][c] == 1)
					sur++;
			}
		}
		System.out.println(sur);
	}
}