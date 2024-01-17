import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 좌석 수
		int N = sc.nextInt();
		sc.nextLine();
		// 쓸 수 있는 컵홀더 개수 >> (좌석수 + 1)로 초기화
		int hold = N + 1;
		// 커플 석의 개수를 셀 변수 cnt
		int cnt = 0;
		// 좌석 정보를 입력받을 문자열 str
		String str = sc.nextLine();
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'L')
				cnt++;
		}
		hold -= cnt / 2;
		if (N < hold)
			System.out.println(N);
		else
			System.out.println(hold);
	}
}