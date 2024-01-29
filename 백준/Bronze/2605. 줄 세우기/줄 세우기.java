import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		List<Integer> stu = new ArrayList<Integer>();
		int N = sc.nextInt();
		
		for (int i = 1; i <= N; i++) {
			int num = sc.nextInt();
			
			stu.add(stu.size() - num, i);
		}

		for (int num : stu) {
			System.out.print(num + " ");
		}
	}
}