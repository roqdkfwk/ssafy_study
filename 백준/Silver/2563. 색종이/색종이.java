import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 도화지를 모눈종이라 생각하고 모두 0으로 초기화한 후에
		// 색종이를 붙인 영역에 1을 할당해서
		// 최종적으로 100 X 100 영역안에 있는 1의 개수 = 넓이 라는 논리로 문제를 풀 예정
		int T = sc.nextInt();
		int[][] paper = new int[100][100];
		int area = 0;
		
		for (int i = 0; i < T; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for (int j = x; j < (x + 10 > 100 ? 100 : x + 10); j++) {
				for (int k = y; k < (y + 10 > 100 ? 100 : y + 10); k++) {
					paper[j][k] = 1;
				}
			}
		}
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (paper[i][j] == 1)
					area++;
			}
		}
		System.out.println(area);
	}
}