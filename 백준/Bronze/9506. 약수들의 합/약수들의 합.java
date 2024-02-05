import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			int sum = 0;
			int N = sc.nextInt();
			if (N == -1) {
				break;
			}
			
			List<Integer> num = new ArrayList<Integer>();
			
			for (int i = 1; i < N; i++) {
				if (N % i == 0) {
					sum += i;
					num.add(i);
				}
			}
			if (N == sum) {
				System.out.print(N + " = " + num.get(0));
				for (int i = 1; i < num.size(); i++) {
					System.out.print(" + " + num.get(i));
				}
				System.out.println();
			} else {
				System.out.println(N + " is NOT perfect.");
			}
		}
	}
}