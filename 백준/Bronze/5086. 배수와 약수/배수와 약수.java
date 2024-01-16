import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			// 첫 번째 숫자 입력
			int num1 = sc.nextInt();
			// 두 번째 숫자 입력
			int num2 = sc.nextInt();
			
			if (num1 == 0 && num2 ==0) {
				break;
			} else if (num1 % num2 == 0) {
				System.out.println("multiple");
			} else if (num2 % num1 == 0) {
				System.out.println("factor");
			} else {
				System.out.println("neither");
			}
		}
	}
}