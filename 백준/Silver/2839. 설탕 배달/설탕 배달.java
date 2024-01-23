import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int quan1 = N / 5;
		boolean exist = false;
		if (N % 5 == 0) {
			System.out.println(N / 5);
			exist = true;
		}
		
		for (int i = N / 5; i >= 0; i--) {
			if (exist == true)
				break;
			int num = 1;
			while ((i * 5 + 3 * num) <= N) {
				if ((i * 5 + 3 * num) == N) {
					System.out.println(i + num);
					exist = true;
					break;
				} else {
					num++;
				}					
			}
		}
		if (exist == false)
			System.out.println(-1);
	}
}