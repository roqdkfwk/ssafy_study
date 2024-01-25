import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] st = new int[2][6];
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			int gen = sc.nextInt();
			int gra = sc.nextInt();
			st[gen][gra - 1]++;
		}
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				if (st[i][j] % K == 0) {
					cnt += st[i][j] / K;
				} else {
					cnt += (st[i][j] / K) + 1;
				}
			}
		}
		System.out.println(cnt);
	}
}