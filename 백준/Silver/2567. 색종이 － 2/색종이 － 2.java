import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int[][] paper = new int[100][100];
		int par = 0;
		int per = 0;
		int bor = 0;
		int len = 0;
		
		for (int i = 0; i < T; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for (int j = x; j < (x + 10 > 100 ? 100 : x + 10); j++) {
				for (int k = y; k < (y + 10 > 100 ? 100 : y + 10); k++) {
					paper[j][k] = 1;
				}
			}
		}
		
		for (int i = 1; i < 100; i++) {
			for (int j = 0; j < 99; j++) {
				if (paper[i][j] != paper[i][j + 1]) {
					per++;
				}
				if (paper[j][i] != paper[j + 1][i]) {
					par++;
				}
			}
		}
		for (int i = 0; i < 99; i++) {
			if (paper[0][i] != paper[0][i + 1])
				per++;
			if (paper[i][0] != paper[i + 1][0])
				par++;
		}
		for (int i = 1; i < 99; i++) {
			if (paper[0][i] == 1)
				bor++;
			if (paper[99][i] == 1)
				bor++;
		}
		for (int i = 1; i < 99; i++) {
			if (paper[i][0] == 1)
				bor++;
			if (paper[i][99] == 1)
				bor++;
		}
		if (paper[0][0] == 1)
			bor += 2;
		if (paper[0][99] == 1)
			bor += 2;
		if (paper[99][0] == 1)
			bor += 2;
		if (paper[99][99] == 1)
			bor += 2;
		len = per + par + bor;
		System.out.println(len);
	}
}